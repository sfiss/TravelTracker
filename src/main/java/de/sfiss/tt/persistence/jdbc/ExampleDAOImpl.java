package de.sfiss.tt.persistence.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.stereotype.Repository;

import de.sfiss.tt.model.Example;
import de.sfiss.tt.persistence.ExampleDAO;

@Repository
public class ExampleDAOImpl implements ExampleDAO {

	@Inject
	private DataSource dataSource;

	@Override
	public void save(Example entity) {
		final String sql = "INSERT INTO example VALUES(:name)";
		final Map<String, Object> params = new HashMap<>();
		params.put("name", entity.getName());

		new SqlUpdate(dataSource, sql) {
			{
				declareParameter(new SqlParameter("name", Types.VARCHAR));
			}
		}.updateByNamedParam(params);
	}

	@Override
	public Collection<Example> findAll() {
		final String sql = "SELECT * FROM example";
		Collection<Example> result = new MappingSqlQuery<Example>(dataSource, sql) {

			@Override
			protected Example mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Example example = new Example();
				example.setName(rs.getString("name"));
				return example;
			}
		}.execute();
		return result;
	}

}
