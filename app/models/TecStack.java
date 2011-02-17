// Released under the terms of the Apache License, Version 2.0 or later.
// See the file 'COPYING' in the root directory for further information.
package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

import play.data.validation.Match;
import play.data.validation.MaxSize;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.jpa.Model;


/**
 * @author ole
 */
@Entity
public class TecStack extends Model {
	@Required
	@MinSize(value = 3)
	@MaxSize(32)
	@Match("[a-zA-Z0-9_]*")
	@Column(nullable = false, length = 32)
	public String	packageName;

	@Required
	@MinSize(value = 3)
	@MaxSize(64)
	@Column(nullable = false, length = 64)
	public String	displayName;

	@Lob
	@Required
	@MinSize(10)
	@MaxSize(10000)
	@Column(nullable = false)
	public String	description;

	public TecStack(String packageName, String displayName, String description) {
		this.packageName = packageName;
		this.displayName = displayName;
		this.description = description;
	}

	@Override
	public String toString() {
		return displayName;
	}
}