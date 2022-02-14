package store.model.zamestnanci;

import store.model.lode.*;
import store.model.objednavky.*;
import store.model.vyroba.Sklad;


/**
 * Trieda <code>Maliar</code> roz�iruje triedu {@link Pracovnik}. 
 * Maliar zodpoved� za namalovanie a nalakovanie pr�ve vytv�ranej lode.
 * 
 * @author Lucia
 *
 */
public class Maliar extends Pracovnik {
		
	public Maliar(String m, Sklad s) {
		super(m, s);
	}
	
	/**
	 * Nama�ovanie lode - pod�a kateg�rie a discipl�ny danej objedn�vky zist� po�et farby potrebnej na nama�ovanie
	 * a toto mno�stvo odoberie zo skladu.
	 * 
	 * @param o		Zadan� objedn�vka na nama�ovanie.
	 */
	public void namaluj( Objednavka o ) {

		Kategoria kat = o.getKategoria();
		Disciplina disc = o.getDisciplina();
		
		sklad.getFarba( kat.namaluj(disc) );
	}
	
	/**
	 * Nalakovanie lode - pod�a kateg�rie a discipl�ny danej objedn�vky zist� po�et laku potrebn� na nalakovanie
	 * a toto mno�stvo odoberie zo skladu. 
	 * 
	 * @param o Objednavka na nalakovanie.
	 */
	public void nalakuj( Objednavka o ) {
		
		Kategoria kat = o.getKategoria();
		Disciplina disc = o.getDisciplina();
		
		sklad.getLak( kat.nalakuj(disc) );
	}

	/**
	 * Met�da zdeden� z triedy {@link Pracovnik}. Zavol� sa met�da nadradenej triedy {@link Pracovnik}, 
	 * n�sledne presunie objedn�vku na ma�ovanie a lakovanie. Po dokon�en� svojej pr�ce nastav� pracovn�k 
	 * svoj stav na vo�n� a zru�� si priradenie danej objedn�vky.
	 * 
	 * @param o 	Zadan� objedn�vka na nama�ovanie a nalakovanie.
	 */
	@Override
	public void urobSvojuPracu(Objednavka o) {
		
		super.urobSvojuPracu(o);
		o.presunNaMalovanie();
		namaluj(o);
		o.presunNaLakovanie();
		nalakuj(o);
		volny();
		zrusPriradenieObjednavky();
	}

	/**
	 * Met�da zdeden� z nadradenej triedy {@link Pracovnik}. Mwt�da na z�skanie poz�cie pracovn�ka ako String.
	 * @return Vr�ti String Maliar.
	 */
	@Override
	public String getPozicia() {
		return "Maliar";
	}

}
