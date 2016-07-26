package com.learncode.flipview.activity;

import java.util.ArrayList;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.SearchManager;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.learncode.flipview.R;
import com.learncode.flipview.adapter.DataAdapter;
import com.learncode.flipview.adapter.DataAdapter.OnItemClickListener;
import com.learncode.flipview.model.Data;
import com.learncode.flipview.model.FlickrApiResponse;
import com.learncode.flipview.model.FlickrPhoto;
import com.learncode.flipview.util.ApiInterface;
import com.learncode.flipview.util.FlickrManager;
import com.learncode.flipview.util.FlipPreferenceManager;
import com.learncode.flipview.util.Fliputils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.list)
    RecyclerView listView;

    DataAdapter mAdapter;
    ArrayList<FlickrPhoto> dataList = new ArrayList<FlickrPhoto>();

    LayoutManager layoutManager;

    Toolbar toolbar;
    FlipPreferenceManager mPreferenceManager;

    String tag ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolBar();



        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        100);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }else{
            loadPhotoFromFlickr();
        }

    }


    private ArrayList<Data> createList() {
        ArrayList<Data> datas = new ArrayList<Data>();

        for (int i = 0; i < 40; i++) {
            Data data = new Data("Hola " + i, "http://i.imgur.com/DvpvklR.png");
            datas.add(data);
        }
        return datas;
    }

    private void loadPhotoFromFlickr() {



        if(mAdapter==null) {
            mAdapter = new DataAdapter(this, dataList);
            layoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
            listView.setLayoutManager(layoutManager);
            listView.setAdapter(mAdapter);
            mAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                }
            });
        }




        mPreferenceManager = FlipPreferenceManager.instance(this);
        tag = mPreferenceManager.getLastSearch();
        if(TextUtils.isEmpty(tag))
        {
            tag = getString(R.string.default_key);
        }


        //url
        //https://api.flickr.com/services/rest/?method=flickr.photos.search&name=value&api_key=e4b54be8bc1646ad170ffa2de2942fbf&format=json&tags=computer

        if (FlickrManager.APIKEY_SEARCH_STRING.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY first from flickr.com", Toast.LENGTH_LONG).show();
            return;
        }
        Fliputils.showProgressDialog(this, "");
        ApiInterface apiService =
                FlickrManager.getClient().create(ApiInterface.class);

        Call<FlickrApiResponse> call = apiService.getPhotos(FlickrManager.APIKEY_SEARCH_STRING, tag);
        call.enqueue(new Callback<FlickrApiResponse>() {
            @Override
            public void onResponse(Call<FlickrApiResponse> call, Response<FlickrApiResponse> response) {
                if(response!=null && response.body()!=null && response.body().getPhotos()!=null) {
                    dataList.clear();
                    dataList.addAll(response.body().getPhotos().getPhoto());
                    mAdapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(MainActivity.this, "something wrong!", Toast.LENGTH_LONG).show();
                }
                Fliputils.hideProgressDialog(MainActivity.this);
            }

            @Override
            public void onFailure(Call<FlickrApiResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e("ERROR", t.toString());
                Fliputils.hideProgressDialog(MainActivity.this);
            }
        });
    }

    public void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("FlipView");

        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "clicking the toolbar!", Toast.LENGTH_SHORT).show();
                    }
                }

        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        // Retrieve the SearchView and plug it into SearchManager
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            public boolean onQueryTextChange(String newText) {
                // this is your adapter that will be filtered
                return true;
            }

            public boolean onQueryTextSubmit(String query) {
                //Here u can get the value "query" which is entered in the search box.
                if(!TextUtils.isEmpty(query)) {
                    mPreferenceManager.setLastSearch(query);
                    loadPhotoFromFlickr();
                }
                return true;
            }
        };
        searchView.setOnQueryTextListener(queryTextListener);
        return true;
    }

    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 100:
                if (grantResults.length > 0
                        || grantResults[0] == PackageManager.PERMISSION_GRANTED
                        || grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                   loadPhotoFromFlickr();

                } else {
                    Toast.makeText(MainActivity.this, "Image loading will not work", Toast.LENGTH_SHORT).show();
                    finish();
                }
                //return; // delete.
        }
    }


}
