import org.apdplat.word.segmentation.Word;
import org.apdplat.word.WordSegmenter;

import java.util.List;

/**
 * Created by liuyang on 16-6-18.
 */

public class ChineseSplit {
    /**
     * 对中文字进行分割，并且去除停用字。使用第三方库(word-1.3.1.jar), 详见https://github.com/ysc/word/tree/word-1.3
     * @param text 原始文本
     * @return 分割后的词语
     */
    public static List<Word> split(String text){
        return WordSegmenter.seg(text);
    }
}
