package com.example.rkroll.auto_scheduler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class EmployeeAdapter
        extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {

    // listeners from MainActivity that are registered for each list item
    private final View.OnClickListener clickListener;
    private final View.OnLongClickListener longClickListener;

    // List<String> used to obtain RecyclerView items' data
    private final List<String> currentEmployees; // search tags

    // constructor
    public EmployeeAdapter(List<String> currentEmployees,
                           View.OnClickListener clickListener,
                           View.OnLongClickListener longClickListener) {
        this.currentEmployees = currentEmployees;
        this.clickListener = clickListener;
        this.longClickListener = longClickListener;
    }

    // nested subclass of RecyclerView.ViewHolder used to implement
    // the view-holder pattern in the context of a RecyclerView--the logic
    // of recycling views that have scrolled offscreen is handled for you
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView textView;

        // configures a RecyclerView item's ViewHolder
        public ViewHolder(View itemView,
                          View.OnClickListener clickListener,
                          View.OnLongClickListener longClickListener) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);

            // attach listeners to itemView
            itemView.setOnClickListener(clickListener);
            itemView.setOnLongClickListener(longClickListener);
        }
    }

    // sets up new list item and its ViewHolder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        // inflate the list_item layout
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.list_item, parent, false);

        // create a ViewHolder for current item
        return (new ViewHolder(view, clickListener, longClickListener));
    }

    // sets the text of the list item to display the search tag
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(currentEmployees.get(position));
    }

    // returns the number of items that adapter binds
    @Override
    public int getItemCount() {
        return currentEmployees.size();
    }
}