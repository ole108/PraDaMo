// Released under the terms of the Apache License, Version 2.0 or later.
// See the file 'COPYING' in the root directory for further information.
package models.fields;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.persistence.Column;
import javax.persistence.Entity;

import models.RecordType;
import net.sf.oval.constraint.ValidateWithMethod;
import play.data.validation.Check;
import play.data.validation.CheckWith;
import play.data.validation.Max;
import play.data.validation.MaxSize;
import play.data.validation.Min;
import play.data.validation.Required;
import validation.BiggerThan;


/**
 * @author ole
 */
@Entity
@ValidateWithMethod(methodName = "isValid", parameterType = StringField.class)
public class StringField extends Field {
	@Min(0)
	@Max(1000)
	public Integer	minLen;

	@Required
	@Min(1)
	@Max(1000000)
	@BiggerThan("minLen")
	@Column(nullable = false)
	public Integer	maxLen;

	@MaxSize(96)
	@CheckWith(RegexCheck.class)
	@Column(length = 96)
	public String	regex;

	public StringField(String fieldName, String displayName, RecordType entity, int maxLen) {
		super(fieldName, displayName, entity);

		this.maxLen = maxLen;
	}

	/** Just for documentation purposes. */
	public boolean isValid(StringField objToValidate) {
		return true;
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