import View.MainView;
import Controller.MainController;
import Model.Database;
import Controller.ClientController;

public class Host {
	
	public static void main(String[] args)
	{
		Database database = new Database();
		database.CreateTables();
		
		MainView mainView = new MainView();
		mainView.setVisible(true);
		
		MainController mainController = new MainController(mainView);
		
		/*
		//TEST DATA
		mainController.clientModel.insertClient("Jan", "Kowalski", 12233455666L, "Warszawa", "Dluga 3");
		mainController.clientModel.insertClient("Janusz", "Nowak", 87213452266L, "Gdansk", "Krotka 4a");
		mainController.clientModel.insertClient("Michal", "Malinowski", 77733755116L, "Gdynia", "Srednia 70");
		mainController.clientModel.insertClient("Dawid", "Wisniewski", 65413415261L, "Sopot", "Szeroka 1A");
		mainController.clientModel.insertClient("Mateusz", "Grucha", 55131477335L, "Poznan", "Waska 11");
		mainController.clientModel.insertClient("Lukasz", "Kowal", 01033055001L, "Wroclaw", "Dluga 289");
		//END TEST DATA
		*/
	}

}
