// Released under the terms of the Apache License, Version 2.0 or later.
// See the file 'COPYING' in the root directory for further information.
package validation;

import java.util.HashMap;
import java.util.Map;

import net.sf.oval.Validator;
import net.sf.oval.context.OValContext;


public class BiggerThanCheck extends AbstractMultiFieldCheck<BiggerThan> {
	final static String	mes	= "validation.biggerThan";

	String				otherName;
	String				otherKey;
	Object				otherValue;

	@Override
	public void configure(BiggerThan anno) {
		this.otherName = anno.value();
		setMessage(anno.message());
	}

	public boolean isSatisfied(Object validatedObject, Object value, OValContext context, Validator validator) {
		requireMessageVariablesRecreation();
		Object[] otherField = getField(validatedObject, context, otherName);
		if (!(Boolean) otherField[0]) {
			return false;
		}
		otherValue = otherField[1];

		if (value == null || otherValue == null) {
			return true;
		}
		if (value instanceof Comparable<?>) {
			try {
				return ((Comparable) value).compareTo(otherValue) >= 0;
			} catch (ClassCastException cce) {
				return false;
			}
		}
		return false;
	}

	@Override
	public Map<String, String> createMessageVariables() {
		Map<String, String> messageVariables = new HashMap<String, String>();
		messageVariables.put("other", otherKey);
		return messageVariables;
	}
}