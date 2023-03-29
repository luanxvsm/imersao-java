import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        //Fazer uma conexão HTTP e buscar os top 250 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        var cliente = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = cliente.send(request, BodyHandlers.ofString());
        String body = response.body();
        //System.out.println(body);

        //Pegar so os dados que interessam (titulo, poster, rating)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        //Exibir e manipular os dados
        var geradora = new stickers();
        for (int i = 0; i<10; i++) {
            Map<String, String> filme = listaDeFilmes.get(i);
            System.out.println("-------------------------------------");

            String urlImagem = filme.get("image");
            String titulo = filme.get("title");

            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = titulo + ".png";

            
            geradora.criar(inputStream, nomeArquivo);

            System.out.print("\u001b[45m \u001b[37m" + filme.get("rank") + ". ");
            System.out.println("\u001b[1m" + filme.get("title") + "\u001b[m");
            System.out.println("\u001b[3m" + filme.get("year") + "\u001b[m");
            System.out.println(filme.get("imDbRating"));
            double classificacao = Double.parseDouble(filme.get("imDbRating"));
            int estrelas = (int) classificacao;
            for(int n=1; n <= estrelas; n++){
                System.out.print("⭐");
            }
            System.out.println();
            System.out.println("-------------------------------------");
        }
    }
}
