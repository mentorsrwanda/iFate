package rw.mentors.ifate.service;

import java.util.List;

import rw.mentors.ifate.domain.Answer;
import rw.mentors.ifate.domain.Cases;
import rw.mentors.ifate.domain.Category;
import rw.mentors.ifate.domain.District;
import rw.mentors.ifate.domain.Domain;
import rw.mentors.ifate.domain.Indicator;
import rw.mentors.ifate.domain.Province;
import rw.mentors.ifate.domain.Response;
import rw.mentors.ifate.domain.Sector;

/**
 * @author NIYOMWUNGERI
 * @Date Aug 3, 2017
 * @Time 3:01:52 AM
 */
public interface AdminService {

	String addDomain(Domain domain);
	
	String addIndicator(Indicator indicator,Category category);
	
	String addcategory(Domain domain,Category category);
	
	String deleteDomain(Domain domain);
	
	String answer(Answer answer,Indicator indicator);

	List<Domain> allDomain();
	
	Domain findDomain(String id);
	
	Category findCategory(String id);
	
	Indicator findIndicator(String id);
	
	Answer findAnswer(String id);
	
	String addCase(Cases cases);
	
	String addResponse(Response response);
	
	List<Response> allResponses();
	
	String addProvince(Province province);
	
	String addDistrict(District district,Province province);
	
	String addSector(Sector sector,District district);
	
	Province findProvince(String id);
	
	District findDistrcit(String id);
	
	List<Province> allProvinces();
	
}
