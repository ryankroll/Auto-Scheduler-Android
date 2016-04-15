package com.example.rkroll.auto_scheduler;

import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseUser;

import java.util.List;

public class EmployeeAdapter
        extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {

    // listeners from MainActivity that are registered for each list item
    private final View.OnLongClickListener longClickListener;

    // List<String> used to obtain RecyclerView items' data
    //private final UserReaderDbHelper userDb;
    private final List<ParseUser> currentEmployees;

    // constructor
    public EmployeeAdapter(List<ParseUser> currentEmployees,
                           View.OnLongClickListener longClickListener) {
        this.currentEmployees = currentEmployees;
        this.longClickListener = longClickListener;
    }

    // nested subclass of RecyclerView.ViewHolder used to implement
    // the view-holder pattern in the context of a RecyclerView--the logic
    // of recycling views that have scrolled offscreen is handled for you
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView textView;

        // configures a RecyclerView item's ViewHolder
        public ViewHolder(View itemView,
                          View.OnLongClickListener longClickListener) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);

            // attach listeners to itemView
            itemView.setOnLongClickListener(longClickListener);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // sets up new list item and its ViewHolder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        // inflate the list_item layout
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.list_item, parent, false);

        // create a ViewHolder for current item
        return (new ViewHolder(view, longClickListener));

    }

    // sets the text of the list item to display the search tag
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(currentEmployees.get(position).getString("name"));
    }

    // returns the number of items that adapter binds
    @Override
    public int getItemCount() {
        return currentEmployees.size();
    }


}