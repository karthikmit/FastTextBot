package com.makemytrip.controllers;

import com.makemytrip.exceptions.IntentCheckFailureException;
import com.makemytrip.responders.PNRStatusIntentResponder;
import com.makemytrip.responders.TrainStatusIntentResponder;
import com.makemytrip.services.ClassifierService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Classifier exposes API for classifications tasks.
 */
@Controller
public class ClassifierController {

    private static Logger logger = LoggerFactory.getLogger(ClassifierController.class);
    private ClassifierService classifierService;

    @Autowired
    public ClassifierController(ClassifierService classifierService) {
        this.classifierService = classifierService;
    }

    @RequestMapping("/api/classify")
    @ResponseBody
    public String classify(@RequestParam(value = "text") String text) {
        logger.debug("Classify request received :: Text :: " + text);
        String labelIdentified = "train_status_query";

        labelIdentified = this.classifierService.classify(text);
        return labelIdentified;
    }

    @RequestMapping("/api/respond")
    @ResponseBody
    public String respond(@RequestParam(value = "text") String text) {
        logger.debug("Respond request received :: Text :: " + text);
        String labelIdentified = this.classifierService.classify(text);

        if(labelIdentified.equals("__label__pnr_query")) {
            PNRStatusIntentResponder pnrStatusIntentResponder = new PNRStatusIntentResponder();
            try {
                return pnrStatusIntentResponder.respond(text);
            } catch (IntentCheckFailureException e) {
            }
        } else if(labelIdentified.equals("__label__train_status_query")) {
            TrainStatusIntentResponder trainStatusIntentResponder = new TrainStatusIntentResponder();
            try {
                return trainStatusIntentResponder.respond(text);
            } catch (IntentCheckFailureException e) {
            }
        }


        return "Sorry, couldn't understand your query";
    }
}
