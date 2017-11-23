package com.makemytrip.entity_extractors;

import java.util.List;

/**
 * Interface for entity extractor
 */
public interface EntityExtractor {

    List<Entity> extractEntity(String text);
}
