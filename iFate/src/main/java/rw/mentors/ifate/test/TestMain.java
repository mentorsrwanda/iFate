package rw.mentors.ifate.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import rw.mentors.ifate.domain.Domain;
import rw.mentors.ifate.service.RecordCaseService;
import rw.mentors.ifate.usecase.AdministratorBean;

/**
 * @author NIYOMWUNGERI
 * @Date Aug 2, 2017
 * @Time 11:48:05 PM
 */
public class TestMain {

	/**
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:rw/mentors/ifate/config/app-context.xml");
		AdministratorBean recordCaseService = context.getBean(AdministratorBean.class);
		for (Domain d : recordCaseService.allDomain()) {
			System.out.println(d.getName());
		}

	}

}
