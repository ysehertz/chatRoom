package writer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * ClassName: BufferedCopy
 * Package: writer
 * Description:
 *
 * @author fuchen
 * @version 1.0
 * @createTime 2024/9/11
 */
public class BufferedCopy {
    public static void main(String[] args) throws Exception{
        String filename = "public/images/io.png";
        String filename2 = "learn_IO/src/writer/io_copy.png";

        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(filename2));
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(filename));
        byte[] bytes = new byte[1024];
        int len;
        while ((len = bufferedInputStream.read(bytes)) != -1){
            bufferedOutputStream.write(bytes,0,len);
        }

        bufferedOutputStream.close();
        bufferedInputStream.close();
    }
}
