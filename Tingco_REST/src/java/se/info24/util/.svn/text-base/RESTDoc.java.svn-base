/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.info24.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author Sridhar Dasari
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RESTDoc {
	 String methodName();
	 String desc();
	 String[] req_Params();
	 String[] opt_Params();
	 String[] method_formats();
	 String [] method_requests();
	 String [] versions();
}
