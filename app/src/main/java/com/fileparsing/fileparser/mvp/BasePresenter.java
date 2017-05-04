package com.fileparsing.fileparser.mvp;

/**
 * BasePresenter that is to be inherited by any presenter
 */
public class BasePresenter<T extends MvpView> implements Presenter<T> {

    private T mMvpView;


    @Override
    public void attachView(T mvpView) {
        if (mvpView == null){
            throw new NullPointerException("mvpView can't be null");
        }
        mMvpView = mvpView;
    }

    @Override
    public void detachView() {
        mMvpView = null;
    }

    // Stub method signatures for propagating activity/fragment lifecycle changes to Presenter

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public T getMvpView() {
        return mMvpView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}
