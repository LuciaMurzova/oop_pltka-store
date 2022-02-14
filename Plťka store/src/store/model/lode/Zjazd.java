package store.model.lode;

/**
 * Trieda <code>Zjazd</code> rozširuje triedu {@link Disciplina} a implementuje vzhniezdené 
 * rozhranie Disciplina.CasProcesov. 
 * 
 * @author Lucia
 *
 */
public class Zjazd extends Disciplina implements Disciplina.CasProcesov{

	/**
	 * Množstvo materiálu potrebné pre zlepenie lode danej triedy.
	 */
	private static int material = 2;
	/**
	 * Množstvo farby potrebné pre nama¾ovanie lode danej triedy.
	 */
	private static int farba = 2;
	/**
	 * Množstvo laku potrebné pre nalakovanie lode danej triedy.
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
