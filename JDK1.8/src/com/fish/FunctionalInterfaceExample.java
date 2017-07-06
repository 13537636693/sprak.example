package com.fish;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

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
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

		Predicate<String> predicate = (x) -> x.length() > 0;
		System.out.println(predicate.test("String"));
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

		// 简写
		Supplier<String> supplier1 = () -> "Test supplier";
		System.out.println(supplier1.get());

		// 标准格式
		Supplier<Integer> supplier2 = () -> {
			return 20;
		};
		System.out.println(supplier2.get() instanceof Integer);
	}
}
