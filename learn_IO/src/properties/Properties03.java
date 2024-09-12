package properties;

import java.io.FileOutputStream;
import java.util.Properties;

/**
 * ClassName: Properties03
 * Package: properties
 * Description:
 *
 * @author fuchen
 * @version 1.0
 * @createTime 2024/9/12
 */
public class Properties03 {
    public static void main(String[] args) throws Exception{
        // 使用Properties类来创建配置文件，修改配置文件内容

        Properties properties = new Properties();

        properties.setProperty("charset", "utf-8");
        properties.setProperty("xiaoqi","xihuanni");
        properties.setProperty("age", "18");

        properties.store(new FileOutputStream("./learn_IO/test2.properties"),null);
    }
}
