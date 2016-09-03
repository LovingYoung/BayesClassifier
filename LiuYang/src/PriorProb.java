/**
 * Created by liuyang on 16-6-18.
 */
public class PriorProb {
    private static TrainingData trainingData = new TrainingData();

    /**
     * 返回先验概率 某个分类的文件数量/总训练集的文件数量
     * @param classification 给定分类
     * @return 先验概率
     */
    public static double calculatePrior(String classification){
        double CountOfClassification = trainingData.getFileCount(classification);
        double CountOfAllFiles = trainingData.getTrainingCount();
        return CountOfClassification/CountOfAllFiles;
    }
}
