package pro.buildmysoftware.webflux.workshop.message.read;

import pro.buildmysoftware.webflux.mongo.Message;
import reactor.core.publisher.Flux;

public interface ReadSystem {
	Flux<Message> allMsgs();

	Flux<Message> msgsByDescription();
}
