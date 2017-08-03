package rw.mentors.ifate.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import rw.mentors.ifate.service.RecordCaseService;

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
		RecordCaseService recordCaseService = context.getBean(RecordCaseService.class);
		System.out.println(recordCaseService.getClass().toString());

	}

}
