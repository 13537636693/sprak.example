package com.fish;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.AnalysisException;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import com.fish.SqlCreatingDatasets.Person;

public class SqlCreatingDataSets2 {

	public static void main(String[] args) throws AnalysisException {
		SparkSession spark = SparkSession.builder()
				.appName("Java Spark SQL basic example").master("local[1]")
				.config("spark.some.config.option", "some-value").getOrCreate();

		JavaRDD<Person> peopleRDD = spark.read()
				.textFile("resources/people.txt").javaRDD()
				.map(new Function<String, Person>() {
					@Override
					public Person call(String line) throws Exception {
						String[] parts = line.split(",");
						Person person = new Person();
						person.setName(parts[0]);
						person.setAge(Integer.parseInt(parts[1].trim()));
						return person;
					}
				});
		// Apply a schema to an RDD of JavaBeans to get a DataFrame
		Dataset<Row> peopleDF = spark.createDataFrame(peopleRDD, Person.class);
		// Register the DataFrame as a temporary view
		peopleDF.createOrReplaceTempView("people");

		// SQL statements can be run by using the sql methods provided by spark
		Dataset<Row> teenagersDF = spark
				.sql("SELECT name FROM people WHERE age BETWEEN 13 AND 19");

		// The columns of a row in the result can be accessed by field index
		Encoder<String> stringEncoder = Encoders.STRING();
		Dataset<String> teenagerNamesByIndexDF = teenagersDF.map(
				new MapFunction<Row, String>() {
					@Override
					public String call(Row row) throws Exception {
						return "Name: " + row.getString(0);
					}
				}, stringEncoder);
		teenagerNamesByIndexDF.show();
		// +------------+
		// | value|
		// +------------+
		// |Name: Justin|
		// +------------+

		// or by field name
		Dataset<String> teenagerNamesByFieldDF = teenagersDF.map(
				new MapFunction<Row, String>() {
					@Override
					public String call(Row row) throws Exception {
						return "Name: " + row.<String> getAs("name");
					}
				}, stringEncoder);
		teenagerNamesByFieldDF.show();
		// +------------+
		// | value|
		// +------------+
		// |Name: Justin|
		// +------------+
	}
}
