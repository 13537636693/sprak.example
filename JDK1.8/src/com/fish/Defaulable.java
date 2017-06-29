package com.fish;

import java.util.function.Supplier;

interface Defaulable {

	default String notRequired() {
		return "Default implementation";
	}

	default String notRequired2() {
		return "Default implementation";
	}

	public static String Required() {
		return "Required implementation";
	}

	static interface DefaulableFactory {
		// Interfaces now allow static methods
		static Defaulable create(Supplier<Defaulable> supplier) {
			return supplier.get();
		}

	}

	class OverridableImpl implements Defaulable {
		@Override
		public String notRequired() {
			return "Overridden implementation";
		}
	}

	static class DefaultableImpl implements Defaulable {
	}

	public static void main(String[] args) {
		// 1�� Defaulable defaulable
		// =DefaulableFactory.create(DefaultableImpl::new);
		// 2.
		Defaulable defaulable = DefaulableFactory
				.create(new Supplier<Defaulable>() {
					@Override
					public Defaulable get() {
						return new DefaultableImpl();
					}
				});
		System.out.println(defaulable.notRequired());

		System.out.println(defaulable.notRequired());
		// ע�⣺������create��Ҫ�󷵻�һ��Supplier��������Supplier�������ķ���
		defaulable = DefaulableFactory.create(() -> new DefaultableImpl());
		defaulable = DefaulableFactory.create(OverridableImpl::new);
		System.out.println(defaulable.notRequired());
		Supplier<Integer> s = new Supplier<Integer>() {
			@Override
			public Integer get() {
				return null;
			}

		};
	}
}