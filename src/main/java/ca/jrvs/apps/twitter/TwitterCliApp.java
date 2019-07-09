package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterRestDao;
import ca.jrvs.apps.twitter.dao.helper.ApacheHttpHelper;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;

public class TwitterCliApp {

    public static void main(String[] args) {
        HttpHelper httpHelper = new ApacheHttpHelper();
        CrdDao dao = new TwitterRestDao(httpHelper);
        TwitterCLIRunner runner = new TwitterCliRunner(service);
        runner.run(args);
    }
}
