package com.learncode.flipview.model;

/**
 * Created by Preeti on 24/07/16.
 */
public class FlickrApiResponse {

    private FlickrPhotos photos;

    private String stat;

    public FlickrPhotos getPhotos ()
    {
        return photos;
    }

    public void setPhotos (FlickrPhotos photos)
    {
        this.photos = photos;
    }

    public String getStat ()
    {
        return stat;
    }

    public void setStat (String stat)
    {
        this.stat = stat;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [photos = "+photos+", stat = "+stat+"]";
    }
}
