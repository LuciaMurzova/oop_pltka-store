package store.model.objednavky;

import store.model.info.*;
import store.model.lode.*;


/**
 * Met�da <code>Objednavka</code> implementuje rozhrania Cenn�k Lod� a Stav 
 * a roz�iruje ju trieds {@link PrednostnaObjednavka}. 
 * Reprezentuje objedn�vku vytvoren� z�kazn�kom - pri zadan� novej objedn�vky sa vytv�ra
 * nov� in�tancia triedy. Objedn�vka m� svoje objekty tried {@link Kategoria} a {@link Disciplina}.
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
	 * ��slo objedn�vky - poradie v zozname v�etk�ch objedn�vok.
	 */
	protected int cisloObjednavky;
	/**
	 * Cena objedn�vky - z�skan� z rozhrania Cenn�k Lod�, pod�a kateg�rie a discipl�ny danej objedn�vky.
	 */
	protected int cena;
	
	/**
	 * Typ kateg�rie objedn�vky. kanoe/kajak
	 */
	protected Kategoria kategoria;
	/**
	 * Typ discipl�ny objedn�vky. slalom/zjazd/�print
	 */
	protected Disciplina disciplina;
	
	/**
	 * Reprezentuje stav objedn�vky. �ak� na v�robu/na ma�ovan�/hotov� - na sklade
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
	 * Vytvorenie novej objedn�vky.
	 */
	public void novaObjednavka(  ) {
		System.out.println("Vytvorena nova objednavka "+ cisloObjednavky+", cena: "+cena);
	}
	
	/**
	 * V�pis aktu�lneho stavu objedn�vky.
	 */
	public void vypisStavObjednavky() {
		System.out.println("Objednavka c."+ cisloObjednavky + ": "+ stavObjednavky );
	}
	
	/**
	 * Nastavenie stavu objedn�vky. 
	 * @param s		Nastavovan� stav objedn�vky.
	 */
	public void nastavStavObjednavky( StavObjednavky s ) {
		stavObjednavky = s;
	}
	
	/**
	 * Zistenie stavu objedn�vky.
	 * @return	Vracia aktu�lny stav objedn�vky - StavObjednavky.
	 */
	public StavObjednavky stavObjednavky() {
		return stavObjednavky;
	}
	
	/**
	 * Zistenie ceny objedn�vky.
	 * @return	Vracia cenu objedn�vky - int.
	 */
	public int getCenaObjednavky() {
		return cena;
	}

	/**
	 * Zistenie ��sla objedn�vky.
	 * @return	Vracia ��slo objedn�vky - int.
	 */
	public int getCisloObjednavky() {
		return cisloObjednavky;
	}
	
	/**
	 * Zistenie kateg�rie objedn�vky.
	 * @return	Vracia kateg�riu objedn�vky - Kategoria.
	 */
	public Kategoria getKategoria() {
		return kategoria;
	}
	
	/**
	 * Zistenie discipl�ny objedn�vky.
	 * @return	Vracia descipl�nu objedn�vky - Discipl�na.
	 */
	public Disciplina getDisciplina() {
		return disciplina;
	}
	
	/**
	 * Zistenie discipl�ny ako String.
	 * @return	Vracia discipl�nu ako String.
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
	 * Zistenie kateg�rie ako String.
	 * @return	Vracia kateg�riu ako String.
	 */
	public String getKategoriaString() {
		if( kategoria instanceof Kanoe )
			return "kanoe";
		else if( kategoria instanceof Kajak )
			return "kajak";
		return null;
	}
	
	/**
	 * Zmen� stav objedn�vky na voVyrobe-lepenie.
	 */
	public void presunNaLepenie() {
		nastavStavObjednavky( StavObjednavky.voVyrobe_lepenie);
	}

	/**
	 * Zmen� stav objedn�vky na voVyrobe-malovanie.
	 */
	public void presunNaMalovanie() {
		nastavStavObjednavky( StavObjednavky.voVyrobe_Malovanie);
	}

	/**
	 * Zmen� stav objedn�vky na voVyrobe-lakovanie.
	 */
	public void presunNaLakovanie() {
		nastavStavObjednavky( StavObjednavky.voVyrobe_Lakovanie);
	}

	/**
	 * Zmen� stav objedn�vky na hotov�.
	 */
	public void presunDoSkladu() {
		nastavStavObjednavky( StavObjednavky.hotova );
	}

}
