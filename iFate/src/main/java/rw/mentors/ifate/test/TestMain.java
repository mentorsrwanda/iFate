package rw.mentors.ifate.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.bouncycastle.asn1.ocsp.ResponderID;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import rw.mentors.ifate.dao.DistrictDao;
import rw.mentors.ifate.domain.Answer;
import rw.mentors.ifate.domain.Category;
import rw.mentors.ifate.domain.District;
import rw.mentors.ifate.domain.Domain;
import rw.mentors.ifate.domain.Indicator;
import rw.mentors.ifate.domain.Province;
import rw.mentors.ifate.domain.Response;
import rw.mentors.ifate.service.AdminService;
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
		AdminService service = context.getBean(AdminService.class);
		Map<String, HashMap<String, Integer>> map = new HashMap<String, HashMap<String, Integer>>();
		Domain domain = service.findDomain("402838815daaa5ce015daab602390001");
		List<Indicator> list = new ArrayList<Indicator>();
		for (Category categorie : domain.getCategories()) {
			System.out.println(categorie.getName());
			for (Indicator indicator : categorie.getIndicators()) {

				if (indicator.getAnswers().isEmpty() == false) {
					list.add(indicator);
				}

			}

		}

		for (Indicator indicator : list) {
			HashMap<String, Integer> mm = new HashMap<String, Integer>();
			for (Answer answer : indicator.getAnswers()) {

				int i = 0;
				for (Response response : service.allResponses()) {
					if (response.getAnswer() != null) {
						if (response.getAnswer().getId().equals(answer.getId())) {
							i++;
						} else {
							i = i;
						}
					}
				}
				mm.put(answer.getOpenAnswer(), i);

			}
			map.put(indicator.getName(), mm);
		}

		for (Map.Entry<String, HashMap<String, Integer>> valeur : map.entrySet()) {
			System.out.println(valeur.getKey());
			for (Map.Entry<String, Integer> values : valeur.getValue().entrySet()) {
				System.out.println(values.getKey() + " " + values.getValue());
			}
		}

	}

}
