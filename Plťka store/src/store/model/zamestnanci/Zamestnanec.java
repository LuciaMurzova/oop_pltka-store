package store.model.zamestnanci;

import store.model.vyroba.Sklad;


/**
 * Abstraktná trieda <code>Zamestnanec</code>, ktorú rozširuje abstraktná trieda {@link Pracovnik} a trieda {@link Manazer}.
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
	 * ID zamestnanca - vytvorene hashom na základe jeho mena a pozície.
	 */
	private int ID;
	/**
	 * Odkaz na sklad - pracovníci èerpajú materiál na výrobu objednávok,
	 * manažér dopåòa potrebný materiál. 
	 */
	protected Sklad sklad;
	
	/**
	 * Konštruktor nastaví meno zamestnanca, vytvorí ID a odkaz na sklad . 
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
	 * Metóda na vytvorenie ID,prekonávajú ju podriadené triedy {@link Pracovnik} a {@link Zamestnanec}.
	 * 
	 * @return		Vráti ID ako int.
	 */
	public abstract int vytvorID( );

	/**
	 * @return Vráti meno zamestnanca ako String.
	 */
	public String getMeno() {
		return meno;
	}
	
	/**
	 * @return Vráti ID zamestnanca ako int.
	 */
	public int getID() {
		return ID;
	}
	
}
