package com.tryone.ezraerani.firebaseinsta;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ezraerani on 7/25/16.
 */
public class PhotoItem extends Object{

    private Map<String, String> comments;
    private int likes;
    private String download_url;
    private String photo_key;

    public PhotoItem() {
    }

    public PhotoItem(HashMap<String, String> comments, int likes, String download_url, String photo_key) {
        this.comments = comments;
        this.likes = likes;
        this.download_url = download_url;
        this.photo_key = photo_key;
    }

    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }

    public Map<String, String> getComments() {
        return comments;
    }

    public void setComments(HashMap<String, String> comments) {
        this.comments = comments;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getPhoto_key() {
        return photo_key;
    }

    public void setPhoto_key(String photo_key) {
        this.photo_key = photo_key;
    }
}
