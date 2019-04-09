
package br.com.lrdsilva.tvmazeapp.models;

import java.util.List;
import com.squareup.moshi.Json;

public class Show {

    @Json(name = "id")
    private Integer id;
    @Json(name = "url")
    private String url;
    @Json(name = "name")
    private String name;
    @Json(name = "type")
    private String type;
    @Json(name = "language")
    private String language;
    @Json(name = "genres")
    private List<String> genres = null;
    @Json(name = "status")
    private String status;
    @Json(name = "runtime")
    private Integer runtime;
    @Json(name = "premiered")
    private String premiered;
    @Json(name = "officialSite")
    private String officialSite;
    @Json(name = "schedule")
    private Schedule schedule;
    @Json(name = "rating")
    private Rating rating;
    @Json(name = "weight")
    private Integer weight;
    @Json(name = "network")
    private Network network;
    @Json(name = "webChannel")
    private Object webChannel;
    @Json(name = "externals")
    private Externals externals;
    @Json(name = "image")
    private Image image;
    @Json(name = "summary")
    private String summary;
    @Json(name = "updated")
    private Integer updated;
    @Json(name = "_links")
    private Links links;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public String getPremiered() {
        return premiered;
    }

    public void setPremiered(String premiered) {
        this.premiered = premiered;
    }

    public String getOfficialSite() {
        return officialSite;
    }

    public void setOfficialSite(String officialSite) {
        this.officialSite = officialSite;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public Object getWebChannel() {
        return webChannel;
    }

    public void setWebChannel(Object webChannel) {
        this.webChannel = webChannel;
    }

    public Externals getExternals() {
        return externals;
    }

    public void setExternals(Externals externals) {
        this.externals = externals;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getUpdated() {
        return updated;
    }

    public void setUpdated(Integer updated) {
        this.updated = updated;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

}
