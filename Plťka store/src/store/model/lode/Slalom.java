package store.model.lode;

/**
 * Trieda <code>Slalom</code> roz�iruje triedu {@link Disciplina} a implementuje vzhniezden� 
 * rozhranie Disciplina.CasProcesov. 
 * 
 * @author Lucia
 *
 */
public class Slalom extends Disciplina implements Disciplina.CasProcesov {

	/**
	 * Mno�stvo materi�lu potrebn� pre zlepenie lode danej triedy.
	 */
	private static int material = 1;
	/**
	 * Mno�stvo farby potrebn� pre nama�ovanie lode danej triedy.
	 */
	private static int farba = 1;
	/**
	 * Mno�stvo laku potrebn� pre nalakovanie lode danej triedy.
	 */
	private static int lak = 1;
	
	
	@Override
	public int vyrob(Kajak lod) {
		trvanieProcesu( zistiCasVyroby(lod, this) );
		System.out.println("Novy slalomovy kajak.");
		return material;
	}

	@Override
	public int vyrob(Kanoe lod){
		trvanieProcesu( zistiCasVyroby(lod, this) );
		System.out.println("Nove slalomove kanoe.");
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
