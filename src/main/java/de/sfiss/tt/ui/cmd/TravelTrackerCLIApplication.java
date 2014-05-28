package de.sfiss.tt.ui.cmd;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TravelTrackerCLIApplication {
	static ApplicationContext ctx;

	static {
		ctx = new ClassPathXmlApplicationContext(
				"META-INF/application-context.xml");

	}
}
