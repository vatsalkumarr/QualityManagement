package com.rest.models;

import java.util.List;

public class TestScript {

	private int scriptId;
	private String scriptTitle;
	private String scriptDesc;
	private String scriptInst;
	private int caseId;
	private List<DataSet> dataSet;

	public int getScriptId() {
		return scriptId;
	}

	public void setScriptId(int scriptId) {
		this.scriptId = scriptId;
	}

	public String getScriptTitle() {
		return scriptTitle;
	}

	public void setScriptTitle(String scriptTitle) {
		this.scriptTitle = scriptTitle;
	}

	public String getScriptDesc() {
		return scriptDesc;
	}

	public void setScriptDesc(String scriptDesc) {
		this.scriptDesc = scriptDesc;
	}

	public String getScriptInst() {
		return scriptInst;
	}

	public void setScriptInst(String scriptInst) {
		this.scriptInst = scriptInst;
	}

	public int getCaseId() {
		return caseId;
	}

	public void setCaseId(int caseId) {
		this.caseId = caseId;
	}

	public List<DataSet> getDataSet() {
		return dataSet;
	}

	public void setDataSet(List<DataSet> dataSet) {
		this.dataSet = dataSet;
	}

	
	

}
