package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterRestDao;
import ca.jrvs.apps.twitter.dao.helper.ApacheHttpHelper;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.service.TwitterService;
import ca.jrvs.apps.twitter.service.TwitterServiceImp;

public class TwitterCliApp {

    public static void main(String[] args) {
        HttpHelper httpHelper = new ApacheHttpHelper();
        CrdDao dao = new TwitterRestDao(httpHelper);
        TwitterService serv = new TwitterServiceImp(dao);
        TwitterCLIRunner runner = new TwitterCLIRunner(serv);
        runner.run(args);
    }
}
