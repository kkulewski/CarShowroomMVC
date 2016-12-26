import View.MainView;
import Controller.MainController;
import Model.Database;

public class Host {
	
	public static void main(String[] args)
	{
		Database database = new Database();
		database.CreateTables();
		
		MainView mainView = new MainView();
		mainView.setVisible(true);
		
		MainController mainController = new MainController(mainView);
		mainController.Run();
		
		//mainController.FillWithTestData();
	}

}
