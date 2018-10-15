import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		Grafo g = new Grafo();
		g = leEntrada(g);
		int grau = g.getGrau(g.getVertice(1));
		System.out.println(grau);
		boolean isAdjacente = g.isAdjacente(g.getVertice(2), g.getVertice(4));
		System.out.println(isAdjacente);
		boolean isNulo = g.isNulo(g);
		System.out.println(isNulo);
		boolean isPendente = g.isPendente(g.getVertice(5));
		System.out.println(isPendente);
		boolean isIsolado = g.isIsolado(g.getVertice(5));
		System.out.println(isIsolado);
		boolean isRegular = g.isRegular(g);
		System.out.println(isRegular);
	}

	private static Grafo leEntrada(Grafo g) {
		try {
			FileReader fr = new FileReader("entrada.txt");
			BufferedReader br = new BufferedReader(fr);
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
