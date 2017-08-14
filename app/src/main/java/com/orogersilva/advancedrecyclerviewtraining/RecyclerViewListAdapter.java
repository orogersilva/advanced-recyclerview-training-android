package com.orogersilva.advancedrecyclerviewtraining;

import android.graphics.Color;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orogersilva.advancedrecyclerviewtraining.helper.ItemTouchHelperAdapter;
import com.orogersilva.advancedrecyclerviewtraining.helper.ItemTouchHelperViewHolder;
import com.orogersilva.advancedrecyclerviewtraining.helper.OnStartDragListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by orogersilva on 8/13/2017.
 */

public class RecyclerViewListAdapter extends RecyclerView.Adapter<RecyclerViewListAdapter.ItemViewHolder> implements ItemTouchHelperAdapter {

    // region FIELDS

    private static final String[] STRINGS = new String[]{
            "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"
    };

    private final List<String> mItems = new ArrayList<>();

    private final OnStartDragListener mDragStartListener;

    // endregion

    // region CONSTRUCTORS

    public RecyclerViewListAdapter(OnStartDragListener dragStartListener) {

        mDragStartListener = dragStartListener;

        mItems.addAll(Arrays.asList(STRINGS));
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item, parent, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {

        holder.textview.setText(mItems.get(position));

        holder.textview.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (MotionEventCompat.getActionMasked(motionEvent) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(holder);
                }

                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {

        if (fromPosition < toPosition) {

            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(mItems, i, i + 1);
            }

        } else {

            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(mItems, i, i - 1);
            }
        }

        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {

        mItems.remove(position);

        notifyItemRemoved(position);
    }

    // endregion

    // region VIEW HOLDER

    public static class ItemViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {

        // region FIELDS

        public final TextView textview;

        // endregion

        // region CONSTRUCTORS

        public ItemViewHolder(View itemView) {

            super(itemView);

            textview = (TextView) itemView.findViewById(R.id.itemviewTextView);
        }

        // endregion

        // region OVERRIDED METHODS

        @Override
        public void onItemSelected() {

            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {

            itemView.setBackgroundColor(0);
        }

        // endregion
    }

    // endregion
}
