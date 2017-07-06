package com.fish;

import java.util.function.Supplier;

interface DefaultDemo {

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
		static DefaultDemo create(Supplier<DefaultDemo> supplier) {
			return supplier.get();
		}

	}

	class OverridableImpl implements DefaultDemo {
		@Override
		public String notRequired() {
			return "Overridden implementation";
		}
	}

	static class DefaultableImpl implements DefaultDemo {
	}

	public static void main(String[] args) {
		// 1。 Defaulable defaulable
		// =DefaulableFactory.create(DefaultableImpl::new);
		// 2.
		DefaultDemo defaulable = DefaulableFactory
				.create(new Supplier<DefaultDemo>() {
					@Override
					public DefaultDemo get() {
						return new DefaultableImpl();
					}
				});
		System.out.println(defaulable.notRequired());

		System.out.println(defaulable.notRequired());
		// 注意：这里面create是要求返回一个Supplier，而不是Supplier中申明的泛型
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