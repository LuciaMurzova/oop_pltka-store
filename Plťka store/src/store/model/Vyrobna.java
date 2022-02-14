package store.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import store.model.objednavky.*;
import store.model.vynimky.ChybneUdaje;
import store.model.vyroba.*;
import store.model.zamestnanci.*;

/**
 * Trieda <code>Vırobòa</code> reprezentuje hlavnı model projektu. 
 * Pri spustení aplikácie sa vytvorí jej inštancia, ktorá vytvorí zoznamy 
 * a iterátory objektov tried {@link Zamestnanec}, {@link Objednavka}
 * a vytvorí aj inštancie objektov tried {@link Sklad} a {@link Vyroba},
 * s ktorımi neskôr pracuje. 
 * 
 * @see 	Zamestnanec
 * @see 	Objednavka
 * @see 	Sklad
 * @see 	Vyroba
 * 
 * @author Lucia
 *
 */

public class Vyrobna {
	/**
	 * Zoznam všetkıch zamestnancov Vırobne.
	 */
	private List<Zamestnanec> listZamestnancov = null;
	/**
	 * Zoznam všetkıch obejdnávok Vırobne.
	 */
	private List<Objednavka> listObjednavok = null;
	
	/**
	 * Iterátor objednávok.
	 */
	private Iterator<Objednavka> iterObjednavok;
	/**
	 * Iterátor zamestnancov.
	 */
	private Iterator<Zamestnanec> iterZamestnancov;
	
	/**
	 * Rozpoèet Vırobne, na zaèiatku inicializovanı na sumu 5 000. Neskôr sa k nemu 
	 * pripoèítavajú platby za objednávky a je moné zaò dokúpi potrebnı materiál. 
	 */
	private int rozpocet;
	
	/**
	 * Odkaz na Vırobu, kde prebieha vıroba jednotlivıch objednávok. 
	 */
	private Vyroba vyroba;
	/**
	 * Odkaz na Sklad s materiálom potrebnım na vırobu. 
	 */
	private Sklad sklad;
	
	/**
	 * Vırobòa je Singleton, kontrola existencie jej jedinej inštancie.
	 */
	private static Vyrobna instancia = null;
	
	/**
	 * Privátny kontrušktor pre Singleton.
	 */
	private Vyrobna() {
		
	}
	
	/**
	 * Metóda pre vytvorenie novej Vırobne - pri spustení programu, alebo získanie odkazu 
	 * na u existujúcu Vırobòu. 
	 * 
	 * @return		Pri inicializácii vráti novú vırobòu, inak vráti odkaz na u existujúcu.
	 */
	public static Vyrobna getVyrobna() {
		if( instancia == null ) {
			instancia = new Vyrobna();
			instancia.inicializuj();
		}
			
		return instancia;
	}
	
	/**
	 * Metóda pre inicializovanie novej Vırobne. Vytvorí zoznam zamestnancov, zoznam objednávok, 
	 * vytvorí novıch zamestnancov, novı {@link Sklad} a {@link Vyroba} a nastaví rozpoèet Vırobne.
	 */
	public void inicializuj() {
		
		listZamestnancov = new ArrayList<Zamestnanec>();
		listObjednavok = new ArrayList<Objednavka>();
		
		sklad = new Sklad();
	
		pridajZamestnancov(sklad);
		
		vyroba = new Vyroba( listZamestnancov );
		
		setRozpocet(5000);	
	}

	/**
	 * Metóda vytvorí nového Manaéra a Pracovníkov.
	 * 
	 * @see Manazer
	 * @see Pracovnik
	 * 
	 * @param sklad 	Odkaz na vytvorenı {@link Sklad} aby s ním mohli jednotlivı zamestnanci pracova.
	 */
	private void pridajZamestnancov(Sklad sklad) {
		Manazer m =  new Manazer("Manazer", sklad, this);
		listZamestnancov.add(m);
		listZamestnancov.add( new Maliar("Viliam", sklad));
		listZamestnancov.add( new Lepic("Milan", sklad));
		listZamestnancov.add( new Skladnik("Jozef", sklad, m));
	}

