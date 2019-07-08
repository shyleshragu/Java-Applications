package ca.jrvs.apps.twitter.example;

import java.util.Arrays;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class TwitterApiTest {
    private static String CONSUMER_KEY = "yYYbgpbXuXdMDwvIVEvMLWek6";
    private static String CONSUMER_SECRET = "uEpGdpVaWlQgtDSUmOUWtTrRL23WjWYPBubl9MXSyQY80zqiVl";
    private static String ACCESS_TOKEN = "1144310212708524032-guBRapFAiXNWBJHdayE0XuOSAJMRUi";
    private static String TOKEN_SECRET = "Ky89ym763XCJ05SMVIAsk9zkWSru60e7Koa2TVTXTAzlN";

    public static void main(String[] args) throws Exception {
        //setup oauth
        OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY,CONSUMER_SECRET);
        consumer.setTokenWithSecret(ACCESS_TOKEN, TOKEN_SECRET);
        // create an HTTP GET request
        HttpGet request = new HttpGet("https://api.twitter.com/1.1/users/search.json?q=realDonaldTrump");
        // sign the request (add headers)
        consumer.sign(request);
        System.out.println("Http Request Headers:");
        Arrays.stream(request.getAllHeaders()).forEach(System.out::println);
        // send/execute the request
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = httpClient.execute(request);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }
}