
package br.com.lrdsilva.tvmazeapp.models;

import com.squareup.moshi.Json;

public class Links {

    @Json(name = "self")
    private Self self;
    @Json(name = "previousepisode")
    private Previousepisode previousepisode;

    public Self getSelf() {
        return self;
    }

    public void setSelf(Self self) {
        this.self = self;
    }

    public Previousepisode getPreviousepisode() {
        return previousepisode;
    }

    public void setPreviousepisode(Previousepisode previousepisode) {
        this.previousepisode = previousepisode;
    }

}
