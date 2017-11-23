package com.makemytrip.responders;

import com.makemytrip.exceptions.IntentCheckFailureException;
import edu.stanford.nlp.simple.Sentence;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Responder for PNR intents.
 */
//@Intent(label="__label__pnr_query")
public class PNRStatusIntentResponder extends IntentResponder {

    @Override
    protected String getIntentCheckHeuristicFailureMessage(String text) {
        return "PNR intent doesn't look right for :: " + text;
    }

    @Override
    protected String getCommandPrefix() {
        return "#checkPnr" + SPACE;
    }

    @Override
    public List<String> fetchEntities(String text) {
        // Find the PNR in the text. Luckily PNR is the number, so find POS tags which are CD.
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

        String identifiedPNR = wordsWhichAreNumbers.get(0);
        return new ArrayList<String>() {{
            add(identifiedPNR);
        }};
    }

    @Override
    public boolean checkIfRightIntent(String text) {
        return text != null && text.contains("pnr");
    }
}
