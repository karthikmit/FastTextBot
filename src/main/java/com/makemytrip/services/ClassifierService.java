package com.makemytrip.services;

import com.github.jfasttext.JFastText;
import com.makemytrip.exceptions.TrainingDataSetFileMissingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Classifier Service implementation based on FastText.
 */
@Service
public class ClassifierService {

    private static final String BINARY_TRAINED_MODEL_WITHOUT_SUFFIX = "/data/ft/railinfo/model_railinfo_jft";
    private static final String BINARY_TRAINED_MODEL = BINARY_TRAINED_MODEL_WITHOUT_SUFFIX + ".bin";
    private static final String TRAINING_DATASET = "/data/ft/railinfo/railinfo_query_model.txt";

    private static Logger logger = LoggerFactory.getLogger(ClassifierService.class);
    private JFastText fastTextEngine = new JFastText();

    public ClassifierService() {
        assertModelExists();
        reloadModel();
    }

    public String classify(String text) {
        return fastTextEngine.predict(text);
    }

    private void reloadModel() {
        fastTextEngine.loadModel(BINARY_TRAINED_MODEL);
    }

    private void assertModelExists() {
        boolean binaryExists = new File(BINARY_TRAINED_MODEL).exists();
        if(!binaryExists) {
            boolean datasetExists = new File(TRAINING_DATASET).exists();
            if(!datasetExists) {
                logger.error("Training Dataset is missing !!");
                throw new TrainingDataSetFileMissingException();
            } else {
                trainDataset();
            }
        }
    }

    private void trainDataset() {
        fastTextEngine.runCmd(new String[] {
                "supervised",
                "-input", TRAINING_DATASET,
                "-output", BINARY_TRAINED_MODEL_WITHOUT_SUFFIX,
                "-epoch", "25",
                "-lr", "1.0"
        });
    }
}
