// Released under the terms of the Apache License, Version 2.0 or later.
// See the file 'COPYING' in the root directory for further information.
package validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.sf.oval.configuration.annotation.AbstractAnnotationCheck;
import net.sf.oval.context.FieldContext;
import net.sf.oval.context.MethodParameterContext;
import net.sf.oval.context.OValContext;
import play.data.binding.Binder;
import play.exceptions.UnexpectedException;
import play.mvc.Scope;
import play.utils.Java;


public abstract class AbstractMultiFieldCheck<A extends Annotation> extends
		AbstractAnnotationCheck<A> {

	protected static Object[] getField(Object validatedObject, OValContext context, String otherName) {
		Object[] ret = new Object[] { Boolean.FALSE, null };

		try {
			if (context != null) {
				if (context instanceof MethodParameterContext) {
					MethodParameterContext ctx = (MethodParameterContext) context;
					Method method = ctx.getMethod();
					String[] paramNames = Java.parameterNames(method);
					int index = -1;
					for (int i = 0; i < paramNames.length; i++) {
						if (paramNames[i].equals(otherName)) {
							index = i;
							break;
						}
					}
					if (index < 0) {
						return ret;
					}
					ret[1] = Binder.bind(otherName, method.getParameterTypes()[index],
							method.getGenericParameterTypes()[index], method.getParameterAnnotations()[index],
							Scope.Params.current().all());
				}
				if (context instanceof FieldContext) {
					FieldContext ctx = (FieldContext) context;
					try {
						Field otherField = ctx.getField().getDeclaringClass().getDeclaredField(otherName);
						ret[1] = otherField.get(validatedObject);
					} catch (Exception e) {
						return ret;
					}
				}
			}
		} catch (Exception e) {
			throw new UnexpectedException(e);
		}

		ret[0] = Boolean.TRUE;
		return ret;
	}

	public AbstractMultiFieldCheck() {
		super();
	}
}