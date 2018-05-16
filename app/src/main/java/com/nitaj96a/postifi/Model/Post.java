package com.nitaj96a.postifi.Model;

import android.graphics.Bitmap;
import android.location.Location;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by n on 5/16/2018.
 */

public class Post {
    private int id;
    private String title;
    private String description;
    private Bitmap photo;
    private Date date;
    private int likes;
    private int dislikes;
    private Location location;
    private Tag tag;
    private User owner;
    private ArrayList<Comment> comments;

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

}
