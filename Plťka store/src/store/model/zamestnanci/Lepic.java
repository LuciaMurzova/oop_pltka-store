package store.model.zamestnanci;

import store.model.lode.*;
import store.model.objednavky.Objednavka;
import store.model.vyroba.Sklad;

/**
 * Trieda <code>Lepic</code> rozširuje triedu {@link Pracovnik}.
 * Lepiè je zodpovedný za zlepenie novej objednávky.
 * 
 * @author Lucia
 */

public class Lepic extends Pracovnik{
	
	public Lepic( String m, Sklad s) {
		super(m, s);
	}

	/**
	 * Pod¾a kategórie a disciplíny danej objednávky zistí množstvo materiálu potrebné na zlepenie
	 * danej objednávky a následne materiál odèíta zo skladu. 
	 * @param o		Objednávka na zlepenie.
	 */
	public void vytvorLod( Objednavka o ) {
		Kategoria kat = o.getKategoria();
		Disciplina disc = o.getDisciplina();
		
		sklad.getMaterial( kat.vyrob(disc) ); // model visitor 	
	}
	
	/**
	 * Prekonaná metóda z nadradenej triedy {@link Pracovnik}. Zavolá sa metóda nadradenej triedy {@link Pracovnik}, 
	 * následne zmení stav objednávky na lepení, zlepí danú loï a po ukonèení si pracovník zmení svoj stav na vo¾ný a 
	 * zruší si priradenie danej objednávky. 
	 * @param o		Zadaná objednávka.
	 */
	@Override
	public void urobSvojuPracu(Objednavka o) {
		super.urobSvojuPracu(o);
		o.presunNaLepenie();	// stav objednavky 	
		vytvorLod(o);
		volny();				// stav azmestnanca - dokoncil pracu, moze prijat dalsiu 
		zrusPriradenieObjednavky();
	}
	
	/**
	 * Metóda zdedená z nadradenej triedy {@link Pracovnik}. Mwtóda na získanie pozície pracovníka ako String.
	 * @return Vracia String Lepic.
	 */
	@Override
	public String getPozicia() {
		return "Lepic";
	}
}
