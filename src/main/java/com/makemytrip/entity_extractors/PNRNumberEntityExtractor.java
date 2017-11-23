package com.makemytrip.entity_extractors;

import java.util.ArrayList;
import java.util.List;

/**
 * PNR Number extractor.
 */
public class PNRNumberEntityExtractor implements EntityExtractor {

    @Override
    public List<Entity> extractEntity(String text) {
        List<String> numberContentList = NumberExtractor.numberExtractor.extractNumberContent(text);
        List<Entity> entityList = new ArrayList<>();

        if(numberContentList == null || numberContentList.size() == 0) return entityList;

        for(String numberContent : numberContentList) {
            if(numberContent.length() == 10) {
                entityList.add(new Entity(EntityType.PNR, numberContent));
                return entityList;
            }
        }

        return entityList;
    }
}
