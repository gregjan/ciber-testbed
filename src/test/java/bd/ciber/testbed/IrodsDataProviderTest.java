package bd.ciber.testbed;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.irods.jargon.core.exception.JargonException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring/service-context.xml" })
public class IrodsDataProviderTest {
	@Autowired
	DataProvider irodsDataProvider;

	@Test
	public void test() throws JargonException, IOException {
		Map<String, Integer> formatSpec = new HashMap<String, Integer>();
		formatSpec.put("pdf", new Integer(50));
		formatSpec.put("doc", new Integer(50));
		formatSpec.put("xls", new Integer(50));
		Set<String> paths = irodsDataProvider.select(formatSpec);
		int total = 0;
		for(Integer i : formatSpec.values()) total = total + i; 
		assertEquals(total, paths.size());
	}

}
