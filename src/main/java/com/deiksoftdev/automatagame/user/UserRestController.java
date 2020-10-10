package com.deiksoftdev.automatagame.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping(value = UserRestController.USER_API)
public class UserRestController {

	public static final String USER_API = "/api/users";
	
	private final UserRepository userRepository;
	
	@Autowired
	public UserRestController(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@GetMapping("/{userId}")
    public User findById(@PathVariable Long userId) {
    	return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }
 
    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User book) {
        return userRepository.save(book);
    }
}