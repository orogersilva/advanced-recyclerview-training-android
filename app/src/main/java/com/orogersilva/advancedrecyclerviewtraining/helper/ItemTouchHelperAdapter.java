package com.orogersilva.advancedrecyclerviewtraining.helper;

/**
 * Created by orogersilva on 8/13/2017.
 */

public interface ItemTouchHelperAdapter {

    // region METHODS

    void onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);

    // endregion
}
