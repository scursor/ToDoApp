package com.gutseeandpro.k1project.todo.ToDoDroid;

import com.gutseeandpro.k1project.todo.ToDoDroid.dao.DataBaseConnector;
import com.gutseeandpro.k1project.todo.ToDoDroid.pojo.UserData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import javax.ws.rs.core.Response;

import lombok.NonNull;

@RestController
@SpringBootApplication
public class ToDoDroidApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToDoDroidApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			System.out.println("Let's inspect the beans provided by Spring Boot:");
			final String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}

		};
	}

	private final DataBaseConnector connector;

	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@RequestMapping("/ToDo/get")
	public List<String> getTodoList(@RequestParam(value = "user") String userId) {
		return connector.getrecord(userId).getTodoData();
	}

	@RequestMapping("/ToDo/post")
	public String saveTodoList(@RequestParam MultiValueMap<String, String> map) {
		final String user = map.get("user").get(0);
		connector.saveData(UserData.builder().userId(user)
				.todoData(map.get("data")).build());
		return "Data Saved Successfully";
	}

	@RequestMapping("/ToDo/delete")
	public String deleteTodoList(@RequestParam(value = "user") String userId) {
		connector.deleteRecord(userId);
		return "Data Deleted Successfully";
	}

	@Autowired
	public ToDoDroidApplication(@NonNull final DataBaseConnector connector) {
		this.connector = connector;
	}
}
