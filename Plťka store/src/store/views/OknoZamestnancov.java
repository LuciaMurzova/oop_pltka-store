package store.views;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
//import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
//import javafx.scene.layout.FlowPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import store.model.Vyrobna;
import store.model.objednavky.Objednavka;

public class OknoZamestnancov extends Stage{
	
	public OknoZamestnancov() {
		super();
		
		setTitle("Staff only");
		setResizable(false);
		
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(60));
		pane.setStyle("-fx-background-color: CadetBlue");
		
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

		Button vsetkyButton = new Button("Vsetky objednávky");
		Button hotoveButton = new Button("Hotové objednávky");
		Button priradeneButton = new Button("Moje objednávky");
		Button odhlasitButton = new Button("Odhlási");

		vsetkyButton.setFont( new Font("System", 24) );
		vsetkyButton.setStyle("-fx-background-color: Black");
		vsetkyButton.setStyle("-fx-color: Black");
		
		hotoveButton.setFont( new Font("System", 24) );
		hotoveButton.setStyle("-fx-background-color: Black");
		hotoveButton.setStyle("-fx-color: Black");
		hotoveButton.setTranslateY(20);
		
		priradeneButton.setFont( new Font("System", 24) );
		priradeneButton.setStyle("-fx-background-color: Black");
		priradeneButton.setStyle("-fx-color: Black");
		priradeneButton.setTranslateY(40);
		
		odhlasitButton.setFont( new Font("System", 24) );
		odhlasitButton.setStyle("-fx-background-color: Black");
		odhlasitButton.setStyle("-fx-color: Black");
		odhlasitButton.setTranslateY(60);
		
		vbox.getChildren().addAll( vsetkyButton, hotoveButton, priradeneButton, odhlasitButton );
		

		vsetkyButton.setOnAction( e-> {
			Vyrobna vyrobna = Vyrobna.getVyrobna();
			ArrayList<Objednavka> listObjednavok = vyrobna.getListObjednavok();
			class VypisObjednavok extends Stage{
				public VypisObjednavok(){
					
					setTitle("Všetky objednávky");
					setResizable(false);
					
					FlowPane pane = new FlowPane();
					TextArea vypis = new TextArea();
					
					pane.getChildren().add(vypis);
					
					for( Objednavka o : listObjednavok ) 
						vypis.appendText( "Cislo objednakvy: "+o.getCisloObjednavky()+", cena: "+o.getCenaObjednavky()+", "
											+o.getDsciplinaString()+"-"+o.getKategoriaString().toString() + "\n");
						
					setScene( new Scene( pane, 600, 400));
					show();
				}
			};
			
			new VypisObjednavok();
			
		});
		
		priradeneButton.setOnAction( e-> {
			Vyrobna vyrobna = Vyrobna.getVyrobna();
			ArrayList<Objednavka> listObjednavok = vyrobna.getListObjednavok();
			class VypisPriradenych extends Stage{
				public VypisPriradenych(){
					
					setTitle("Všetky objednávky");
					setResizable(false);
					
					FlowPane pane = new FlowPane();
					TextArea vypis = new TextArea();
					
					pane.getChildren().add(vypis);
					
					for( Objednavka o : listObjednavok ) {
						// treba prejst zoznam zamestnancov, vytvorit referenciu na prihlaseneho zametsnanca pre porovnanie 
						// priradenej objednavky - prerobit priradene objednavky na zoznam priradenych, nie len premennu
						vypis.appendText( "Cislo objednakvy: "+o.getCisloObjednavky()+", cena: "+o.getCenaObjednavky()+", "
								+o.getDsciplinaString()+"-"+o.getKategoriaString().toString() + "\n");
					}
						
						
					setScene( new Scene( pane, 600, 400));
					show();
				}
			};
			
			new VypisPriradenych();
		});
		
		odhlasitButton.setOnAction( e-> close() );
		
		return vbox;
	}

}

