import java.util.ArrayList;
import java.util.List;

public class Aresta {
	
	private static int contador = 1;
	private int id;
	private List<Vertice> verticesLigados = new ArrayList<Vertice>();
	
	public Aresta(Vertice v1, Vertice v2) {
		this.id = Aresta.contador;
		Aresta.contador++;
		this.verticesLigados.add(v1);
		this.verticesLigados.add(v2);
	}
	
	public int getId() {
		return id;
	}
	
	public List<Vertice> getVerticesLigados() {
		return verticesLigados;
	}

	@Override
	public String toString() {
		return "\n[" + id + ", verticesLigados = " + verticesLigados + " ]\n";
	}
	
}
