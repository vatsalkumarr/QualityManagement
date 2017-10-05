package com.rest.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.rest.models.DataSet;
import com.rest.models.TestPlan;

public class DataSetDaoImpl implements DataSetDao {

	private JdbcTemplate jdbcTemplate;

	public DataSetDaoImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int save(DataSet dataSet) {
		String sql = "insert into scriptdataset (dataset,output,testscid) values(?,?,?)";

		jdbcTemplate.update(sql, dataSet.getInput(), dataSet.getOutput(),
				dataSet.getScriptId());
		return getMaxId();
	}
	public int getMaxId() {

		String sql = "SELECT MAX(scriptdatasetid) FROM scriptdataset";

		int maxID = jdbcTemplate.queryForObject(sql, new Object[] {},
				Integer.class);
		
		if (maxID > 0) {
			return maxID;
		}
		return 0;

	}
	public void update(DataSet dataSet) {
		String sql = "Update scriptdataset SET dataset = ?, output = ?, testscid = ? where scriptdatasetid = ?";
		jdbcTemplate.update(sql, dataSet.getInput(), dataSet.getOutput(),
				dataSet.getScriptId(), dataSet.getDataSetId());
	}

	public void delete(int datasetid) {
		String sql = "DELETE FROM scriptdataset WHERE scriptdatasetid = ?";
		jdbcTemplate.update(sql, datasetid);
	}

	@Override
	public boolean isExist(DataSet dataSet) {

		String sql = "SELECT count(*) FROM scriptdataset where dataset = ? AND testscid = ?";

		int count = jdbcTemplate.queryForObject(sql,
				new Object[] { dataSet.getInput(),dataSet.getScriptId() }, Integer.class);

		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<DataSet> list() {
		String sql = "select * from scriptdataset ORDER BY scriptdatasetid DESC";

		List<DataSet> dataSet = jdbcTemplate.query(sql,
				new RowMapper<DataSet>() {

					@Override
					public DataSet mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						DataSet dataSet = new DataSet();
						dataSet.setDataSetId(rs.getInt("scriptdatasetid"));
						dataSet.setInput(rs.getString("dataset"));
						dataSet.setOutput(rs.getString("output"));
						dataSet.setScriptId(rs.getInt("testscid"));

						return dataSet;
					}
				});
		return dataSet;
	}

}
