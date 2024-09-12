package outputstream;

import java.io.FileOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * ClassName: ObjectOutStream
 * Package: outputstream
 * Description:
 *
 * @author fuchen
 * @version 1.0
 * @createTime 2024/9/11
 */
public class ObjectOutStream {
    public static void main(String[] args) throws  Exception{
        String filePath = "./learn_IO/src/data.txt";

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filePath));

        objectOutputStream.writeInt(100);
        objectOutputStream.writeBoolean(true);
        objectOutputStream.writeChar('a');
        objectOutputStream.writeDouble(3.1415926);
        objectOutputStream.writeUTF("你好,我喜欢你");
        objectOutputStream.writeObject(new Dog("八戒",2));

        objectOutputStream.close();
        System.out.println("数据写入成功");
    }
}
