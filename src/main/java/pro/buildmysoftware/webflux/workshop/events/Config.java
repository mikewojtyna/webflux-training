package pro.buildmysoftware.webflux.workshop.events;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;

@Configuration
public class Config {
	@Bean
	public ReactiveEventSource reactiveEventSource() {
		return new ReactiveEventSource(Executors
			.newSingleThreadExecutor());
	}
}
