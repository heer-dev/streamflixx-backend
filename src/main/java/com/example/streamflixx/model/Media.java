package com.example.streamflixx.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "media")
public class Media {
    @Id
    private String id;
    private String name;
    private Double price;
    private String synopsis;
    private Boolean isMovie; // Ensure this matches with your repository queries
    private String smallPosterPath;
    private String largePosterPath;
    private Double rentPrice;
    private Double purchasePrice;

    private Boolean isFeatured;
    private String tag;

    // Constructors
    public Media() {
    }

    public Media(String id, String name, String synopsis, Boolean isMovie, String smallPosterPath, String largePosterPath, Double rentPrice, Double purchasePrice, Double price, Boolean isFeatured, String tag) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.synopsis = synopsis;
        this.isMovie = isMovie;
        this.smallPosterPath = smallPosterPath;
        this.largePosterPath = largePosterPath;
        this.rentPrice = rentPrice;
        this.purchasePrice = purchasePrice;
        this.isFeatured = isFeatured;
        this.tag=tag;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Double getPrice() { return price; } // Getter for the new price field
    public void setPrice(Double price) { this.price = price; } // Setter for the new price field
    public String getSynopsis() { return synopsis; }
    public void setSynopsis(String synopsis) { this.synopsis = synopsis; }
    public Boolean getIsMovie() { return isMovie; }
    public void setIsMovie(Boolean isMovie) { this.isMovie = isMovie; }
    public String getSmallPosterPath() { return smallPosterPath; }
    public void setSmallPosterPath(String smallPosterPath) { this.smallPosterPath = smallPosterPath; }
    public String getLargePosterPath() { return largePosterPath; }
    public void setLargePosterPath(String largePosterPath) { this.largePosterPath = largePosterPath; }
    public Double getRentPrice() { return rentPrice; }
    public void setRentPrice(Double rentPrice) { this.rentPrice = rentPrice; }
    public Double getPurchasePrice() { return purchasePrice; }
    public void setPurchasePrice(Double purchasePrice) { this.purchasePrice = purchasePrice; }
    public Boolean getIsFeatured() { return isFeatured; }
    public void setIsFeatured(Boolean isFeatured) { this.isFeatured = isFeatured; }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
