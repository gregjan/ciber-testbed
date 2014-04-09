package bd.ciber.testbed.db;

import org.mongojack.ObjectId;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TestBatchResult {
	private long startTime;
	private long elapsedTime;
	public long getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(long elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	private String profileId;
	
	public String getProfileId() {
		return profileId;
	}

	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long currentTimeMillis) {
		this.startTime = currentTimeMillis;
	}
	
	private String id;

	@ObjectId
	@JsonProperty("_id")
	public String getId() {
		return id;
	}

	@ObjectId
	@JsonProperty("_id")
	public void setId(String id) {
		this.id = id;
	}

}
