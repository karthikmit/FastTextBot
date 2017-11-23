package com.makemytrip.responders;

import edu.stanford.nlp.simple.Sentence;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karthik on 13/11/17.
 */
// @Intent(label="__label__train_status_query")
public class TrainStatusIntentResponder extends IntentResponder {
    @Override
    protected String getIntentCheckHeuristicFailureMessage(String text) {
        return "Train Status intent doesn't match for the text :: " + text;
    }

    @Override
    protected String getCommandPrefix() {
        return "#trainStatus" + SPACE;
    }

    @Override
    protected List<String> fetchEntities(String textNormalized) {
        // Find the Train Number in the text. Luckily Train Number is the number, so find POS tags which are CD.
        Sentence sentence = new Sentence(textNormalized);
        List<String> posTags = sentence.posTags();
        List<String> words = sentence.words();
        List<String> wordsWhichAreNumbers = new ArrayList<>();
        for(int index = 0; index < posTags.size(); index++) {
            boolean isNumber = posTags.get(index).equals("CD");
            if(isNumber) {
                wordsWhichAreNumbers.add(words.get(index));
            }
        }

        String identifiedTrainNumber = wordsWhichAreNumbers.get(0);
        return new ArrayList<String>() {{
            add(identifiedTrainNumber);
        }};
    }

    @Override
    protected boolean checkIfRightIntent(String textNormalized) {
        return textNormalized != null && textNormalized.contains("train");
    }
}
