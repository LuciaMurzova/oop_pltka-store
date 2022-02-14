package store.model.vyroba;

import java.util.*;

import store.model.info.Stav.StavZamestnanca;
import store.model.objednavky.Objednavka;
import store.model.zamestnanci.*;


/**
 * Trieda <code>Vyroba</code> reprezentuje vırobu kajakov/kanoe,
 * do ktorej vstupujú objekty tried {@link Objednavka} a {@link Zamestnanec}.
 * Pri inicializovaní Vyrobne sa vytvorí inštancia tejto triedy, s ktorou
 * sa neskôr pracuje.
 * 
 * @see 	Objednavka
 * @see		Zamestnanec
 * 
 * @author Lucia
 *
 */

public class Vyroba {

	/**
	 * Zoznam objednávok èakajúcich na vırobu.
	 */
	private List<Objednavka> cakajuceObjednavky; 		// cakajuce na vyrobu
	/**
	 * Zoznam objednávok, ktoré sú aktuálne lepené. 
	 */
	private List<Objednavka> objednavkyNaLepeni; 		// prebieha lepenie
	/**
	 * Zoznam objednávok, na ktorıch prebieha malovanie a lakovanie.
	 */
	private List<Objednavka> objednavkyNaMalovani; 		// prebieha malovanie
	
	/**
	 * Zoznam všetkıch lepièov vo vırobni.
	 */
	private List<Pracovnik> zoznamLepicov;
	
	/**
	 * Zoznam všetkıch maliarov vo vırobni.
	 */
	public List<Pracovnik> zoznamMaliarov;
	
	/**
	 * Zoznam všetkıch skladníkov vo vırobni.
	 */
	public List<Pracovnik> zoznamSkladnikov;
	
	/**
	 * Zoznam všetkıch zamestnancov vo vırobni.
	 */
	public List<Zamestnanec> zamestnanci;
	
	
	/**
	 * Inicializovanie Vıroby, vytvoria sa zoznamy jednotlivıch zamestnancov.
	 * 
	 * @param listZamestnancov   Zoznam všetkıch zamestnancov Vırobne. 
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
	 * Prechádza zadanı zoznam pracovníkov a h¾adá pracovníkov daného typu,
	 * ktorí majú stav vo¾nı - nevenujú sa aktuálne iadnej objednávke a môu sa 
	 * podie¾a na vırobe novej objednávky.
	 * 
	 * @param zoznam		Zoznam pracovníkov z ktorého chceme nájs vo¾ného (lepièov/maliarov/skladníkov)
	 * @return pracovnik 	Vracia nájdeného vo¾ného pracovníka alebo null v prípade, e takého nenájde.
	 */
	public Pracovnik najdiVolneho( List<? extends Pracovnik> zoznam ) {
		
		for( Pracovnik z : zoznam ) {
			if( z.getStav().compareTo(StavZamestnanca.volny) == 0 )
				return z;
		}
		return null;
	}
	
	/**
	 * Presunie zadanú objednávku z jedného zoznamu do druhého.
	 * Simuluje presun objednávok medzi jednotlivımi zástavkami v 
	 * procese vıroby. 
	 * 
	 * @param vymaz			Zoznam, z ktorého objednávku vymae.
	 * @param pridaj		Zoznam, do ktorého objednávku pridá.
	 * @param o				0bjednávka, ktorú presúva.
	 */
	public void presun( List<Objednavka> vymaz, List<Objednavka> pridaj, Objednavka o ) {
		
		System.out.println("Presuvam "+o.getCisloObjednavky());
		vymaz.remove(o);
		pridaj.add(o);
	}
	
	/**
	 * Simuluje vúrobnı proces jednotlivıch objednávok. metóda je volaná pri pridaní novej objednávky 
	 * a postupne ju posúva na lepenie, malovanie s lakovaním a do skladu.
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
	 * Pridanie novej objednávy do procesu vıroby - pridanie na koniec zoznamu èakajúcich objednávok.
	 * 
	 * @param o		Pridávaná objednávka.
	 */
	public void novaObjednavka(Objednavka o) {
		cakajuceObjednavky.add(o);
		zacniVyrobu();
	}

	/**
	 * Priadnie prednostnej objednávky do procesu vıroby - pridanie na zaèiatok zoznamu èakajúcich objednávok. 
	 * 
	 * @param o		Pridávaná prednostná objednávka.
	 */
	public void novaPrednostnaObjednavka(Objednavka o) {
		cakajuceObjednavky.add(0, o);	// pridanie prednostnej objednavky na zaciatok 
		zacniVyrobu();
	}

}
