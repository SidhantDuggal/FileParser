package com.fileparsing.fileparser.home;

import com.fileparsing.fileparser.utils.FileUtils;
import com.fileparsing.fileparser.utils.MapUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class that acts as the data source for the presenter. Contains data-manipulation techniques
 */

public class HomeDataSource {

    private File dataSource;
    private static final String ERROR_MSG = "Oops! Some error occurred. Please try again";
    private final static String WORD_REGEX_PATTERN = "[\\w']+";
    private final static int RANGE_MULTIPLIER = 10;

    public void setDataSource(File file){
        this.dataSource = file;
    }

    /**
     * Method that generates <word>:<frequency> map
     */
    private HashMap<String, Integer> getWordFreqMap(String text){
        HashMap<String, Integer> wordFreq = new HashMap<>();
        Pattern p = Pattern.compile(WORD_REGEX_PATTERN);
        Matcher m = p.matcher(text);
        String word;
        while (m.find()){
            word = text.substring(m.start(), m.end());
            if (wordFreq.containsKey(word)){
                wordFreq.put(word, wordFreq.get(word) + 1);
            }
            else{
                wordFreq.put(word, 1);
            }
        }
        return wordFreq;
    }

    /**
     * Method for parsing the word-frequency map and creating the final strings
     */
    private ArrayList<String> createDisplayStrings(Map<String, Integer> sortedWordFreq){
        String word;
        ArrayList<String> items = new ArrayList<>();
        int currRangeMin = 0, i = 0, freq;
        for(Map.Entry<String, Integer> entry : sortedWordFreq.entrySet()) {
            word = entry.getKey();
            freq = entry.getValue();
            if (i == 0){
                if (freq >= 1 && freq <= RANGE_MULTIPLIER){
                    currRangeMin = 0;
                    items.add((currRangeMin * RANGE_MULTIPLIER) + " - "
                            + ((currRangeMin + 1) * RANGE_MULTIPLIER));
                }else {
                    currRangeMin = freq / RANGE_MULTIPLIER;
                    items.add((currRangeMin * RANGE_MULTIPLIER) + " - "
                            + ((currRangeMin + 1) * RANGE_MULTIPLIER));
                }
            }else {
                if ((currRangeMin + 1) * RANGE_MULTIPLIER < freq){
                    currRangeMin = freq / RANGE_MULTIPLIER;
                    items.add((currRangeMin * RANGE_MULTIPLIER) + " - "
                            + ((currRangeMin + 1) * RANGE_MULTIPLIER));
                }
            }
            items.add("   " + word + " " + freq);
            i++;
        }
        return items;
    }

    public ArrayList<String> getParseResults(){
        String fileContent;
        ArrayList<String> items = new ArrayList<>();
        try {
            fileContent = FileUtils.readFileContent(dataSource);
        }
        catch (IOException e) {
            e.printStackTrace();
            items.add(ERROR_MSG);
            return items;
        }
        HashMap<String, Integer> wordFreq = getWordFreqMap(fileContent);
        Map<String, Integer> sortedWordFreq = MapUtils.sortByValue(wordFreq);
        items = createDisplayStrings(sortedWordFreq);
        return items;
    }
}