	/**
	 * Vypíše všetky doteraz vytvorené objednávky.
	 */
	public void vypisVsetkyObjednavky() {
		iterObjednavok = listObjednavok.iterator();
		while(  iterObjednavok.hasNext() ) {
			Objednavka o = iterObjednavok.next();
			System.out.println( o.getCisloObjednavky()+ " "+o.stavObjednavky() );
		}	
	}

	/**
	 * Metóda kontroluje správnos zadanıch údajov, v prípade nesprávneho vstupu vyhodí 
	 * vlastnú vınimku. Inak pridá objednávku do zoznamu objednávok, pripoèíta do rozpoètu 
	 * sumu objednávky a prejde na vytvorenie zadanej objednávky na základe jednotlivıch
	 * údajov. 
	 * 
	 * @param kat				Typ kategórie - kajak / kanoe.
	 * @param disc				Typ disciplíny - slalom / zjazd / šprint.
	 * @param prednostna		Typ objednávky - prednostná - áno / nie.
	 * @throws ChybneUdaje		Vınimka pre zle zadané údaje pri vytváraní objednávky.
	 */
	public void novaObjednavka( String kat, String disc, String prednostna ) throws ChybneUdaje {
		
		// kotrola spravnych udajov 
		if( !(disc.equals("slalom") || disc.equals("sprint") || disc.equals("zjazd")) )
			throw new ChybneUdaje("Pri vıbere disciplíny ste zadali nesprávne údaje.");
		if( !(kat.equals("kajak") || kat.equals("kanoe")))
			throw new ChybneUdaje("Pri vıbere kategórie ste zadali nesprávne údaje.");
		
		if( prednostna.equals("nie")) 
		{
			Objednavka o = new Objednavka( kat, disc, listObjednavok.size()+1 );
			listObjednavok.add( o );
			setRozpocet(o.getCenaObjednavky());
			vyroba.novaObjednavka( o );	
			return;
		}
				
		// prednostne objednavky idu na zaciatok pola objednavok vo vyrobe 
		else if( prednostna.equals("ano") ) 
		{
			Objednavka o = new PrednostnaObjednavka( kat, disc, listObjednavok.size()+1 );
			listObjednavok.add( o );
			setRozpocet(o.getCenaObjednavky());
			vyroba.novaPrednostnaObjednavka( o );
			return;
		}

		throw new ChybneUdaje("Pri vytváraní objednávky ste zadali nesprávne údaje.");
		
	}
	
	/**
	 * @return	Vracia poèet všetkıch vytvorenıch objednávok.
	 */
	public int pocetObjednavok() {
		return listObjednavok.size();
	}
	
	/**
	 * @return	Vracia aktuálny rozpoèet Vırobne.
	 */
	public int getRozpocet() {
		return rozpocet;
	}
	
	/**
	 * Nastavenie rozpoètu Vırobne.
	 * @param i		Suma, ktorú pripoèíta do rozpoètu. V prípade napr. núkupu materiálu sa posiela záporná suma.
	 */
	public void setRozpocet( int i ) {
		rozpocet += i;
	}

	/**
	 * Kontroluje správnos zadanıch údajov pri prihlasovaní zamestnancov do systému. 
	 * 
	 * @param meno		Meno prihlasovaného zamestnanca.
	 * @param ID		ID zamestnanca, ktoré je mu pridelené po jeho pridaní. 
	 * @return			Pri zadaní správnych údajov vracia true, inak false. 
	 */
	public boolean skontrolujUdaje( String meno, int ID ) {
		
		iterZamestnancov = listZamestnancov.iterator();

		while( iterZamestnancov.hasNext() ) {
			Zamestnanec z = iterZamestnancov.next();

			if( z.getMeno().equals(meno) && z.getID() == ID )
				return true;
		}
		return false;
	}
	
	
	public ArrayList<Objednavka> getListObjednavok() {
		return (ArrayList<Objednavka>) listObjednavok;
	}
	
}
