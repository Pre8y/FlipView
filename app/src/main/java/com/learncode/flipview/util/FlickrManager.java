package com.learncode.flipview.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Preeti on 24/07/16.
 */
public class FlickrManager {


    //https://api.flickr.com/services/rest/?method=flickr.photos.search&name=value&api_key=e4b54be8bc1646ad170ffa2de2942fbf&format=json&tags=computer

    // String to create Flickr API urls
    public static final String FLICKR_BASE_URL = "https://api.flickr.com";

    //You can set here your API_KEY
    public static final String APIKEY_SEARCH_STRING = "e4b54be8bc1646ad170ffa2de2942fbf";



    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit == null) {

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(FLICKR_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}
