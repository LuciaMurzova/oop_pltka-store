package store.model.zamestnanci;

import java.util.Random;

import store.model.Vyrobna;
import store.model.info.CennikMaterialu;
import store.model.vyroba.Sklad;
import store.observer.Observer;

/**
 * Trieda <code>Manazer</code> rožiruje triedu {@link Zamestnanec} a implementuje rozhrania Observer a Cenník Materiálu.
 * Manažér je zodpovedný za nakupovanie potrebného materiálu do skladu. 
 * 
 * @author Lucia
 *
 */
public class Manazer extends Zamestnanec implements Observer, CennikMaterialu {

	/**
	 * Odkaz na Výrobòu.
	 */
	private Vyrobna vyrobna = null;
	
	public Manazer(String m, Sklad s, Vyrobna v) {
		super(m, s);
		vyrobna = v;
	}

	 
	/**
	 * Metóda zdedená z triedy {@link Zamestnanec} na vytvorenie ID.
	 * @return		Vráti ID ako int.
	 */
	@Override
	public int vytvorID(  ) {
		Random rand = new Random();
		String s = "Manazer";
		int id = ( ( this.getMeno().hashCode() + s.hashCode()) % 100000) + rand.nextInt(100);
		return Math.abs(id);
	}

	
	/**
	 * Prekonáva metódu z rozhrania Observer. Subjekt - {@link Skladnik} informuje Manažéra o tom,
	 * ktorí materiál treba dokúpi. Manažér skontroluje rozpoèet a pod¾a toho dokúpi poèet potrebného materiálu. 
	 * 
	 * @param s 	Materiál, ktorý treba dokúpi - ako String.
	 */
	@Override
	public void update(String s) {
		
		if( s.equals("lak") ) {
			for( int i = 3; i > 0; i++ ) {
				if( vyrobna.getRozpocet() - (i * ZistiCenu("lak")) >= 0 ) {
					kupLak(i);
					return;
				}
			}
		}
		
		else if( s.equals("farba")) {
			for( int i = 3; i > 0; i++ ) {
				if( vyrobna.getRozpocet() - (i * ZistiCenu("farba")) >= 0 ) {
					kupFarbu(i);
					return;
				}
			}
		}
		
		else if( s.equals("material")) {
			for( int i = 3; i > 0; i++ ) {
				if( vyrobna.getRozpocet() - (i * ZistiCenu("material")) >= 0 ) {
					kupMaterial(i);
					return;
				}
			}
		}
	}

	/**
	 * Metóda odpoèíta z rozpoètu cenu za daný poèet lakov a pridá lak do skladu.
	 * @param pocet		Poèet, ktorý manažér kupuje.
	 */
	public void kupLak( int pocet ) {
		vyrobna.setRozpocet( -( pocet * ZistiCenu("lak")) );
		sklad.setLak( pocet );
	}
	
	/**
	 * Metóda odpoèíta z rozpoètu cenu za daný poèet farieb a pridá farby do skladu.
	 * @param pocet		Poèet, ktorý manažér kupuje.
	 */
	public void kupFarbu( int pocet ) {
		vyrobna.setRozpocet( -( pocet * ZistiCenu("farba")) );
		sklad.setLak( pocet );
	}
	
	/**
	 * Metóda odpoèíta z rozpoètu cenu za daný poèet materiálu a pridá materiál do skladu.
	 * @param pocet		Poèet, ktorý manažér kupuje.
	 */
	public void kupMaterial( int pocet ) {
		vyrobna.setRozpocet( -( pocet * ZistiCenu("material")) );
		sklad.setLak( pocet );
	}

}
