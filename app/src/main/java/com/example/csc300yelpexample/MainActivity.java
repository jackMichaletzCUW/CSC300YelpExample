package com.example.csc300yelpexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        YelpAPI yelp = new YelpAPI();
        yelp.start();

        // Wait until we have the data
        while(!yelp.haveData)
        {

        }

        ListView businessList = this.findViewById(R.id.businessList);

        YelpBusinessArrayAdapter arrayAdapter = new YelpBusinessArrayAdapter(this, R.layout.list_view_row_advanced, Core.theBusinesses);

        businessList.setAdapter(arrayAdapter);

        businessList.invalidateViews();
    }
}
