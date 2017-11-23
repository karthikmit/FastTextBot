package com.makemytrip.responders;

import com.makemytrip.exceptions.IntentCheckFailureException;

import java.util.List;
import java.util.StringJoiner;

/**
 * Interface for intent responders.
 */
public abstract class IntentResponder {
    public static final String SPACE = " ";

    public String respond(String text) throws IntentCheckFailureException {
        String textNormalized = text.toLowerCase();

        // Assert this is the right sentence for PNR Status query.
        boolean intentCheck = checkIfRightIntent(textNormalized);
        if(!intentCheck) {
            throw new IntentCheckFailureException(getIntentCheckHeuristicFailureMessage(text));
        }
        List<String> entities = fetchEntities(textNormalized);
        if(entities == null || entities.size() == 0) return getCommandPrefix();

        StringJoiner joiner = new StringJoiner(" ");
        entities.forEach(joiner::add);
        String entitiesStringified = joiner.toString();

        return getCommandPrefix() + entitiesStringified;
    }

    protected abstract String getIntentCheckHeuristicFailureMessage(String text);

    protected abstract String getCommandPrefix();

    protected abstract List<String> fetchEntities(String textNormalized);

    protected abstract boolean checkIfRightIntent(String textNormalized);
}
