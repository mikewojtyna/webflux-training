package pro.buildmysoftware.webflux.web.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple2;

import java.time.Duration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureWebTestClient
public class SendAndReceiveFluxDataTest {

	@Autowired
	private WebTestClient webTestClient;

	@DisplayName("send flux data")
	@Test
	void send() throws Exception {
		webTestClient.post().uri("/controller/data")
			.contentType(MediaType.APPLICATION_STREAM_JSON)
			.body(BodyInserters.fromPublisher(Flux
				.just(new Data("data 0"), new Data("data 1"),
					new Data("data 2"))
				.zipWith(Flux.interval(Duration.ofSeconds(1)))
				.map(Tuple2::getT1), Data.class)).exchange()
			.expectStatus().isOk();

		Thread.sleep(4000);
	}

	@DisplayName("get flux data")
	@Test
	void get() throws Exception {
		Flux<Data> responseBody = webTestClient.get()
			.uri("/controller/data").exchange().expectStatus()
			.isOk().returnResult(Data.class).getResponseBody();

		// @formatter:off
		StepVerifier.create(responseBody)
			.expectNext(new Data("data 0"))
			.expectNext(new Data("data 1"))
			.expectNext(new Data("data 2"))
			.verifyComplete();
		// @formatter:on)
	}

	@DisplayName("get and send flux data")
	@Test
	void getAndSend() throws Exception {
		webTestClient.post().uri("/controller/data")
			.contentType(MediaType.APPLICATION_STREAM_JSON)
			.body(BodyInserters.fromPublisher(webTestClient.get()
				.uri("/controller/data").exchange()
				.returnResult(Data.class)
				.getResponseBody(), Data.class)).exchange();

		Thread.sleep(4000);
	}

	@DisplayName("get using regular web client")
	@Test
	void webClient() throws Exception {
		Flux<Data> dataFlux = WebClient
			.create("http://localhost:8080/controller/data").get()
			.retrieve().bodyToFlux(Data.class);

		// @formatter:off
		StepVerifier.create(dataFlux)
			.expectNext(new Data("data 0"))
			.expectNext(new Data("data 1"))
			.expectNext(new Data("data 2"))
			.verifyComplete();
		// @formatter:on
	}

	@DisplayName("call API")
	@Test
	void api() throws Exception {
		Flux<Data> response = webTestClient.get()
			.uri(uriBuilder -> uriBuilder.path("/controller/data")
				.queryParam("callApi").build()).exchange()
			.returnResult(Data.class).getResponseBody();

		// @formatter:off
		StepVerifier.create(response)
			.expectNext(new Data("data 0"))
			.expectNext(new Data("data 1"))
			.expectNext(new Data("data 2"))
			.verifyComplete();
		// @formatter:on
	}
}
