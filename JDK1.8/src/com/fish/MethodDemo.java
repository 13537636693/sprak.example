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
		// ��һ�ַ��������ǹ��������ã������﷨��Class::new�����߸�һ���Class< T >::new����ע�⹹����û�в�����
		final MethodDemo car = MethodDemo.create(MethodDemo::new);
		final List<MethodDemo> cars = Arrays.asList(car);

		// �ڶ��ַ��������Ǿ�̬�������ã������﷨��Class::static_method����ע�������������һ��Car���͵Ĳ�����
		cars.forEach(MethodDemo::collide);

		// �����ַ����������ض�����������ķ������ã������﷨��Class::method����ע�⣬�������û�в�����
		cars.forEach(MethodDemo::repair);

		// ��󣬵����ַ����������ض�����ķ������ã������﷨��instance::method����ע�⣬�����������һ��Car���͵Ĳ���
		final MethodDemo police = MethodDemo.create(MethodDemo::new);
		cars.forEach(police::follow);
	}
}