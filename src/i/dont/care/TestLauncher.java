package i.dont.care;

import i.dont.care.tictactoe.clientside.model.Model;
import i.dont.care.tictactoe.clientside.mvc.IController;
import i.dont.care.tictactoe.clientside.mvc.IModel;
import i.dont.care.tictactoe.clientside.controller.Controller;
import i.dont.care.tictactoe.clientside.view.console.ConsoleUI;

public class TestLauncher {
	
	public static final String START = "start";
	
	public static void main(String[] args) {
		
		String action = args[0];
		
		IModel model = new Model();
		IController controller = new Controller(model);
		ConsoleUI view = new ConsoleUI(controller);
		model.addViewObserver(view);
		
		if (action.equalsIgnoreCase(START)) {
			view.startServer(6660);
		}
		
		view.printName();
		

		

		
		
		
	}
	
}
