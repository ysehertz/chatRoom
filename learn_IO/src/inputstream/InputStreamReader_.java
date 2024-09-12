package inputstream;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * ClassName: InputStreamReder_
 * Package: inputstream
 * Description:
 *
 * @author fuchen
 * @version 1.0
 * @createTime 2024/9/11
 */
public class InputStreamReader_ {
    public static void main(String[] args) throws Exception {
        String filePath = "./public/he.txt";
        // 指定编码
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(filePath),"UTF-8");

        // 把 inputStreamReader 传入 BufferedReader
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        System.out.println(bufferedReader.readLine());

        bufferedReader.close();

    }
}
