package store.model.zamestnanci;

import java.util.Random;

import store.model.info.Stav;
import store.model.objednavky.Objednavka;
import store.model.vyroba.Sklad;

/**
 * Abstraktná trieda <code>Pracovník</code> rozširuje triedu {@link Zamestnanec} a 
 * dedia z nej triedy {@link Lepic}, {@link Maliar} a {@link Skladnik}.
 * 
 * @see		Zamestnanec
 * @see		Lepic
 * @see		Maliar
 * @see		Skladnik
 * 
 * @author Lucia
 *
 */

public abstract class Pracovnik extends Zamestnanec implements Stav{

	/**
	 * Urèuje, èi je zamestnanec vo¾nı a môe prevzia vırobu novej objednávky, alebo je zaneprázdenı 
	 * a venuje sa vırobe predošlej objednávky.
	 */
	private StavZamestnanca stavZamestnanca;
	/**
	 * Odkaz na objednávku, na ktorej zamestnanec aktuálne pracuje.
	 */
	private Objednavka objednavka;
	
	/**
	 * Konštruktor zavolá super konštruktor nadradenej triedy {@link Zamestnanec} s menom a skladom.
	 * Tie nastaví stav zamestnanca na vo¾nı a odkaz na priradenú objednávku nastaví na null. 
	 * 
	 * @param m		Meno zamestnanca.
	 * @param s		Odkaz na Sklad.
	 */
	public Pracovnik(String m, Sklad s) {
		super(m, s);
		stavZamestnanca = StavZamestnanca.volny;
		setObjednavka(null);
	}

	/**
	 * Metóda na vytvorenie ID pod¾a hashu mena a pozície pracovníka.
	 * 
	 * @return		Vráti ID ako int.
	 */	
	public int vytvorID() {
		Random rand = new Random();
		String s = ((Pracovnik)this).getPozicia();
		int id = ( (this.getMeno().hashCode() + s.hashCode() ) % 10000) + rand.nextInt(100);
		return Math.abs(id);
	}
	
	/**
	 * Metóda, ktorú prekonávajú podradené triedy {@link Lepic}, {@link Maliar} a {@link Skladnik}. 
	 * Priadí pracovníkovi objednávku a nastaví jeho stav na zaneprázdnenı. 
	 * 
	 * @param o		Objednávka, ktorú priraïuje pracovníkovi. 
	 */
	public void urobSvojuPracu( Objednavka o ) {
		setObjednavka(o);
		zaneprazdneny();
	}
	
	/**
	 * Nastaví stav pracovníka na zaneprázdnenı.
	 */
	public void zaneprazdneny() {
		setStav( StavZamestnanca.zaneprazdneny );
	}
	
	/**
	 * Nastaví stav pracovníka na vo¾nı.
	 */
	public void volny() {
		setStav( StavZamestnanca.volny );
	}

	/**
	 * Priradí pracovníkovy zadanú objednávku. 
	 * 
	 * @param o		Objednávka, ktorú priradí pracovníkovi. 
	 */
	public void priradObjednavku( Objednavka o ) {
		setObjednavka(o);
	}
	
	/**
	 * Nastaví pracovníkovi priradenú objednávku na null. 
	 */
	public void zrusPriradenieObjednavky() {
		setObjednavka(null);
	}
	
	/**
	 * Abstraktná metóda, ktorú prekonávajú podradené triedy {@link Lepic}, {@link Maliar} a {@link Skladnik}.
	 * @return	Vracia pozíciu daného pracovníka ako String.
	 */
	protected abstract String getPozicia();
	
	/**
	 * Nastaví pracovníkovi poadovanı stav.
	 * @param s		Stav, ktorı nastaví pracovníkovi.
	 */
	public void setStav( StavZamestnanca s ) {
		stavZamestnanca = s;
	}
	
	/** 
	 * @return		Vráti aktuálny stav pracovníka.
	 */
	public StavZamestnanca getStav() {
		return stavZamestnanca;
	}
	
	/**
	 * @return		Vráti objednávku, ktorú má pracovník aktuálne pridelenú.
	 */
	public Objednavka getObjednavka() {
		return objednavka;
	}

	/**
	 * Priradenie danej objednávky pracovníkovi. 
	 * @param o		Objednávka, ktorú priraïuje pracovníkovi.
	 */
	public void setObjednavka( Objednavka o ) {
		objednavka = o;
	}

}
