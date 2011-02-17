/**
 * 
 */
package boot;

import models.SecUser;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;


/**
 * @author ole
 */
@OnApplicationStart
public class Bootstrap extends Job {
	public void doJob() {
		// Check if the database is empty
		if (SecUser.count() == 0) {
			Fixtures.load("initial-data.yml");
		}
	}
}
