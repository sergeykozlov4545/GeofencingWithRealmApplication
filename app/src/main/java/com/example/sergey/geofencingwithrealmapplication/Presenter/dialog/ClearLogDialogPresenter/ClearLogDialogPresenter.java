package com.example.sergey.geofencingwithrealmapplication.Presenter.dialog.ClearLogDialogPresenter;

import com.example.sergey.geofencingwithrealmapplication.Presenter.base.MVPPresenter;
import com.example.sergey.geofencingwithrealmapplication.View.dialog.base.DialogView;

public interface ClearLogDialogPresenter extends MVPPresenter<DialogView> {
    void onConfirmClearLogButtonClick();

    void onNegativeButtonClick();
}
