package pro.buildmysoftware.webflux.workshop.events;

import reactor.core.publisher.FluxSink;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;

public class ReactiveEventSource implements Consumer<FluxSink<Event>> {
	private BlockingQueue<Event> queue;
	private Executor executor;

	public ReactiveEventSource(Executor executor) {
		this.executor = executor;
		queue = new LinkedBlockingQueue<>(100);
	}

	public void handle(Event event) {
		queue.offer(event);
	}

	@Override
	public void accept(FluxSink<Event> eventFluxSink) {
		executor.execute(() -> {
			try {
				while (true) {
					eventFluxSink.next(queue.take());
				}
			}
			catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		});
	}
}
