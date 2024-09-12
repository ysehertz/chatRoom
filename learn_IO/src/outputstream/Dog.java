package outputstream;

import java.io.Serializable;

/**
 * ClassName: Dog
 * Package: outputstream
 * Description:
 *
 * @author fuchen
 * @version 1.0
 * @createTime 2024/9/11
 */
public class Dog implements Serializable {
    private static final long serialVersionUID = 6256273526044495566L;
    private String name;
    private int age;

    public Dog() {
    }

    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
