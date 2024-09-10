package upload;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * ClassName: StreamUtils
 * Package: upload
 * Description:
 *
 * @author fuchen
 * @version 1.0
 * @createTime 2024/9/9
 */
public class StreamUtils {
    /**
     * 将输入流转换成byte[],即把文件的内容读入到byte[]中
     * @param is
     * @return
     * @throws Exception
     */
    public static byte[] streamToByteArray(InputStream is) throws  Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = is.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
        byte[] bytes = out.toByteArray();
        out.close();
        return bytes;
    }

    /**
     *  将InputStream转换为String
     * @param is
     * @return
     * @throws Exception
     */
    public static String streamToString(InputStream is) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while((line = bufferedReader.readLine()) != null){
            sb.append(line);
        }
        return sb.toString();
    }
}
