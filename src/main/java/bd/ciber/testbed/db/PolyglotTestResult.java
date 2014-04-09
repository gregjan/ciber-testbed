package bd.ciber.testbed.db;

import org.mongojack.ObjectId;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PolyglotTestResult {
	public static final int SUCCESS = 1;
	public static final int UNKNOWN_FAILURE = -1;
	public static final int NO_CONVERSIONS = -2;
	public static final int INPUT_DATA_ERROR = -3;
	
	private String inPath; // input file (full path/URL)
	private String inSize; // input file size
	private String inFormat;
	private String inProviderId;
	
	private String outFormat; // output format
	private Long outSize;
	
	private Long submitted; // time submitted
	private Long elapsed; // time returned
	private int returnCode; // success/failure
	private String returnMessage;
	
	public String getInPath() {
		return inPath;
	}

	public void setInPath(String inPath) {
		this.inPath = inPath;
	}

	public String getInSize() {
		return inSize;
	}

	public void setInSize(String inSize) {
		this.inSize = inSize;
	}

	public String getInFormat() {
		return inFormat;
	}

	public void setInFormat(String inFormat) {
		this.inFormat = inFormat;
	}

	public String getInProviderId() {
		return inProviderId;
	}

	public void setInProviderId(String inProviderId) {
		this.inProviderId = inProviderId;
	}

	public String getOutFormat() {
		return outFormat;
	}

	public void setOutFormat(String outFormat) {
		this.outFormat = outFormat;
	}

	public Long getOutSize() {
		return outSize;
	}

	public void setOutSize(Long outSize) {
		this.outSize = outSize;
	}

	public Long getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Long submitted) {
		this.submitted = submitted;
	}

	public Long getElapsed() {
		return elapsed;
	}

	public void setElapsed(Long elapsed) {
		this.elapsed = elapsed;
	}

	public int getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMessage() {
		return returnMessage;
	}

	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}

	private String id;
	private String batchId;

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

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	
	public String getBatchId() {
		return this.batchId;
	}
}
