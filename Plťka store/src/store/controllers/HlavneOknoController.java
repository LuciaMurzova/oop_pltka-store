package store.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import store.views.Main;
import store.views.OknoPrihlasenia;

public class HlavneOknoController {
	
	@FXML
	private Button objednavka;
	
	@FXML
	private Button prihlasenieButton;
	
	
	public void novaObjednavka( ActionEvent event ) throws IOException  {
		Main hlavne = new Main();
		
		hlavne.zmenOkno("OknoObjednavky.fxml");

	}
	
	public void prihlas( ActionEvent event ) {
		new OknoPrihlasenia();
	}
	
}
