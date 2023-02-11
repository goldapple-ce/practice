package springbook.user.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import springbook.user.domain.Level;
import springbook.user.domain.User;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=DaoFactory.class)
public class UserDaoTest {
	@Autowired
	private ApplicationContext context;
	@Autowired
	private UserDao dao;
	
	User user1 = null;
	User user2 = null;
	User user3 = null;
	
	
	@Before
	public void setUp() {
		this.user1 = new User("abc","에비씨","springno1",Level.BASIC, 1, 0);
		this.user2 = new User("def","디디엪","springno2",Level.SILVER, 55, 10);
		this.user3 = new User("ghi","쥐엧아","springno3",Level.GOLD, 100, 40);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void addAndGet() throws SQLException{
		dao.deleteAll();
		assertThat(dao.getCount(),is(0));
		
		User user = new User();
		
		user.setId("whiteship");
		user.setName("백기선");
		user.setPassword("married");
		
		dao.add(user);
		
		assertThat(dao.getCount(),is(1));
		
		User user2 = dao.get(user.getId());
		
		assertThat(user2.getName(),is(user.getName()));
		assertThat(user2.getPassword(),is(user.getPassword()));
	}
//	
//	@SuppressWarnings("deprecation")
//	@Test
//	public void count() throws SQLException{
//		User user1 = new User("gyumee","박성철","springno1");
//		User user2 = new User("leegw700","이길원","springno2");
//		User user3 = new User("bumjin","박범진","springno3");
//		
//		dao.deleteAll();
//		assertThat(dao.getCount(),is(0));
//		
//		dao.add(user1);
//		assertThat(dao.getCount(),is(1));
//		
//		dao.add(user2);
//		assertThat(dao.getCount(),is(2));
//		
//		dao.add(user3);
//		assertThat(dao.getCount(),is(3));
//	}
//	
//	@SuppressWarnings("deprecation")
//	@Test
//	public void getAll() throws SQLException {
//		User user1 = new User("gyumee","박성철","springno1");
//		User user2 = new User("leegw700","이길원","springno2");
//		User user3 = new User("bumjin","박범진","springno3");
//		
//		dao.deleteAll();
//		
//		List<User> users0 = dao.getAll();
//		assertThat(users0.size(),is(0));
//		
//		dao.add(user1);
//		List<User> users1 = dao.getAll();
//		assertThat(users1.size(),is(1));
//		checkSameUser(user1,users1.get(0));
//		
//		dao.add(user2);
//		List<User> users2 = dao.getAll();
//		assertThat(users2.size(),is(2));
//		checkSameUser(user1,users2.get(0));
//		checkSameUser(user2,users2.get(1));
//		
//		dao.add(user3);
//		List<User> users3 = dao.getAll();
//		assertThat(users3.size(),is(3));
//		checkSameUser(user3,users3.get(0));
//		checkSameUser(user1,users3.get(1));
//		checkSameUser(user2,users3.get(2));
//	}
//	
	@SuppressWarnings("deprecation")
	private void checkSameUser(User user1, User user2) {
		assertThat(user1.getId(),is(user2.getId()));
		assertThat(user1.getName(),is(user2.getName()));
		assertThat(user1.getPassword(),is(user2.getPassword()));
		assertThat(user1.getLevel(),is(user2.getLevel()));
		assertThat(user1.getLogin(),is(user2.getLogin()));
		assertThat(user1.getRecommend(),is(user2.getRecommend()));
		
	}
	
}