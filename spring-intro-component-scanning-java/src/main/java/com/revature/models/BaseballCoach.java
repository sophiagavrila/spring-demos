package com.revature.models;

import com.revature.services.MotivationService;
import com.revature.services.SportMotivationService;
import org.springframework.stereotype.Component;

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
public class BaseballCoach implements Coach {

	private MotivationService motivationService;

	public BaseballCoach(SportMotivationService motivationService) {
		System.out.println("BaseballCoach constructor invoked!");
		this.motivationService = motivationService;
	}

	@Override
	public String getDailyWorkout() {
		return "Today's workout: Spend 30 minutes on batting practice.";
	}

	@Override
	public String getMotivation() {
		return "The baseball coach says: " + motivationService.getMotivation();
	}

	@Override
	public MotivationService getMotivationService() {
		return motivationService;
	}
}
