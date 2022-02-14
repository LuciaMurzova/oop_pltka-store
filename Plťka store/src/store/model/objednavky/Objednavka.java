package store.model.objednavky;

import store.model.info.*;
import store.model.lode.*;


/**
 * Metóda <code>Objednavka</code> implementuje rozhrania Cenník Lodí a Stav 
 * a rozširuje ju trieds {@link PrednostnaObjednavka}. 
 * Reprezentuje objednávku vytvorenú zákazníkom - pri zadaní novej objednávky sa vytvára
 * nová inštancia triedy. Objednávka má svoje objekty tried {@link Kategoria} a {@link Disciplina}.
 * 
 * @see Kategoria
 * @see Disciplina
 * @see PrednostnaObjednavka
 * 
 * @author Lucia
 *
 */
public class Objednavka implements CennikLodi, Stav {
	
	/**
	 * Èíslo objednávky - poradie v zozname všetkých objednávok.
	 */
	protected int cisloObjednavky;
	/**
	 * Cena objednávky - získaná z rozhrania Cenník Lodí, pod¾a kategórie a disciplíny danej objednávky.
	 */
	protected int cena;
	
	/**
	 * Typ kategórie objednávky. kanoe/kajak
	 */
	protected Kategoria kategoria;
	/**
	 * Typ disciplíny objednávky. slalom/zjazd/šprint
	 */
	protected Disciplina disciplina;
	
	/**
	 * Reprezentuje stav objednávky. èaká na výrobu/na ma¾ovaní/hotová - na sklade
	 */
	protected StavObjednavky stavObjednavky;
	
	public Objednavka(String kat, String disc, int cislo ) {
		cisloObjednavky = cislo;
		
		if( kat.equals("kajak") )
			kategoria = new Kajak();
		else if( kat.equals("kanoe") )
			kategoria = new Kanoe();
		
		if( disc.equals("slalom") )
			disciplina = new Slalom();
		else if( disc.equals("sprint") )
			disciplina = new Sprint();
		else if( disc.equals("zjazd") )
			disciplina = new Zjazd();
		
		cena = ZistiCenu( kategoria, disciplina );
		stavObjednavky = StavObjednavky.cakaNaVyrobu;
		
		if( !(this instanceof PrednostnaObjednavka) )
			novaObjednavka();
	}
	
	/**
	 * Vytvorenie novej objednávky.
	 */
	public void novaObjednavka(  ) {
		System.out.println("Vytvorena nova objednavka "+ cisloObjednavky+", cena: "+cena);
	}
	
	/**
	 * Výpis aktuálneho stavu objednávky.
	 */
	public void vypisStavObjednavky() {
		System.out.println("Objednavka c."+ cisloObjednavky + ": "+ stavObjednavky );
	}
	
	/**
	 * Nastavenie stavu objednávky. 
	 * @param s		Nastavovaný stav objednávky.
	 */
	public void nastavStavObjednavky( StavObjednavky s ) {
		stavObjednavky = s;
	}
	
	/**
	 * Zistenie stavu objednávky.
	 * @return	Vracia aktuálny stav objednávky - StavObjednavky.
	 */
	public StavObjednavky stavObjednavky() {
		return stavObjednavky;
	}
	
	/**
	 * Zistenie ceny objednávky.
	 * @return	Vracia cenu objednávky - int.
	 */
	public int getCenaObjednavky() {
		return cena;
	}

	/**
	 * Zistenie èísla objednávky.
	 * @return	Vracia èíslo objednávky - int.
	 */
	public int getCisloObjednavky() {
		return cisloObjednavky;
	}
	
	/**
	 * Zistenie kategórie objednávky.
	 * @return	Vracia kategóriu objednávky - Kategoria.
	 */
	public Kategoria getKategoria() {
		return kategoria;
	}
	
	/**
	 * Zistenie disciplíny objednávky.
	 * @return	Vracia desciplínu objednávky - Disciplína.
	 */
	public Disciplina getDisciplina() {
		return disciplina;
	}
	
	/**
	 * Zistenie disciplíny ako String.
	 * @return	Vracia disciplínu ako String.
	 */
	public String getDsciplinaString() {
		if( disciplina instanceof Slalom )
			return "slalom";
		else if( disciplina instanceof Zjazd )
			return "zjazd";
		else if( disciplina instanceof Sprint )
			return "sprint";
		return null;
	}
	
	/**
	 * Zistenie kategórie ako String.
	 * @return	Vracia kategóriu ako String.
	 */
	public String getKategoriaString() {
		if( kategoria instanceof Kanoe )
			return "kanoe";
		else if( kategoria instanceof Kajak )
			return "kajak";
		return null;
	}
	
	/**
	 * Zmení stav objednávky na voVyrobe-lepenie.
	 */
	public void presunNaLepenie() {
		nastavStavObjednavky( StavObjednavky.voVyrobe_lepenie);
	}

	/**
	 * Zmení stav objednávky na voVyrobe-malovanie.
	 */
	public void presunNaMalovanie() {
		nastavStavObjednavky( StavObjednavky.voVyrobe_Malovanie);
	}

	/**
	 * Zmení stav objednávky na voVyrobe-lakovanie.
	 */
	public void presunNaLakovanie() {
		nastavStavObjednavky( StavObjednavky.voVyrobe_Lakovanie);
	}

	/**
	 * Zmení stav objednávky na hotová.
	 */
	public void presunDoSkladu() {
		nastavStavObjednavky( StavObjednavky.hotova );
	}

}
