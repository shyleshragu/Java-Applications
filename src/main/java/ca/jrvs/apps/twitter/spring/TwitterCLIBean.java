package ca.jrvs.apps.twitter.spring;

import ca.jrvs.apps.twitter.TwitterCLIRunner;
import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterRestDao;
import ca.jrvs.apps.twitter.dao.helper.ApacheHttpHelper;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.service.TwitterService;
import ca.jrvs.apps.twitter.service.TwitterServiceImp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

@Configuration
//@PropertySource("/home/centos/dev/jrvs/bootcamp/java_apps/src/main/resources/application.properties")
public class TwitterCLIBean {

    /*@Value("$(logging.level.root)")
    private String loggingLevelroot;

    @Value("$(logging.level.org.springframework.beans.factory")
    private String logginglevelorgspringframeworkbeansfactory;*/


    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(TwitterCLIBean.class);
        TwitterCLIRunner runner = context.getBean(TwitterCLIRunner.class);
        runner.run(args);

        //System.out.println(loggingLevelroot + ": " + logginglevelorgspringframeworkbeansfactory);

    }

    @Bean
    public TwitterCLIRunner twitterCLIRunner(TwitterService service) {
        return new TwitterCLIRunner(service);
    }

    @Bean
    public TwitterService twitterService(CrdDao dao) {
        return new TwitterServiceImp(dao);
    }

    @Bean
    public CrdDao twitterDao(HttpHelper httpHelper) {
        return new TwitterRestDao(httpHelper);
    }

    @Bean
    HttpHelper helper() {
        return new ApacheHttpHelper();
    }

}