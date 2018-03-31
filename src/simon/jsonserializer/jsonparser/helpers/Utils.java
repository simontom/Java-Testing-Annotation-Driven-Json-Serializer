package simon.jsonserializer.jsonparser.helpers;

import java.util.Iterator;

public class Utils {

    public static <T> Iterable<T> iteratorToIterable(final Iterator<T> iterator) {
        return new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                return iterator;
            }
        };
    }

}
