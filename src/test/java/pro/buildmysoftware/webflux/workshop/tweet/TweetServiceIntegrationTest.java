package pro.buildmysoftware.webflux.workshop.tweet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TweetServiceIntegrationTest {
	@Autowired
	private TweetRepository tweetRepository;

	// @formatter:off
	@DisplayName(
		"given 3 tweets in the db," +
		"we handle them all as a stream"
	)
	// @formatter:on
	@Test
	void test() throws Exception {
		// given
		Duration timeout = Duration.ofSeconds(3);
		Tweet tweet0 = new Tweet("hello 0");
		Tweet tweet1 = new Tweet("hello 1");
		Tweet tweet2 = new Tweet("hello 2");
		tweetRepository.saveAll(Flux.just(tweet0, tweet1, tweet2))
			.blockLast(timeout);

		// when
		List<Tweet> allTweets = tweetRepository.findAll().log()
			.collectList().block();

		// then
		assertThat(allTweets)
			.containsExactlyInAnyOrder(tweet0, tweet1, tweet2);
	}
}
