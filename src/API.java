/* Alexandros Florides */

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class API {

    public API() {}

    // method that takes two parameters, a string path which is an attribute of api and a string
    // searchquery which can be either null or a number(indicating a page number). The method
    // returns the result of getRequest method which is jsonObject
    public JsonObject getBuilder(String path, String searchquery) throws Exception {
        HttpGet httpGet;
        if (searchquery == null) {
            httpGet = new HttpGet("https://swapi.dev/api/" + path + "/");
        } else {
            httpGet = new HttpGet("https://swapi.dev/api/" + path + "/?page=" + searchquery);
        }
        return getRequest(httpGet);
    }

    // method that that takes as parameter a HttpGet object and retrieves the requested data
    // from the web api if succeeds. It returns a deserialized JsonObject back to getBuilder
    // method
    public JsonObject getRequest(HttpGet getRequest) throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        getRequest.addHeader("accept", "application/json");
        HttpResponse response = httpClient.execute(getRequest);

        // throw RuntimeException if the status code returned is not 200, meaning retrieving
        // the requested data failed
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatusLine().getStatusCode());
        }

        // adds the content of the retrieved data into a BufferedReader object
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader((response.getEntity().getContent())));


        // iterate through the BufferedReader object appending each line into a
        // StringBuilder object
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }

        // create a JsonObject object that holds the deserialized stringBuilder
        // and closes the BufferedReader stream to release system resources
        JsonObject jsonObject = deserialize(stringBuilder.toString());
        bufferedReader.close();

        return jsonObject;
    }

    // method to deserialize json string to Gson object
    public JsonObject deserialize(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, JsonObject.class);
    }

    // method that accepts any url as parameter and pass it to getRequest method to
    // return a jsonObject
    public JsonObject innerRequest(String uri) throws IOException {
        HttpGet httpGet = new HttpGet(uri);
        return getRequest(httpGet);
    }
}

/* Alexandros Florides */