package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.Util.JsonUtil;
import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dto.Tweet;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)  //initializes mock
public class TwitterServiceImpTest {

    private final String tweetStr = "{\n"
            + "   \"created_at\":\"Mon Feb 18 21:24:39 +0000 2019\",\n"
            + "   \"id\":1097607853932564480,\n"
            + "   \"id_str\":\"1097607853932564480\",\n"
            + "   \"text\":\"test with loc223\",\n"
            + "   \"entities\":{\n"
            + "      \"hashtags\":[\n"
            + "         {\n"
            + "            \"text\":\"documentation\",\n"
            + "            \"indices\":[\n"
            + "               211,\n"
            + "               225\n"
            + "            ]\n"
            + "         },\n"
            + "         {\n"
            + "            \"text\":\"parsingJSON\",\n"
            + "            \"indices\":[\n"
            + "               226,\n"
            + "               238\n"
            + "            ]\n"
            + "         },\n"
            + "         {\n"
            + "            \"text\":\"GeoTagged\",\n"
            + "            \"indices\":[\n"
            + "               239,\n"
            + "               249\n"
            + "            ]\n"
            + "         }\n"
            + "      ],\n"
            + "      \"user_mentions\":[\n"
            + "         {\n"
            + "            \"name\":\"Twitter API\",\n"
            + "            \"indices\":[\n"
            + "               4,\n"
            + "               15\n"
            + "            ],\n"
            + "            \"screen_name\":\"twitterapi\",\n"
            + "            \"id\":6253282,\n"
            + "            \"id_str\":\"6253282\"\n"
            + "         }\n"
            + "      ]\n"
            + "   },\n"
            + "   \"coordinates\":{\n"
            + "      \"coordinates\":[\n"
            + "         -75.14310264,\n"
            + "         40.05701649\n"
            + "      ],\n"
            + "      \"type\":\"Point\"\n"
            + "   },\n"
            + "   \"retweet_count\":0,\n"
            + "   \"favorite_count\":0,\n"
            + "   \"favorited\":false,\n"
            + "   \"retweeted\":false\n"
            + "}";

    @Mock
    private CrdDao dao;

    @InjectMocks
    TwitterServiceImp service;

    @Before
    public void initializeMockito(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void postTweet() {
        try {
            when(dao.create(any())).thenReturn(new Tweet());    //'when()' and 'any()' are belong to mockito
            service.postTweet("mockito test", 50.1, 50.2);
        } catch (Exception e){
            fail();
        }

    }

    @Test
    public void showTweet() throws IOException {

    }

    @Test
    public void selectFields_happypath() throws IOException {
        try {
            Tweet ttweet = JsonUtil.toObjectFromJson(tweetStr, Tweet.class);
            Tweet rtweet = service.selectFields(ttweet, new String[]{"created_at", "id"});
            assertNotNull(rtweet.getCreatedAt());
            assertNotNull(rtweet.getId());
            assertNull(rtweet.getText());
            assertNotNull(ttweet.getText());    //due to deep copy, original tweet object was not repalced
        } catch (RuntimeException e){
            fail();
        }
    }

    @Test
    public void selectFields_sadpath() throws IOException {
        try {
            Tweet ttweet = JsonUtil.toObjectFromJson(tweetStr, Tweet.class);
            service.selectFields(ttweet, new String[]{"created_at", "id", "fake"});
            fail();
        } catch (RuntimeException e){
            assertTrue(true);
        }
    }

    @Test
    public void deleteTweets() {
    }
}