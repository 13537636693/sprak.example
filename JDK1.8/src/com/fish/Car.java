package com.fish;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class Car {
	public static Car create(final Supplier<Car> supplier) {
		return supplier.get();
	}

	public static void collide(final Car car) {
		System.out.println("Collided " + car.toString());
	}

	public void follow(final Car another) {
		System.out.println("Following the " + another.toString());
	}

	public void repair() {
		System.out.println("Repaired " + this.toString());
	}

	public static void main(String[] args) {
		// ��һ�ַ��������ǹ��������ã������﷨��Class::new�����߸�һ���Class< T >::new����ע�⹹����û�в�����
		final Car car = Car.create(Car::new);
		final List<Car> cars = Arrays.asList(car);

		// �ڶ��ַ��������Ǿ�̬�������ã������﷨��Class::static_method����ע�������������һ��Car���͵Ĳ�����
		cars.forEach(Car::collide);

		// �����ַ����������ض�����������ķ������ã������﷨��Class::method����ע�⣬�������û�в�����
		cars.forEach(Car::repair);

		// ��󣬵����ַ����������ض�����ķ������ã������﷨��instance::method����ע�⣬�����������һ��Car���͵Ĳ���
		final Car police = Car.create(Car::new);
		cars.forEach(police::follow);
	}
}