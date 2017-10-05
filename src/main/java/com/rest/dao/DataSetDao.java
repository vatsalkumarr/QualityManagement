package com.rest.dao;

import java.util.List;

import com.rest.models.DataSet;
import com.rest.models.TestPlan;

public interface DataSetDao {
	
	public int save(DataSet dataSet);
	
	public void update(DataSet dataSet);

	public void delete(int datasetid);
	
	boolean isExist(DataSet dataSet);
	
	public List<DataSet> list();

}
