package properties;

import java.io.FileReader;
import java.util.Properties;

/**
 * ClassName: Properties02
 * Package: properties
 * Description:
 *
 * @author fuchen
 * @version 1.0
 * @createTime 2024/9/12
 */
public class Properties02 {
    public static void main(String[] args) throws Exception{
        // 使用Properties 类来读取test.properties文件

        // 1. 创建Properties 对象
        Properties properties = new Properties();

        // 2. 加载指定配置文件
        properties.load(new FileReader("./learn_IO/test.properties"));

//        properties.list(System.out);

        String user = properties.getProperty("I");
        System.out.println(user);
    }

}
