package com.solusi247.reportManagementApi.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.solusi247.reportManagementApi.dao.UserDao;
import com.solusi247.reportManagementApi.model.User;

public class UserServiceImpl implements UserService{

	private static final AtomicLong counter = new AtomicLong();
	private static List<User> users;
	
	UserDao userDao = new UserDao();
	
	static{
        users= populateDummyUsers();
    }
	
	public User getUser(String email, String password ){
		return userDao.getUser(email, password);		
	}
	
	public void createUser(User user){
		userDao.createUser(user);
	}
	
	public User findUser(String email) {
		return userDao.findUser(email);
	}
	
	public User findById(long id) {
		for(User user : users){
            if(user.getUser_id() == id){
                return user;
            }
        }
        return null;
	}

	public User findByName(String name) {
		for(User user : users){
            if(user.getName().equalsIgnoreCase(name)){
                return user;
            }
        }
        return null;
	}

	public void saveUser(User user) {
		user.setUser_id(((int) counter.incrementAndGet()));
        users.add(user);
	}

	public void updateUser(User user) {
		int index = users.indexOf(user);
        users.set(index, user);
	}

	public void deleteUserById(long id) {
		for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
            User user = iterator.next();
            if (user.getUser_id() == id) {
                iterator.remove();
            }
        }
	}

	public List<User> getAllUser() {
		return userDao.getAllUser();
	}

	public void deleteAllUsers() {
		users.clear();
	}

	public boolean isUserExist(User user) {
		return findByName(user.getName())!=null;
	}

	private static List<User> populateDummyUsers(){
        List<User> users = new ArrayList<User>();
//        users.add(new User(((int) counter.incrementAndGet()), "Yoedi", "yoedi.arianto@solusi247.com", "1234", "1234"));
//        users.add(new User(((int) counter.incrementAndGet()), "Arianto", "arianto.yoedi@solusi247.com", "1234", "1234"));
        return users;
    }
}
