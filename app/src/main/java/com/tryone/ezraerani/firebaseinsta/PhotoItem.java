package com.tryone.ezraerani.firebaseinsta;

import android.graphics.Bitmap;
import android.net.Uri;

import java.util.ArrayList;

/**
 * Created by ezraerani on 7/25/16.
 */
public class PhotoItem extends Object{

    private ArrayList<String> comments;
    private int likes;
    private Bitmap image;
    private String caption;
    private Uri downloadUrl;

    public PhotoItem() {
    }

    public PhotoItem(ArrayList<String> comments, int likes, Bitmap image, String caption, Uri downloadUrl) {
        this.comments = comments;
        this.likes = likes;
        this.image = image;
        this.caption = caption;
        this.downloadUrl = downloadUrl;
    }

    public Uri getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(Uri downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
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

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
