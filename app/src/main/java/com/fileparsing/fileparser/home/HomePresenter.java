package com.fileparsing.fileparser.home;

import android.view.View;

import com.fileparsing.fileparser.mvp.BasePresenter;

import java.io.File;
import java.util.ArrayList;

/**
 * Presenter responsible for responding to HomeActivity's events. Acts as intermediate b/w datasource
 * (model) and view. Contains business logic.
 */
public class HomePresenter extends BasePresenter<HomeActivity> implements ParseResultsContract.Presenter{

    private HomeDataSource dataSource;

    @Override
    public void attachView(HomeActivity mvpView) {
        super.attachView(mvpView);
        if (!(getMvpView() instanceof ParseResultsContract.View)){
            throw new ClassCastException(mvpView.toString() + " must implement ParseResultsContract.View");
        }
        dataSource = new HomeDataSource();
    }

    @Override
    public void onStart() {
        super.onStart();
        getMvpView().initViews();
    }

    public void onFileParsingButtonClick(View view) {
        getMvpView().displayFileChooser();
    }

    /**
     * Method that asks model to read data from datasource, parse it and return the appropriate
     * items to be displayed by view.
     */
    public void onFileSelected(File file){
        dataSource.setDataSource(file);
        ArrayList<String> items = dataSource.getParseResults();
        getMvpView().displayParsingResult(items);
    }
}
