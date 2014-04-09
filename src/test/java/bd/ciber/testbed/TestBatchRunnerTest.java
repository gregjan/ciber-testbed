package bd.ciber.testbed;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring/service-context.xml" })
public class TestBatchRunnerTest {
	private static final Logger LOG = LoggerFactory.getLogger(TestBatchRunnerTest.class);
	
	@Autowired
	private TestBatchRunner runner;

	@Test
	public void testCall() {
		fail("Not yet implemented");
	}

}
