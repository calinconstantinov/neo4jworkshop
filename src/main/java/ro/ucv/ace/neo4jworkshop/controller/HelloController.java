package ro.ucv.ace.neo4jworkshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ro.ucv.ace.neo4jworkshop.model.User;
import ro.ucv.ace.neo4jworkshop.repository.UserRepository;

@RestController
@RequestMapping("/hello")
public class HelloController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping
	public String hello(@RequestParam(value = "name") String name) {

		User user;

		user = new User();
		user.setUuid(1);
		user.setName("Calin");
		userRepository.save(user);

		user = new User();
		user.setUuid(2);
		user.setName("Mihai");
		userRepository.save(user);

		user = new User();
		user.setUuid(3);
		user.setName("Emilian");
		userRepository.save(user);

		user = new User();
		user.setUuid(4);
		user.setName("Stefan");
		userRepository.save(user);

		user = new User();
		user.setUuid(5);
		user.setName("Vladucu");
		userRepository.save(user);

		user = new User();
		user.setUuid(6);
		user.setName("Mihaela");
		userRepository.save(user);

		user = new User();
		user.setUuid(7);
		user.setName("Valentina");
		userRepository.save(user);

		user = new User();
		user.setUuid(8);
		user.setName("Theodora");
		userRepository.save(user);

		user = new User();
		user.setUuid(9);
		user.setName("Andra");
		userRepository.save(user);

		user = new User();
		user.setUuid(10);
		user.setName("Adelina");
		userRepository.save(user);

		return "Hello " + name + "!";
	}

}
