package com.fish;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.swing.JButton;

public class Lambda {

	public static void main(String[] args) {
		// ��1����lambda���ʽʵ��Runnable
		// example1();
		// ��2��ʹ��Java 8 lambda���ʽ�����¼�����
		// example2();
		// ��3��ʹ��lambda���ʽ���б���е���
		// example3();
		// ��4��ʹ��lambda���ʽ�ͺ���ʽ�ӿ�Predicate
		// example4();
		// ��5�������lambda���ʽ�м���Predicate
		// example5();
		// ��6��Java 8��ʹ��lambda���ʽ��Map��Reduceʾ��
		example6();
		// ��6.2��Java 8��ʹ��lambda���ʽ��Map��Reduceʾ��
		example7();
		// ��7��ͨ�����˴���һ��String�б�
		example8();
		// ��8�����б��ÿ��Ԫ��Ӧ�ú���
		example9();
		// ��10�����㼯��Ԫ�ص����ֵ����Сֵ���ܺ��Լ�ƽ��ֵ
		example10();
	}

	private static void example10() {
		// ��ȡ���ֵĸ�������Сֵ�����ֵ���ܺ��Լ�ƽ��ֵ
		List<Integer> primes = Arrays
				.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
		IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x)
				.summaryStatistics();
		System.out.println("Highest prime number in List : " + stats.getMax());
		System.out.println("Lowest prime number in List : " + stats.getMin());
		System.out.println("Sum of all prime numbers : " + stats.getSum());
		System.out.println("Average of all prime numbers : "
				+ stats.getAverage());
	}

	private static void example9() {
		// �����в�ͬ�����ִ���һ���������б�
		List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
		// List<Integer> distinct = numbers.stream().map(i -> i * i).distinct()
		// .collect(Collectors.toList());
		List<Integer> distinct = numbers.stream().distinct()
				.collect(Collectors.toList());
		System.out.printf(
				"Original List : %s,  Square Without duplicates : %s %n",
				numbers, distinct);
	}

	private static void example8() {
		// ����һ���ַ����б�ÿ���ַ������ȴ���2
		List<String> names = Arrays.asList("Java", "Scala", "C++", "Haskell",
				"Lisp");
		List<String> filtered = names.stream().filter(x -> x.length() > 2)
				.collect(Collectors.toList());
		System.out.printf("Original List : %s, filtered list : %s %n", names,
				filtered);

	}

	private static void example7() {
		// Ϊÿ����������12%��˰
		// �Ϸ�����
		List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
		double total = 0;
		for (Integer cost : costBeforeTax) {
			double price = cost + .12 * cost;
			total = total + price;
		}
		System.out.println("Total : " + total);

		// �·�����
		costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
		double bill = costBeforeTax.stream().map((cost) -> cost + .12 * cost)
				.reduce((sum, cost) -> sum + cost).get();
		System.out.println("Total : " + bill);

	}

	private static void example6() {
		// ��ʹ��lambda���ʽΪÿ����������12%��˰
		List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
		for (Integer cost : costBeforeTax) {
			double price = cost + .12 * cost;
			System.out.println(price);
		}

		// ʹ��lambda���ʽ
		costBeforeTax.stream().map((v) -> v = (int) (v + v * 0.12))
				.forEach(w -> System.out.println(w));

		costBeforeTax.forEach(x -> System.out.println(x));

	}

	private static void example5() {
		List<String> names = Arrays.asList("Java", "Scala", "C++", "Haskell",
				"Lisp");
		// ����������and()��or()��xor()�߼��������ϲ�Predicate��
		// ����Ҫ�ҵ�������J��ʼ������Ϊ�ĸ���ĸ�����֣�����Ժϲ�����Predicate������
		Predicate<String> startsWithJ = (n) -> n.startsWith("J");
		Predicate<String> fourLetterLong = (n) -> n.length() == 4;
		names.stream()
				.filter(startsWithJ.and(fourLetterLong))
				.forEach(
						(n) -> System.out
								.print("nName, which starts with 'J' and four letter long is : "
										+ n));

	}

	private static void example4() {
		List<String> languages = Arrays.asList("Java", "Scala", "C++",
				"Haskell", "Lisp");

		System.out.println("Languages which starts with J :");
		filter(languages, (str) -> str.startsWith("J"));

		System.out.println("Languages which ends with a ");
		filter(languages, (str) -> str.endsWith("a"));

		System.out.println("Print all languages :");
		filter(languages, (str) -> true);

		System.out.println("Print no language : ");
		filter(languages, (str) -> false);

		System.out.println("Print language whose length greater than 4:");
		filter(languages, (str) -> str.length() > 4);
	}

	public static void filter(List<String> names, Predicate<String> condition) {
		for (String name : names) {
			if (condition.test(name)) {
				System.out.println(name + " ");
			}
		}
	}

	private static void example3() {
		// Java 8֮ǰ��
		List<String> features = Arrays.asList("Lambdas", "Default Method",
				"Stream API", "Date and Time API");
		for (String feature : features) {
			System.out.println(feature);
		}
		features.forEach(n -> System.out.println(n));

		// ����һ���ַ����б�ÿ���ַ������ȴ���2
		List<String> filtered = features.stream().filter(w -> w.length() > 2)
				.collect(Collectors.toList());
		System.out.printf("Original List : %s, filtered list : %s %n",
				features, filtered);
	}

	private static void example2() {

		// Java 8 before
		JButton show = new JButton("Show");
		show.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out
						.println("Event handling without lambda expression is boring");
			}
		});

		// Java 8
		show.addActionListener((e) -> {
			System.out
					.println("Light, Camera, Action !! Lambda expressions Rocks");
		});
	}

	private static void example1() {

		// Java 8 before
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out
						.println("Before Java8, too much code for too little to do");
			}
		}).start();
		// Java 8
		new Thread(
				() -> System.out
						.println("In Java8, Lambda expression rocks !!"))
				.start();
	}
}
