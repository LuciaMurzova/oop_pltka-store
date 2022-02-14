package store.model.info;

public interface Stav {
		
		public enum StavObjednavky{
			cakaNaVyrobu,
			voVyrobe_lepenie,
			voVyrobe_Malovanie,
			voVyrobe_Lakovanie,
			hotova
		} 
		
		public enum StavZamestnanca{
			volny,
			zaneprazdneny
		} 

}
