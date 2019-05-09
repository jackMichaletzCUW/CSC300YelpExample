package com.example.csc300yelpexample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class YelpBusinessArrayAdapter extends ArrayAdapter
{
    Context context;
    int resource;
    YelpBusiness[] theBusinesses;

    public YelpBusinessArrayAdapter(Context context, int resource, YelpBusiness[] theBusinesses)
    {
        super(context, resource, theBusinesses);

        this.context = context;
        this.resource = resource;
        this.theBusinesses = theBusinesses;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View listItem = convertView;

        if(listItem == null)
        {
            // Inflate us
            listItem = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        TextView nameTxt = listItem.findViewById(R.id.nameTxt);
        TextView phoneTxt = listItem.findViewById(R.id.phoneTxt);

        if(theBusinesses[position] != null)
        {
            nameTxt.setText(theBusinesses[position].getName());
            phoneTxt.setText(theBusinesses[position].getPhoneNumber());
        }
        else
        {
            nameTxt.setText("");
            phoneTxt.setText("");
        }

        return listItem;
    }
}
