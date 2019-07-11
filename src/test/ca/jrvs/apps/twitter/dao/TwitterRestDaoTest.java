package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.ApacheHttpHelper;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dto.Coordinates;
import ca.jrvs.apps.twitter.dto.Tweet;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TwitterRestDaoTest {
    private HttpHelper helper;
    private TwitterRestDao dao;
    private Tweet expectedTweet = new Tweet();
    private String id;

    public TwitterRestDaoTest() {
    }

    @Before
    public void setup() {
        this.helper = new ApacheHttpHelper();
        this.dao = new TwitterRestDao(this.helper);
        String TweetText = "this is a test tweet" + System.currentTimeMillis();
        this.expectedTweet.setText(TweetText);
    }

    @Test
    public void create() {
        Coordinates coordinates = new Coordinates();
        new ArrayList();
        coordinates.setCoordinates(Arrays.asList(50.0D, 50.0D));
        this.expectedTweet.setCoordinates(coordinates);
        System.out.println(this.expectedTweet);
        Tweet actualTweet = this.dao.create(this.expectedTweet);
        Assert.assertNotNull(actualTweet);
        Assert.assertEquals(this.expectedTweet.getText(), actualTweet.getText());
        this.id = actualTweet.getIdStr();
    }

    @Test
    public void findbyid() {
        Coordinates coordinates = new Coordinates();
        coordinates.setCoordinates(Arrays.asList(50.0D, 50.0D));
        this.expectedTweet.setCoordinates(coordinates);
        System.out.println(this.expectedTweet);
        Tweet actualTweet = this.dao.create(this.expectedTweet);
        this.id = actualTweet.getIdStr();
        Tweet showTweet = this.dao.findById(this.id);
        Assert.assertEquals(actualTweet.getIdStr(), showTweet.getIdStr());
    }

    @After
    public void cleanup() {
        this.dao.deleteById(this.id);
    }
}
