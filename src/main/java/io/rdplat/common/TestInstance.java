package io.rdplat.common;

import java.io.*;
import java.lang.reflect.Constructor;
import java.util.*;
import java.util.concurrent.locks.Lock;

public class TestInstance {

    public static void main(String[] args) {

//        Map<String, Object> map = new HashMap<>();
//        map.put("test", 123);
//        map.put("test1", 234);
//        map.put("test2", 456);
//        map.put(null, 345);
//        Object o = map.get(null);
//        System.out.println(o);
//        System.out.println(map);

//        int[] arr = {5, 2, 14, 53, 12};
//        int[] sort = sort(arr);
//        for (int i : sort) {
//            System.out.println(i);
//        }

        int[] arr = {42, 12, 22, 11, 63};
        int length = arr.length;
        for (int i = 0; i < length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[length - 1 - i];
            arr[length - 1 - i] = temp;
        }

        System.out.println(Arrays.toString(arr));
    }

    public static int[] sort(int[] arr) {
        int length = arr.length - 1;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                }
            }
        }
        return arr;
    }


    public void testInstance() throws Exception {
        System.out.println("----------1-----------");
        Student test = new Student("使用 new 关键字创建实例对象", 123);
        System.out.println(test);

        System.out.println();
        System.out.println("----------2-----------");
        System.out.println("使用Class类的newInstance方法创建对象：");
        //对应类必须具有无参构造方法，且只有这一种创建方式
        Student student = Student.class.newInstance();
        System.out.println(student);

        System.out.println();
        System.out.println("----------3-----------");
        System.out.println("使用Constructor类的newInstance方法创建对象：");
        Constructor<Student> constructor = Student.class.getConstructor(String.class, Integer.class);
        Student instance = constructor.newInstance("Constructor", 123123);
        System.out.println(instance);

        System.out.println();
        System.out.println("----------4-----------");
        System.out.println("使用Clone方法创建对象：");
        Student clone = (Student) instance.clone();
        System.out.println(clone);


        //将对象序列化到硬盘中
        ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("student.bin"));
        stream.writeObject(clone);
        stream.close();

        //反序列化 写对象
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("student.bin"));
        Object readObject = inputStream.readObject();
        System.out.println(readObject);
    }
}


class Student implements Cloneable, Serializable {

    private static final long serialVersionUID = -619292301908676980L;
    //    private Integer id;
    private String name;
    private Integer age;


    public Student() {
        System.out.println("student 无参构造器");
    }

    public Student(String name, Integer age) {
        System.out.println("student 有参构造");
        this.name = name;
        this.age = age;
    }

//    public Student(Integer id, String name, Integer age) {
//        this.id = id;
//        this.name = name;
//        this.age = age;
//    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }


//    @Override
//    public String toString() {
//        return "Student{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", age=" + age +
//                '}';
//    }
}

