package store.model.objednavky;


/**
 * Trieda <code>PrednostnaObjednavka</code> roz�iruje triedu {@link Objednavka}. 
 * Reprezentuje prednostn� objedn�vku - pri vytvoren� prednostnej objedn�vky sa
 * v�dy vytvor� nov� in�tancia.
 * 
 * @author Lucia
 *
 */
public class PrednostnaObjednavka extends Objednavka{
	
	public PrednostnaObjednavka(String kat, String disc, int cislo ) {
		super( kat, disc, cislo );
		cena = ZistiCenu( kategoria, disciplina ) + cenaPrednostnej;
		novaObjednavka();
	}

	/**
	 * Prekon�va met�du nadradenej triedy {@link Objednavka}.
	 * V�pis o vytvoren� novej prednostnej objedn�vky.
	 */
	@Override
	public void novaObjednavka( ) {
		System.out.println("Vytvorena nova objednavka "+ cisloObjednavky+", cena: "+cena);
	}
	
	/**
	 * Prekon�va met�du nadradenej triedy {@link Objednavka}.
	 * V�pis aktu�lneho stavu objedn�vky. 
	 */
	@Override
	public void vypisStavObjednavky( ) {
		System.out.println("Prednostna objednavka c."+ cisloObjednavky + ", cena: "+ stavObjednavky );
	}
}
