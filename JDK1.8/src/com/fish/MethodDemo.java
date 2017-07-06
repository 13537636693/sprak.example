package com.fish;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class MethodDemo {
	public static MethodDemo create(final Supplier<MethodDemo> supplier) {
		return supplier.get();
	}

	public static void collide(final MethodDemo car) {
		System.out.println("Collided " + car.toString());
	}

	public void follow(final MethodDemo another) {
		System.out.println("Following the " + another.toString());
	}

	public void repair() {
		System.out.println("Repaired " + this.toString());
	}

	public static void main(String[] args) {
		// 第一种方法引用是构造器引用，它的语法是Class::new，或者更一般的Class< T >::new。请注意构造器没有参数。
		final MethodDemo car = MethodDemo.create(MethodDemo::new);
		final List<MethodDemo> cars = Arrays.asList(car);

		// 第二种方法引用是静态方法引用，它的语法是Class::static_method。请注意这个方法接受一个Car类型的参数。
		cars.forEach(MethodDemo::collide);

		// 第三种方法引用是特定类的任意对象的方法引用，它的语法是Class::method。请注意，这个方法没有参数。
		cars.forEach(MethodDemo::repair);

		// 最后，第四种方法引用是特定对象的方法引用，它的语法是instance::method。请注意，这个方法接受一个Car类型的参数
		final MethodDemo police = MethodDemo.create(MethodDemo::new);
		cars.forEach(police::follow);
	}
}