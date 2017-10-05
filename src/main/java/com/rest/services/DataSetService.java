package com.rest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rest.dao.DataSetDao;
import com.rest.dao.DataSetDaoImpl;
import com.rest.models.DataSet;

public class DataSetService {
	
	@Autowired
	private DataSetDao dataSetDao;
	
	public void savePlan(DataSet dataSet) {
		dataSetDao.save(dataSet);
	}
	
	public boolean isPlanExist(DataSet dataSet) {
		return dataSetDao.isExist(dataSet);
	}
	
	public void deletePlan(int datasetid) {
		dataSetDao.delete(datasetid);
	}
	
	public void updatePlan(DataSet dataSet) {
		dataSetDao.update(dataSet);
	}
	
	public List<DataSet> getDataSet() {
		return ((DataSetDaoImpl) dataSetDao).list();
	}

}
