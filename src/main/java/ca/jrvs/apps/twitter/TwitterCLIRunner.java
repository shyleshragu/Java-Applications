package ca.jrvs.apps.twitter;


import ca.jrvs.apps.twitter.service.TwitterService;
import ca.jrvs.apps.twitter.Util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TwitterCLIRunner {

    private TwitterService service;

    @Autowired
    public TwitterCLIRunner(TwitterService service) {
        this.service = service;
    }

    public void run(String[] args) {
        if (args.length < 2)
            throw new RuntimeException("Usage: TwitterCLIApp post|show|delete|Tweet");


        String choice = args[0].toLowerCase();
        System.out.println("Usage: TwitterCLIApp post|show|delete");
        if (choice.equals("post")) {
            postTweet(args);
        } else if (choice.equals("show")) {
            showTweet(args);
        } else if (choice.equals("delete")) {
            deleteTweet(args);
        }

    }

    protected void postTweet(String[] args) {
        if (args.length != 3)
            throw new RuntimeException("Usage: TwitterCLIApp post \"tweet_text\"latitude:longitude\"");

        Double lat = null,lon =null;
        String tweet_txt = args[1];
        String coordin = args[2];
        String[] coordinArray = coordin.split(":");
        if (coordinArray.length != 2 || StringUtil.isEmpty(tweet_txt))
            throw new RuntimeException("Invalid location format \nUsage: TwitterCLIApp post \"tweet_text\"\"latitude:longitude\"");

        try {
            lat = Double.parseDouble(coordinArray[0]);
            lon = Double.parseDouble(coordinArray[1]);
        } catch (RuntimeException e){
            throw new RuntimeException(e);
        }

        service.postTweet(tweet_txt, lat, lon);
    }

    protected void showTweet(String[] args){
        if (args.length < 2)
            throw new RuntimeException("Usage: TwitterCLIApp show tweet_id (fields)");

        String[] fieldsArray = null;
        String tweet_id = null;


        if (args.length == 3){
            String fields = args[2];
            if (StringUtil.isEmpty(fields))
                throw new RuntimeException("Error: empty fields");

            fieldsArray = fields.split(",");

        } else if (args.length == 2){
            tweet_id = args[1];
            if (StringUtil.isEmpty(tweet_id))
                throw new RuntimeException("Error: empty ID");

        }
        service.showTweet(tweet_id, fieldsArray);
    }

    protected void deleteTweet(String[] args){
        if (args.length != 2 || StringUtil.isEmpty(args[1]))
            throw new RuntimeException("Usage: TwitterCLIApp deleteTweets tweet_ids");

        String tweetIds = args[1];
        String[] ids = tweetIds.split(",");
        service.deleteTweets(ids);

    }


}
