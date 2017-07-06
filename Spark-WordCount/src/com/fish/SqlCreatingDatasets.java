package com.fish;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;

import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class SqlCreatingDatasets {
	public static void main(String[] args) {
		SparkSession spark = SparkSession.builder()
				.appName("Java Spark SQL basic example").master("local[1]")
				.config("spark.some.config.option", "some-value").getOrCreate();
		// Create an instance of a Bean class
		Person person = new Person();
		person.setName("Andy");
		person.setAge(32);

		// Encoders are created for Java beans
		Encoder<Person> personEncoder = Encoders.bean(Person.class);
		Dataset<Person> javaBeanDS = spark.createDataset(
				Arrays.asList(person, person), personEncoder);
		javaBeanDS.show();
		// +---+----+
		// |age|name|
		// +---+----+
		// | 32|Andy|
		// +---+----+
		//
		// Encoders for most common types are provided in class Encoders
		Encoder<Integer> integerEncoder = Encoders.INT();
		Dataset<Integer> primitiveDS = spark.createDataset(
				Arrays.asList(1, 2, 3), integerEncoder);
		// 发现DataSet和RDD的操作很像
		Dataset<Integer> transformedDS = primitiveDS.map(
				new MapFunction<Integer, Integer>() {
					@Override
					public Integer call(Integer value) throws Exception {
						return value + 1;
					}
				}, integerEncoder);
		transformedDS.collect(); // Returns [2, 3, 4]

		// DataFrames can be converted to a Dataset by providing a class.
		// Mapping based on name
		String path = "resources/people.json";
		Dataset<Person> peopleDS = spark.read().json(path).as(personEncoder);
		peopleDS.show();
		peopleDS.createOrReplaceTempView("people");
		System.out.println("SELECT * FROM people");
		Dataset<Row> sqlDF = spark.sql("SELECT * FROM people");
		sqlDF.show();
	}

	public static class Person implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = -2387961666089218666L;
		private String name;
		private int age;

		public Person() {

		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}
	}
}
