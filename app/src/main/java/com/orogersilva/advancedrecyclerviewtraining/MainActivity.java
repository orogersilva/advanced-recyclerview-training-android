package com.orogersilva.advancedrecyclerviewtraining;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.orogersilva.advancedrecyclerviewtraining.helper.OnStartDragListener;
import com.orogersilva.advancedrecyclerviewtraining.helper.SimpleItemTouchHelperCallback;

public class MainActivity extends AppCompatActivity implements OnStartDragListener {

    // region FIELDS

    private RecyclerView mRecyclerView;
    private RecyclerViewListAdapter mAdapter;

    private ItemTouchHelper mItemTouchHelper;

    // endregion

    // region ACTIVITY LIFECYCLE METHODS

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.itemsRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new RecyclerViewListAdapter(this);

        mRecyclerView.setAdapter(mAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mAdapter);

        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {

        mItemTouchHelper.startDrag(viewHolder);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    // endregion
}
