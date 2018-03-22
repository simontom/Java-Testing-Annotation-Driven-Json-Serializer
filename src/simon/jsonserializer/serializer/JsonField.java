package simon.jsonserializer.serializer;

import com.sun.istack.internal.NotNull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention (RetentionPolicy.RUNTIME)
@Target (ElementType.FIELD)
public @interface JsonField {
    @NotNull
    String name() default "";

    boolean optional() default true;

    Class<? extends TypeConverter> typeConverter() default TypeConverter.DEFAULT.class;
}
