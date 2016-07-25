package com.learncode.flipview.util;

import com.learncode.flipview.model.FlickrApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
 
 
public interface ApiInterface {
    @GET("/services/rest/?method=flickr.photos.search&name=value&format=json&nojsoncallback=1")
    Call<FlickrApiResponse> getPhotos(@Query("api_key") String apiKey, @Query("tags") String tags);
 
}