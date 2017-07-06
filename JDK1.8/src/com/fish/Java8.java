package com.fish;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class Java8 {
	public static void main(String[] args) {
		List<String> wordList = Arrays.asList("a", "abc", "bcd");
		List<String> output = wordList.stream().map(String::toUpperCase)
				.collect(Collectors.toList());
		System.out.println(output);
		System.out.println("MATCH:"
				+ wordList.stream().anyMatch(x -> x.length() == 5));
		long startsWithB = wordList.stream().filter((s) -> s.startsWith("b"))
				.count();
		System.out.println(startsWithB); // 3
		String name = null;
		Optional<String> o = Optional.ofNullable(name);
		o.orElse("deault");
		System.out.println("ifPresent=" + o.isPresent());
		System.out.println("orElse=" + o.orElse("or else"));
		System.out.println("orElseGet=" + o.orElseGet(() -> "Default Value"));
		// System.out.println("Optional=" + o.get());

		List<String> list = Arrays.asList("asb", "ab", "de");
		// 为每个订单加上12%的税
		// 老方法：
		List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
		double total = 0;
		for (Integer cost : costBeforeTax) {
			double price = cost + .12 * cost;
			total = total + price;
		}
		System.out.println("Total : " + total);

	
		
		double bill2 =costBeforeTax.stream().map((i)-> i+i*0.12).reduce((a,b)->a+b).get();
		System.out.println("Total : " + bill2);
		
	}
}
