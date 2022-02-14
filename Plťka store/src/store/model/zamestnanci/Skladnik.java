package store.model.zamestnanci;

import java.util.ArrayList;
import java.util.List;

import store.model.objednavky.Objednavka;
import store.model.vyroba.Sklad;
import store.observer.Observer;
import store.observer.Subjekt;

/**
 * Trieda <code>Skladnik</code> rozširuje triedu {@link Pracovnik} a implementuje rozhranie {@link Subjekt}.
 * Skladník zodpovedá za presunuetie hotovej objednávky do skladu a kontrolu materiálu v sklade po 
 * dokonèení kadej objednávky.
 * 
 * @author Lucia
 *
 */
public class Skladnik extends Pracovnik implements Subjekt{
	
	/**
	 * Zoznam observerov skladníka.
	 */
	private List<Observer> observer = null;
	
	public Skladnik(String m, Sklad s, Manazer manazer) {
		super(m, s);
		observer = new ArrayList<Observer>();
		pridajObserver( manazer );
	}

	/**
	 * Prekonaná metóda z triedy {@link Pracovnik}. Skladník presúva hotové objednávky do skladu.
	 * Tie po kadej vyrobenej objednávke kontroluje sklad, v prípade 
	 * malého poètu zásob informuje observerov - manaéra o potreve dokúpi materiál.
	 * 
	 * @param o 	Hotová objednávka, ktorú presúva do skladu.
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
	 * Metóda zdedená z nadradenej triedy {@link Pracovnik}. Mwtóda na získanie pozície pracovníka ako String.
	 * @return 	Vracia String skladník.
	 */
	@Override
	public String getPozicia() {
		return "Skladnik";
	}
	
	/**
	 * Kontrola poètu jednotlivého materiálu v sklade.
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
	 * Metóda prekonáva metódu s rozhrania {@link Observer},  
	 * infromuje observerov - Manaéra o potrebe dokúpi danı materiál.
	 * 
	 * @param s 	Materiál - String, ktorı treba dokúpi.
	 */
	@Override
	public void informujObserverov(String s) {
		System.out.println("Treba dokupit "+s);
		for( Observer o : observer )
			o.update( s );
	}

	/**
	 * Prekonávaná metóda z rozhranie {@link Observer}, pre pridanie nového observera do zoznamu observerov.
	 * @param o 	Observer, ktorı pridá do zoznamu.
	 */
	@Override
	public void pridajObserver(Observer o) {
		observer.add( o );
	}

	/**
	 * Prekonávaná metóda z rozhranie {@link Observer}, pre zmazanie observera z zoznamu observerov.
	 * @param o 	Observer, ktorı mae zo zoznamu.
	 */
	@Override
	public void odstranObserver(Observer o) {
		observer.remove( o );
	}
}
