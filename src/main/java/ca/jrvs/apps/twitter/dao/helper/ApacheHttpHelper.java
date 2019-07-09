package ca.jrvs.apps.twitter.dao.helper;

@Component
public class ApacheHttpHelper implements HttpHelper {
    String CONSUMER_KEY = System.getenv("consumerKey");
    String CONSUMER_SECRET = System.getenv("consumerSecret");
    String ACCESS_TOKEN = System.getenv("accessToken");
    String TOKEN_SECRET = System.getenv("tokenSecret");


}
