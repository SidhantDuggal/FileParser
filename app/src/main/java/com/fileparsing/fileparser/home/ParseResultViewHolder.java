package com.fileparsing.fileparser.home;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.fileparsing.fileparser.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Viewholder for an indivdual parse result (either a group-header or a view-frequency string)
 */

public class ParseResultViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.parse_result_text)
    protected TextView textView;

    public ParseResultViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindDataToViews(String text){
        textView.setText(text);
    }

}
