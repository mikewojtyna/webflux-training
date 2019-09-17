package pro.buildmysoftware.webflux.operators;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;

public class CreateCustomFluxExampleTest {
	// @formatter:off
	@DisplayName(
		"example of creating a custom flux using separate thread"
	)
	// @formatter:on
	@Test
	void create() throws Exception {
		Flux<Integer> flux = Flux.create(sink -> {
			new Thread(() -> {
				try {
					while (true) {
						Thread.sleep(1000);
						sink.next(new Random()
							.nextInt());
					}
				}
				catch (InterruptedException e) {
					throw new RuntimeException(e);
				}

			}).start();
		});

		flux.subscribe(System.out::println);

		Thread.sleep(5000);
	}

	// @formatter:off
	@DisplayName(
		"example using custom flux and queue"
	)
	// @formatter:on
	@Test
	void queue() throws Exception {
		FluxQueue fluxQueue = new FluxQueue();
		Flux<Integer> flux = Flux.create(fluxQueue);
		new Thread(() -> {
			while (true) {
				try {
					Thread.sleep(1000);
					fluxQueue.add(new Random().nextInt());
				}
				catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}).start();

		flux.doOnNext(System.out::println).log().blockLast();
	}

	private class FluxQueue implements Consumer<FluxSink<Integer>> {
		private BlockingQueue<Integer> queue;

		public FluxQueue() {
			queue = new LinkedBlockingQueue<>();
		}

		@Override
		public void accept(FluxSink<Integer> fluxSink) {
			new Thread(() -> {
				while (true) {
					try {
						Thread.sleep(1000);
						fluxSink.next(queue.take());
					}
					catch (InterruptedException e) {
						throw new RuntimeException(e);
					}

				}
			}).start();
		}

		void add(int element) {
			queue.offer(element);
		}
	}

}
