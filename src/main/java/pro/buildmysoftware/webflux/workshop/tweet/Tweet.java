package pro.buildmysoftware.webflux.workshop.tweet;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Collection;

@Data
@NoArgsConstructor
public class Tweet {
	@Id
	private String id;
	private String description;
	private String author;
	private Collection<String> tags;

	public Tweet(String description) {
		this.description = description;
	}
}
