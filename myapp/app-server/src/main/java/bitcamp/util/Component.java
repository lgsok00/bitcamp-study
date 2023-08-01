package bitcamp.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public @interface Component {
  // 메서드에 붙일 애노테이션

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Component {
 String value() default "";
}
