package com.vzaar.apiclient.sample.categorylist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vzaar.apiclient.model.Category;
import com.vzaar.apiclient.sample.R;
import com.vzaar.apiclient.sample.util.ItemSelectedListener;

import java.util.ArrayList;
import java.util.List;

class CategoryListRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final ItemSelectedListener<Category> interactionListener;
    private List<Category> categories;

    CategoryListRecyclerAdapter(ItemSelectedListener<Category> interactionListener) {
        this.categories = new ArrayList<>();
        this.interactionListener = interactionListener;
    }

    void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    @Override
    public CategoryListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_category_item, parent, false);
        return new CategoryListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Category item = categories.get(position);
        ((CategoryListItemViewHolder) holder).bind(item);
        ((CategoryListItemViewHolder) holder).itemView
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        interactionListener.onItemSelected(item);
                    }
                });
    }
}
