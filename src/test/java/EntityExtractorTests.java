import com.makemytrip.entity_extractors.DateTimeExtractor;
import com.makemytrip.entity_extractors.Entity;
import com.makemytrip.entity_extractors.PNRNumberEntityExtractor;
import com.makemytrip.entity_extractors.TrainNumberEntityExtractor;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * Tests for entity extraction.
 */
public class EntityExtractorTests {

    @Test
    public void extractPNRNumberTest() {
        String testMessage = "What is the status of PNR 1234567890";

        PNRNumberEntityExtractor pnrNumberEntityExtractor = new PNRNumberEntityExtractor();
        List<Entity> entityList = pnrNumberEntityExtractor.extractEntity(testMessage);

        Assert.assertTrue(entityList != null && entityList.size() != 0);
        Assert.assertEquals("1234567890", entityList.get(0).getValue());
    }

    @Test
    public void extractTrainNumberTest() {
        String testMessage = "What is the status of train 16286";

        TrainNumberEntityExtractor trainNumberEntityExtractor = new TrainNumberEntityExtractor();
        List<Entity> entityList = trainNumberEntityExtractor.extractEntity(testMessage);

        Assert.assertTrue(entityList != null && entityList.size() != 0);
        Assert.assertEquals("16286", entityList.get(0).getValue());
    }

    @Test
    public void extractDatesTest() {
        String testMessage = "What is the date of 4 days from today";

        DateTimeExtractor dateTimeExtractor = new DateTimeExtractor();
        List<Entity> entityList = dateTimeExtractor.extractEntity(testMessage);
        System.out.println(entityList);
        Assert.assertTrue(entityList != null && entityList.size() != 0);
        long fourDaysFromTodayTime = new Date().getTime() + (4 * 24 * 60 * 60 * 1000);
        Assert.assertEquals(DateTimeExtractor.getFormattedDate(new Date(fourDaysFromTodayTime)), entityList.get(0).getValue().toString());

        testMessage = "Leaving home at tomorrow 6pm";

        entityList = dateTimeExtractor.extractEntity(testMessage);
        System.out.println(entityList);
        Assert.assertTrue(entityList != null && entityList.size() != 0);
        long tomorrowTime = new Date().getTime() + (24 * 60 * 60 * 1000);
        Assert.assertEquals(DateTimeExtractor.getFormattedDate(new Date(tomorrowTime)) + "T18:00", entityList.get(0).getValue().toString());
    }
}
