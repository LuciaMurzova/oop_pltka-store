package store.model.lode;

/**
 * Abstraktná trieda <code>Disciplina</code> predstavuje typ disciplíny novej objednávky.
 * Pre každú objednávku sa vytvorí nová inštancia triedy. 
 * Túto triedu roširujú triedy {@link Slalom}, {@link Zjazd} a {@link Sprint}.
 * 
 * @author Lucia
 *
 */
public abstract class Disciplina {
	
	/**
	 * Metóda simuluje výrobný proces pozastavením vetvy na èas potrebný 
	 * pre vykonanie danej operácie. 
	 * 
	 * @param cas	Èas trvania zitený pod¾a kategórie a disciplíny z vhniezdeného rozhrania.
	 */
	public void trvanieProcesu(int cas) {
		try {
			Thread.sleep(cas);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	abstract int vyrob(Kajak lod);
	abstract int vyrob(Kanoe lod);
	abstract int namaluj(Kajak lod);
	abstract int namaluj(Kanoe lod);
	abstract int nalakuj(Kajak lod);
	abstract int nalakuj(Kanoe lod);
	
	
	/**
	 * Vhniezdený interface pre zistenie trvania jednotlivých procesov vo výrobe lodí
	 * pod¾a ich kategórie a disciplíny. 
	 * 
	 * @author Lucia
	 */
	interface CasProcesov {
		
		default public int zistiCasVyroby( Kategoria l, Disciplina d ) {
			// 1000 = 1 sekunda -- berieme ako 1 hodina
			if( l instanceof Kajak ) {
				if( d instanceof Slalom )
					return 50000;		
				else if( d instanceof Sprint )
					return 6000;
				else if( d instanceof Zjazd )
					return 7000;
			}
			
			else if( l instanceof Kanoe ) {
				if( d instanceof Slalom )
					return 5000;
				else if( d instanceof Sprint )
					return 6000;
				else if( d instanceof Zjazd )
					return 7000;
			}
			
			return 0;
		}
		
		default public int zistiCasMalovania( Kategoria l, Disciplina d ) {
			// 1000 = 1 sekunda -- berieme ako 1 hodina
			if( l instanceof Kajak ) {
				if( d instanceof Slalom )
					return 3000;		
				else if( d instanceof Sprint )
					return 4000;
				else if( d instanceof Zjazd )
					return 6000;	
			}
					
			else if( l instanceof Kanoe ) {
				if( d instanceof Slalom )
					return 3000;
				else if( d instanceof Sprint )
					return 4000;
				else if( d instanceof Zjazd )
					return 6000;
			}
					
			return 0;
		}
		
		default public int zistiCasLakovania( Kategoria l, Disciplina d ) {
			// 1000 = 1 sekunda -- berieme ako 1 hodina
			if( l instanceof Kajak ) {
				if( d instanceof Slalom )
					return 1000;		
				else if( d instanceof Sprint )
					return 1000;
				else if( d instanceof Zjazd )
					return 1000;	
			}
								
			else if( l instanceof Kanoe ) {
				if( d instanceof Slalom )
					return 1000;
				else if( d instanceof Sprint )
					return 1000;
				else if( d instanceof Zjazd )
					return 1000;
			}			
			return 0;
		}
	}
}
