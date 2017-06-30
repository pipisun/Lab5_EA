package edu.mum.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.dao.GenericDao;
import edu.mum.dao.UserDao;
import edu.mum.domain.User;

@Service
@Transactional 
public class UserServiceImpl implements edu.mum.service.UserService {
	
 	@Autowired
	private UserDao userDao;

 	
     public void save( User user) {  		
  		userDao.save(user);
 	}
  	
  	
	public List<User> findAll() {
		return (List<User>)userDao.findAll();
	}

 	public User update(User user) {
		 return userDao.update(user);

	}

	
	public List<User> findAllJoinFetch() {
		 return userDao.findAllJoinFetch();

 	}
	
	public List<User> findAllBatch() {
		List<User> users =  (List<User>)this.findAll();
 		// hydrate - need to access ALL since we don't know batch Size
		// e.g. if size =2  AND there are 6 members we need to hydrate members #1 & #3 & #5
       	for (User user : users)
       		if (!user.getBoughtItems().get(0).toString().isEmpty()) user.getBoughtItems().get(0);
		
		// This will work for our example as there are size =2 & there are 3 members
/*      members.get(0).getAddresses().get(0);
      	members.get(2).getAddresses().get(0);
*/
        return users;

	}
	
	public List<User> findAllSubSelect() {
 		// hydrate since LAZY load
       	List<User> users =  (List<User>)this.findAll();
       	users.get(0).getBoughtItems().get(0);
			           
        return users;	
	}
}
