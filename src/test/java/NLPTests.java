/**
 * Created by karthik on 10/11/17.
 */
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.simple.*;
import org.junit.Test;

import java.util.List;

public class NLPTests {

    @Test
    public void analyseSentences() {
        Sentence sent = new Sentence("what is the status of my PNR 1234567890");

        List<String> tags = sent.nerTags();
        System.out.println(tags);

        List<String> posTags = sent.posTags();
        System.out.println(posTags);

        /*SemanticGraph semanticGraph = sent.dependencyGraph();
        System.out.println(semanticGraph);

        System.out.println("Topological Sorted :: " + semanticGraph.topologicalSort());*/

        List<String> words = sent.words();
        for(int wordIndex = 0; wordIndex < words.size(); wordIndex++) {
            System.out.println(words.get(wordIndex) + " :: " + sent.posTag(wordIndex));
        }
    }
}
