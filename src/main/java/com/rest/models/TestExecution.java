package com.rest.models;

public class TestExecution {

	private int executionId;
	private String runOn;
	private String result;
	private String scriptStatus;
	private int scriptId;
	private String testPlan;
	private String testCase;
	public int getExecutionId() {
		return executionId;
	}

	public void setExecutionId(int executionId) {
		this.executionId = executionId;
	}

	public String getRunOn() {
		return runOn;
	}

	public void setRunOn(String runOn) {
		this.runOn = runOn;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getScriptStatus() {
		return scriptStatus;
	}

	public void setScriptStatus(String scriptStatus) {
		this.scriptStatus = scriptStatus;
	}

	public int getScriptId() {
		return scriptId;
	}

	public void setScriptId(int scriptId) {
		this.scriptId = scriptId;
	}

	public String getTestPlan() {
		return testPlan;
	}

	public void setTestPlan(String testPlan) {
		this.testPlan = testPlan;
	}

	public String getTestCase() {
		return testCase;
	}

	public void setTestCase(String testCase) {
		this.testCase = testCase;
	}

}
