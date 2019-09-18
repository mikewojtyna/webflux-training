package pro.buildmysoftware.webflux.workshop.message.write;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// @RestController
public class WriteSystemAdapterController {
	private WriteSystem writeSystem;

	public WriteSystemAdapterController(WriteSystem writeSystem) {
		this.writeSystem = writeSystem;
	}

	@PostMapping
	public void create(@RequestBody Message message) {
		writeSystem.create(message);
	}
}
