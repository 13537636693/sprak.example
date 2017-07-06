package com.fish;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;

import scala.Tuple2;

//import java.util.function.Function;

public final class JavaRddAPI {

	@SuppressWarnings({ "resource", "serial", "unused" })
	public static void main(String[] args) throws Exception {

		SparkConf sparkConf = new SparkConf().setAppName("JavaWordCount")
				.setMaster("local[1]");
		JavaSparkContext sc = new JavaSparkContext(sparkConf);

		// List<String> list = new ArrayList<String>();
		// // 建立列表，列表中包含以下自定义表项
		// list.add("error:a");
		// list.add("error:b");
		// list.add("error:c");
		// list.add("warning:d");
		// list.add("hadppy ending!");
		// // 将列表转换为RDD对象
		// JavaRDD<String> lines = sc.parallelize(list);
		// // 将RDD对象lines中有error的表项过滤出来，放在RDD对象errorLines中
		// JavaRDD<String> errorLines = lines
		// .filter(new Function<String, Boolean>() {
		// public Boolean call(String v1) throws Exception {
		// return v1.contains("error");
		// }
		// });
		// // 遍历过滤出来的列表项
		// List<String> errorList = errorLines.collect();
		// for (String line : errorList)
		// System.out.println(line);
		// //
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// JavaRDD<String> warningLines = lines
		// .filter(new Function<String, Boolean>() {
		// public Boolean call(String v1) throws Exception {
		// return v1.contains("warning");
		// }
		// });
		// JavaRDD<String> unionLines = errorLines.union(warningLines);
		// for (String line : unionLines.collect())
		// System.out.println(line);
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// List<String> strLine = new ArrayList<String>();
		// strLine.add("how are you");
		// strLine.add("I am ok");
		// strLine.add("do you love me");
		// JavaRDD<String> input = sc.parallelize(strLine);
		// JavaRDD<String> words = input
		// .flatMap(new FlatMapFunction<String, String>() {
		// public Iterator<String> call(String s) throws Exception {
		// return Arrays.asList(s.split(" ")).iterator();
		// }
		// });
		// words = input.flatMap(s -> Arrays.asList(s.split(" ")).iterator());
		// words = words.filter(s -> s.contains("you"));
		// //
		// JavaPairRDD<String, Integer> counts = words
		// .mapToPair(new PairFunction<String, String, Integer>() {
		// @SuppressWarnings({ "unchecked", "rawtypes" })
		// public Tuple2<String, Integer> call(String s)
		// throws Exception {
		// System.out.println("S=" + s);
		// return new Tuple2(s, 1);
		// }
		// });
		// //
		// JavaPairRDD<String, Integer> results = counts
		// .reduceByKey(new
		// org.apache.spark.api.java.function.Function2<Integer, Integer,
		// Integer>() {
		// public Integer call(Integer v1, Integer v2)
		// throws Exception {
		// return v1 + v2;
		// }
		// });
		// List<Tuple2<String, Integer>> output = results.collect();
		// for (Tuple2<?, ?> tuple : output) {
		// System.out.println(tuple._1() + ": " + tuple._2());
		// }
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// JavaRDD<Integer> inputInt = sc.parallelize(Arrays.asList(1, 2, 3, 4,
		// 1,
		// 2));
		//
		// System.out.println("distinct and reduce="
		// + inputInt.distinct().reduce((a, b) -> a + b));
		//
		// Integer res1 = inputInt.map(i -> i).reduce((a, b) -> a + b);
		// System.out.println("Inter1 * is:" + res1);
		// Integer res2 = inputInt.map(i -> i).fold(3,
		// (Integer a, Integer b) -> a + b);
		// System.out.println("Inter2 * is:" + res2);
		//
		// List<Tuple2<Boolean, Iterable<Integer>>> g = inputInt.groupBy(
		// i -> i % 2 == 0).collect();
		// for (Tuple2<Boolean, Iterable<Integer>> tuple : g) {
		// System.out.println(tuple._1() + ": " + tuple._2());
		// }
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

		// JavaRDD<Integer> rdd1 = sc.parallelize(Arrays.asList(1, 2, 3, 4));
		// JavaRDD<Integer> rdd2 = sc.parallelize(Arrays.asList(1, 2));
		// JavaPairRDD<Integer, Integer> pairRdd = rdd1.cartesian(rdd2);
		// for (Tuple2<Integer, Integer> tuple : pairRdd.collect())
		// System.out.println(tuple._1() + "->" + tuple._2());
		// // 一维数组转成二维
		// List<Tuple2<Integer, Integer>> cartes =
		// rdd1.cartesian(rdd2).collect();
		//
		// List<Integer> taeks = rdd1.take(2);
		// System.out.println("takes" + taeks);
		// System.out.println("takes sum" + rdd1.reduce((a, b) -> a + b));
		//
		// List<Integer> kyes = rdd1.cartesian(rdd2).keys().collect();
		//
		// int sum = pairRdd.mapValues(x -> x * x).values()
		// .reduce((a, b) -> a + b);
		// System.out.println("may be 20[" + sum);
		// System.out.println("pairRdd:" + pairRdd.mapValues(x -> x * x));

		// JavaPairRDD<Integer, String> kvFruit = sc.parallelizePairs(Arrays
		// .asList(new Tuple2<Integer, String>(1, "apple"),
		// new Tuple2<Integer, String>(2, "orange"),
		// new Tuple2<Integer, String>(3, "banana"),
		// new Tuple2<Integer, String>(4, "grape")));
		// // 每次执行一次转换，都要将以下两个集合传送到Worker Node才能执行转换
		// Map<Integer, String> fruitMap = kvFruit.collectAsMap();
		// JavaRDD<Integer> fruitIds = sc.parallelize(Arrays.asList(2, 4, 1,
		// 3));
		//
		// List<String> fruitNames = fruitIds.map(x ->
		// fruitMap.get(x)).collect();
		// System.out.println("fruitNames=" + fruitNames);
		// Broadcast<Map<Integer, String>> fruitMapBroadcast = sc
		// .broadcast(fruitMap);
		// List<String> fruitNamesBroadcast = fruitIds.map(
		// x -> fruitMapBroadcast.value().get(x)).collect();
		// System.out.println("fruitNamesBroadcast=" + fruitNames);

		JavaRDD<Integer> orderby = sc.parallelize(Arrays.asList(8, 2, 3, 4, 5));
		orderby = orderby.sortBy(x -> x, true, 0);
		Integer max = orderby.max((x1, x2) -> x1.compareTo(x2));
		System.out.println("MAX:" + max);

	}
}