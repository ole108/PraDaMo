// Released under the terms of the Apache License, Version 2.0 or later.
// See the file 'COPYING' in the root directory for further information.
package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.data.validation.Match;
import play.data.validation.MaxSize;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.jpa.Model;


/**
 * @author ole
 */
@Entity
public class Project extends Model {
	@Required
	@MinSize(value = 3)
	@MaxSize(64)
	@Column(nullable = false, unique = true, length = 64)
	public String	projectName;

	@Required
	@MinSize(value = 3)
	@MaxSize(96)
	@Match("[a-zA-Z0-9_/]*")
	@Column(nullable = false, unique = true, length = 96)
	public String	projectDirectory;

	@MinSize(value = 3)
	@MaxSize(96)
	@Match("[a-zA-Z0-9_.]*")
	@Column(length = 96)
	public String	basePackage;

	@Lob
	@Required
	@MinSize(10)
	@MaxSize(10000)
	@Column(nullable = false)
	public String	description;

	@Required
	@ManyToOne(optional = false)
	public TecStack	tecStack;

	// TODO: The following two fields should be replaced by a feature mechanism one day.
	// That way it would be much more modular and a many-to-many relationship
	// could ensure that only features available for the technology stack are chosen.
	@Column(nullable = false)
	public boolean	loginRequired		= true;

	// TODO: This should be supported when we have integrated Apache Shiro.
	@Column(nullable = false)
	public boolean	withAuthorization	= false;

	@Temporal(TemporalType.TIMESTAMP)
	public Date		lastGeneratedAt		= null;

	public Project(String projectName, String projectDirectory, String basePackage, String description,
			TecStack tecStack) {
		this.projectName = projectName;
		this.projectDirectory = projectDirectory;
		this.basePackage = basePackage;
		this.description = description;
		this.tecStack = tecStack;
	}

	@Override
	public String toString() {
		return projectName;
	}
}