package org.aksw.mex.tests;

/**
 * Created by dnes on 12/12/15.
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Execution {

    //should ignore this test?
    public boolean enabled() default true;
    public double accuracy() default 0.0;
    String id() default "";

}

