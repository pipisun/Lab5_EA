package edu.mum.dao.impl;

 

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import edu.mum.dao.UserDao;
import edu.mum.domain.User;


@SuppressWarnings("unchecked")
@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

	public UserDaoImpl() {
		super.setDaoType(User.class );
		}

 	public List<User> findAllJoinFetch() {
		  Query query =  entityManager.createQuery("SELECT distinct u FROM User AS u JOIN FETCH u.boughtItems AS a");
		  List<User> users =  query.getResultList();
		  return users;
	}

	@Override
	public List<User> findAllBatch() {
		// TODO Auto-generated method stub
		return null;
	}


 }