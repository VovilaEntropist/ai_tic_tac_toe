package i.dont.care.clientserver.message;

import java.util.ArrayList;

public class PublicMessageCollection extends ArrayList<PublicMessage> {
	
	public MessageCollection grabMessageCollection(Object dest) {
		MessageCollection messageCollection = new MessageCollection();
		for (PublicMessage publicMessage : this) {
			if (publicMessage.hasDestination(dest)) {
				messageCollection.add(publicMessage.grabMessageFor(dest));
			}
		}
		
		return messageCollection;
	}
}
