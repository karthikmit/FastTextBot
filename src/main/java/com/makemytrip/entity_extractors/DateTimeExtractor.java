package com.makemytrip.entity_extractors;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.time.SUTime;
import edu.stanford.nlp.time.TimeAnnotations;
import edu.stanford.nlp.time.TimeAnnotator;
import edu.stanford.nlp.time.TimeExpression;
import edu.stanford.nlp.util.CoreMap;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Created by karthik on 16/11/17.
 */
public class DateTimeExtractor implements EntityExtractor {

    @Override
    public List<Entity> extractEntity(String text) {
        Properties props = new Properties();
        AnnotationPipeline pipeline = new AnnotationPipeline();
        pipeline.addAnnotator(new TokenizerAnnotator(false));
        pipeline.addAnnotator(new WordsToSentencesAnnotator(false));
        pipeline.addAnnotator(new POSTaggerAnnotator(false));
        pipeline.addAnnotator(new TimeAnnotator("sutime", props));

        Annotation annotation = new Annotation(text);
        annotation.set(CoreAnnotations.DocDateAnnotation.class, getFormattedDate(new Date()));
        pipeline.annotate(annotation);

        List<Entity> entityList = new ArrayList<>();
        List<CoreMap> timexAnnsAll = annotation.get(TimeAnnotations.TimexAnnotations.class);
        for (CoreMap cm : timexAnnsAll) {
            SUTime.Time time = cm.get(TimeExpression.Annotation.class).getTemporal().getTime();
            entityList.add(new Entity(EntityType.SUTIME, time));
        }

        return entityList;
    }

    public static String getFormattedDate(Date date) {
        return new SimpleDateFormat("YYYY-MM-dd").format(date);
    }
}
