package bd.ciber.testbed.db;

import java.util.HashMap;

import org.mongojack.ObjectId;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DataProfile {
	private HashMap<String, Integer> formatSpec;

	// demarkated ranges and frequencies within range;
	// public SortedMap<Integer, Integer> sizeDist;

	// public Map<String, Integer> virtualMachines;

	private int threads;

	private String name;

	public HashMap<String, Integer> getFormatSpec() {
		return formatSpec;
	}

	public void setFormatSpec(HashMap<String, Integer> formatSpec2) {
		this.formatSpec = formatSpec2;
	}

	public int getThreads() {
		return threads;
	}

	public void setThreads(int threads) {
		this.threads = threads;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
