package PackageBlackjack;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Game extends Application{


    @Override
	public void start(Stage primary) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("prototype1.fxml"));
		Scene scene = new Scene(root);
		primary.setTitle("Blackjack Remastered");
		primary.setScene(scene);
		primary.show();
	}
    
}

