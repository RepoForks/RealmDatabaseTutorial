package com.okason.prontoquotes.core.listeners;

/**
 * Created by Valentine on 2/24/2016.
 */
public interface OnDatabaseOperationCompleteListener {
    void onSaveOperationFailed(String error);
    void onSaveOperationSucceeded(long id);
    void onDeleteOperationCompleted(String message);
    void onDeleteOperationFailed(String error);
    void onUpdateOperationCompleted(String message);
    void onUpdateOperationFailed(String error);
}
