package store.model.zamestnanci;

import store.model.vyroba.Sklad;


/**
 * Abstraktn� trieda <code>Zamestnanec</code>, ktor� roz�iruje abstraktn� trieda {@link Pracovnik} a trieda {@link Manazer}.
 * 
 * @author Lucia
 *
 */
public abstract class Zamestnanec{

	/**
	 * Meno zamestnanca.
	 */
	private String meno;
	/**
	 * ID zamestnanca - vytvorene hashom na z�klade jeho mena a poz�cie.
	 */
	private int ID;
	/**
	 * Odkaz na sklad - pracovn�ci �erpaj� materi�l na v�robu objedn�vok,
	 * mana��r dop��a potrebn� materi�l. 
	 */
	protected Sklad sklad;
	
	/**
	 * Kon�truktor nastav� meno zamestnanca, vytvor� ID a odkaz na sklad . 
	 * @param m		Meno zamestnanca.
	 * @param s		Odkaz na sklad.
	 */
	public Zamestnanec( String m, Sklad s ) {
		meno = m;
		sklad = s;
		ID = vytvorID( );
		System.out.println("Novy: "+ meno + " " + ID);
	}
	
	/**
	 * Met�da na vytvorenie ID,prekon�vaj� ju podriaden� triedy {@link Pracovnik} a {@link Zamestnanec}.
	 * 
	 * @return		Vr�ti ID ako int.
	 */
	public abstract int vytvorID( );

	/**
	 * @return Vr�ti meno zamestnanca ako String.
	 */
	public String getMeno() {
		return meno;
	}
	
	/**
	 * @return Vr�ti ID zamestnanca ako int.
	 */
	public int getID() {
		return ID;
	}
	
}
