package outputstream;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * ClassName: FileCopy
 * Package: outputstream
 * Description:
 *
 * @author fuchen
 * @version 1.0
 * @createTime 2024/9/10
 */
public class FileCopy {
    public static void main(String[] args) throws Exception{
        String filePath = "./public/images/io.png";
        String fielPath1 = "learn_IO/src/copy.png";

        FileInputStream fileInputStream = new FileInputStream(filePath);
        FileOutputStream fileOutputStream = new FileOutputStream(fielPath1);

        byte[] bytes = new byte[16];
        int len;
        while ((len = fileInputStream.read(bytes)) != -1){
            fileOutputStream.write(bytes,0,len);
        }

        fileInputStream.close();
        fileOutputStream.close();
    }
}
