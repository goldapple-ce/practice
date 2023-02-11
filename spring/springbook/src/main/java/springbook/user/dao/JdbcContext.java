package springbook.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

public class JdbcContext {
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void workWithStatementStrategy(StatementStrategy stmt) throws SQLException{
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			c = dataSource.getConnection(); 
			ps = stmt.makePreParedStatement(c);
			ps.executeUpdate();
		}catch(SQLException e) {
			throw e;
		}finally{
			//ps.executeUpdate에서 오류가 났을 경우
			if(ps != null) {try { ps.close();}catch(SQLException e) {}}
			//c.prepareStatement에서 오류가 났을 경우
			if(c != null) {try {c.close();}catch(SQLException e) {}}
			//dataSource.getConnection()에서 오류가 났을 경우는 리소스 반환이 필요 없음.
		}
	}
	
	public void executeSql(final String query) throws SQLException{
		workWithStatementStrategy(new StatementStrategy() {
			@Override
			public PreparedStatement makePreParedStatement(Connection c) throws SQLException {
				return c.prepareStatement(query);
			}
		});
	}
}
