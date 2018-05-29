package com.nitaj96a.postifi.Model;

import android.graphics.Bitmap;
import android.location.Location;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by n on 5/16/2018.
 */

public class Post implements Serializable {
    @SerializedName(value = "id")
    @Expose
    private int id;
    @SerializedName(value = "title")
    @Expose
    private String title;
    @SerializedName(value = "description")
    @Expose
    private String description;
//    @SerializedName(value = "photo")
//    @Expose
    private transient Bitmap photo;
    @SerializedName(value = "date")
    @Expose
    private Date date;
    @SerializedName(value = "likes")
    @Expose
    private int likes;
    @SerializedName(value = "dislikes")
    @Expose
    private int dislikes;
    private transient Location location;
    // Maybe make this a list.. if a Post could have multiple tags?
//    @SerializedName("tag")
//    @Expose
    @SerializedName("tag")
    @Expose
    private Tag tag;
    @SerializedName(value = "owner")
    @Expose
    private User owner;
    private transient ArrayList<Comment> comments;

    public Post() {

    }

    public Post(int id, String title, String description, Bitmap photo, Date date, int likes, int dislikes, Location location, User owner) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.photo = photo;
        this.date = date;
        this.likes = likes;
        this.dislikes = dislikes;
        this.location = location;
        this.owner = owner;
    }

    public Post(int id, String title, String description, Bitmap photo, Date date, int likes, int dislikes, Location location) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.photo = photo;
        this.date = date;
        this.likes = likes;
        this.dislikes = dislikes;
        this.location = location;
    }

    public Post(int id, String title, String description, Bitmap photo, Date date, int likes, int dislikes, Location location, Tag tag) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.photo = photo;
        this.date = date;
        this.likes = likes;
        this.dislikes = dislikes;
        this.location = location;
        this.tag = tag;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }


    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", photo=" + photo +
                ", date=" + date +
                ", likes=" + likes +
                ", dislikes=" + dislikes +
                ", location=" + location +
                ", tag=" + tag +
                ", owner=" + owner +
                ", comments=" + comments +
                '}';
    }
}
