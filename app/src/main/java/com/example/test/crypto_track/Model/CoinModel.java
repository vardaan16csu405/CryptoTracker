package com.example.test.crypto_track.Model;

public class CoinModel {

    public String id;
    public String name;
    public String symbol;
    public String price_usd;
    public String price_change_1hr;
    public String price_change_24hr;
    public String price_change_7d;

    public CoinModel(){

    }

    public CoinModel(String id, String name, String symbol, String price_usd, String price_change_1hr, String price_change_24hr, String price_change_7d){
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.price_usd = price_usd;
        this.price_change_1hr = price_change_1hr;
        this.price_change_24hr = price_change_24hr;
        this.price_change_7d = price_change_7d;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getPrice_usd() {
        return price_usd;
    }

    public void setPrice_usd(String price_usd) {
        this.price_usd = price_usd;
    }

    public String getPrice_change_1hr() {
        return price_change_1hr;
    }

    public void setPrice_change_1hr(String price_change_1hr) {
        this.price_change_1hr = price_change_1hr;
    }

    public String getPrice_change_24hr() {
        return price_change_24hr;
    }

    public void setPrice_change_24hr(String price_change_24hr) {
        this.price_change_24hr = price_change_24hr;
    }

    public String getPrice_change_7d() {
        return price_change_7d;
    }

    public void setPrice_change_7d(String price_change_7d) {
        this.price_change_7d = price_change_7d;
    }
}
