package pro.buildmysoftware.webflux.mongo;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class MongoInit implements ApplicationRunner {
	private ReactiveMongoTemplate reactiveMongoTemplate;

	public MongoInit(ReactiveMongoTemplate reactiveMongoTemplate) {
		this.reactiveMongoTemplate = reactiveMongoTemplate;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		reactiveMongoTemplate.dropCollection("message")
			.then(reactiveMongoTemplate
				.createCollection("message", CollectionOptions
					.empty().capped().size(2048)
					.maxDocuments(10000))).subscribe();
	}
}
