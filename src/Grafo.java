import java.util.ArrayList;
import java.util.List;

public class Grafo {
	
	private List<Vertice> vertices = new ArrayList<Vertice>();
	private List<Aresta> arestas = new ArrayList<Aresta>();
	private boolean digrafo = false;
	
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
		if(!this.isDigrafo()) {
			for (Aresta aresta : arestas) {
				if(aresta != null && ((aresta.getVerticesLigados().get(0) == v1 && aresta.getVerticesLigados().get(1) == v2) ||
						(aresta.getVerticesLigados().get(0) == v2 && aresta.getVerticesLigados().get(1) == v1))) {
					return true;
				}
			}
		}
		else {
			for (Aresta aresta : arestas) {
				if(aresta != null && (aresta.getVerticesLigados().get(0) == v1 && aresta.getVerticesLigados().get(1) == v2)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public int getGrau(Vertice v) {
		int grau = 0;
		if(!isDigrafo()) {
			for (Aresta aresta : arestas) {
				if(aresta != null && aresta.getVerticesLigados().contains(v)) {
					if(aresta.getVerticesLigados().get(0) == aresta.getVerticesLigados().get(1)) {
						grau++;
					}
					grau++;
				}
			}
			return grau/2;
		}
		else {
			for(Vertice vertice : this.getListaVertices()) {
				if(this.isAdjacente(vertice, v))
					grau++;
			}
			return grau;
		}
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
		for(Vertice v1 : g.getListaVertices()) {
			for(Vertice v2 : g.getListaVertices()) {
				if((!g.isAdjacente(v1, v2) && v1!=v2) && (v1 != null && v2 != null))
					return false;
			}
		}
		return true;
	}
	
	public boolean isConexo(Grafo g) {
		for(int i=1; i < g.getListaVertices().size(); i++) {
			for(int j = 1; j < g.getListaVertices().size(); j++) {
				Grafo grafoVazio = new Grafo();
				if(!consegueChegar(g.getVertice(i), g.getVertice(j), grafoVazio) && i!=j)
					return false;
			}
		}
		return true;
	}
	//Método que verifica se um vértice consegue chegar a outro 
	private boolean consegueChegar(Vertice verticeAtual, Vertice verticeProcurado, Grafo g) {
		if(g.getListaVertices().contains(verticeAtual))
			return false;
		else if (isAdjacente(verticeAtual, verticeProcurado))
			return true;
		else {
			g.addVertice(verticeAtual);
			List<Vertice> listaAdjacentes = obterVerticesAdjacentes(verticeAtual);
			if(listaAdjacentes != null) {
				for(Vertice v : listaAdjacentes) {
					if(consegueChegar(v, verticeProcurado, g))
						return true;
				}
			}
		}
		return false;
	}
	//Método que verifica quais são os vértices adjacentes a um determinado vértice.
	private List<Vertice> obterVerticesAdjacentes(Vertice v){
		List<Vertice> listaAdjacentes = new ArrayList<Vertice>();
		for (Vertice vertice : vertices) {
			if(this.isAdjacente(v, vertice))
				listaAdjacentes.add(vertice);		
		}
		
		return listaAdjacentes;
	}
	
	
	//public boolean isBipartido(Grafo g) { }
	
	public Grafo getComplementar(Grafo g) {
		Grafo complementar = new Grafo();
		complementar.vertices.addAll(g.getListaVertices());
		for(int i=1; i<g.getListaVertices().size()-1; i++){
			for(int j=i+1; j < g.getListaVertices().size(); j++) {
				if(!isAdjacente(g.getListaVertices().get(i), g.getListaVertices().get(j)))
					complementar.addAresta(new Aresta(g.getListaVertices().get(i), g.getListaVertices().get(j)));				
			}
		}
		
		return complementar;
	}
	
	public boolean isEuleriano(Grafo g) { 
		if(!isConexo(g))
			return false;
		else {
			for(Vertice v : g.getListaVertices()) {
				int grau = g.getGrau(v);
				if(grau%2 != 0)
					return false;
			}
		}
		return true;
	}
	
	
	public boolean isUnicursal(Grafo g) {
		if(!isConexo(g))
				return false;
		int verticesImpares = 0;
		for(Vertice v : g.getListaVertices()) {
			int grau = g.getGrau(v);
			if(grau%2 != 0)
				verticesImpares++;
		}
		if(verticesImpares > 2 || verticesImpares < 2)
			return false;
		return true;
	}
	
	
	public boolean hasCiclo(Grafo g) {
		for(Vertice v : g.getListaVertices()) {
			if(g.isAdjacente(v, v))
				return true;
		}
		for(Vertice v : g.getListaVertices()) {
			Grafo grafoVazio = new Grafo();
			if(hasCiclo(v, v, grafoVazio))
				return true;
		}
		
		return false;
	}
	private boolean hasCiclo(Vertice verticeAtual, Vertice verticeProcurado, Grafo g) {
		if(g.getListaVertices().contains(verticeAtual))
			return false;
		else if (isAdjacente(verticeAtual, verticeProcurado) && g.getListaVertices().size() >=2)
			return true;
		else {
			g.addVertice(verticeAtual);
			List<Vertice> listaAdjacentes = obterVerticesAdjacentes(verticeAtual);
			if(listaAdjacentes != null) {
				for(Vertice v : listaAdjacentes) {
					if(!g.getListaVertices().contains(v))
						if(hasCiclo(v, verticeProcurado, g))
						return true;
				}
			}
		}
		g.getListaVertices().remove(verticeAtual);
		return false;
	}
	
	
	/*
	public void ordenacaoTopologica(Grafo g) { hasCiclo(Grafo g); }
	
	public Grafo getTransposto(Grafo g) { }
	
	public boolean isFConexo(Grafo g) { }
	*/
	
	@Override
	public String toString() {
		return "Grafo g:\n\n[\n\nvertices=" + vertices + "\n\narestas=" + arestas + "\n\n]";
	}

	public boolean isDigrafo() {
		return digrafo;
	}

	public void setDigrafo(boolean digrafo) {
		this.digrafo = digrafo;
	}
}
