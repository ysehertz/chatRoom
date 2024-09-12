package inputstream;
import org.junit.Test;

import java.io.FileInputStream;

/**
 * ClassName: FileInputStream
 * Package: inputstream
 * Description:
 *
 * @author fuchen
 * @version 1.0
 * @createTime 2024/9/10
 */
public class FileInputStream1 {
    public static void main(String[] args) {

    }

    @Test
    public void readFile01() throws Exception{
        String filePath = "./../public/hello.txt";
//        String filePath = "E:\\java\\code\\chatRoom\\public\\hello.txt";

        // 创建FileInputStream对象，用于读取文件
        FileInputStream fileInputStream = new FileInputStream(filePath);

        int readData = 0;
        while ((readData = fileInputStream.read()) != -1) {
            System.out.print((char) readData);
        }

        fileInputStream.close();
    }

    @Test
    public void readFile02() throws Exception{
        String filePath = "./../public/hello.txt";
//        String filePath = "E:\\java\\code\\chatRoom\\public\\hello.txt";

        // 创建FileInputStream对象，用于读取文件
        FileInputStream fileInputStream = new FileInputStream(filePath);

        byte[] buffer = new byte[1024];
        int len;
        while ((len = fileInputStream.read(buffer)) != -1) {
            System.out.print(new String(buffer,0,len));
        }

        fileInputStream.close();
    }
}
