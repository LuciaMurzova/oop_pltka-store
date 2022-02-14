package store.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import store.model.Vyrobna;
import store.model.vynimky.ChybneUdaje;
import store.views.Main;

public class OknoObjednavkyController {
	
	@FXML
	private MenuBar menu;
	@FXML
	private MenuItem zrusit;
	@FXML
	private MenuItem vytvorit;
	
	@FXML
	private Label labelKat;
	@FXML
	private TextField katText;
	@FXML
	private Label labelDisc;
	@FXML
	private TextField discText;
	@FXML
	private Label labelPrednostna;
	@FXML
	private TextField prednostnaText;
	@FXML
	private Button odoslanie;
	@FXML
	private Label zleUdaje;
	
	
	public void odosliObjednavku(ActionEvent event) throws IOException {
		
		Main okno = new Main();
		Vyrobna vyrobna = Vyrobna.getVyrobna();
		
		String vstupKat = katText.getText();
		String vstupDisc = discText.getText();
		String vstupPrednostna = prednostnaText.getText();
		
		System.out.println(vstupKat + " " + vstupDisc + " " + vstupPrednostna);
		
		int vynimka = 0;
		try {
			vyrobna.novaObjednavka(vstupKat, vstupDisc, vstupPrednostna);
		} catch( ChybneUdaje ex ) {
			System.out.println(ex);	
			vynimka = 1;
			okno.zmenOkno("OknoObjednavky.fxml");
		}
		
		if( vynimka == 0 )
			okno.zmenOkno("OdoslanaObjednavka.fxml");
	}
	
	public void zrusObjednavku(ActionEvent event) throws IOException {
		Main okno = new Main();
		okno.zmenOkno("HlavneOkno.fxml");
	}
	
	public void vytvorObjednavku(ActionEvent event) throws IOException {
		Main okno = new Main();
		okno.zmenOkno("OknoObjednavky.fxml");
	}
}
