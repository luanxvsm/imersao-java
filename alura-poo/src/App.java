import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        //Fazer uma conexão HTTP e buscar os top 250 filmes
        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        //ExtratorDeConteudo extrator = new ExtratorDeConteudosDoIMDB();

        String url = "https://api.nasa.gov/planetary/apod?api_key=NiOKhSTOLWR6IFyegeabdr4Y5Y354aYGwyeDhu4X&start_date=2022-04-14&end_date=2022-04-16";
        ExtratorDeConteudo extrator = new ExtratorDeConteudosDaNasa();
        
        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        //Exibir e manipular os dados
        
        List<conteudo> conteudos = extrator.extraiConteudos(json);
        var geradora = new stickers();
        for (int i = 0; i<3; i++) {
            conteudo Conteudo = conteudos.get(i);

            InputStream inputStream = new URL(Conteudo.getUrlImagem()).openStream();
            String nomeArquivo = "saida/" + Conteudo.getTitulo() + ".png";

            
            geradora.criar(inputStream, nomeArquivo);

            System.out.println(Conteudo.getTitulo());
            System.out.println();

        }
    }
}
