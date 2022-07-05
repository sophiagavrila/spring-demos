package com.revature;

import com.revature.models.Coach;
import com.revature.models.FootballCoach;

import java.util.Arrays;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDriver {

	public static void main(String[] args) {

		System.out.println("Creating bean container");

		try (ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("beans.xml")) {

			System.out.println("Bean container created!");

			// Retrieve a bean from the container with the name "myCoach"
			Coach coach = container.getBean("myCoach", Coach.class);

			// Call some methods on the retrieved bean to ensure it is configured properly
			System.out.println(coach.getDailyWorkout());
			System.out.println(coach.getMotivation());

			// ---------------------------------------------

			// Attempt to get an assistant coach
			Coach assistantCoach = container.getBean("myCoach", Coach.class);
			System.out.println(coach == assistantCoach); // false
			System.out.println(coach.getMotivationService() == assistantCoach.getMotivationService()); // true

			// ---------------------------------------------

			FootballCoach footballCoach = container.getBean("footballCoach", FootballCoach.class);
			System.out.println(footballCoach.getDailyWorkout());
			System.out.println(footballCoach.getMotivation());
			System.out.println(footballCoach.getTeamName());
			System.out.println(footballCoach.getEmail());
			System.out.println(footballCoach.getSomeCalculatedValue());

			System.out.println("\nPrinting all beans loaded by the Spring Container below\n");

			/**
			 * Spring does not hold any reference to the prototype beans it creates. It's up
			 * to you to create a bean post-processor that would hold references to these
			 * beans if needed.
			 * 
			 * https://stackoverflow.com/questions/10956292/spring-prototype-beans-and-benefits-of-spring#:~:text=It%20explicitely%20says%3A-,To%20get%20the%20Spring%20container%20to%20release%20resources%20held%20by%20prototype%2Dscoped%20beans%2C%20try%20using%20a%20custom%20bean%20post%2Dprocessor%2C%20which%20holds%20a%20reference%20to%20beans%20that%20need%20to%20be%20cleaned%20up.,-So%20Spring%20does
			 */
			String[] beans = container.getBeanDefinitionNames();
			Arrays.sort(beans);
			for (String bean : beans) {
				System.out.println(bean);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
