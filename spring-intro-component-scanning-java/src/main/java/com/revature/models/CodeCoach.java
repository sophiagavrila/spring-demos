package com.revature.models;

import com.revature.services.MotivationService;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class CodeCoach implements Coach {

	private MotivationService motivationService;

	/**
	 * @Qualifier annotation in Spring is used to differentiate a bean among the
	 *            same type of bean objects. If we have more than one bean of the
	 *            same type and want to wire only one of them then use
	 *            the @Qualifier annotation along with @Autowired to specify which
	 *            exact bean will be wired.
	 */
	public CodeCoach(@Qualifier("codeHypeService") MotivationService service) {
		System.out.println("CodeCoach constructor invoked!");
		this.motivationService = service;
	}

	@Override
	public String getDailyWorkout() {
		return "Complete a HackerRank challenge.";
	}

	@Override
	public String getMotivation() {
		return "The code coach says: " + motivationService.getMotivation();
	}

	@Override
	public MotivationService getMotivationService() {
		return motivationService;
	}

}
