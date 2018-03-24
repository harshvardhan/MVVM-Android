package hellofresh.test.com.hellofresh.vo;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import hellofresh.test.com.hellofresh.db.HelloFreshTypeConverters;

@Entity(primaryKeys = "id")
@TypeConverters(HelloFreshTypeConverters.class)
public class Recipe {

    @SerializedName("calories")
    public String calories;

    @SerializedName("carbos")
    public String carbos;

    @SerializedName("card")
    public String card;

    @SerializedName("country")
    public String country;

    @SerializedName("deliverable_ingredients")
    public List<String> deliverableIngredients = null;

    @SerializedName("description")
    public String description;

    @SerializedName("difficulty")
    public Integer difficulty;

    @SerializedName("fats")
    public String fats;

    @SerializedName("favorites")
    public Integer favorites;

    @SerializedName("fibers")
    public String fibers;

    @SerializedName("headline")
    public String headline;

    @SerializedName("highlighted")
    public Boolean highlighted;

    @SerializedName("id")
    @NonNull
    public String id;

    @SerializedName("image")
    public String image;

    @SerializedName("ingredients")
    public List<String> ingredients = null;

    @SerializedName("keywords")
    public List<String> keywords = null;

    @SerializedName("name")
    public String name;

    @SerializedName("products")
    public List<String> products = null;

    @SerializedName("proteins")
    public String proteins;

    @SerializedName("rating")
    public Double rating;

    @SerializedName("ratings")
    public Integer ratings;

    @SerializedName("thumb")
    public String thumb;

    @SerializedName("time")
    public String time;

    @SerializedName("undeliverable_ingredients")
    public List<String> undeliverableIngredients = null;

    @SerializedName("weeks")
    public List<String> weeks = null;

    public boolean isFavourite;

    /**
     *
     * @param headline
     * @param deliverableIngredients
     * @param carbos
     * @param difficulty
     * @param highlighted
     * @param id
     * @param ingredients
     * @param time
     * @param description
     * @param name
     * @param fats
     * @param card
     * @param calories
     * @param products
     * @param fibers
     * @param keywords
     * @param image
     * @param favorites
     * @param weeks
     * @param country
     * @param undeliverableIngredients
     * @param rating
     * @param proteins
     * @param thumb
     * @param ratings
     */
    public Recipe(String calories, String carbos, String card, String country,
                  List<String> deliverableIngredients, String description, Integer difficulty,
                  String fats, Integer favorites, String fibers, String headline,
                  Boolean highlighted, String id, String image,
                  List<String> ingredients, List<String> keywords, String name,
                  List<String> products, String proteins, Double rating, Integer ratings,
                  String thumb, String time, List<String> undeliverableIngredients, List<String> weeks, boolean isFavourite) {
        this.calories = calories;
        this.carbos = carbos;
        this.card = card;
        this.country = country;
        this.deliverableIngredients = deliverableIngredients;
        this.description = description;
        this.difficulty = difficulty;
        this.fats = fats;
        this.favorites = favorites;
        this.fibers = fibers;
        this.headline = headline;
        this.highlighted = highlighted;
        this.id = id;
        this.image = image;
        this.ingredients = ingredients;
        this.keywords = keywords;
        this.name = name;
        this.products = products;
        this.proteins = proteins;
        this.rating = rating;
        this.ratings = ratings;
        this.thumb = thumb;
        this.time = time;
        this.undeliverableIngredients = undeliverableIngredients;
        this.weeks = weeks;
        this.isFavourite = isFavourite;
    }

    public String getCalories() {
        return this.calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getCarbos() {
        return this.carbos;
    }

    public void setCarbos(String carbos) {
        this.carbos = carbos;
    }

    public String getCard() {
        return this.card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<String> getDeliverableIngredients() {
        return this.deliverableIngredients;
    }

    public void setDeliverableIngredients(List<String> deliverableIngredients) {
        this.deliverableIngredients = deliverableIngredients;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDifficulty() {
        return this.difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public String getFats() {
        return this.fats;
    }

    public void setFats(String fats) {
        this.fats = fats;
    }

    public Integer getFavorites() {
        return this.favorites;
    }

    public void setFavorites(Integer favorites) {
        this.favorites = favorites;
    }

    public String getFibers() {
        return this.fibers;
    }

    public void setFibers(String fibers) {
        this.fibers = fibers;
    }

    public String getHeadline() {
        return this.headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public Boolean getHighlighted() {
        return this.highlighted;
    }

    public void setHighlighted(Boolean highlighted) {
        this.highlighted = highlighted;
    }

    @NonNull
    public String getId() {
        return this.id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getKeywords() {
        return this.keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getProducts() {
        return this.products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }

    public String getProteins() {
        return this.proteins;
    }

    public void setProteins(String proteins) {
        this.proteins = proteins;
    }

    public Double getRating() {
        return this.rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getRatings() {
        return this.ratings;
    }

    public void setRatings(Integer ratings) {
        this.ratings = ratings;
    }

    public String getThumb() {
        return this.thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<String> getUndeliverableIngredients() {
        return this.undeliverableIngredients;
    }

    public void setUndeliverableIngredients(List<String> undeliverableIngredients) {
        this.undeliverableIngredients = undeliverableIngredients;
    }

    public List<String> getWeeks() {
        return this.weeks;
    }

    public void setWeeks(List<String> weeks) {
        this.weeks = weeks;
    }

    public boolean isFavourite() {
        return this.isFavourite;
    }

    public void setFavourite(boolean favourite) {
        this.isFavourite = favourite;
    }
}
