package springbook.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.mysql.cj.exceptions.MysqlErrorNumbers;

import springbook.exception.DuplicateUserIdException;
import springbook.user.domain.User;

public class UserDao {
	private JdbcTemplate jdbcTemplate;
	
	//Dependency Injection방식
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void add(final User user) throws DuplicateUserIdException{
		this.jdbcTemplate.update("insert into users(id,name, password) values(?,?,?)",user.getId(),user.getName(),user.getPassword());
	}
	
	public User get(String id ){
		return this.jdbcTemplate.queryForObject("select * from users where id=?",
			new Object[] {id},
			this.userMapper);
	}
	
	public void deleteAll(){
		this.jdbcTemplate.update("delete from users");
	}
	
	public int getCount() throws SQLException{
		return this.jdbcTemplate.query(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException{
				return con.prepareStatement("select count(*) from users");
			}
		}, new ResultSetExtractor<Integer>() {

			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				rs.next();
				return rs.getInt(1);
			}
		});
	}
	
	public List<User> getAll(){
		return this.jdbcTemplate.query("select * from users order by id", this.userMapper);
	}
	
	private RowMapper<User> userMapper = new RowMapper<User>() {

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			return user;
		}

	};
}