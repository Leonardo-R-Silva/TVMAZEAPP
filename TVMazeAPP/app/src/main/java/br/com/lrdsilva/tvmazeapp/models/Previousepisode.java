
package br.com.lrdsilva.tvmazeapp.models;

import com.squareup.moshi.Json;

public class Previousepisode {

    @Json(name = "href")
    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

}
