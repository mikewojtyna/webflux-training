package pro.buildmysoftware.webflux.workshop.message.read;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MessageReactiveRepository extends ReactiveMongoRepository<Message, String> {
}
