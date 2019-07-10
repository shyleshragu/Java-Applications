package ca.jrvs.apps.twitter.example;

public class Calculator {

    public int evaluate(String expres){
        int sum = 0;
        for (String summard : expres.split("\\+"))
            sum += Integer.valueOf(summard);
        return sum;
    }
}
