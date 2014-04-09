package bd.ciber.testbed;

import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring/service-context.xml" })
public class PolyglotClientTest {
	private static final Logger LOG = LoggerFactory.getLogger(PolyglotClientTest.class);
	
	@Autowired
	PolyglotClient polyglotClient;

	@Test
	public void testGetConversionsForInput() {
		List<String> result = polyglotClient.getConversionsForInput("pdf");
		assertTrue("more than 10 PDF conversions available", result.size() > 10);
	}
	
	@Test
	public void testConvert() throws IOException {
		InputStream in = new FileInputStream("src/test/resources/test.pdf");
		String result = polyglotClient.convert("test.pdf", in, "jpg");
		LOG.info("got result: {}", result);
	}

}
