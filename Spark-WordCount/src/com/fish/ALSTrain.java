package com.fish;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.recommendation.ALS;
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel;
import org.apache.spark.mllib.recommendation.Rating;

import scala.Tuple2;

public class ALSTrain {
	public static void main(String[] args) {
		String uDateFile = "D:/git-fish-spark-example-repository/Spark-WordCount/ml-100k/u.data";
		String uItemFile = "D:/git-fish-spark-example-repository/Spark-WordCount/ml-100k/u.item";
		SparkConf sparkConf = new SparkConf().setAppName("ALSTrain").setMaster(
				"local[1]");
		JavaSparkContext sc = new JavaSparkContext(sparkConf);
		JavaRDD<String> rawUserData = sc.textFile(uDateFile, 1);
		Map<String, String> movieTitle = sc
				.textFile(uItemFile, 1)
				.mapToPair(
						(li -> new Tuple2<String, String>(li.split("\\|")[0],
								li.split("\\|")[1]))).collectAsMap();

		JavaRDD<Integer> maps = rawUserData.map(
				li -> Integer.parseInt(li.split("\t")[0])).sortBy(x -> x, true,
				0);

		List<String> find196 = rawUserData.filter(
				li -> li.split("\t")[0].equals("196")).collect();
		for (String s : find196)
			System.out.println("196 :" + s.toString());

		System.out.println("count:" + maps.count());

		JavaRDD<Rating> ratings = rawUserData.map(li -> new Rating(Integer
				.parseInt(li.split("\t")[0]),
				Integer.parseInt(li.split("\t")[1]), Double.parseDouble(li
						.split("\t")[2])));
		MatrixFactorizationModel model = ALS.train(ratings.rdd(), 10, 10, 0.01);
		Rating[] productsList = model.recommendProducts(196, 4);
		for (Rating s : productsList)
			System.out.println("User(196) suggest products:" + s.toString());

		System.out.println("评分:" + model.predict(194, 464));
		Rating[] usersList = model.recommendUsers(464, 5);
		for (Rating s : usersList)
			System.out.println("Product(464) suggest users:" + s.toString());
		sc.close();

	}

}
