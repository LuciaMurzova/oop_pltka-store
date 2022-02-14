package store.model.vyroba;

import java.util.ArrayList;
import java.util.List;

import store.model.objednavky.Objednavka;

public class Sklad {
	
	private int material;
	private int farba;
	private int lak;
	
	private List<Objednavka> hotoveObjednavky = null;
	
	public Sklad() {
		setMaterial(10);
		setFarba(10);
		setLak(10);
		hotoveObjednavky = new ArrayList<Objednavka>();
	}

	public void pridajObjednavku( Objednavka o ) {
		hotoveObjednavky.add(o);
	}
	
	public void odovzdajObjednavku( Objednavka o ) {
		hotoveObjednavky.remove(o);
	}
	
	
	public int getMaterial( int i ) {
		setMaterial(-i);
		return material;
	}

	public void setMaterial(int m) {
		material += m;
	}

	public int stavMaterialu() {
		return material;
	}
	
	
	public int getFarba( int i ) {
		setMaterial(-i);
		return farba;
	}

	public void setFarba(int f) {
		farba += f;
	}

	public int stavFarby() {
		return farba;
	}
	
	
	public int getLak(int i) {
		setLak(-i);
		return lak;
	}

	public void setLak(int l) {
		lak += l;
	}
	
	public int stavLaku() {
		return lak;
	}
	
}
