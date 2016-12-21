package i.dont.care.clientserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketChanel {
	
	private Socket socket;
	private ObjectOutputStream outputStream;
	private ObjectInputStream inputStream;
	
	public SocketChanel(Socket socket) {
		this.socket = socket;
	}
	
	public boolean create() {
		try {
			outputStream = new ObjectOutputStream(socket.getOutputStream());
			inputStream = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			return false;
		}
		
		return true;
	}
	
	public boolean write(Object object) {
		if (outputStream == null) {
			return false;
		}
		
		try {
			outputStream.reset();
			outputStream.writeObject(object);
		} catch (IOException e) {
			return false;
		}
		
		return true;
	}
	
	public Object read() {
		if (inputStream == null) {
			return null;
		}
		
		Object result = null;
		
		try {
			result = inputStream.readObject();
		} catch (IOException | ClassNotFoundException e) {
			return null;
		}
		
		return result;
	}
	
	public void close() {
		try {
			outputStream.close();
			inputStream.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
