package pro.buildmysoftware.webflux.stacjait.conference.readmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class State {

	String room;
	List<String> users;
}
