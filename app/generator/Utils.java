// Released under the terms of the Apache License, Version 2.0 or later.
// See the file 'COPYING' in the root directory for further information.
package generator;

import java.io.File;
import java.util.Map;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;

import play.libs.IO;
import play.templates.GroovyTemplate;


public class Utils {
	public static File join(String headPath, String tailPath) {
		return new File(headPath + File.separator + tailPath);
	}

	public static File makeAbsolute(String path) {
		File file = new File(path);
		if (!file.isAbsolute()) {
			return join(System.getProperty("user.home", "."), path);
		}
		return file;
	}

	public static boolean exec(String command, File workingDir, String... args) {
		try {
			CommandLine cmdLine = new CommandLine(command);
			cmdLine.addArguments(args, false);
			DefaultExecutor exe = new DefaultExecutor();
			exe.setWorkingDirectory(workingDir);
			return exe.execute(cmdLine) == 0;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean copyTemplate(String templateFile, Map<String, Object> args, File outFile) {
		// Play.getFile("templates/entity.tpl");
		GroovyTemplate tpl = new GroovyTemplate("templates/entity.tpl");
		String content = tpl.render(args);
		IO.writeContent(content, outFile);
		return true;
	}
}
