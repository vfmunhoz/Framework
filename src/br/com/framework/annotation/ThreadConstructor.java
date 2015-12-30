package br.com.framework.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation should be used to mark the constructor that must be called if for 
 * some reason the framework has to create a new object for this thread.
 * 
 * @author viniciusmunhoz
 *
 */
@Documented
@Target(ElementType.CONSTRUCTOR)
@Retention(RetentionPolicy.RUNTIME)
public @interface ThreadConstructor {
	
}
