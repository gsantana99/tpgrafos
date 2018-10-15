import java.util.ArrayList;
import java.util.List;

public class Grafo {
	
	private List<Vertice> vertices = new ArrayList<Vertice>();
	private List<Aresta> arestas = new ArrayList<Aresta>();
	
	public Grafo() { }
	
	public void addVertice(Vertice v) {
		vertices.add(v);
	}
	
	public void addAresta(Aresta e) {
		arestas.add(e);
	}
	
	public Vertice getVertice(int i) {
		return vertices.get(i);
	}
	
	public Aresta getAresta(int i) {
		return arestas.get(i);
	}
	
	public List<Vertice> getListaVertices(){
		return vertices;
	}
	
	public List<Aresta> getListaArestas(){
		return arestas;
	}
	
	public boolean isAdjacente(Vertice v1, Vertice v2) {
		for (Aresta aresta : arestas) {
			if(aresta != null && (aresta.getVerticesLigados().contains(v1) && aresta.getVerticesLigados().contains(v2))) {
				return true;
			}
		}
		return false;
	}
	
	public int getGrau(Vertice v) {
		int grau = 0;
		for (Aresta aresta : arestas) {
			if(aresta != null && aresta.getVerticesLigados().contains(v)) {
				if(aresta.getVerticesLigados().get(0) == aresta.getVerticesLigados().get(1)) {
					grau++;
				}
				grau++;
			}
		}
		return grau;
	}
		
	public boolean isRegular(Grafo g) {
		int grauV1 = getGrau(g.getListaVertices().get(1));
		int soma = 0;
		for (int i = 1; i < g.getListaVertices().size(); i++) {
			soma += getGrau(g.getListaVertices().get(i));
		}
		int media = ((soma)/(g.getListaVertices().size()-1));
		if(grauV1 == media) {
			return true;
		}
		return false;
	}
	
	public boolean isIsolado(Vertice v) {
		if(getGrau(v) == 0) {
			return true;
		}
		return false;
	}
	
	public boolean isPendente(Vertice v) {
		if(getGrau(v) == 1) {
			return true;
		}
		return false;
	}
	
	public boolean isNulo(Grafo g) {
		if(g.getListaArestas().size() == 1) {
			return true;
		}
		return false;
	}
	
	public boolean isCompleto(Grafo g) {
		int e = g.getListaArestas().size();
		int v = g.getListaVertices().size();
		if(e == (((v-1)*v)/2)) {
			return true;
		}
		return false;
	}
	
	/*
	public boolean isConexo(Grafo g) { }
	
	public boolean isBipartido(Grafo g) { }
	
	public Grafo getComplementar(Grafo g) { }
	
	public boolean isEuleriano(Grafo g) { }
	
	public boolean isUnicursal(Grafo g) { }
	
	public boolean hasCiclo(Grafo g) { }
	
	public int getGrauEntrada(Vertice v) { }
	
	public void ordenacaoTopologica(Grafo g) { hasCiclo(Grafo g); }
	
	public Grafo getTransposto(Grafo g) { }
	
	public boolean isFConexo(Grafo g) { }
	*/
	
	@Override
	public String toString() {
		return "Grafo g:\n\n[\n\nvertices=" + vertices + "\n\narestas=" + arestas + "\n\n]";
	}
}
