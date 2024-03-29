package integration.persistence;

import javax.inject.Inject;

import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/persistence/application-test-context.xml" })
public abstract class PersistenceTest {

	@Inject
	ApplicationContext applicationContext;

}
