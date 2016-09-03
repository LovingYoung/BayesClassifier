/**
 * Created by liuyang on 16-6-18.
 */

import org.apdplat.word.segmentation.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * 贝叶斯分类器
 */
public class BayesClassifier {
    private TrainingData trainingData = new TrainingData();
    private String trainingPath;
    private static double zoom = 30;

    private double calculateConditionalProb(String classification, String[] keywords){
        double retValue = 1;
        for(int i = 0; i < keywords.length; i++){
            retValue = retValue * ConditionalProb.calculateCondionalProb(classification, keywords[i]) * zoom;
        }
        retValue = retValue * PriorProb.calculatePrior(classification);
        return retValue;
    }

    public String classify(String text){
        List<Word> words = ChineseSplit.split(text);
        String[] terms = ListOfWordToStringArray(words);
        String[] classifications = trainingData.getTrainingClassification();
        double prob = 0;
        List<Classify> results = new ArrayList<>();
        for(int i = 0; i < classifications.length; i++){
            String classification = classifications[i];
            prob = calculateConditionalProb(classification, terms);
            Classify result = new Classify();
            result.classification = classification;
            result.prob = prob;
            System.out.println("In process ...");
            System.out.println(classification + ":" + prob);
            results.add(result);
        }
        Classify max = null;
        for(int i = 0; i < results.size(); i++){
            if(max == null || max.prob < results.get(i).prob){
                max = results.get(i);
            }
        }
        return max.classification;
    }

    /**
     * 将List&gt;Word&lt;转换为String[]
     * @param words 需要转换的List&gt;Word&lt;
     * @return 转换好的String[]
     */
    private String[] ListOfWordToStringArray(List<Word> words) {
        String[] result = new String[words.size()];
        for(int i = 0; i < words.size(); i++){
            result[i] = words.get(i).toString();
        }
        return result;
    }
}
