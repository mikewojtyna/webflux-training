package pro.buildmysoftware.webflux.workshop.message.write;

import lombok.Data;

@Data
public class Message {
	private long id;
	private String description;
	private String author;
	private String date;
}
