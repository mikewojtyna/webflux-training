package pro.buildmysoftware.webflux.mongo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/controller/mongo")
public class MongoController {
	private MessageRepository messageRepository;

	public MongoController(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	@GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Message> findMessagesByNameStreams(@RequestParam("msg") String msg) {
		return messageRepository.findByMsg(msg).log();
	}

	@PostMapping
	public Mono<Message> save(@RequestBody Message message) {
		return messageRepository.save(message);
	}
}
