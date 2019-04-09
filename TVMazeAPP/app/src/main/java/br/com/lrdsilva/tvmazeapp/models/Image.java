
package br.com.lrdsilva.tvmazeapp.models;

import com.squareup.moshi.Json;

public class Image {

    @Json(name = "medium")
    private String medium;
    @Json(name = "original")
    private String original;

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

}
