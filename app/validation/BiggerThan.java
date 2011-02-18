// Released under the terms of the Apache License, Version 2.0 or later.
// See the file 'COPYING' in the root directory for further information.
package validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.sf.oval.configuration.annotation.Constraint;


/**
 * This field must be bigger than or equals to another field.
 * Both field values must be of the same class and implement Comparable.
 * Message key: validation.biggerThan
 * $1: field name
 * $2: other field name
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Constraint(checkWith = BiggerThanCheck.class)
public @interface BiggerThan {
	String message() default BiggerThanCheck.mes;

	/**
	 * The other field name
	 */
	String value();
}
