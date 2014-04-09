package bd.ciber.testbed;

import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import bd.ciber.testbed.db.DataProfile;
import bd.ciber.testbed.db.Settings;
import bd.ciber.testbed.db.TestBatchResult;

import com.mongodb.DB;

public class TestbedService implements ApplicationContextAware {
	private static final Logger LOG = LoggerFactory.getLogger(TestbedService.class);

	private Timer timer = new Timer();
	private boolean running = false;
	
	@Autowired(required = false)
	private boolean autostart = false;
	
	@Autowired
	DB db;
	
	@Autowired
	private Controller controller;

	private ApplicationContext applicationContext;

	@PostConstruct
	public void init() {
		if(autostart) start();
	}
	
	/**
	 * Stop running tests after this batch.
	 */
	public synchronized void shutdown() {
		timer.cancel();
		this.running = false;
		timer.purge();
	}
	
	public synchronized void start() {
		if(this.running) return;
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				Settings settings = controller.getSettings();
				LOG.debug("got settings {}", settings);
				String profileKey = settings.getCurrentDataProfile();
				LOG.debug("got profile key {}", profileKey);
				DataProfile profile = controller.getDataProfile(profileKey);
				LOG.debug("got profile {}", profile);
				TestBatchRunner runner = (TestBatchRunner) applicationContext.getBean(
						"testBatchRunner", profile);
				try {
					TestBatchResult result = runner.call();
					controller.postTestBatchResult(result.getId(), result);
				} catch(Exception e) {
					LOG.error("Test Batch failed", e);
				}
			}
		}, 1000, 1000*60);
		this.running = true;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

}
