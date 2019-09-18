package pro.buildmysoftware.webflux.workshop.message.write;

public interface WriteSystem {
	void create(Message message);

	void delete(String msgId);
}
