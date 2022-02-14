package store.views;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import store.model.Vyrobna;

public class OknoPrihlasenia extends Stage{
	
	public OknoPrihlasenia() {
		super();
		
		setTitle("Prihlasenie");
		setResizable(false);
		
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(60));
		pane.setStyle("-fx-background-color: CadetBlue");
		
		HBox hornyBox = hornyBox();
		pane.setTop(hornyBox);
		
		VBox strednyBox = strednyBox();
		strednyBox.setTranslateY(-30);
		strednyBox.setTranslateX(50);
		pane.setCenter(strednyBox);
		
		HBox dolnyBox = dolnyBox();
		pane.setBottom(dolnyBox);
		
		setScene( new Scene( pane, 600, 400));
		show();
	}

	
	public HBox dolnyBox() {
		HBox hbox = new HBox();

		String path = "./store/obrazky/vlny.png";
		Image image1 = new Image(path);
		
		ImageView view1 = new ImageView(image1);
		view1.setTranslateX(-60);
		view1.setTranslateY(50);
		view1.setFitWidth(300);
		view1.setFitHeight(82);

		Image image2 = new Image(path);
		
		ImageView view2 = new ImageView(image2);
		view2.setTranslateX(-60);
		view2.setTranslateY(50);
		view2.setFitWidth(300);
		view2.setFitHeight(82);
		
		hbox.getChildren().addAll( view1, view2 );
		
		return hbox;
	}
	
	public VBox strednyBox() {
		VBox vbox = new VBox();
		
		Label menoLabel = new Label("Meno: ");
		Label idLabel = new Label("ID: ");
		Button prihlasit = new Button("Prihlási");
		
		TextField meno = new TextField("Meno");
		meno.setMaxWidth(200);
		TextField ID = new TextField("ID");
		ID.setMaxWidth(200);
		
		menoLabel.setFont( new Font("System", 24) );
		idLabel.setFont( new Font("System", 24) );

		prihlasit.setFont( new Font("System", 24) );
		prihlasit.setStyle("-fx-background-color: Black");
		prihlasit.setStyle("-fx-color: Black");
		prihlasit.setTranslateY(20);
		
		vbox.getChildren().addAll( menoLabel, meno, idLabel, ID, prihlasit );
		

		prihlasit.setOnAction( e-> {
			Vyrobna vyrobna = Vyrobna.getVyrobna();
			
			if( vyrobna.skontrolujUdaje( meno.getText(), Integer.parseInt(ID.getText())) )
			{
				new OknoZamestnancov();
				close();
			}
			else {
				Alert a = new Alert(AlertType.WARNING);
				a.setTitle("Nesprávne údaje");
				a.setContentText("Zadali ste nesprávne prihlasovacie údaje");
				a.showAndWait();
			}
		});
		
		
		return vbox;
	}
	
	public HBox hornyBox(){
		HBox hbox = new HBox();
		
		Label nadpis = new Label("Zadajte Vaše údaje:");
		nadpis.setStyle("-fx-font-weight: bold");
		nadpis.setFont( new Font("System", 24) );
		nadpis.setTranslateY(-50);
		
		hbox.getChildren().add(nadpis);
		
		return hbox;
	}
}
