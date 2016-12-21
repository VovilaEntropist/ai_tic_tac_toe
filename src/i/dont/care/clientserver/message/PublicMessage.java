package i.dont.care.clientserver.message;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class PublicMessage {
	
	private Message message;
	private Set<Object> destinations;
	
	public PublicMessage(Message message, Set<Object> destinations) {
		this.message = message;
		this.destinations = destinations;
	}
	
	public PublicMessage(Message message) {
		this(message, new HashSet<>());
	}
	
	public boolean hasDestination(Object dest) {
		return destinations.contains(dest);
	}
	
	public Message grabMessageFor(Object dest) {
		destinations.remove(dest);
		return message;
	}
	
	public void addDestination(Collection dest) {
		destinations.addAll(dest);
	}
	
	public void addDestination(Object... dest) {
		addDestination(Arrays.asList(dest));
	}
	
}
