package store.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import store.model.objednavky.*;
import store.model.vynimky.ChybneUdaje;
import store.model.vyroba.*;
import store.model.zamestnanci.*;

/**
 * Trieda <code>V�rob�a</code> reprezentuje hlavn� model projektu. 
 * Pri spusten� aplik�cie sa vytvor� jej in�tancia, ktor� vytvor� zoznamy 
 * a iter�tory objektov tried {@link Zamestnanec}, {@link Objednavka}
 * a vytvor� aj in�tancie objektov tried {@link Sklad} a {@link Vyroba},
 * s ktor�mi nesk�r pracuje. 
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
	 * Zoznam v�etk�ch zamestnancov V�robne.
	 */
	private List<Zamestnanec> listZamestnancov = null;
	/**
	 * Zoznam v�etk�ch obejdn�vok V�robne.
	 */
	private List<Objednavka> listObjednavok = null;
	
	/**
	 * Iter�tor objedn�vok.
	 */
	private Iterator<Objednavka> iterObjednavok;
	/**
	 * Iter�tor zamestnancov.
	 */
	private Iterator<Zamestnanec> iterZamestnancov;
	
	/**
	 * Rozpo�et V�robne, na za�iatku inicializovan� na sumu 5 000. Nesk�r sa k nemu 
	 * pripo��tavaj� platby za objedn�vky a je mo�n� za� dok�pi� potrebn� materi�l. 
	 */
	private int rozpocet;
	
	/**
	 * Odkaz na V�robu, kde prebieha v�roba jednotliv�ch objedn�vok. 
	 */
	private Vyroba vyroba;
	/**
	 * Odkaz na Sklad s materi�lom potrebn�m na v�robu. 
	 */
	private Sklad sklad;
	
	/**
	 * V�rob�a je Singleton, kontrola existencie jej jedinej in�tancie.
	 */
	private static Vyrobna instancia = null;
	
	/**
	 * Priv�tny kontru�ktor pre Singleton.
	 */
	private Vyrobna() {
		
	}
	
	/**
	 * Met�da pre vytvorenie novej V�robne - pri spusten� programu, alebo z�skanie odkazu 
	 * na u� existuj�cu V�rob�u. 
	 * 
	 * @return		Pri inicializ�cii vr�ti nov� v�rob�u, inak vr�ti odkaz na u� existuj�cu.
	 */
	public static Vyrobna getVyrobna() {
		if( instancia == null ) {
			instancia = new Vyrobna();
			instancia.inicializuj();
		}
			
		return instancia;
	}
	
	/**
	 * Met�da pre inicializovanie novej V�robne. Vytvor� zoznam zamestnancov, zoznam objedn�vok, 
	 * vytvor� nov�ch zamestnancov, nov� {@link Sklad} a {@link Vyroba} a nastav� rozpo�et V�robne.
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
	 * Met�da vytvor� nov�ho Mana��ra a Pracovn�kov.
	 * 
	 * @see Manazer
	 * @see Pracovnik
	 * 
	 * @param sklad 	Odkaz na vytvoren� {@link Sklad} aby s n�m mohli jednotliv� zamestnanci pracova�.
	 */
	private void pridajZamestnancov(Sklad sklad) {
		Manazer m =  new Manazer("Manazer", sklad, this);
		listZamestnancov.add(m);
		listZamestnancov.add( new Maliar("Viliam", sklad));
		listZamestnancov.add( new Lepic("Milan", sklad));
		listZamestnancov.add( new Skladnik("Jozef", sklad, m));
	}

	/**
	 * Vyp�e v�etky doteraz vytvoren� objedn�vky.
	 */
	public void vypisVsetkyObjednavky() {
		iterObjednavok = listObjednavok.iterator();
		while(  iterObjednavok.hasNext() ) {
			Objednavka o = iterObjednavok.next();
			System.out.println( o.getCisloObjednavky()+ " "+o.stavObjednavky() );
		}	
	}

	/**
	 * Met�da kontroluje spr�vnos� zadan�ch �dajov, v pr�pade nespr�vneho vstupu vyhod� 
	 * vlastn� v�nimku. Inak prid� objedn�vku do zoznamu objedn�vok, pripo��ta do rozpo�tu 
	 * sumu objedn�vky a prejde na vytvorenie zadanej objedn�vky na z�klade jednotliv�ch
	 * �dajov. 
	 * 
	 * @param kat				Typ kateg�rie - kajak / kanoe.
	 * @param disc				Typ discipl�ny - slalom / zjazd / �print.
	 * @param prednostna		Typ objedn�vky - prednostn� - �no / nie.
	 * @throws ChybneUdaje		V�nimka pre zle zadan� �daje pri vytv�ran� objedn�vky.
	 */
	public void novaObjednavka( String kat, String disc, String prednostna ) throws ChybneUdaje {
		
		// kotrola spravnych udajov 
		if( !(disc.equals("slalom") || disc.equals("sprint") || disc.equals("zjazd")) )
			throw new ChybneUdaje("Pri v�bere discipl�ny ste zadali nespr�vne �daje.");
		if( !(kat.equals("kajak") || kat.equals("kanoe")))
			throw new ChybneUdaje("Pri v�bere kateg�rie ste zadali nespr�vne �daje.");
		
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

		throw new ChybneUdaje("Pri vytv�ran� objedn�vky ste zadali nespr�vne �daje.");
		
	}
	
	/**
	 * @return	Vracia po�et v�etk�ch vytvoren�ch objedn�vok.
	 */
	public int pocetObjednavok() {
		return listObjednavok.size();
	}
	
	/**
	 * @return	Vracia aktu�lny rozpo�et V�robne.
	 */
	public int getRozpocet() {
		return rozpocet;
	}
	
	/**
	 * Nastavenie rozpo�tu V�robne.
	 * @param i		Suma, ktor� pripo��ta do rozpo�tu. V pr�pade napr. n�kupu materi�lu sa posiela z�porn� suma.
	 */
	public void setRozpocet( int i ) {
		rozpocet += i;
	}

	/**
	 * Kontroluje spr�vnos� zadan�ch �dajov pri prihlasovan� zamestnancov do syst�mu. 
	 * 
	 * @param meno		Meno prihlasovan�ho zamestnanca.
	 * @param ID		ID zamestnanca, ktor� je mu pridelen� po jeho pridan�. 
	 * @return			Pri zadan� spr�vnych �dajov vracia true, inak false. 
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
