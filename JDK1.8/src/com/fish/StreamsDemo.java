package com.fish;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsDemo {
	private enum Status {
		OPEN, CLOSED
	};

	private static final class Task {
		private final Status status;
		private final Integer points;

		Task(final Status status, final Integer points) {
			this.status = status;
			this.points = points;
		}

		public Integer getPoints() {
			return points;
		}

		public Status getStatus() {
			return status;
		}

		@Override
		public String toString() {
			return String.format("[%s, %d]", status, points);
		}
	}

	public static void main(String[] args) {
		final Collection<Task> tasks = Arrays.asList(new Task(Status.OPEN, 5),
				new Task(Status.OPEN, 13), new Task(Status.CLOSED, 8));
		tasks.stream().filter(k -> k.getStatus().equals(Status.OPEN))
				.mapToInt(m -> m.getPoints()).sum();
		tasks.stream().filter(k -> k.getStatus().equals(Status.OPEN))
				.mapToInt(m -> m.getPoints()).average().getAsDouble();
		tasks.stream().filter(k -> k.getStatus().equals(Status.OPEN))
				.collect(Collectors.toList());
		// 下面等同于Collectors.toList()
		List<Task> list = tasks
				.stream()
				.filter(k -> k.getStatus().equals(Status.OPEN))
				.collect(() -> new ArrayList<Task>(),
						(left, target) -> left.add(target),
						(left, right) -> left.addAll(right));
		System.out.println(list);

		List<Status> statuslist = tasks.stream()
				.flatMap(new Function<Task, Stream<? extends Status>>() {
					@Override
					public Stream<? extends Status> apply(Task t) {
						return Arrays.asList(t.getStatus()).stream();
					}
				}).collect(Collectors.toList());
		System.out.println("statuslist size:" + statuslist.size() + " info:"
				+ statuslist);

	}
}