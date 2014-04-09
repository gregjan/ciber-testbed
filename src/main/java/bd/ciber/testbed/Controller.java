package bd.ciber.testbed;

import static org.mongojack.JacksonDBCollection.wrap;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.mongojack.JacksonDBCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import bd.ciber.testbed.db.DataProfile;
import bd.ciber.testbed.db.PolyglotTestResult;
import bd.ciber.testbed.db.Settings;
import bd.ciber.testbed.db.TestBatchResult;

import com.mongodb.DB;

@Component
@Scope("prototype")
@Path("/rest")
public class Controller {
	private static final Logger LOG = LoggerFactory.getLogger(Controller.class);
	
	@Autowired
	private DB db;

	private JacksonDBCollection<DataProfile, String> dataProfileColl;
	private JacksonDBCollection<Settings, String> settingsColl;
	private JacksonDBCollection<TestBatchResult, String> testBatchResultColl;
	private JacksonDBCollection<PolyglotTestResult, String> polyglotTestResultColl;

	@PostConstruct
	public void init() {
		dataProfileColl = wrap(db.getCollection(DataProfile.class.getName()), DataProfile.class, String.class);
		settingsColl = wrap(db.getCollection(Settings.class.getName()), Settings.class, String.class);
		testBatchResultColl = wrap(db.getCollection(TestBatchResult.class.getName()), TestBatchResult.class, String.class);
		polyglotTestResultColl = wrap(db.getCollection(PolyglotTestResult.class.getName()), PolyglotTestResult.class, String.class);
	}

	@Path("/DataProfile")
	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	public String postDataProfile(@RequestBody DataProfile profile) {
		return dataProfileColl.insert(profile).getSavedId();
	}

	@Path("/DataProfile")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public DataProfile getDataProfile(@QueryParam("id") String id) {
		return dataProfileColl.findOneById(id);
	}
	
	@Path("/PolyglotTestResult")
	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	public String postPolyglotTestResult(@RequestBody PolyglotTestResult polyglotTestResult) {
		return polyglotTestResultColl.insert(polyglotTestResult).getSavedId();
	}

	@Path("/PolyglotTestResult")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public PolyglotTestResult getPolyglotTestResult(@QueryParam("id") String id) {
		return polyglotTestResultColl.findOneById(id);
	}
	
	@Path("/TestBatchResult")
	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	public String putTestBatchResult(@RequestBody TestBatchResult testBatchResult) {
		return testBatchResultColl.insert(testBatchResult).getSavedId();
	}
	
	@Path("/TestBatchResult/{id}")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public void postTestBatchResult(@PathVariable String id, @RequestBody TestBatchResult testBatchResult) {
		testBatchResultColl.updateById(id, testBatchResult);
	}

	@Path("/TestBatchResult")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public TestBatchResult getTestBatchResult(@QueryParam("id") String id) {
		return testBatchResultColl.findOneById(id);
	}
	
	@Path("/Settings")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Settings getSettings() {
		return settingsColl.findOne();
	}
	
	@Path("/Settings")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public void updateSettings(@RequestBody Settings settings) {
		Settings existing = settingsColl.findOne();
		LOG.debug("found settings {}", existing);
		if(existing != null) {
			settingsColl.updateById(existing.getId(), settings);
		} else {
			settingsColl.insert(settings);
		}
	}

	public void dropAll() {
		dataProfileColl.drop();
		settingsColl.drop();
		testBatchResultColl.drop();
		polyglotTestResultColl.drop();
	}
}
