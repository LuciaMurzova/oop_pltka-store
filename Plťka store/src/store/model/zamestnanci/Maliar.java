package store.model.zamestnanci;

import store.model.lode.*;
import store.model.objednavky.*;
import store.model.vyroba.Sklad;


/**
 * Trieda <code>Maliar</code> rozširuje triedu {@link Pracovnik}. 
 * Maliar zodpovedá za namalovanie a nalakovanie práve vytváranej lode.
 * 
 * @author Lucia
 *
 */
public class Maliar extends Pracovnik {
		
	public Maliar(String m, Sklad s) {
		super(m, s);
	}
	
	/**
	 * Nama¾ovanie lode - pod¾a kategórie a disciplíny danej objednávky zistí poèet farby potrebnej na nama¾ovanie
	 * a toto množstvo odoberie zo skladu.
	 * 
	 * @param o		Zadaná objednávka na nama¾ovanie.
	 */
	public void namaluj( Objednavka o ) {

		Kategoria kat = o.getKategoria();
		Disciplina disc = o.getDisciplina();
		
		sklad.getFarba( kat.namaluj(disc) );
	}
	
	/**
	 * Nalakovanie lode - pod¾a kategórie a disciplíny danej objednávky zistí poèet laku potrebný na nalakovanie
	 * a toto množstvo odoberie zo skladu. 
	 * 
	 * @param o Objednavka na nalakovanie.
	 */
	public void nalakuj( Objednavka o ) {
		
		Kategoria kat = o.getKategoria();
		Disciplina disc = o.getDisciplina();
		
		sklad.getLak( kat.nalakuj(disc) );
	}

	/**
	 * Metóda zdedená z triedy {@link Pracovnik}. Zavolá sa metóda nadradenej triedy {@link Pracovnik}, 
	 * následne presunie objednávku na ma¾ovanie a lakovanie. Po dokonèení svojej práce nastavý pracovník 
	 * svoj stav na vo¾ný a zruší si priradenie danej objednávky.
	 * 
	 * @param o 	Zadaná objednávka na nama¾ovanie a nalakovanie.
	 */
	@Override
	public void urobSvojuPracu(Objednavka o) {
		
		super.urobSvojuPracu(o);
		o.presunNaMalovanie();
		namaluj(o);
		o.presunNaLakovanie();
		nalakuj(o);
		volny();
		zrusPriradenieObjednavky();
	}

	/**
	 * Metóda zdedená z nadradenej triedy {@link Pracovnik}. Mwtóda na získanie pozície pracovníka ako String.
	 * @return Vráti String Maliar.
	 */
	@Override
	public String getPozicia() {
		return "Maliar";
	}

}
