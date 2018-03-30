package simon.jsonserializer;

import org.json.JSONException;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import simon.jsonserializer.dataobjects.Base;
import simon.jsonserializer.jsonparser.exceptions.JsonParserException;

public class Main {
    public Base[] a;
    public ArrayList<Base> al;
    public HashMap<String, Base> hm;
    public HashMap<String, Base> hm2;

    public static void main(String[] args) throws JsonParserException, JSONException, NoSuchFieldException {
        Field fieldA = Main.class.getDeclaredField("a");
        Field fieldAL = Main.class.getDeclaredField("al");
        Field fieldHM = Main.class.getDeclaredField("hm");
        Field fieldHM2 = Main.class.getDeclaredField("hm2");

        printInfo(fieldA);
        printInfo(fieldAL);
        printInfo(fieldHM);
        printInfo(fieldHM2);
    }

    public static void printInfo(Field field) {
        Type type = field.getGenericType();
        System.out.println("type: " + type);

        if (type instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) type;
            System.out.println("raw type: " + pt.getRawType());
            System.out.println("actual type args:");
            for (Type t : pt.getActualTypeArguments()) {
                System.out.println("    " + t);
            }
        }
        System.out.print("\n\n");
    }

}
