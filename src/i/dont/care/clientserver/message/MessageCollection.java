package i.dont.care.clientserver.message;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class MessageCollection extends ArrayList<Message> implements Serializable {
	
	public MessageCollection(Message[] messages) {
		super();
		this.addAll(Arrays.asList(messages));
	}
	
	public MessageCollection(Message message) {
		this(new Message[] { message });
	}
	
	public MessageCollection() {
		super();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		this.forEach(message -> sb.append(message).append("; "));
		
		return sb.toString();
	}
}
