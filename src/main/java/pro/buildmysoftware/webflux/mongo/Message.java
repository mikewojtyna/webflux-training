package pro.buildmysoftware.webflux.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "message")
@Data
public class Message {
	@Id
	private String id;
	private String msg;
}
