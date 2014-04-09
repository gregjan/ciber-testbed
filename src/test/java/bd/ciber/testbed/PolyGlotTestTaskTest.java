package bd.ciber.testbed;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring/service-context.xml" })
public class PolyGlotTestTaskTest {
	
	@Mock private DataProvider dataProvider;
	
	@Autowired
	PolyglotClient polyglotClient;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test() throws FileNotFoundException, IOException {
		PolyglotTestTask ptt = new PolyglotTestTask(dataProvider, polyglotClient, "test.pdf");
		Mockito.when(dataProvider.stream("test.pdf")).thenReturn(new FileInputStream("src/test/resources/test.pdf"));
		ptt.call();
	}

}
