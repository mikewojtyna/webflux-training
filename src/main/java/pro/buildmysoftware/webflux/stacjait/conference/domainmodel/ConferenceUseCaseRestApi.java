package pro.buildmysoftware.webflux.stacjait.conference.domainmodel;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stacjait/conferences/{id}/users")
public class ConferenceUseCaseRestApi {

	private ConferenceUseCase conferenceUseCase;

	public ConferenceUseCaseRestApi(ConferenceUseCase conferenceUseCase) {
		this.conferenceUseCase = conferenceUseCase;
	}

	@PostMapping
	public void join(@RequestBody UserDto userDto, @PathVariable(name =
		"id") String conferenceId) {
		conferenceUseCase.join(new UserId(userDto
			.getId()), new ConferenceId(conferenceId));
	}
}
