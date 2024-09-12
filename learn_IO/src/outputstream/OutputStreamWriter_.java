package outputstream;

import jdk.swing.interop.DragSourceContextWrapper;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;

/**
 * ClassName: OutputStreamWriter
 * Package: outputstream
 * Description:
 *
 * @author fuchen
 * @version 1.0
 * @createTime 2024/9/11
 */
public class OutputStreamWriter_ {
    public static void main(String[] args)  throws Exception{
        String filePath = "./learn_IO/src/data1.txt";
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(filePath),"utf8");
        outputStreamWriter.write("我喜欢你");
        outputStreamWriter.close();
        System.out.println("写入完毕(utf_8)");


    }
}
