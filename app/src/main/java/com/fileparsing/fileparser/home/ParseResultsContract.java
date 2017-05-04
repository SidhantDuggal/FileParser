package com.fileparsing.fileparser.home;

import java.io.File;
import java.util.ArrayList;

/**
 * Contract that defines the methods that the view and presenter must implement for successful file
 * parsing.
 */

public class ParseResultsContract {

    public interface View{
        void initViews();
        void displayFileChooser();
        void displayParsingResult(ArrayList<String> items);
    }

    public interface Presenter{
        void onFileParsingButtonClick(android.view.View view);
        void onFileSelected(File file);
    }

}
