package com.fish;

import java.util.function.Consumer;
import java.util.function.Function;

public class FunctionalInterfaceExample {
	public static void main(String[] args) {
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// 简单的,只有一行
		Function<Integer, String> function1 = (x) -> " test result: " + x;

		// 标准的,有花括号, return, 分号.
		Function<String, String> function2 = (x) -> {
			return "after function1" + x;
		};
		System.out.println(function1.apply(6));
		System.out.println(function1.andThen(function2).apply(7));
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		Consumer<String> consumer1 = (x) -> System.out.print(x);
		Consumer<String> consumer2 = (x) -> {
			System.out.println(" after consumer 1");
		};
		consumer1.andThen(consumer2).accept("test consumer1");
	}
}
