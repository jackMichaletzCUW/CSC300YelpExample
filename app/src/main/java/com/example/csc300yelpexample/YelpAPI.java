package com.example.csc300yelpexample;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.Scanner;

public class YelpAPI extends Thread
{
    public boolean haveData = false;

    @Override
    public void run()
    {
        try
        {
            // Set up the request to retrieve data in JSON format
            URL businessEndpoint = new URL("https://api.yelp.com/v3/businesses/search?location=Mequon+WI&categories=restaurants");
            HttpURLConnection conn = (HttpURLConnection)businessEndpoint.openConnection();
            conn.setRequestProperty("Authorization", "Bearer zdVXLEp9Xo7aw7jqPiPd74N7FiqMMkHV2quqUR2RvOkdi0RNcjDy7V--RZGhmd4yoNjuS9mF6JbvfokUf3kS8B09UsijMV84O6zz8Kzo6ApqPHvsfFEqfRmD-BXLXHYx");
            Scanner input = new Scanner(conn.getInputStream());
            String data = "";

            // Get the data
            while(input.hasNextLine())
            {
                data += input.nextLine();
            }

            input.close();

            System.out.println("*** DATA " + data);

            // Parse the data
            JSONObject jobj = new JSONObject(data);

            JSONArray jbusinesses = jobj.getJSONArray("businesses");

            // Take the JSON and put it into our YelpBusiness array
            Core.theBusinesses = new YelpBusiness[jbusinesses.length()];

            for(int jc = 0; jc < jbusinesses.length(); jc++)
            {
                Core.theBusinesses[jc] = new YelpBusiness(
                    jbusinesses.getJSONObject(jc).getString("name"),
                    jbusinesses.getJSONObject(jc).getString("display_phone")
                );

                System.out.println("*** " + Core.theBusinesses[jc].getName() + " " + Core.theBusinesses[jc].getPhoneNumber());
            }

            // Set the flag that the data has been retrieved
            this.haveData = true;
        }
        catch(Exception e)
        {

        }
    }
}
