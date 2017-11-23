package com.makemytrip.entity_extractors;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karthik on 16/11/17.
 */
public class TrainNumberEntityExtractor implements EntityExtractor {

    @Override
    public List<Entity> extractEntity(String text) {
        List<String> numberContentList = NumberExtractor.numberExtractor.extractNumberContent(text);
        List<Entity> entityList = new ArrayList<>();

        if(numberContentList == null || numberContentList.size() == 0) return entityList;
        for(String numberContent : numberContentList) {
            if(numberContent.length() == 5) {
                entityList.add(new Entity(EntityType.TRAIN_NUMBER, numberContent));
                return entityList;
            }
        }

        return entityList;
    }
}
