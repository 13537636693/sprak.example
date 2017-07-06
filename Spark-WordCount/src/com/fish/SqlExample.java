package com.fish;

import org.apache.spark.sql.AnalysisException;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import static org.apache.spark.sql.functions.col;

public class SqlExample {

	public static void main(String[] args) throws AnalysisException {
		// 风格和其它两种上下文的构建不一致
		SparkSession spark = SparkSession.builder()
				.appName("Java Spark SQL basic example").master("local[1]")
				.config("spark.some.config.option", "some-value").getOrCreate();

		Dataset<Row> df = spark.read().json("resources/people.json");
		df.show();
		// Print the schema in a tree format
		System.out.println("df.printSchema()");
		df.printSchema();
		// root
		// |-- age: long (nullable = true)
		// |-- name: string (nullable = true)

		// Select only the "name" column
		System.out.println("df.select(\"name\").show()");
		df.select("name").show();
		// +-------+
		// | name|
		// +-------+
		// |Michael|
		// | Andy|
		// | Justin|
		// +-------+
		// Select everybody, but increment the age by 1
		System.out
				.println("df.select(col(\"name\"), col(\"age\").plus(1)).show();");
		df.select(col("name"), col("age").plus(1)).show();
		// +-------+---------+
		// | name|(age + 1)|
		// +-------+---------+
		// |Michael| null|
		// | Andy| 31|
		// | Justin| 20|
		// +-------+---------+
		// Select people older than 21
		df.filter(col("age").gt(21)).show();
		// +---+----+
		// |age|name|
		// +---+----+
		// | 30|Andy|
		// +---+----+
		// Count people by age
		df.groupBy("age").count().show();
		// +----+-----+
		// | age|count|
		// +----+-----+
		// | 19| 1|
		// |null| 1|
		// | 30| 1|
		// +----+-----+
		df.createOrReplaceTempView("people");
		System.out.println("SELECT * FROM people");
		Dataset<Row> sqlDF = spark.sql("SELECT * FROM people");
		sqlDF.show();

		// Register the DataFrame as a global temporary view
		df.createGlobalTempView("people");

		// Global temporary view is tied to a system preserved database
		// `global_temp`
		spark.sql("SELECT * FROM global_temp.people").show();
		// +----+-------+
		// | age| name|
		// +----+-------+
		// |null|Michael|
		// | 30| Andy|
		// | 19| Justin|
		// +----+-------+

		// Global temporary view is cross-session
		spark.newSession().sql("SELECT * FROM global_temp.people").show();
		// +----+-------+
		// | age| name|
		// +----+-------+
		// |null|Michael|
		// | 30| Andy|
		// | 19| Justin|
		// +----+-------+
	}
}
