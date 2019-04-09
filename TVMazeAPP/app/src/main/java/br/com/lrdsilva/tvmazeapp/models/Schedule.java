
package br.com.lrdsilva.tvmazeapp.models;

import java.util.List;
import com.squareup.moshi.Json;

public class Schedule {

    @Json(name = "time")
    private String time;
    @Json(name = "days")
    private List<String> days = null;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<String> getDays() {
        return days;
    }

    public void setDays(List<String> days) {
        this.days = days;
    }

}
