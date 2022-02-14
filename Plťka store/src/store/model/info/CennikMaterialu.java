package store.model.info;

public interface CennikMaterialu {

	default public int ZistiCenu( String s ) {
		
		if( s.equals("material")) 
			return 1000;
		
		else if( s.equals("farba")) 
			return 500;
		
		else if( s.equals("lak")) 
			return 250;	

		return 0;
	}
	
}
