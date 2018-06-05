package com.wristcode.dynamoexample;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

    Context context;

    public  MyAdapter(Context context){
        this.context=context;

    }

    @Override
    public int getCount() {
        return GenerateKeys.keyholder.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View row=view;
        viewholder viewholder=null;
        if(row==null){
            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            layoutInflater.inflate(R.layout.row,viewGroup);
            viewholder=new viewholder(row);
            row.setTag(viewholder);
        }
        else
        {
            viewholder= (MyAdapter.viewholder) row.getTag();
        }
        viewholder.txttitle.setText(GenerateKeys.keyholder.get(i).get("title").toString());

        Log.d("title",GenerateKeys.keyholder.get(i).get("title").toString());

        return null;
    }


    private class viewholder{
        public TextView txttitle, txttimestamp1, txttimestamp2;

        public viewholder(View view){

            txttitle = (TextView) view.findViewById(R.id.txttitle);
            txttimestamp1 = (TextView) view.findViewById(R.id.txttimestamp1);
            txttimestamp2 = (TextView) view.findViewById(R.id.txttimestamp2);

        }
    }
}
