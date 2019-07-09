package ca.jrvs.apps.twitter.service;


import ca.jrvs.apps.twitter.Util.JsonUtil;
import ca.jrvs.apps.twitter.Util.StringUtil;
import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dto.Tweet;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;

import static ca.jrvs.apps.twitter.Util.TweetUtil.buildTweet;
import static ca.jrvs.apps.twitter.Util.TweetUtil.validId;
import static ca.jrvs.apps.twitter.Util.TweetUtil.validatePostTweet;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class TwitterServiceImp implements TwitterService {

    private CrdDao dao;

    public TwitterServiceImp(CrdDao dao) {
        this.dao = dao;
    }

    @Override
    public Tweet postTweet(String text, Double latitude, Double longitude) {
        Tweet postTweet = buildTweet(text, longitude, latitude);
        validatePostTweet(postTweet);

        try{
            Tweet responseTweet = (Tweet) dao.create(postTweet);
            printTweet(responseTweet);
        } catch (Exception e){
            throw new RuntimeException(e + "\nFailed to post tweet");
        }

        return postTweet;

    }

    @Override
    public Tweet showTweet(String id, String[] fields) {
        Tweet tweet;

        if (!validId.test(id))
            throw new IllegalArgumentException("ID not numbers");

        try {
            tweet = (Tweet) dao.findById(id);
            printTweet(selectFields(tweet, fields));
        } catch (IOException e){
            throw new RuntimeException("Failed to show tweet");
        }
        return tweet;
    }

    private Tweet selectFields(Tweet tweet, String[] fields) {
        if (fields == null || fields.length == 0) {
            return tweet;
        }
        //rTweet = deep copy of tweet
        Tweet rTweet = JsonUtil.toObjectFromJson(JsonUtil.toPrettyJson(tweet), Tweet.class);

        //helper lambda function to remove leading and trailing spaces
        Function<String[], String[]> trimStrArray = (items) -> Arrays.stream(items).map(String::trim)
                .toArray(String[]::new);
        //Make fieldSet for fast lookup and removal
        Set<String> fieldSet = new HashSet<>(Arrays.asList(trimStrArray.apply(fields)));

        Predicate<Method> isSetter = (method) -> method.getName().startsWith("set");
        Arrays.stream(Tweet.class.getMethods())
                .filter(isSetter)
                .forEach(setter ->
                {
                    JsonProperty jsonProperty = setter.getDeclaredAnnotation(JsonProperty.class);
                    if (jsonProperty == null || StringUtil.isEmpty(jsonProperty.value())) {
                        throw new RuntimeException(
                                "@JsonProperty is not defined for method" + setter.getName());
                    }
                    String value = jsonProperty.value();
                    if (fieldSet.contains(value)) {
                        fieldSet.remove(value);
                    } else {
                        try {
                            setter.invoke(rTweet, new Object[]{null});
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            throw new RuntimeException("unable to set setter:" + setter.getName(), e);
                        }
                    }
                });

        if (!fieldSet.isEmpty()) {
            String invalidFields = String.join(",", fieldSet);
            throw new RuntimeException("Found invalid select field(s):" + invalidFields);
        }
        return rTweet;
    }

    }

    @Override
    public List<Tweet> deleteTweets(String[] ids) {
        List<Tweet> tweets = new ArrayList<>();
        for (String id : ids){
            validId.test(id);
            Tweet tweet - (Tweet) dao.deleteById(id);
            printTweet(tweet);
            tweets.add(tweet);
        }
        return tweets;
    }


    private void printTweet(Tweet tweet){
        try{
            System.out.println(JsonUtil.toPrettyJson(tweet));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to convert tweet object to string", e);
        }
    }


}
