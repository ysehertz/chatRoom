package properties;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

/**
 * ClassName: Properties01
 * Package: properties
 * Description:
  *
 * @author fuchen
 * @version 1.0
 * @createTime 2024/9/12
 */
public class Properties01 {
    public static void main(String[] args) throws Exception{
        new BufferedReader(new FileReader("./test.properties"));
    }
}
