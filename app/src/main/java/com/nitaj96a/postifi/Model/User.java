package com.nitaj96a.postifi.Model;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by n on 5/16/2018.
 */

public class User {
    private int id;
    private String name;
    private String email; //maybe use Email?
    private String password;//oops
    private Bitmap photo;
    private ArrayList<Post> posts;
    private ArrayList<Comment> comments;

    public User(){

    }

    public User(int id, String name, String email, String password, Bitmap photo) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.photo = photo;
    }

    public User(int id, String name, String email, String password) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
}
