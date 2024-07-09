package uz.pdp.g42.bot.service;

import org.json.JSONArray;
import org.json.JSONObject;


import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;


public class HTTPService {

    public String searchHttpRequest(String searchTerm) {
        try {
            String encodedSearchTerm = URLEncoder.encode(searchTerm, StandardCharsets.UTF_8);
            URI uri = new URI("https://uz.wikipedia.org/w/api.php?action=query&list=search&srsearch="
                    + encodedSearchTerm + "&format=json");

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());


            JSONObject jsonResponse = new JSONObject(response.body());
            JSONArray searchResults = jsonResponse.getJSONObject("query").getJSONArray("search");

            if (searchResults.length() > 0) {
                int pageId = searchResults.getJSONObject(0).getInt("pageid");

                URI detailUri = new URI("https://uz.wikipedia.org/w/api.php?action=query&prop=extracts&pageids="
                        + pageId + "&format=json&explaintext=true");


                HttpRequest detailRequest = HttpRequest.newBuilder()
                        .uri(detailUri)
                        .GET()
                        .build();

                HttpResponse<String> detailResponse = httpClient.send(detailRequest, HttpResponse.BodyHandlers.ofString());

                JSONObject detailJsonResponse = new JSONObject(detailResponse.body());
                JSONObject pages = detailJsonResponse.getJSONObject("query").getJSONObject("pages");
                JSONObject page = pages.getJSONObject(String.valueOf(pageId));

                return page.getString("extract");
            } else {
                return "No search results found.";

            }
        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred while processing the request.";
        }
    }


}

