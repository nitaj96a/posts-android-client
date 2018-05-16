package com.nitaj96a.postifi.Model;

import java.util.Date;

/**
 * Created by n on 5/16/2018.
 */

public class Comment {
    private int id;
    private String title;
    private String description;
    private Date date;
    private int likes;
    private int dislikes;
    private Post postId;
    private User owner;

    public Comment(){

    }

    public Comment(int id, String title, String description, Date date, int likes, int dislikes, Post postId, User owner) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.likes = likes;
        this.dislikes = dislikes;
        this.postId = postId;
        this.owner = owner;
    }

    public Comment(int id, String title, String description, Date date, int likes, int dislikes, Post postId) {
        this.id = id;

        this.title = title;
        this.description = description;
        this.date = date;
        this.likes = likes;
        this.dislikes = dislikes;
        this.postId = postId;
    }

    public Comment(int id, String title, String description, Date date, int likes, int dislikes) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.likes = likes;
        this.dislikes = dislikes;
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

    public Post getPostId() {
        return postId;
    }

    public void setPostId(Post postId) {
        this.postId = postId;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
