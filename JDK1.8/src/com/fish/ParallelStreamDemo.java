package com.fish;

import java.util.Arrays;
import java.util.List;

public class ParallelStreamDemo {

	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
		numbers.parallelStream().forEach(System.out::print);
		System.out.println("");
		numbers.stream().forEach(System.out::print);
		System.out.println("");
		numbers.parallelStream().forEachOrdered(System.out::print);

	}
}
