// Released under the terms of the Apache License, Version 2.0 or later.
// See the file 'COPYING' in the root directory for further information.
package generator;

import java.io.File;
import java.util.List;

import models.Project;
import models.RecordType;


public class ProjectGenerator {
	private final Project	project;
	private File			baseDir;

	public ProjectGenerator(Project project) {
		this.project = project;
	}

	public void generateProject() {
		generateBaseDirectory(project.projectDirectory);
		initializeProject();
		generateEntities();
	}

	private void initializeProject() {
		// TODO: Pipe project.projectName into the command
		Utils.exec("play", baseDir, "new", project.projectDirectory, "--with", "crud");

		// TODO: eclipsify, ... should be done too
	}

	private void generateEntities() {
		List<RecordType> entities = RecordType.find("byProject", project).fetch();
		for (RecordType entity : entities) {
			generateEntity(entity);
		}
	}

	private void generateEntity(RecordType entity) {
	}

	private boolean generateBaseDirectory(String dirName) {
		File dir = Utils.makeAbsolute(dirName);
		if (!dir.isDirectory()) {
			// TODO: report error!
			System.err.println("Generation error: Directory doesn't exist: " + dir.getAbsolutePath());
			return false;
		} else {
			baseDir = dir;
		}

		return true;
	}

}
