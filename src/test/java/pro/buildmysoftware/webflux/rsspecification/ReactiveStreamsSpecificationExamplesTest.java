package pro.buildmysoftware.webflux.rsspecification;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Random;

class ReactiveStreamsSpecificationExamplesTest {

	// @formatter:off
	@DisplayName(
		"have some fun with publishers"
	)
	//@formatter:on
	@Test
	void test() throws Exception {
		Publisher<String> publisher = subscriber -> {
			Subscription subscription = new Subscription() {
				private int createdSignals;

				@Override
				public void request(long demand) {
					if (createdSignals > 100) {
						subscriber.onComplete();
					}
					createdSignals += demand;
					for (int i = 0; i < demand; i++) {
						subscriber
							.onNext("Random " +
								"message" + new Random()
								.nextInt());
					}
				}

				@Override
				public void cancel() {

				}
			};
			subscriber.onSubscribe(subscription);
		};
		publisher.subscribe(new Subscriber<>() {
			private Subscription subscription;
			private boolean completed;

			@Override
			public void onSubscribe(Subscription subscription) {
				this.subscription = subscription;
				System.out.println("onSubscribe");
				subscription.request(5);
			}

			@Override
			public void onNext(String s) {
				System.out.println("Received signal: " + s);
				if (!completed) {
					subscription.request(5);
				}
			}

			@Override
			public void onError(Throwable throwable) {

			}

			@Override
			public void onComplete() {
				completed = true;
				System.out.println("Complete");
			}
		});
	}
}
