package pro.buildmysoftware.webflux.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/controller/exception")
public class ExceptionThrowingController {
	@GetMapping
	public void throwEx() {
		throw new RuntimeException("Exception from " + ExceptionThrowingController.class);
	}
}
