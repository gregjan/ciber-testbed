package bd.ciber.testbed.db;

import org.mongojack.ObjectId;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Settings {
	private String currentDataProfile;

	public String getCurrentDataProfile() {
		return currentDataProfile;
	}

	public void setCurrentDataProfile(String currentDataProfile) {
		this.currentDataProfile = currentDataProfile;
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
