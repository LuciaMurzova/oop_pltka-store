package store.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import store.views.Main;

public class OdoslanaObjednavkaController {

	@FXML
	private Button spat;
	
	public void prejdiSpat(ActionEvent event) throws IOException {
		Main m = new Main();
		m.zmenOkno("HlavneOkno.fxml");
	}
	
	
}
