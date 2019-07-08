package ca.jrvs.apps.twitter.example;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.IOException;

public class JSonParser {

    public static final String companystr = "{   \"symbol\":\"AAPL\",\n" +
            "   \"companyName\":\"Apple Inc.\",   \"exchange\":\"Nasdaq Global Select\",   \"description\":\"Apple Inc is designs, manufactures and markets mobile communication and media devices and personal computers, and sells a variety of related software, services, accessories, networking solutions and third-party digital content and applications.\",   \"CEO\":\"Timothy D. Cook\",   \"sector\":\"Technology\",   \"financials\":[      {         \"reportDate\":\"2018-12-31\",         \"grossProfit\":32031000000,         \"costOfRevenue\":52279000000,         \"operatingRevenue\":84310000000,         \"totalRevenue\":84310000000,         \"operatingIncome\":23346000000,         \"netIncome\":19965000000      },      {         \"reportDate\":\"2018-09-30\",         \"grossProfit\":24084000000,         \"costOfRevenue\":38816000000,         \"operatingRevenue\":62900000000,         \"totalRevenue\":62900000000,         \"operatingIncome\":16118000000,         \"netIncome\":14125000000      }   ],   \"dividends\":[\n" +
            "5. Discuss some of the ?nancial terms 6. Read the o?cial document and write the following two methods: https://github.com/FasterXML/jackson-databind/\n" +
            "      {         \"exDate\":\"2018-02-09\",         \"paymentDate\":\"2018-02-15\",         \"recordDate\":\"2018-02-12\",         \"declaredDate\":\"2018-02-01\",         \"amount\":0.63      },      {         \"exDate\":\"2017-11-10\",         \"paymentDate\":\"2017-11-16\",         \"recordDate\":\"2017-11-13\",         \"declaredDate\":\"2017-11-02\",         \"amount\":0.63      }   ] }";


    /**
     *
     *  Convert a java object to JSON string
     *  @param object input object
     *  @return JSON String
     *  @throws JsonProcessingException
     *  */
    public static String toJson(Object object, boolean prettyJson, boolean includeNullValues) throws JsonProcessingException {
        ObjectMapper m = new ObjectMapper();
        if (!includeNullValues){
            m.setSerializationInclusion(Include.NON_NULL);
        }
        if (prettyJson){
            m.enable(SerializationFeature.INDENT_OUTPUT);
        }
        return m.writeValueAsString(object);

    }


    /**
     *  Parse JSON string to a object
     *  @param json JSON str
     *  @param clazz object class
     *  @param <T> Type
     *  @return Object
     *  @throws IOException
     *  */

    public static <T> T toObjectFromJson(String json, Class clazz) throws IOException {
        ObjectMapper m = new ObjectMapper();
        return (T) m.readValue(json, clazz);
    }

    public static void main(String[] args) throws IOException {
        Company company = new Company();
        company.setCompanyName("Jarvis");
        System.out.println(toJson(company, true, true));

        Company company = toObjectFromJson(companystr, Company.class);
        System.out.println(company.toString());
    }

}
