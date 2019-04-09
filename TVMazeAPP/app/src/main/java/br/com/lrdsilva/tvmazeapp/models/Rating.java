
package br.com.lrdsilva.tvmazeapp.models;

import com.squareup.moshi.Json;

public class Rating {

    @Json(name = "average")
    private Double average;

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

}
