package store.model.objednavky;


/**
 * Trieda <code>PrednostnaObjednavka</code> rozširuje triedu {@link Objednavka}. 
 * Reprezentuje prednostnú objednávku - pri vytvorení prednostnej objednávky sa
 * vždy vytvorá nová inštancia.
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
	 * Prekonáva metódu nadradenej triedy {@link Objednavka}.
	 * Výpis o vytvorení novej prednostnej objednávky.
	 */
	@Override
	public void novaObjednavka( ) {
		System.out.println("Vytvorena nova objednavka "+ cisloObjednavky+", cena: "+cena);
	}
	
	/**
	 * Prekonáva metódu nadradenej triedy {@link Objednavka}.
	 * Výpis aktuálneho stavu objednávky. 
	 */
	@Override
	public void vypisStavObjednavky( ) {
		System.out.println("Prednostna objednavka c."+ cisloObjednavky + ", cena: "+ stavObjednavky );
	}
}
