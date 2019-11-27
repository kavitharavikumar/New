package com.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User createUser(User user) {
		Session session = this.sessionFactory.openSession();
		// Transaction transaction=session.beginTransaction();
		session.save(user);
		// transaction.commit();
		return user;

	}

	@Override
	public List<User> readUser() {
		Session session = this.sessionFactory.openSession();

		// HQL Query
		String readUser = "from User";
		List<User> users = session.createQuery(readUser).list();
		return users;
	}

	@Override
	public User readUserById(int userId) {
		User user = null;
		Session session = this.sessionFactory.openSession();

		String readUserById = "from User where userId = :userId";
		Query query = session.createQuery(readUserById);
		query.setParameter("userId", userId);

		List<User> users = query.list();
		/*
		 * Iterator<User> itr= (Iterator<User>) query.iterate();
		 * while(itr.hasNext()) { user= (User) itr.next(); }
		 */
		return users.get(0);
	}

	@Override
	public User readUserByName(String userName) {
		User user = null;
		Session session = this.sessionFactory.openSession();

		String readUserById = "from User where userName = :userName";
		Query query = session.createQuery(readUserById);
		query.setParameter("userName", userName);

		List<User> users = query.list();
		/*
		 * Iterator<User> itr= (Iterator<User>) query.iterate();
		 * while(itr.hasNext()) { user= (User) itr.next(); }
		 */
		return users.get(0);

	}

	@Override
	public User updateUser(User user) {

		
		Session session = this.sessionFactory.openSession();
		/*Transaction tx = null;
		tx = session.beginTransaction();
		String update = "update User set userName=:userName, password=:password where id=:id ";
		Query query = session.createQuery(update);
		query.setParameter("userName", user.getUserName());
		query.setParameter("password", user.getPassword());
		query.setParameter("id", user.getUserId());
		int row = query.executeUpdate();
		tx.commit();*/
		Transaction tr=session.beginTransaction();
		session.saveOrUpdate(user);
         tr.commit();
		/*String readUserById = "from User where userId=:Id";
		Query query1 = session.createQuery(readUserById);
		query1.setParameter("Id", user.getUserId());
        List<User> users = query1.list();
	*/
		return user;

	}

	@Override
	public User deleteUserById(int userId) {

		Session session = this.sessionFactory.openSession();
		/*User user = null;
		String readUserById = "from User where userId = :userId";
		Query query = session.createQuery(readUserById);
		query.setParameter("userId", userId);

		List<User> users = query.list();

		Transaction tx = null;
		tx = session.beginTransaction();
		String del = "delete from User where id=:id1";
		Query query1 = session.createQuery(del);
		query1.setParameter("id1", userId);
		int row = query1.executeUpdate();
		tx.commit();*/
        
		User user=session.load(User.class, userId);
		if(user != null)
		{
		Transaction tr=null;
		tr=session.beginTransaction();
		session.delete(user);
		tr.commit();
		
		}
		return user;
	}

}
