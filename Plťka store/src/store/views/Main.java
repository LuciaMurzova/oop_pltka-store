package store.views;
	
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;
import store.model.Vyrobna;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	private static Stage stage;
	public Vyrobna vyrobna;
	
	@Override
	public void start(Stage HlavneOkno) {
		try {
			stage = HlavneOkno;
			HlavneOkno.setResizable(false);
			
			Parent root = FXMLLoader.load(getClass().getResource("HlavneOkno.fxml"));
			HlavneOkno.setTitle("Plùka store");
			
			Scene scene = new Scene(root,600,400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
			vyrobna = Vyrobna.getVyrobna();
			
			HlavneOkno.setScene( scene );
			HlavneOkno.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void zmenOkno( String fxml ) throws IOException {
		Parent panel = FXMLLoader.load(getClass().getResource(fxml));
		stage.getScene().setRoot(panel);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
