package store.observer;

public interface Subjekt {
	
	public void informujObserverov(String s);
	
	public void pridajObserver(Observer o);
	public void odstranObserver(Observer o);
}
