import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by liuyang on 16-6-18.
 */
public class TrainingData {
    private String[] trainingClassification;
    private File traningDir;
    private static String defaultPath = "TrainingData";

    public TrainingData(){
        traningDir = new File(defaultPath);
        if(!traningDir.isDirectory()){
            throw new IllegalArgumentException("训练语料库搜索失败["+defaultPath+"]");
        }
        this.trainingClassification = traningDir.list();
    }

    /**
     * 获得分类信息
     * @return classes 分类信息
     */
    public String[] getTrainingClassification(){
        return this.trainingClassification;
    }

    /**
     * 返回分类的所有子文件完整路径
     * @param classification 分类
     * @return 某个分类的所有文件的完整路径
     */
    public String[] getFilesPath(String classification){
        File classDir = new File(traningDir.getPath() + File.separator + classification);
        String[] retValue = classDir.list();
        for(int i = 0; i < retValue.length; i++){
            retValue[i] = traningDir.getPath() + File.separator + classification + File.separator + retValue[i];
        }
        return retValue;
    }

    /**
     * 返回文件的内容
     * @param filePath 文件的路径
     * @return 文件的内容
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String getText(String filePath) throws IOException{
        InputStreamReader isReader = new InputStreamReader(new FileInputStream(filePath), "GBK");
        BufferedReader reader = new BufferedReader(isReader);
        String text;
        StringBuilder builder = new StringBuilder();
        while((text = reader.readLine()) != null){
            builder.append(text);
        }
        isReader.close();
        reader.close();
        return builder.toString();
    }

    /**
     * 返回某个分类下的文件个数
     * @param classification 给定分类
     * @return 分类下的文件个数
     */
    public int getFileCount(String classification){
        File classDir = new File(traningDir.getPath() + File.separator + classification);
        return classDir.list().length;
    }

    /**
     * 返回整个训练集的文件个数
     * @return 整个训练集的文件个数
     */
    public int getTrainingCount(){
        int retValue = 0;
        for(int i = 0; i < trainingClassification.length; i++){
            retValue += getFileCount(trainingClassification[i]);
        }
        return retValue;
    }

    /**
     * 返回在给定分类下，有某关键字的文件个数
     * @param classification 给定的分类
     * @param keyword 给定的关键词
     * @return 含有该关键词的文件个数
     */
    public int getKeywordsCount(String classification, String keyword){
        int retValue = 0;
        try{
            String[] filePath = getFilesPath(classification);
            for(int j = 0; j < filePath.length; j++){
                String text = getText(filePath[j]);
                if(text.contains(keyword)){
                    retValue += 1;
                }
            }
        } catch (IOException e){
            Logger.getLogger(TrainingData.class.getName()).log(Level.SEVERE, null, e);
        }
        return retValue;
    }
}
