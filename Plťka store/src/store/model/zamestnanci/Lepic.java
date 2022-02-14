package store.model.zamestnanci;

import store.model.lode.*;
import store.model.objednavky.Objednavka;
import store.model.vyroba.Sklad;

/**
 * Trieda <code>Lepic</code> roz�iruje triedu {@link Pracovnik}.
 * Lepi� je zodpovedn� za zlepenie novej objedn�vky.
 * 
 * @author Lucia
 */

public class Lepic extends Pracovnik{
	
	public Lepic( String m, Sklad s) {
		super(m, s);
	}

	/**
	 * Pod�a kateg�rie a discipl�ny danej objedn�vky zist� mno�stvo materi�lu potrebn� na zlepenie
	 * danej objedn�vky a n�sledne materi�l od��ta zo skladu. 
	 * @param o		Objedn�vka na zlepenie.
	 */
	public void vytvorLod( Objednavka o ) {
		Kategoria kat = o.getKategoria();
		Disciplina disc = o.getDisciplina();
		
		sklad.getMaterial( kat.vyrob(disc) ); // model visitor 	
	}
	
	/**
	 * Prekonan� met�da z nadradenej triedy {@link Pracovnik}. Zavol� sa met�da nadradenej triedy {@link Pracovnik}, 
	 * n�sledne zmen� stav objedn�vky na lepen�, zlep� dan� lo� a po ukon�en� si pracovn�k zmen� svoj stav na vo�n� a 
	 * zru�� si priradenie danej objedn�vky. 
	 * @param o		Zadan� objedn�vka.
	 */
	@Override
	public void urobSvojuPracu(Objednavka o) {
		super.urobSvojuPracu(o);
		o.presunNaLepenie();	// stav objednavky 	
		vytvorLod(o);
		volny();				// stav azmestnanca - dokoncil pracu, moze prijat dalsiu 
		zrusPriradenieObjednavky();
	}
	
	/**
	 * Met�da zdeden� z nadradenej triedy {@link Pracovnik}. Mwt�da na z�skanie poz�cie pracovn�ka ako String.
	 * @return Vracia String Lepic.
	 */
	@Override
	public String getPozicia() {
		return "Lepic";
	}
}
