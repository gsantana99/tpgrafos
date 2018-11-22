import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class Main {

	public static void main(String[] args) {
		Grafo g = new Grafo();
		g = leEntrada(g);
		int grau = g.getGrau(g.getVertice(4));
		System.out.println("Grau do vértice: " + grau);
		boolean isAdjacente = g.isAdjacente(g.getVertice(2), g.getVertice(4));
		System.out.println("Os vértices informados são adjacentes: " + isAdjacente);
		boolean isNulo = g.isNulo(g);
		System.out.println("O grafo é nulo: " + isNulo);
		boolean isPendente = g.isPendente(g.getVertice(4));
		System.out.println("O vértice informado é pendente: " + isPendente);
		boolean isIsolado = g.isIsolado(g.getVertice(4));
		System.out.println("O vértice informado é isolado: " + isIsolado);
		boolean isRegular = g.isRegular(g);
		System.out.println("O grafo é regular: " + isRegular);
		boolean isCompleto = g.isCompleto(g);
		System.out.println("O grafo é completo: " + isCompleto);
		Grafo complementar = g.getComplementar(g);
		System.out.println("***** Grafo Complementar *****");
		System.out.println(complementar.toString());
		System.out.println();
		boolean isConexo = g.isConexo(g);
		System.out.println("O grafo é conexo: " + isConexo);
		
		boolean isBipartido = g.isBipartido(g);
		System.out.println("O grafo é bipartido: " + isBipartido);
		boolean isEuleriano = g.isEuleriano(g);
		System.out.println("O grafo é euleriano: " + isEuleriano);
		
		boolean isUnicursal = g.isUnicursal(g);
		System.out.println("O grafo é unicursal: " + isUnicursal);
		
		boolean hasCiclo = g.hasCiclo(g);
		System.out.println("O grafo possui ciclo: " + hasCiclo);
		
		g.ordenacaoTopologica(g);
		
		boolean isFConexo = g.isFConexo(g);
		System.out.println("O grafo é fortemente conexo: " + isFConexo);
		
		Grafo transposto = g.getTransposto(g);
		System.out.println("***** Grafo Transposto *****");
		System.out.println(transposto.toString());
		
		
	}

	private static Grafo leEntrada(Grafo g) {
		try {
			FileReader fr = new FileReader("entrada.txt");
			BufferedReader br = new BufferedReader(fr);
			String digrafo = br.readLine().substring(0, 1);
			if(digrafo.compareTo("S") == 0) {
				g.setDigrafo(true);
			}
			int numVertices = Integer.parseInt(br.readLine());
			g.addVertice(null);
			g.addAresta(null);
			String linha = br.readLine();
			String[] conexoes = new String[numVertices];
			for (int i = 0; i < numVertices; i++) {
				int id = Integer.parseInt(linha.substring(0, linha.indexOf(' ')));
				g.addVertice(new Vertice(id));
				conexoes[i] = linha.substring(linha.lastIndexOf(' ') + 1, linha.indexOf(';'));
				linha = br.readLine();
			}
			int i = 0;
			while(i < numVertices) {
				String[] listaConexoes = conexoes[i].split("-");
				for (int j = 1; j < listaConexoes.length; j++) {
					if(listaConexoes[j] != "") {
						g.addAresta(new Aresta(g.getVertice(i+1), g.getVertice(Integer.parseInt(listaConexoes[j]))));
					}
				}
				i++;
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return g;
	}

}
