        package com.example.realmdatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter
{

    Context context;
    ArrayList<Model>arrayList;


   MyAdapter(Context context,   ArrayList<Model>arrayList)
   {
       this.context=context;
       this.arrayList=arrayList;
   }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        convertView= View.inflate(context,R.layout.design,null);

        TextView txt1=convertView.findViewById(R.id.txt1);
        TextView txt2=convertView.findViewById(R.id.txt2);

        txt1.setText(arrayList.get(position).getName());
        txt2.setText(arrayList.get(position).getPass());


        return convertView;
    }
}
