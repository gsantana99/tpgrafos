
public class Vertice {
	
	private int id;
	
	public Vertice(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "[" + id + "]";
	}
	
}
