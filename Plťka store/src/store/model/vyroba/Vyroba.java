package store.model.vyroba;

import java.util.*;

import store.model.info.Stav.StavZamestnanca;
import store.model.objednavky.Objednavka;
import store.model.zamestnanci.*;


/**
 * Trieda <code>Vyroba</code> reprezentuje v�robu kajakov/kanoe,
 * do ktorej vstupuj� objekty tried {@link Objednavka} a {@link Zamestnanec}.
 * Pri inicializovan� Vyrobne sa vytvor� in�tancia tejto triedy, s ktorou
 * sa nesk�r pracuje.
 * 
 * @see 	Objednavka
 * @see		Zamestnanec
 * 
 * @author Lucia
 *
 */

public class Vyroba {

	/**
	 * Zoznam objedn�vok �akaj�cich na v�robu.
	 */
	private List<Objednavka> cakajuceObjednavky; 		// cakajuce na vyrobu
	/**
	 * Zoznam objedn�vok, ktor� s� aktu�lne lepen�. 
	 */
	private List<Objednavka> objednavkyNaLepeni; 		// prebieha lepenie
	/**
	 * Zoznam objedn�vok, na ktor�ch prebieha malovanie a lakovanie.
	 */
	private List<Objednavka> objednavkyNaMalovani; 		// prebieha malovanie
	
	/**
	 * Zoznam v�etk�ch lepi�ov vo v�robni.
	 */
	private List<Pracovnik> zoznamLepicov;
	
	/**
	 * Zoznam v�etk�ch maliarov vo v�robni.
	 */
	public List<Pracovnik> zoznamMaliarov;
	
	/**
	 * Zoznam v�etk�ch skladn�kov vo v�robni.
	 */
	public List<Pracovnik> zoznamSkladnikov;
	
	/**
	 * Zoznam v�etk�ch zamestnancov vo v�robni.
	 */
	public List<Zamestnanec> zamestnanci;
	
	
	/**
	 * Inicializovanie V�roby, vytvoria sa zoznamy jednotliv�ch zamestnancov.
	 * 
	 * @param listZamestnancov   Zoznam v�etk�ch zamestnancov V�robne. 
	 */
	public Vyroba( List<Zamestnanec> listZamestnancov ) {
		cakajuceObjednavky = new ArrayList<>();
		objednavkyNaLepeni = new ArrayList<>();
		objednavkyNaMalovani = new ArrayList<>();
		
		zoznamLepicov = new ArrayList<>();
		zoznamMaliarov = new ArrayList<>();
		zoznamSkladnikov = new ArrayList<>();
		
		zamestnanci = listZamestnancov;
		
		for( Zamestnanec z : zamestnanci )
		{
			if( z instanceof Lepic )
				zoznamLepicov.add((Pracovnik) z);
			else if( z instanceof Maliar )
				zoznamMaliarov.add((Pracovnik) z);
			else if( z instanceof Skladnik )
				zoznamSkladnikov.add((Pracovnik) z);
		}
	}
	
	
	/**
	 * Prech�dza zadan� zoznam pracovn�kov a h�ad� pracovn�kov dan�ho typu,
	 * ktor� maj� stav vo�n� - nevenuj� sa aktu�lne �iadnej objedn�vke a m��u sa 
	 * podie�a� na v�robe novej objedn�vky.
	 * 
	 * @param zoznam		Zoznam pracovn�kov z ktor�ho chceme n�js� vo�n�ho (lepi�ov/maliarov/skladn�kov)
	 * @return pracovnik 	Vracia n�jden�ho vo�n�ho pracovn�ka alebo null v pr�pade, �e tak�ho nen�jde.
	 */
	public Pracovnik najdiVolneho( List<? extends Pracovnik> zoznam ) {
		
		for( Pracovnik z : zoznam ) {
			if( z.getStav().compareTo(StavZamestnanca.volny) == 0 )
				return z;
		}
		return null;
	}
	
	/**
	 * Presunie zadan� objedn�vku z jedn�ho zoznamu do druh�ho.
	 * Simuluje presun objedn�vok medzi jednotliv�mi z�stavkami v 
	 * procese v�roby. 
	 * 
	 * @param vymaz			Zoznam, z ktor�ho objedn�vku vyma�e.
	 * @param pridaj		Zoznam, do ktor�ho objedn�vku prid�.
	 * @param o				0bjedn�vka, ktor� pres�va.
	 */
	public void presun( List<Objednavka> vymaz, List<Objednavka> pridaj, Objednavka o ) {
		
		System.out.println("Presuvam "+o.getCisloObjednavky());
		vymaz.remove(o);
		pridaj.add(o);
	}
	
	/**
	 * Simuluje v�robn� proces jednotliv�ch objedn�vok. met�da je volan� pri pridan� novej objedn�vky 
	 * a postupne ju pos�va na lepenie, malovanie s lakovan�m a do skladu.
	 */
	public void zacniVyrobu( ) {
		
		Lepic l;
		if( zoznamLepicov.size() >= objednavkyNaLepeni.size() && ( l = (Lepic) najdiVolneho(zoznamLepicov)) != null &&
				!cakajuceObjednavky.isEmpty() ) 
		{
			Objednavka o = cakajuceObjednavky.get(0);
			System.out.println("Objednavka c."+o.getCisloObjednavky()+"ide na lepenie. Vybavuje: "+l.getMeno());
			presun( cakajuceObjednavky, objednavkyNaLepeni, o ); // presun objednavky do zoznamu pre lepenie
			l.urobSvojuPracu( o );
		}
		
		Maliar m;
		if( zoznamMaliarov.size() >= objednavkyNaMalovani.size() && ( m = (Maliar) najdiVolneho(zoznamMaliarov)) != null && 
				!objednavkyNaLepeni.isEmpty() ) 
		{
			Objednavka o = objednavkyNaLepeni.get(0);
			System.out.println("Objednavka c."+o.getCisloObjednavky()+" ide na malovanie. Vybavuje: "+m.getMeno());
			presun( objednavkyNaLepeni, objednavkyNaMalovani, o );  // presun objednavky do zoznamu pre malovanie
			m.urobSvojuPracu( o );
		}
		
		Skladnik s;
		if( ( s = (Skladnik) najdiVolneho(zoznamSkladnikov)) != null  ) 
		{
			Objednavka o = objednavkyNaMalovani.get(0);
			System.out.println("Objednavka c."+o.getCisloObjednavky()+" ide do skladu. Vybavuje: "+s.getMeno());
			objednavkyNaMalovani.remove(o);
			s.urobSvojuPracu( o );
		}
		
		else
			System.out.println("prebieha vyroba inej lode");
	}

	/**
	 * Pridanie novej objedn�vy do procesu v�roby - pridanie na koniec zoznamu �akaj�cich objedn�vok.
	 * 
	 * @param o		Prid�van� objedn�vka.
	 */
	public void novaObjednavka(Objednavka o) {
		cakajuceObjednavky.add(o);
		zacniVyrobu();
	}

	/**
	 * Priadnie prednostnej objedn�vky do procesu v�roby - pridanie na za�iatok zoznamu �akaj�cich objedn�vok. 
	 * 
	 * @param o		Prid�van� prednostn� objedn�vka.
	 */
	public void novaPrednostnaObjednavka(Objednavka o) {
		cakajuceObjednavky.add(0, o);	// pridanie prednostnej objednavky na zaciatok 
		zacniVyrobu();
	}

}
