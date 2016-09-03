/**
 * Created by liuyang on 16-6-18.
 */

public class ConditionalProb {
    private static TrainingData trainingData = new TrainingData();
    private static final double M = 0; //加权

    /**
     * 返回给类条件概率(在某个分类中，包含关键字keyword文件的比例)
     * @param classification 给定分类
     * @param keyword 给定关键字
     * @return 类条件概率
     */
    public static double calculateCondionalProb(String classification, String keyword){
        double keyCount = trainingData.getKeywordsCount(classification, keyword);
        double fileCount = trainingData.getFileCount(classification);
        double classifications = trainingData.getTrainingClassification().length;
        return (keyCount + 1)/(fileCount + M + classifications);
    }
}
