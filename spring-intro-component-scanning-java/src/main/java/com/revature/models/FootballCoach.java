package com.revature.models;

import com.revature.services.MotivationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Component is an annotation that allows Spring to automatically detect our
 *            custom beans.
 * 
 *            What is difference between @Component and @Bean in Spring? So the
 *            bottom line is that both can do the job of wiring your bean. It's
 *            just that with @Bean you have to define each class explicitly and
 *            in case of @Component, Spring does this automatically for you.
 *            The @Bean way of wiring your bean is analogous to defining Beans
 *            in XML, prior to Spring 2.5.
 * 
 * @Component is a class level annotation whereas @Bean is a method level
 *            annotation and name of the method serves as the bean
 *            name. @Component need not to be used with the @Configuration
 *            annotation where as @Bean annotation has to be used within the
 *            class which is annotated with @Configuration.
 */
@Component
public class FootballCoach implements Coach {

	@Value("${coach.email}")
	private String email;

	@Value("The Jets")
	private String teamName;

	private MotivationService motivationService;

	public FootballCoach() {
		System.out.println("FootballCoach no-args constructor invoked!");
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public MotivationService getMotivationService() {
		return motivationService;
	}

	/**
	 * @Autowired marks a Constructor, Setter method, Properties and Config() method
	 *            as to be autowired; that is ‘injecting beans'(Objects) at runtime
	 *            by Spring Dependency Injection mechanism.
	 */
	@Autowired
	public void setMotivationService(MotivationService sportMotivationService) {
		System.out.println("FootballCoach.setMotivationService() invoked!");
		this.motivationService = sportMotivationService;
	}

	@PostConstruct
	private void customInit() {
		System.out.println("FootballCoach.customInit() invoked!");
	}

	@PreDestroy
	private void customDestroy() {
		System.out.println("FootballCoach.customDestroy() invoked!");
	}

	@Override
	public String getDailyWorkout() {
		return "Today's workout: Suicide runs to 40, 50, 60, 80, 100 yard lines";
	}

	@Override
	public String getMotivation() {
		return "The football coach says: " + motivationService.getMotivation();
	}
}
