package sandbox;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class DynamicClass {
    
    public static void main(String[] args) {
        Class<?> clazz = null;
        try {
            clazz = Class.forName(String.valueOf(A_MySQL.class));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Constructor<?> constructor = null;
        try {
            constructor = clazz.getConstructor(String.class, Integer.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            Object instance = constructor.newInstance("stringparam", 42);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}


interface A {
    void func();
}

class A_MySQL implements A {
    String key;
    
    A_MySQL(String key) {
        this.key = key;
    }
    
    @Override
    public void func() {
        System.out.println("mysql");
    }
}

class A_Oracle implements A {
    String key;
    
    A_Oracle(String key) {
        this.key = key;
    }
    
    @Override
    public void func() {
        System.out.println("Oracle");
    }
}