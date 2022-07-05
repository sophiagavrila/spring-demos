package com.revature.config;

import com.revature.models.BaseballCoach;
import com.revature.models.Coach;
import com.revature.models.FootballCoach;
import com.revature.models.TrackCoach;
import com.revature.services.MotivationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

/**
 * @Configuration indicates that a class declares one or more @Bean methods and
 *                may be processed by the Spring container to generate bean
 *                definitions and service requests for those beans at runtime.
 * 
 * @PropertySource defines where a properties file might exist that defines some
 *                 values
 *
 * @ComponentScan With Spring, we use the @ComponentScan annotation along with
 *                the @Configuration annotation to specify the packages that we
 *                want to be scanned. @ComponentScan without arguments tells
 *                Spring to scan the current package and all of its
 *                sub-packages.
 */
@Configuration
@PropertySource("classpath:app.properties")
@ComponentScan("com.revature")
public class AppConfig {

	@Value("${coach.email}")
	private String coachEmail;

	/**
	 * @Bean is an annotation tells that a method produces a bean to be managed by
	 *       the Spring container. It is a method-level annotation. During Java
	 *       configuration ( @Configuration ), the method is executed and its return
	 *       value is registered as a bean within a BeanFactory
	 */
	@Bean
	@Scope("prototype")
	public Coach myCoach() {
		return new TrackCoach(myMotivationService());
	}

	@Bean
	public MotivationService myMotivationService() {
		return new MotivationService();
	}

	@Bean(initMethod = "customInit", destroyMethod = "customDestroy")
	public FootballCoach myFootballCoach() {
		FootballCoach coach = new FootballCoach();
		coach.setTeamName("The Cafebabes");
		coach.setEmail(coachEmail);
		coach.setMotivationService(myMotivationService());
		return coach;
	}

}
