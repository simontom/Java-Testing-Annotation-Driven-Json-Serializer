package simon.jsonserializer;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import simon.jsonserializer.dataobjects.Base;

public class Main {
    public Base[] a;
    public ArrayList<Base> al;
    public HashMap<String, Base> hm;
    public HashMap<Base, String> hm2;

    public static void main(String[] args) throws NoSuchFieldException {
        Field fieldA = Main.class.getDeclaredField("a");
        Field fieldAL = Main.class.getDeclaredField("al");
        Field fieldHM = Main.class.getDeclaredField("hm");
        Field fieldHM2 = Main.class.getDeclaredField("hm2");

        printInfoArray(fieldA);
        printInfoArrayList(fieldAL);
        printInfoHashMap(fieldHM);
        printInfoHashMap(fieldHM2);
    }

    private static void printInfoArray(Field field) {
        Class<?> clazz = field.getType();

        System.out.println("clazz: " + clazz);
        System.out.println("clazz.isArray: " + clazz.isArray());
        System.out.println("clazz.array_type: " + clazz.getComponentType());

        System.out.print("\n\n");
    }

    private static void printInfoArrayList(Field field) {
        Class<?> clazz = field.getType();
        Type genType = field.getGenericType();

        System.out.println("clazz: " + clazz);
        System.out.println("genType: " + genType);

        System.out.println("genType is ParameterizedType: " + (genType instanceof ParameterizedType));

        if (genType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genType;

            System.out.println("-raw genType: " + parameterizedType.getRawType());
            System.out.println("-actual genType args:");
            System.out.println("\t" + parameterizedType.getActualTypeArguments()[0]);
        }

        System.out.print("\n\n");
    }

    private static void printInfoHashMap(Field field) {
        Class<?> clazz = field.getType();
        Type genType = field.getGenericType();

        System.out.println("clazz: " + clazz);
        System.out.println("genType: " + genType);

        System.out.println("genType is ParameterizedType: " + (genType instanceof ParameterizedType));

        if (genType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genType;

            System.out.println("-raw genType: " + parameterizedType.getRawType());
            System.out.println("-actual genType args:");
            System.out.println("\t" + parameterizedType.getActualTypeArguments()[0]);
            System.out.println("\t" + parameterizedType.getActualTypeArguments()[1]);
        }
        System.out.print("\n\n");
    }

    private static void printInfo(Field field) {
        Class<?> clazz = field.getType();
        Type type = field.getGenericType();
        Type type2 = field.getType();

        System.out.println("clazz: " + clazz);
        System.out.println("type: " + type);
        System.out.println("type2: " + type2);

        System.out.println("clazz.isArray: " + clazz.isArray());
        System.out.println("type.isArray: " + type.getClass().isArray());
        System.out.println("type2.isArray: " + type2.getClass().isArray());

        System.out.println("type is ParameterizedType: " + (type instanceof ParameterizedType));
        System.out.println("type2 is ParameterizedType: " + (type2 instanceof ParameterizedType));

        if (type instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) type;
            System.out.println("raw type: " + pt.getRawType());
            System.out.println("actual type args:");
            for (Type t : pt.getActualTypeArguments()) {
                Class c = (Class) t;
                System.out.println("    " + t);
                System.out.println("    " + t.getClass());
            }
        }
        System.out.print("\n\n");
    }

}
