package store.model.zamestnanci;

import java.util.Random;

import store.model.info.Stav;
import store.model.objednavky.Objednavka;
import store.model.vyroba.Sklad;

/**
 * Abstraktn� trieda <code>Pracovn�k</code> roz�iruje triedu {@link Zamestnanec} a 
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
	 * Ur�uje, �i je zamestnanec vo�n� a m��e prevzia� v�robu novej objedn�vky, alebo je zanepr�zden� 
	 * a venuje sa v�robe predo�lej objedn�vky.
	 */
	private StavZamestnanca stavZamestnanca;
	/**
	 * Odkaz na objedn�vku, na ktorej zamestnanec aktu�lne pracuje.
	 */
	private Objednavka objednavka;
	
	/**
	 * Kon�truktor zavol� super kon�truktor nadradenej triedy {@link Zamestnanec} s menom a skladom.
	 * Tie� nastav� stav zamestnanca na vo�n� a odkaz na priraden� objedn�vku nastav� na null. 
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
	 * Met�da na vytvorenie ID pod�a hashu mena a poz�cie pracovn�ka.
	 * 
	 * @return		Vr�ti ID ako int.
	 */	
	public int vytvorID() {
		Random rand = new Random();
		String s = ((Pracovnik)this).getPozicia();
		int id = ( (this.getMeno().hashCode() + s.hashCode() ) % 10000) + rand.nextInt(100);
		return Math.abs(id);
	}
	
	/**
	 * Met�da, ktor� prekon�vaj� podraden� triedy {@link Lepic}, {@link Maliar} a {@link Skladnik}. 
	 * Priad� pracovn�kovi objedn�vku a nastav� jeho stav na zanepr�zdnen�. 
	 * 
	 * @param o		Objedn�vka, ktor� prira�uje pracovn�kovi. 
	 */
	public void urobSvojuPracu( Objednavka o ) {
		setObjednavka(o);
		zaneprazdneny();
	}
	
	/**
	 * Nastav� stav pracovn�ka na zanepr�zdnen�.
	 */
	public void zaneprazdneny() {
		setStav( StavZamestnanca.zaneprazdneny );
	}
	
	/**
	 * Nastav� stav pracovn�ka na vo�n�.
	 */
	public void volny() {
		setStav( StavZamestnanca.volny );
	}

	/**
	 * Prirad� pracovn�kovy zadan� objedn�vku. 
	 * 
	 * @param o		Objedn�vka, ktor� prirad� pracovn�kovi. 
	 */
	public void priradObjednavku( Objednavka o ) {
		setObjednavka(o);
	}
	
	/**
	 * Nastav� pracovn�kovi priraden� objedn�vku na null. 
	 */
	public void zrusPriradenieObjednavky() {
		setObjednavka(null);
	}
	
	/**
	 * Abstraktn� met�da, ktor� prekon�vaj� podraden� triedy {@link Lepic}, {@link Maliar} a {@link Skladnik}.
	 * @return	Vracia poz�ciu dan�ho pracovn�ka ako String.
	 */
	protected abstract String getPozicia();
	
	/**
	 * Nastav� pracovn�kovi po�adovan� stav.
	 * @param s		Stav, ktor� nastav� pracovn�kovi.
	 */
	public void setStav( StavZamestnanca s ) {
		stavZamestnanca = s;
	}
	
	/** 
	 * @return		Vr�ti aktu�lny stav pracovn�ka.
	 */
	public StavZamestnanca getStav() {
		return stavZamestnanca;
	}
	
	/**
	 * @return		Vr�ti objedn�vku, ktor� m� pracovn�k aktu�lne pridelen�.
	 */
	public Objednavka getObjednavka() {
		return objednavka;
	}

	/**
	 * Priradenie danej objedn�vky pracovn�kovi. 
	 * @param o		Objedn�vka, ktor� prira�uje pracovn�kovi.
	 */
	public void setObjednavka( Objednavka o ) {
		objednavka = o;
	}

}
