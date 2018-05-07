package com.demo.song.model;

import android.graphics.Bitmap;

/**
 * Created by song on 2017/7/20.
 */
public class TitleAndPhoto {
    private String title;
    private Bitmap photo;

    public TitleAndPhoto(String title, Bitmap photo) {
        this.title = title;
        this.photo = photo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }
}
