package controllers;

import generator.ProjectGenerator;
import models.Project;
import play.mvc.Controller;


public class Application extends Controller {

	public static void index() {
		render();
	}

	public static void generate(Long projectId) {
		new ProjectGenerator(Project.<Project> findById(projectId)).generateProject();
		render();
	}
}