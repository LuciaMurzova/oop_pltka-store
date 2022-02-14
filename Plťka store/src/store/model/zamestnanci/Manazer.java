package store.model.zamestnanci;

import java.util.Random;

import store.model.Vyrobna;
import store.model.info.CennikMaterialu;
import store.model.vyroba.Sklad;
import store.observer.Observer;

/**
 * Trieda <code>Manazer</code> ro�iruje triedu {@link Zamestnanec} a implementuje rozhrania Observer a Cenn�k Materi�lu.
 * Mana��r je zodpovedn� za nakupovanie potrebn�ho materi�lu do skladu. 
 * 
 * @author Lucia
 *
 */
public class Manazer extends Zamestnanec implements Observer, CennikMaterialu {

	/**
	 * Odkaz na V�rob�u.
	 */
	private Vyrobna vyrobna = null;
	
	public Manazer(String m, Sklad s, Vyrobna v) {
		super(m, s);
		vyrobna = v;
	}

	 
	/**
	 * Met�da zdeden� z triedy {@link Zamestnanec} na vytvorenie ID.
	 * @return		Vr�ti ID ako int.
	 */
	@Override
	public int vytvorID(  ) {
		Random rand = new Random();
		String s = "Manazer";
		int id = ( ( this.getMeno().hashCode() + s.hashCode()) % 100000) + rand.nextInt(100);
		return Math.abs(id);
	}

	
	/**
	 * Prekon�va met�du z rozhrania Observer. Subjekt - {@link Skladnik} informuje Mana��ra o tom,
	 * ktor� materi�l treba dok�pi�. Mana��r skontroluje rozpo�et a pod�a toho dok�pi po�et potrebn�ho materi�lu. 
	 * 
	 * @param s 	Materi�l, ktor� treba dok�pi� - ako String.
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
	 * Met�da odpo��ta z rozpo�tu cenu za dan� po�et lakov a prid� lak do skladu.
	 * @param pocet		Po�et, ktor� mana��r kupuje.
	 */
	public void kupLak( int pocet ) {
		vyrobna.setRozpocet( -( pocet * ZistiCenu("lak")) );
		sklad.setLak( pocet );
	}
	
	/**
	 * Met�da odpo��ta z rozpo�tu cenu za dan� po�et farieb a prid� farby do skladu.
	 * @param pocet		Po�et, ktor� mana��r kupuje.
	 */
	public void kupFarbu( int pocet ) {
		vyrobna.setRozpocet( -( pocet * ZistiCenu("farba")) );
		sklad.setLak( pocet );
	}
	
	/**
	 * Met�da odpo��ta z rozpo�tu cenu za dan� po�et materi�lu a prid� materi�l do skladu.
	 * @param pocet		Po�et, ktor� mana��r kupuje.
	 */
	public void kupMaterial( int pocet ) {
		vyrobna.setRozpocet( -( pocet * ZistiCenu("material")) );
		sklad.setLak( pocet );
	}

}
