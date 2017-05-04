package com.fileparsing.fileparser.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fileparsing.fileparser.R;

import java.util.ArrayList;


public class ParseResultsAdapter extends RecyclerView.Adapter<ParseResultViewHolder>{

    private final Context context;
    private final ArrayList<String> items;

    public ParseResultsAdapter(Context context, ArrayList<String> items){
        this.context = context;
        this.items = items;
    }

    @Override
    public ParseResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.parse_result, parent, false);
        return new ParseResultViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ParseResultViewHolder holder, int position) {
        holder.bindDataToViews(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}


