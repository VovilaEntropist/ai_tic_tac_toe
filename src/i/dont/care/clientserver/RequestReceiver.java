package i.dont.care.clientserver;

import i.dont.care.clientserver.message.Message;
import i.dont.care.clientserver.message.MessageCollection;

public class RequestReceiver extends Thread {
	
	private RequestProcessor processor;
	private SocketChanel chanel;
	private boolean connected = false;
	
	public RequestReceiver(RequestProcessor processor, SocketChanel chanel) {
		this.processor = processor;
		this.chanel = chanel;
		if (chanel.create()) {
			connected = true;
		}
	}
	
	@Override
	public void run() {
		while (connected) {
			MessageCollection request = null;
			
			request = (MessageCollection) chanel.read();
			if (request == null) {
				continue;
			}
			
			handleRequest(request);
		}
	}
	
	
	private void handleRequest(MessageCollection requests) {
		for (Message request : requests) {
			
			if (!chanel.write(processor.handleRequest(request))) {
				chanel.close();
				connected = false;
				break;
			}
		}
	}
}
