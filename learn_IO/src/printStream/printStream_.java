package printStream;

import java.io.FileWriter;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * ClassName: printStream_
 * Package: printStream
 * Description:
 *
 * @author fuchen
 * @version 1.0
 * @createTime 2024/9/11
 */
public class printStream_ {
    public static void main(String[] args) throws Exception{
//        PrintStream out = System.out;
//        out.println("我喜欢你");
//        out.write("我喜欢你".getBytes());

        System.setOut(new PrintStream("./learn_IO/src/data2.txt"));
        System.out.println("我喜欢你");

        PrintWriter printWriter = new PrintWriter(new FileWriter("./learn_IO/src/data3.txt"));
        printWriter.println("我好喜欢你");
        printWriter.close(); // flush + 关闭流
        
    }
}
