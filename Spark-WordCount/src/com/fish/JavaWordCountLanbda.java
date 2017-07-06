package com.fish;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

public final class JavaWordCountLanbda {
	@SuppressWarnings("unused")
	private static final Pattern SPACE = Pattern.compile(" ");

	@SuppressWarnings({ "resource" })
	public static void main(String[] args) throws Exception {
		String file = "d:/LICENSE.txt";
		SparkConf sparkConf = new SparkConf().setAppName("JavaWordCount")
				.setMaster("local[4]");
		JavaSparkContext ctx = new JavaSparkContext(sparkConf);
		JavaRDD<String> lines = ctx.textFile(file, 1);
		JavaPairRDD<String, Integer> counts = lines
				.flatMap(s -> Arrays.asList(s.split(" ")).iterator())
				.mapToPair(word -> new Tuple2<>(word, 1))// 返回一个健值对
				.reduceByKey((a, b) -> a + b);
		List<Tuple2<String, Integer>> output = counts.collect();
		for (Tuple2<?, ?> tuple : output) {
			System.out.println(tuple._1() + ": " + tuple._2());
		}
		ctx.stop();
	}
}