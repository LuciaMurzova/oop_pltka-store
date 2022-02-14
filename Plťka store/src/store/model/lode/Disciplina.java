package store.model.lode;

/**
 * Abstraktn� trieda <code>Disciplina</code> predstavuje typ discipl�ny novej objedn�vky.
 * Pre ka�d� objedn�vku sa vytvor� nov� in�tancia triedy. 
 * T�to triedu ro�iruj� triedy {@link Slalom}, {@link Zjazd} a {@link Sprint}.
 * 
 * @author Lucia
 *
 */
public abstract class Disciplina {
	
	/**
	 * Met�da simuluje v�robn� proces pozastaven�m vetvy na �as potrebn� 
	 * pre vykonanie danej oper�cie. 
	 * 
	 * @param cas	�as trvania ziten� pod�a kateg�rie a discipl�ny z vhniezden�ho rozhrania.
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
	 * Vhniezden� interface pre zistenie trvania jednotliv�ch procesov vo v�robe lod�
	 * pod�a ich kateg�rie a discipl�ny. 
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
