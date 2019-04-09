
package br.com.lrdsilva.tvmazeapp.models;

import com.squareup.moshi.Json;

public class Country {

    @Json(name = "name")
    private String name;
    @Json(name = "code")
    private String code;
    @Json(name = "timezone")
    private String timezone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

}
