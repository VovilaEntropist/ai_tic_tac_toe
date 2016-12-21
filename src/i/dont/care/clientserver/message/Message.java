package i.dont.care.clientserver.message;

import java.io.Serializable;

public class Message implements Serializable {
	
	private int command;
	private ParameterCollection parameters;
	
	public Message(int command, ParameterCollection parameters) {
		this.command = command;
		this.parameters = parameters;
	}
	
	public Message(int command) {
		this(command, new ParameterCollection());
	}
	
	public int getCommand() {
		return command;
	}
	
	public void addParameter(String key, Object value) {
		parameters.put(key, value);
	}
	
	public void removeParameter(String key) {
		parameters.remove(key);
	}
	
	public Object getParameter(String key) {
		return parameters.get(key);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Com: ");
		sb.append(command);
		
		parameters.forEach((key, value) ->
				sb.append(String.format(", %s=%s", key, String.valueOf(value))));
		
		return sb.toString();
	}
}
