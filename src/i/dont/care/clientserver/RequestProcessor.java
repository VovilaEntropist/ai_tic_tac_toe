package i.dont.care.clientserver;

import i.dont.care.clientserver.message.Message;
import i.dont.care.clientserver.message.MessageCollection;

public interface RequestProcessor {
	
	MessageCollection handleRequest(Message request);
	
}
