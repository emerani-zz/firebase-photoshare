package com.tryone.ezraerani.firebaseinsta;

import java.util.ArrayList;

/**
 * Created by ezraerani on 7/25/16.
 */
public class PhotoItem extends Object{

    private ArrayList<String> comments;
    private int likes;
    private String download_url;

    public PhotoItem() {
    }

    public PhotoItem(ArrayList<String> comments, int likes, String download_url) {
        this.comments = comments;
        this.likes = likes;
        this.download_url = download_url;
    }

    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }

    public ArrayList<String> getComments() {
        return comments;
    }

    public void setComments(ArrayList<String> comments) {
        this.comments = comments;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
