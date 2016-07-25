package com.learncode.flipview.model;

import java.util.ArrayList;

/**
 * Created by Preeti on 24/07/16.
 */
public class FlickrPhotos {


    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPerpage() {
        return perpage;
    }

    public void setPerpage(int perpage) {
        this.perpage = perpage;
    }

    public ArrayList<FlickrPhoto> getPhoto() {
        return photo;
    }

    public void setPhoto(ArrayList<FlickrPhoto> photo) {
        this.photo = photo;
    }

    int page, pages, perpage;
    String total;


    private ArrayList<FlickrPhoto> photo;



}
