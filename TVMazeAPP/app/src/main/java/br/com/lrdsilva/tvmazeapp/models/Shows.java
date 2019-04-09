
package br.com.lrdsilva.tvmazeapp.models;

import com.squareup.moshi.Json;

public class Shows {

    @Json(name = "score")
    private Double score;
    @Json(name = "show")
    private Show show;

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

}
