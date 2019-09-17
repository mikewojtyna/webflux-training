package pro.buildmysoftware.webflux.examples.processors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.UnicastProcessor;

public class UnicastProcessorExampleTest {
	@DisplayName("unicast processor example")
	@Test
	void test() throws Exception {
		UnicastProcessor<Integer> processor =
			UnicastProcessor.create();
		FluxSink<Integer> sink = processor.sink();

		sink.next(1);
		sink.next(2);

		processor.subscribe(System.out::println);
		// try to add another subscribe - the processor should crash

		sink.next(3);
		sink.next(4);
	}
}
