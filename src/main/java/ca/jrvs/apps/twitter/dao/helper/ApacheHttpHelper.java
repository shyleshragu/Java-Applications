package ca.jrvs.apps.twitter.dao.helper;

import ca.jrvs.apps.twitter.Util.StringUtil;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;

@SuppressWarnings("Duplicates")
//@Component
//@Profile("dev")
public class ApacheHttpHelper implements HttpHelper {

    private OAuthConsumer consume;
    private HttpClient httpClient;

    public ApacheHttpHelper(OAuthConsumer consume, HttpClient httpClient) {
        this.consume = consume;
        this.httpClient = httpClient;
    }


    public ApacheHttpHelper() {System.out.println("dev");
        String CONSUMER_KEY = System.getenv("consumerKey");
        String CONSUMER_SECRET = System.getenv("consumerSecret");
        String ACCESS_TOKEN = System.getenv("accessToken");
        String TOKEN_SECRET = System.getenv("tokenSecret");


        if (StringUtil.isEmpty(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, TOKEN_SECRET))
            throw new RuntimeException("Unable to detect key and tokens from environment");

        consume = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
        consume.setTokenWithSecret(ACCESS_TOKEN, TOKEN_SECRET);

        httpClient = new DefaultHttpClient();
    }

    public ApacheHttpHelper(String consumerKey, String consumerSecret, String accessToken, String tokenSecret) {
        consume = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
        consume.setTokenWithSecret(accessToken, tokenSecret);

        httpClient = new DefaultHttpClient();
    }


    @Override
    public HttpResponse httpPost(URI uri) {
        try {
            return executeHttpRequest(HttpMethod.POST, uri, null);
        } catch (IOException e) {
            throw new RuntimeException("Failed to execute", e);
        } catch (OAuthException ex) {
            throw new RuntimeException("Failed to execute", ex);
        }
    }

    @Override
    public HttpResponse httpPost(URI uri, StringEntity stringEntity) {
        try {
            return executeHttpRequest(HttpMethod.POST, uri, stringEntity);
        } catch (IOException e) {
            throw new RuntimeException("Failed to execute", e);
        } catch (OAuthException ex) {
            throw new RuntimeException("Failed to execute", ex);
        }
    }

    @Override
    public HttpResponse httpGet(URI uri) {
        try {
            return executeHttpRequest(HttpMethod.GET, uri, null);
        } catch (IOException e) {
            throw new RuntimeException("Failed to execute", e);
        } catch (OAuthException ex) {
            throw new RuntimeException("Failed to execute", ex);
        }
    }

    public HttpResponse executeHttpRequest(HttpMethod method, URI uri, StringEntity stringEntity)  throws OAuthException, IOException {
        if (method == HttpMethod.GET) {
            HttpGet request = new HttpGet(uri);
            consume.sign(request);
            return httpClient.execute(request);
        } else if (method == HttpMethod.POST) {
            HttpPost request = new HttpPost(uri);
            if (stringEntity != null) {
                request.setEntity(stringEntity);
            }
            consume.sign(request);
            return httpClient.execute(request);
        } else {
            throw new IllegalArgumentException("Unknown HTTP method: " + method.name());
        }

    }


}
