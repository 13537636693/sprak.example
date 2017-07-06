package com.fish;

import java.util.Map;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.recommendation.Rating;
import org.apache.spark.sql.Dataset;

import scala.Tuple2;

public class ALSTrain2 {
	public static void main(String[] args) {

		JavaRDD<Rating>[] splits = prepareData();
		JavaRDD<Rating> trainData = splits[0];
		JavaRDD<Rating> validationData = splits[1];
		JavaRDD<Rating> testData = splits[2];

		trainValidation(trainData, validationData);
	}

	private static void trainValidation(JavaRDD<Rating> trainData,
			JavaRDD<Rating> validationData) {
		System.out.println("评估rank参数使用");
		evaluateParameter(trainData, validationData, "rank", new Integer[] { 5,
				10, 15, 20, 50, 100 }, new Integer[] { 10 },
				new Double[] { 0.1 });
		System.out.println("评估numIterations参数使用");
		evaluateParameter(trainData, validationData, "numIterations",
				new Integer[] { 10 }, new Integer[] { 5, 10, 15, 20, 25 },
				new Double[] { 0.1 });
		System.out.println("评估lambda参数使用");
		evaluateParameter(trainData, validationData, "lambda",
				new Integer[] { 10 }, new Integer[] { 5, 10, 15, 20, 25 },
				new Double[] { 0.05, 0.1, 1D, 5D, 10.0 });
	}

	private static void evaluateParameter(JavaRDD<Rating> trainData,
			JavaRDD<Rating> validationData, String evaluateParameter,
			Integer[] rankArray, Integer[] numIterationsArray,
			Double[] lambdaArray) {
		// TODO Auto-generated method stub
//		datas

	}

	public static JavaRDD<Rating>[] prepareData() {
		String uDateFile = "D:/git-fish-spark-example-repository/Spark-WordCount/ml-100k/u.data";
		String uItemFile = "D:/git-fish-spark-example-repository/Spark-WordCount/ml-100k/u.item";
		SparkConf sparkConf = new SparkConf().setAppName("ALSTrain").setMaster(
				"local[1]");
		JavaSparkContext sc = new JavaSparkContext(sparkConf);
		JavaRDD<String> rawUserData = sc.textFile(uDateFile, 1);
		// 构建评分数据
		JavaRDD<Rating> ratingsRDD = rawUserData.map(li -> new Rating(Integer
				.parseInt(li.split("\t")[0]),
				Integer.parseInt(li.split("\t")[1]), Double.parseDouble(li
						.split("\t")[2])));
		// 电影ID与名称对照表
		Map<String, String> movieTitle = sc
				.textFile(uItemFile, 1)
				.mapToPair(
						(li -> new Tuple2<String, String>(li.split("\\|")[0],
								li.split("\\|")[1]))).collectAsMap();
		JavaRDD<Rating>[] splits = ratingsRDD.randomSplit(new double[] { 0.8,
				0.1, 0.1 });
		return splits;
	}
}
