package com.fish;

import java.util.function.Consumer;

public class ConsumerDemo {

	public static void main(String[] args) {
		// 函数接口【有且只有一个抽象方法】
		// Consumer<User> c = (u) -> u.setName("new name...");
		Consumer<User> c = new Consumer<User>() {
			@Override
			public void accept(User t) {
				t.setName("new name..");
			}
		};
		User u = new User();
		c.accept(u);
		System.out.println(u.getName());

	}

	public static class User {
		private String name;

		public void setName(String name) {
			this.name = name;
		}

		public String getName() {
			return this.name;
		}
	}
}
