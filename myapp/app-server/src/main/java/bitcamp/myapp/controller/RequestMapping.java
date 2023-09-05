package bitcamp.myapp.controller;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME) // 컴파일 할 때 바이트 코드에 삽입, 실행 중 추출
public @interface RequestMapping {

}
