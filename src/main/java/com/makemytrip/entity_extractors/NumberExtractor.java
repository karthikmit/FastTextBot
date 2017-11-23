package com.makemytrip.entity_extractors;

import edu.stanford.nlp.simple.Sentence;

import java.util.ArrayList;
import java.util.List;

/**
 * Extractor for Numbers in the text.
 */
public class NumberExtractor {

    public static NumberExtractor numberExtractor = new NumberExtractor();

    public List<String> extractNumberContent(String text) {
        Sentence sentence = new Sentence(text);
        List<String> posTags = sentence.posTags();
        List<String> words = sentence.words();
        List<String> wordsWhichAreNumbers = new ArrayList<>();
        for(int index = 0; index < posTags.size(); index++) {
            boolean isNumber = posTags.get(index).equals("CD");
            if(isNumber) {
                wordsWhichAreNumbers.add(words.get(index));
            }
        }

        return wordsWhichAreNumbers;
    }
}
