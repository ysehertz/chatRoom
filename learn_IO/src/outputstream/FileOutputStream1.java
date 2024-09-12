package outputstream;

import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * ClassName: FileOutputStream1
 * Package: outputstream
 * Description:
 *
 * @author fuchen
 * @version 1.0
 * @createTime 2024/9/10
 */
public class FileOutputStream1 {
    public static void main(String[] args) {

    }
    @Test
    public void test1() throws IOException {
        String filePath = "../public/he.txt";
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);

        // 写入
//        fileOutputStream.write('a');


        // new FileOutputStream(filePath,true)创建方式，当写入内容是，是追加到文件后面
        String s = "我喜欢你";
        fileOutputStream.write(s.getBytes());
        fileOutputStream.close();
    }
}
