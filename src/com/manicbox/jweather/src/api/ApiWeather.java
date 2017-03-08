package com.manicbox.jweather.src.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 *
 * @author isaac.herrera
 */
public class ApiWeather {

    private final String URL = "http://weathers.co/api.php";
    private final String PARAM_KEY = "city";
    private CloseableHttpClient client;
    private HttpGet request;
    private HttpResponse response;
    private URIBuilder uri;

    public ApiWeather() {
        try {
            client = HttpClients.createDefault();
            uri = new URIBuilder(URL);
            request = new HttpGet();
        } catch (URISyntaxException ex) {
           
        }
    }

    public void addParameter(String value) {
        this.uri.addParameter(PARAM_KEY, value);
    }
    
    public String makeRequest () throws URISyntaxException, IOException {
        StringBuffer buffer = new StringBuffer();
        String line = "";
        request.setURI(uri.build());
        request.addHeader(HttpHeaders.CONTENT_TYPE, "text/plain");
        System.out.println(request.getURI().toString());
        response = client.execute(request);
        BufferedReader bf = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        while ((line = bf.readLine()) != null) {
            buffer.append(line);
        }
        return buffer.toString();
    }
    

}
