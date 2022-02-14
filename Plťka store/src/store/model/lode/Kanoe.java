package store.model.lode;

public class Kanoe implements Kategoria{
	public int vyrob(Disciplina d){
		return d.vyrob(this);
	}

	@Override
	public int namaluj(Disciplina d) {
		return d.namaluj(this);
	}

	@Override
	public int nalakuj(Disciplina d) {
		return d.nalakuj(this);
	}
}
