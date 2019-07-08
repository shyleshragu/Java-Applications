package ca.jrvs.apps.twitter.example.dto;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "reportDate",
        "grossProfit",
        "costOfRevenue",
        "operatingRevenue",
        "totalRevenue",
        "operatingIncome",
        "netIncome"
})
public class Financial {

    @JsonProperty("reportDate")
    private String reportDate;
    @JsonProperty("grossProfit")
    private long grossProfit;
    @JsonProperty("costOfRevenue")
    private long costOfRevenue;
    @JsonProperty("operatingRevenue")
    private long operatingRevenue;
    @JsonProperty("totalRevenue")
    private long totalRevenue;
    @JsonProperty("operatingIncome")
    private long operatingIncome;
    @JsonProperty("netIncome")
    private long netIncome;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("reportDate")
    public String getReportDate() {
        return reportDate;
    }

    @JsonProperty("reportDate")
    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    @JsonProperty("grossProfit")
    public long getGrossProfit() {
        return grossProfit;
    }

    @JsonProperty("grossProfit")
    public void setGrossProfit(long grossProfit) {
        this.grossProfit = grossProfit;
    }

    @JsonProperty("costOfRevenue")
    public long getCostOfRevenue() {
        return costOfRevenue;
    }

    @JsonProperty("costOfRevenue")
    public void setCostOfRevenue(long costOfRevenue) {
        this.costOfRevenue = costOfRevenue;
    }

    @JsonProperty("operatingRevenue")
    public long getOperatingRevenue() {
        return operatingRevenue;
    }

    @JsonProperty("operatingRevenue")
    public void setOperatingRevenue(long operatingRevenue) {
        this.operatingRevenue = operatingRevenue;
    }

    @JsonProperty("totalRevenue")
    public long getTotalRevenue() {
        return totalRevenue;
    }

    @JsonProperty("totalRevenue")
    public void setTotalRevenue(long totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    @JsonProperty("operatingIncome")
    public long getOperatingIncome() {
        return operatingIncome;
    }

    @JsonProperty("operatingIncome")
    public void setOperatingIncome(long operatingIncome) {
        this.operatingIncome = operatingIncome;
    }

    @JsonProperty("netIncome")
    public long getNetIncome() {
        return netIncome;
    }

    @JsonProperty("netIncome")
    public void setNetIncome(long netIncome) {
        this.netIncome = netIncome;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "Financial{" +
                "reportDate='" + reportDate + '\'' +
                ", grossProfit=" + grossProfit +
                ", costOfRevenue=" + costOfRevenue +
                ", operatingRevenue=" + operatingRevenue +
                ", totalRevenue=" + totalRevenue +
                ", operatingIncome=" + operatingIncome +
                ", netIncome=" + netIncome +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}