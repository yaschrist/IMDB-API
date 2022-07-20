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

        //make a HTTP connection and and bring the TOP 250 movies
        //create a variable
        String url = "https://api.mocki.io/v2/549a5d8b";
        // ctrl + . = assign the statement to a new local variable
        URI address = URI.create(url);
        var client = HttpClient.newHttpClient();
        //GET - get data of an url or uri
        var request = HttpRequest.newBuilder(address).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        //bodyHandler = creat way to read the classes

        String body = response.body();


        //extract only the data that interests me (title, image, rating) - parsear os dados
        //create the object to call the parser
        var parser = new jsonParser();
        List<Map<String, String>> moviesList = parser.parse(body);

        //System.out.println(moviesList.size());  Tamanho da lista
        System.out.println(moviesList.get(0));


        //display and manipulate the data
        var factory = new stickerFactory();
        for (int i = 0; i <10; i++) {
            
            Map<String, String> movie = moviesList.get(i);
            String Imageurl = movie.get("image");
            String title = movie.get("title");

            InputStream inputStream = new URL(Imageurl).openStream();
            String fileName = "output/" + title + ".png";

            factory.creat(inputStream, fileName);

            System.out.println("\u001b[34;1m" + movie.get("title") + "\u001b[m");
            //System.out.println(movie.get("image"));
            System.out.println("\033[32mRating IMDb: " + movie.get("imDbRating"));
            //to decorate terminal: https://www.alura.com.br/artigos/decorando-terminal-cores-emojis
            System.out.println();
        }

    }
}
