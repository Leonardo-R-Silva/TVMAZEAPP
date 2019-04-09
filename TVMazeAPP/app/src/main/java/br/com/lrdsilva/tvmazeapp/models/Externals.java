
package br.com.lrdsilva.tvmazeapp.models;

import com.squareup.moshi.Json;

public class Externals {

    @Json(name = "tvrage")
    private Integer tvrage;
    @Json(name = "thetvdb")
    private Integer thetvdb;
    @Json(name = "imdb")
    private String imdb;

    public Integer getTvrage() {
        return tvrage;
    }

    public void setTvrage(Integer tvrage) {
        this.tvrage = tvrage;
    }

    public Integer getThetvdb() {
        return thetvdb;
    }

    public void setThetvdb(Integer thetvdb) {
        this.thetvdb = thetvdb;
    }

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

}
