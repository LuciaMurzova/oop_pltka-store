package store.model.zamestnanci;

import java.util.ArrayList;
import java.util.List;

import store.model.objednavky.Objednavka;
import store.model.vyroba.Sklad;
import store.observer.Observer;
import store.observer.Subjekt;

/**
 * Trieda <code>Skladnik</code> roz�iruje triedu {@link Pracovnik} a implementuje rozhranie {@link Subjekt}.
 * Skladn�k zodpoved� za presunuetie hotovej objedn�vky do skladu a kontrolu materi�lu v sklade po 
 * dokon�en� ka�dej objedn�vky.
 * 
 * @author Lucia
 *
 */
public class Skladnik extends Pracovnik implements Subjekt{
	
	/**
	 * Zoznam observerov skladn�ka.
	 */
	private List<Observer> observer = null;
	
	public Skladnik(String m, Sklad s, Manazer manazer) {
		super(m, s);
		observer = new ArrayList<Observer>();
		pridajObserver( manazer );
	}

	/**
	 * Prekonan� met�da z triedy {@link Pracovnik}. Skladn�k pres�va hotov� objedn�vky do skladu.
	 * Tie� po ka�dej vyrobenej objedn�vke kontroluje sklad, v pr�pade 
	 * mal�ho po�tu z�sob informuje observerov - mana��ra o potreve dok�pi� materi�l.
	 * 
	 * @param o 	Hotov� objedn�vka, ktor� pres�va do skladu.
	 */
	@Override
	public void urobSvojuPracu(Objednavka o) {
		super.urobSvojuPracu(o);
		System.out.println("Presuvam do skladu.");
		o.presunDoSkladu();
		sklad.pridajObjednavku(o);
		skontrolujSklad();
		volny();
		zrusPriradenieObjednavky();
	}

	/**
	 * Met�da zdeden� z nadradenej triedy {@link Pracovnik}. Mwt�da na z�skanie poz�cie pracovn�ka ako String.
	 * @return 	Vracia String skladn�k.
	 */
	@Override
	public String getPozicia() {
		return "Skladnik";
	}
	
	/**
	 * Kontrola po�tu jednotliv�ho materi�lu v sklade.
	 */
	public void skontrolujSklad() {
		
		int material = sklad.stavMaterialu();
		int farba = sklad.stavFarby();
		int lak = sklad.stavLaku();

		if( material <= 2 )
			informujObserverov("material");
		if( farba <= 2 )
			informujObserverov("farba");
		if( lak <= 2 )
			informujObserverov("lak");
		
	}

	/**
	 * Met�da prekon�va met�du s rozhrania {@link Observer},  
	 * infromuje observerov - Mana��ra o potrebe dok�pi� dan� materi�l.
	 * 
	 * @param s 	Materi�l - String, ktor� treba dok�pi�.
	 */
	@Override
	public void informujObserverov(String s) {
		System.out.println("Treba dokupit "+s);
		for( Observer o : observer )
			o.update( s );
	}

	/**
	 * Prekon�van� met�da z rozhranie {@link Observer}, pre pridanie nov�ho observera do zoznamu observerov.
	 * @param o 	Observer, ktor� prid� do zoznamu.
	 */
	@Override
	public void pridajObserver(Observer o) {
		observer.add( o );
	}

	/**
	 * Prekon�van� met�da z rozhranie {@link Observer}, pre zmazanie observera z zoznamu observerov.
	 * @param o 	Observer, ktor� ma�e zo zoznamu.
	 */
	@Override
	public void odstranObserver(Observer o) {
		observer.remove( o );
	}
}
