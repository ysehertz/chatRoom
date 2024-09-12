package inputstream;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * ClassName: ObjectInputstream
 * Package: inputstream
 * Description:
 *
 * @author fuchen
 * @version 1.0
 * @createTime 2024/9/11
 */
public class ObjectInputstream {
    public static void main(String[] args) throws Exception
    {
        String filePath = "./learn_IO/src/data.txt";

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));

        // 读取（反序列化）的顺序需要和你保存数据（序列化）的顺序一致，否则会出现异常

        System.out.println(ois.readInt());
        System.out.println(ois.readBoolean());
        System.out.println(ois.readChar());
        System.out.println(ois.readDouble());
        System.out.println(ois.readUTF());
        Object o = ois.readObject();
        System.out.println(o);

        ois.close();
    }
}
