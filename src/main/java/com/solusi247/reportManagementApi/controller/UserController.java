package com.solusi247.reportManagementApi.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.solusi247.reportManagementApi.model.User;
import com.solusi247.reportManagementApi.service.UserServiceImpl;

@Path("/user") 	
public class UserController {
	
	User user = new User();
	UserServiceImpl userService = new UserServiceImpl();

	@GET
	@Path("/getAllUser")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> listAllUsers(){
//		List<User> users = userService.findAllUsers();
//		return new User(1, "yudi", "yoedi.arianto@solusi247.com", "1234", "1234");
		return userService.getAllUser();
	}
	
	@GET
	@Path("/getUser")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@QueryParam("email") String email, @QueryParam("password") String password){
		return userService.getUser(email, password);
	}
	
	@POST
	@Path("/createUser")
	public void createUser(@QueryParam("name") String name,
											@QueryParam("email") String email,
											@QueryParam("password") String password){
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);
		
		userService.createUser(user);
	}
	
	@GET
	@Path("/findUser")
	@Produces(MediaType.APPLICATION_JSON)
	public User findUser(@QueryParam("email") String email){
		return userService.findUser(email);
	}
	
//    public ResponseEntity<List<User>> listAllUsers() {
//        List<User> users = userService.findAllUsers();
//        if(users.isEmpty()){
//        	//You many decide to return HttpStatus.NOT_FOUND
//            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
//    }
//	
//	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<User> getUser(@PathVariable("id") long id) {
//        System.out.println("Fetching User with id " + id);
//        User user = userService.findById(id);
//        if (user == null) {
//            System.out.println("User with id " + id + " not found");
//            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<User>(user, HttpStatus.OK);
//    }
//	
//	@RequestMapping(value = "/user/", method = RequestMethod.POST)
//    public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
//        System.out.println("Creating User " + user.getName());
// 
//        if (userService.isUserExist(user)) {
//            System.out.println("A User with name " + user.getName() + " already exist");
//            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
//        }
// 
//        userService.saveUser(user);
// 
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
//        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
//    }
//	
//	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
//    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
//        System.out.println("Updating User " + id);
//         
//        User currentUser = userService.findById(id);
//         
//        if (currentUser==null) {
//            System.out.println("User with id " + id + " not found");
//            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
//        }
// 
//        currentUser.setName(user.getName());
//         
//        userService.updateUser(currentUser);
//        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
//    }
//	
//	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
//    public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
//        System.out.println("Fetching & Deleting User with id " + id);
// 
//        User user = userService.findById(id);
//        if (user == null) {
//            System.out.println("Unable to delete. User with id " + id + " not found");
//            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
//        }
// 
//        userService.deleteUserById(id);
//        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
//    }
//	
//	@RequestMapping(value = "/user/", method = RequestMethod.DELETE)
//    public ResponseEntity<User> deleteAllUsers() {
//        System.out.println("Deleting All Users");
// 
//        userService.deleteAllUsers();
//        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
//    }
	
}
