package uz.pdp.g42.bot.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.SneakyThrows;
import org.glassfish.grizzly.http.util.UEncoder;
import uz.pdp.g42.common.model.User;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class HttpService {
    private final HttpClient httpClient;


    public HttpService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @SneakyThrows
    public String searchHttpReuest(String  searchTerm){
        try {
            String encodedSearchTerm = URLEncoder.encode(searchTerm, StandardCharsets.UTF_8);
            URI uri = new URI("http://uz.wikipedia.org/w/api.php?action=query&list=search&srsearch=" +encodedSearchTerm +"&format=json");

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());

//            JSONPObject jsonpObject

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
