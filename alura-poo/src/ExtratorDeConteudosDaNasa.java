import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudosDaNasa implements ExtratorDeConteudo{
    public List<conteudo> extraiConteudos(String json){
        //Pegar so os dados que interessam (titulo, poster, rating)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);

        List<conteudo> conteudos = new ArrayList<>();
        
        //popular a lista de conteudos
        for (Map<String, String> atributos : listaDeAtributos) {
            String titulo = atributos.get("title");
            String urlImagem = atributos.get("url");
            var Conteudo = new conteudo(titulo, urlImagem);

            conteudos.add(Conteudo);
        }        


        return conteudos;
    }
}
