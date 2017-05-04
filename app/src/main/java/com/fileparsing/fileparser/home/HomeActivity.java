package com.fileparsing.fileparser.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.fileparsing.fileparser.R;
import com.fileparsing.fileparser.mvp.MvpView;
import com.fileparsing.fileparser.utils.FileChooser;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Activity that is launched first when the app starts. It allows user to choose a file and displays
 * the word frequency.
 */
public class HomeActivity extends AppCompatActivity implements MvpView, ParseResultsContract.View {

    private HomePresenter presenter;
    private FileChooser fileChooser;

    @BindView(R.id.parse_results)
    protected RecyclerView recyclerView;

    @BindView(R.id.parse_button)
    protected Button button;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        presenter = new HomePresenter();
        if (!(presenter instanceof ParseResultsContract.Presenter)){
            throw new ClassCastException(this.toString() + " must implement ParseResultsContract.Presenter");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.attachView(this);
        presenter.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
        presenter.detachView();
    }

    public void initViews(){
        View.OnClickListener buttonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onFileParsingButtonClick(v);
            }
        };
        button.setOnClickListener(buttonListener);
        recyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
    }

    @Override
    public void showMessage(String message) {

    }

    public void displayFileChooser(){
        if (fileChooser != null){
            fileChooser.showDialog();
            return;
        }
        FileChooser.FileSelectedListener listener = new FileChooser.FileSelectedListener() {
            @Override
            public void fileSelected(File file) {
                presenter.onFileSelected(file);
            }
        };
        fileChooser = new FileChooser(this);
        fileChooser.setFileListener(listener);
        fileChooser.showDialog();
    }

    public void displayParsingResult(ArrayList<String> items){
        recyclerView.setVisibility(View.VISIBLE);
        ParseResultsAdapter ca = new ParseResultsAdapter(HomeActivity.this, items);
        recyclerView.setAdapter(ca);
    }
}
