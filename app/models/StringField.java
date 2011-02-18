// Released under the terms of the Apache License, Version 2.0 or later.
// See the file 'COPYING' in the root directory for further information.
package models;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.persistence.Column;
import javax.persistence.Entity;

import net.sf.oval.constraint.ValidateWithMethod;
import play.data.validation.Check;
import play.data.validation.CheckWith;
import play.data.validation.Max;
import play.data.validation.MaxSize;
import play.data.validation.Required;


/**
 * @author ole
 */
@Entity
@ValidateWithMethod(methodName = "isValid", parameterType = StringField.class)
public class StringField extends Field {
	@Column(nullable = false)
	public boolean	isLob	= false;

	@Max(1000)
	public Integer	minLen;

	@Required
	@Max(1000000)
	@Column(nullable = false)
	public Integer	maxLen;

	@MaxSize(128)
	@CheckWith(RegexCheck.class)
	@Column(length = 96)
	public String	regex;

	public StringField(String fieldName, String displayName, RecordType entity) {
		super(fieldName, displayName, entity);
	}

	public boolean isValid(StringField objToValidate) {
		if (objToValidate.minLen == null || objToValidate.maxLen == null) {
			return true;
		}
		return objToValidate.minLen <= objToValidate.maxLen;
	}

	public StringField save() {
		isLob = maxLen > 100; // This is the way play decides between text and largetext
		return super.save();
	}

	static class RegexCheck extends Check {
		public boolean isSatisfied(Object stringField, Object regex) {
			try {
				Pattern.compile((String) regex);
				return true;
			} catch (PatternSyntaxException pse) {
				return false;
			}
		}
	}
}