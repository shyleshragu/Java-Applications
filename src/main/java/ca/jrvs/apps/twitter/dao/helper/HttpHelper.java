package ca.jrvs.apps.twitter.dao.helper;

import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;

import java.net.URI;

public interface HttpHelper {
    HttpResponse httpPost(URI uri);
    HttpResponse httpPost(URI uri, StringEntity stringEntity);
    HttpResponse httpGet(URI uri);
}
