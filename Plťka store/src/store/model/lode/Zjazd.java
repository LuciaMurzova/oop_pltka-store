package store.model.lode;

/**
 * Trieda <code>Zjazd</code> roz�iruje triedu {@link Disciplina} a implementuje vzhniezden� 
 * rozhranie Disciplina.CasProcesov. 
 * 
 * @author Lucia
 *
 */
public class Zjazd extends Disciplina implements Disciplina.CasProcesov{

	/**
	 * Mno�stvo materi�lu potrebn� pre zlepenie lode danej triedy.
	 */
	private static int material = 2;
	/**
	 * Mno�stvo farby potrebn� pre nama�ovanie lode danej triedy.
	 */
	private static int farba = 2;
	/**
	 * Mno�stvo laku potrebn� pre nalakovanie lode danej triedy.
	 */
	private static int lak = 2;
	
	
	@Override
	public int vyrob(Kajak lod) {
		System.out.println("Vytvaram novy zjazdovy kajak.");
		trvanieProcesu( zistiCasVyroby(lod, this) );
		return material;
	}

	@Override
	public int vyrob(Kanoe lod) {
		System.out.println("Vytvaram nove zjazdove kanoe.");
		trvanieProcesu( zistiCasVyroby(lod, this) );
		return material;
	}


	@Override
	public int namaluj(Kajak lod) {
		System.out.println("Malujem");
		trvanieProcesu( zistiCasMalovania(lod, this) );
		return farba;
	}

	@Override
	public int namaluj(Kanoe lod) {
		System.out.println("Malujem");
		trvanieProcesu( zistiCasMalovania(lod, this) );
		return farba;
	}

	@Override
	public int nalakuj(Kajak lod) {
		System.out.println("Lakujem");
		trvanieProcesu( zistiCasLakovania(lod, this) );
		return lak;
	}

	@Override
	public int nalakuj(Kanoe lod) {
		System.out.println("Lakujem");
		trvanieProcesu( zistiCasLakovania(lod, this) );
		return lak;
	}
}
