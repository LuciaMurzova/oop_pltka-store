package store.model.info;

import store.model.lode.*;

public interface CennikLodi {
	
	public int cenaPrednostnej = 300;
	
	default public int ZistiCenu( Kategoria l, Disciplina disciplina ) {
			
		if( l instanceof Kajak ) {
			if( disciplina instanceof Slalom )
				return 1500;
			else if( disciplina instanceof Sprint )
				return 2000;
			else if( disciplina instanceof Zjazd )
				return 2500;	
		}
		
		else if( l instanceof Kanoe ) {
			if( disciplina instanceof Slalom )
				return 1700;
			else if( disciplina instanceof Sprint )
				return 2100;
			else if( disciplina instanceof Zjazd )
				return 2700;
		}
		
		return 0;
	}
	
}
