package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dto.Tweet;
import ca.jrvs.apps.twitter.Util.JsonUtil;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TwitterRestDao implements CrdDao<Tweet, String> {

    private static final String API_BASE_URI = "https://api.twitter.com";
    private static final String POST_PATH = "/1.1/statuses/update.json";
    private static final String SHOW_PATH = "/1.1/statuses/show.json";
    private static final String DELETE_PATH = "/1.1/statuses/destroy";

    //response code
    private static final int HTTP_OK = 200;

    private static HttpHelper httpHelper;

    @Autowired
    public TwitterRestDao(HttpHelper httpHelper) {
        this.httpHelper = httpHelper;
    }

    @Override
    public Tweet create(Tweet tweet) {
        URI uri;
        try {
            uri = getPostUri(tweet);
        } catch (URISyntaxException e){
            throw new IllegalArgumentException("Error: Invalid tweet input", e);
        } catch (UnsupportedEncodingException ex){
            throw new IllegalArgumentException("Error: Invalid tweet input", ex);
        }

        HttpResponse response = httpHelper.httpPost(uri);
        return parseResponseBody(response, HTTP_OK);
   }

    private URI getPostUri(Tweet tweet) throws URISyntaxException, UnsupportedEncodingException{
        String text = tweet.getText();
        Double longitude = tweet.getCoordinates().getCoordinates().get(0);
        Double latitude = tweet.getCoordinates().getCoordinates().get(1);

        StringBuilder sb = new StringBuilder();
        sb.append(API_BASE_URI).append(POST_PATH).append("?");

        appendQueryParam(sb, "status", URLEncoder.encode(text, StandardCharsets.UTF_8.name()), true);
        appendQueryParam(sb, "long", longitude.toString(), false);
        appendQueryParam(sb, "lat", latitude.toString(), false);

        return new URI(sb.toString());
    }

    @Override
    public Tweet findById(String id) {
        URI uri;
        try {
            uri = getShowUri(id);
        } catch (URISyntaxException e){
            throw new IllegalArgumentException("Error: Unable to construct URI", e);
        }

        HttpResponse response = httpHelper.httpGet(uri);
        return parseResponseBody(response, HTTP_OK);
    }

    protected URI getShowUri(String id) throws URISyntaxException{
        StringBuilder sb = new StringBuilder();
        sb.append(API_BASE_URI).append(SHOW_PATH).append("?");
        appendQueryParam(sb, "id", id, true);
        return new URI(sb.toString());
    }

    @Override
    public Tweet deleteById(String id) {
        URI uri;
        try {
            uri = getDeletedUri(id);
        } catch (URISyntaxException e){
            throw new IllegalArgumentException("Error: Unable to contruct URI", e);
        }

        HttpResponse response = httpHelper.httpPost(uri);
        return parseResponseBody(response, HTTP_OK);
    }

    protected URI getDeletedUri(String id) throws URISyntaxException{
        StringBuilder sb = new StringBuilder();
        sb.append(API_BASE_URI).append(DELETE_PATH).append("/").append(id).append(".json");

        return new URI(sb.toString());
    }

    protected void appendQueryParam(StringBuilder sb, String key, String value, boolean fparam){
        if (!fparam)
            sb.append("&");
        sb.append(key).append("=").append(value);
    }

    protected Tweet parseResponseBody(HttpResponse response, Integer expectedStatusCode){
        Tweet tweet = null;

        int status = response.getStatusLine().getStatusCode();
        if (status != expectedStatusCode){
            try {
                System.out.println(EntityUtils.toString(response.getEntity()));
            } catch (IOException e){
                System.out.println("Error: Response has no entity");
            }
            throw new RuntimeException("Error: Unexpeected HTTP status: " + status);
        }

        if (response.getEntity() == null)
            throw  new RuntimeException("Error: Empty response");

        String jsonStr;
        try {
            jsonStr = EntityUtils.toString(response.getEntity());
        } catch (IOException e){
            throw new RuntimeException("Error: failed to convert entity to String", e);
        }


        try {
            tweet = (Tweet) JsonUtil.toObjectFromJson(jsonStr, Tweet.class);
        } catch (IOException e){
            throw new RuntimeException("Error: Unable to convert JSON str to Object", e);
        }

        return tweet;
    }
}
