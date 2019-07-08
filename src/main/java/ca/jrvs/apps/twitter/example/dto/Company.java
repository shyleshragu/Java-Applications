package ca.jrvs.apps.twitter.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonAppend;

import java.util.List;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "symbol",
        "companyName",
        "exchange",
        "description",
        "CEO",
        "sector",
        "financials",
        "dividends"
})
public class Company {

    @JsonProperty("symbol")
    private String symbol;
    @JsonProperty("companyName")
    private String companyName;
    @JsonProperty("exchange")
    private String exchange;
    @JsonProperty("description")
    private String description;
    @JsonProperty("CEO")
    private String cEO;
    @JsonProperty("sector")
    private String sector;
    @JsonProperty("financials")
    private List<Financial> financials = null;
    @JsonProperty("dividends")
    private List<Dividend> dividends = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("symbol")
    public String getSymbol() {
        return symbol;
    }

    @JsonProperty("symbol")
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @JsonProperty("companyName")
    public String getCompanyName() {
        return companyName;
    }

    @JsonProperty("companyName")
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @JsonProperty("exchange")
    public String getExchange() {
        return exchange;
    }

    @JsonProperty("exchange")
    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("CEO")
    public String getCEO() {
        return cEO;
    }

    @JsonProperty("CEO")
    public void setCEO(String cEO) {
        this.cEO = cEO;
    }

    @JsonProperty("sector")
    public String getSector() {
        return sector;
    }

    @JsonProperty("sector")
    public void setSector(String sector) {
        this.sector = sector;
    }

    @JsonProperty("financials")
    public List<Financial> getFinancials() {
        return financials;
    }

    @JsonProperty("financials")
    public void setFinancials(List<Financial> financials) {
        this.financials = financials;
    }

    @JsonProperty("dividends")
    public List<Dividend> getDividends() {
        return dividends;
    }

    @JsonProperty("dividends")
    public void setDividends(List<Dividend> dividends) {
        this.dividends = dividends;
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
        return "Example{" +
                "symbol='" + symbol + '\'' +
                ", companyName='" + companyName + '\'' +
                ", exchange='" + exchange + '\'' +
                ", description='" + description + '\'' +
                ", CEO='" + cEO + '\'' +
                ", sector='" + sector + '\'' +
                ", financials=" + financials +
                ", dividends=" + dividends +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}