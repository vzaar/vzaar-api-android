package com.vzaar.apiclient.sample.categorylist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.vzaar.apiclient.model.Category;
import com.vzaar.apiclient.sample.R;

class CategoryListItemViewHolder extends RecyclerView.ViewHolder {
    private final TextView nameText;

    CategoryListItemViewHolder(View view) {
        super(view);
        nameText = (TextView) view.findViewById(R.id.category_item_text_name);
    }

    void bind(Category category) {
        nameText.setText(category.getName());
    }
}