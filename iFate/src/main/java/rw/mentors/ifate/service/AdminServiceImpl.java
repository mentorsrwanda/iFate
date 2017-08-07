package rw.mentors.ifate.service;

import java.util.List;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.mentors.ifate.dao.AnswersDao;
import rw.mentors.ifate.dao.CaseDao;
import rw.mentors.ifate.dao.CategoryDao;
import rw.mentors.ifate.dao.DistrictDao;
import rw.mentors.ifate.dao.DomainDao;
import rw.mentors.ifate.dao.IndicatorDao;
import rw.mentors.ifate.dao.ProvinceDao;
import rw.mentors.ifate.dao.ResponseDao;
import rw.mentors.ifate.dao.SectorDao;
import rw.mentors.ifate.domain.Answer;
import rw.mentors.ifate.domain.Cases;
import rw.mentors.ifate.domain.Category;
import rw.mentors.ifate.domain.District;
import rw.mentors.ifate.domain.Domain;
import rw.mentors.ifate.domain.Indicator;
import rw.mentors.ifate.domain.Province;
import rw.mentors.ifate.domain.Response;
import rw.mentors.ifate.domain.Sector;
import rw.mentors.ifate.exception.DataManipulationException;

/**
 * @author NIYOMWUNGERI
 * @Date Aug 3, 2017
 * @Time 3:02:26 AM
 */
@Service
public class AdminServiceImpl extends TransactionAware implements AdminService {

	@Autowired
	private DomainDao domainDao;

	@Autowired
	private CategoryDao categorydao;

	@Autowired
	private IndicatorDao indicatordao;

	@Autowired
	private AnswersDao answerdao;

	@Autowired
	private CaseDao casedao;

	@Autowired
	private ResponseDao responsedao;

	@Autowired
	private ProvinceDao provincedao;

	@Autowired
	private DistrictDao distrcitdao;

	@Autowired
	private SectorDao sectordao;

	@Override
	public String addDomain(Domain domain) {

		try {

			Domain d = domainDao.save(domain);
			return "Domain Added " + d.getName() + " Succesfully";

		} catch (Exception e) {
			throw new DataManipulationException(e.getMessage());
		}
	}

	@Override
	public List<Domain> allDomain() {
		return domainDao.findAll();
	}

	@Override
	public String deleteDomain(Domain domain) {
		try {
			domainDao.delete(domain);
			return "Domain is well removed";
		} catch (Exception e) {
			throw new DataManipulationException(e.getMessage());
		}
	}

	@Override
	public String addcategory(Domain domain, Category category) {
		try {
			category.setDomain(domain);
			categorydao.save(category);
			return "Category  " + category.getName() + " is well recorded";
		} catch (Exception e) {
			throw new DataManipulationException(e.getMessage());
		}
	}

	@Override
	public Domain findDomain(String id) {
		try {
			return domainDao.findOne(id);
		} catch (Exception e) {
			throw new DataManipulationException(e.getMessage());
		}
	}

	@Override
	public Category findCategory(String id) {
		try {
			return categorydao.findOne(id);
		} catch (Exception e) {
			throw new DataManipulationException(e.getMessage());
		}
	}

	@Override
	public String addIndicator(Indicator indicator, Category category) {
		try {
			indicator.setCategory(category);
			indicatordao.save(indicator);
			return "Indicator is well recorded";
		} catch (Exception e) {
			throw new DataManipulationException(e.getMessage());
		}
	}

	@Override
	public String answer(Answer answer, Indicator indicator) {
		try {
			answer.setQuestion(indicator);
			answer.setState(true);
			answerdao.save(answer);
			return "Answer is well recorded";
		} catch (Exception e) {
			throw new DataManipulationException(e.getMessage());
		}
	}

	@Override
	public Indicator findIndicator(String id) {
		try {
			return indicatordao.findOne(id);
		} catch (Exception e) {
			throw new DataManipulationException(e.getMessage());
		}
	}

	@Override
	public Answer findAnswer(String id) {
		try {
			return answerdao.findOne(id);
		} catch (Exception e) {
			throw new DataManipulationException(e.getMessage());
		}
	}

	@Override
	public String addCase(Cases cases) {
		try {
			casedao.save(cases);
			return "New Case is well recorded";
		} catch (Exception e) {
			throw new DataManipulationException(e.getMessage());
		}
	}

	@Override
	public String addResponse(Response response) {
		try {
			responsedao.save(response);
			return "Response is well recorded";
		} catch (Exception e) {
			throw new DataManipulationException(e.getMessage());
		}
	}

	@Override
	public List<Response> allResponses() {
		try {
			return responsedao.findAll();
		} catch (Exception e) {
			throw new DataManipulationException(e.getMessage());
		}
	}

	@Override
	public String addProvince(Province province) {
		try {
			provincedao.save(province);
			return "New Province is well recorded";
		} catch (Exception e) {
			throw new DataManipulationException(e.getMessage());
		}
	}

	@Override
	public String addDistrict(District district, Province province) {
		try {
			district.setProvince(province);
			distrcitdao.save(district);
			return "New District is well recorded";
		} catch (Exception e) {
			throw new DataManipulationException(e.getMessage());
		}
	}

	@Override
	public String addSector(Sector sector, District district) {
		try {
			sector.setDistrict(district);
			sectordao.save(sector);
			return "New Sector is  well recorded";
		} catch (Exception e) {
			throw new DataManipulationException(e.getMessage());
		}
	}

	@Override
	public Province findProvince(String id) {
		try {
			return provincedao.findOne(id);
		} catch (Exception e) {
			throw new DataManipulationException(e.getMessage());
		}
	}

	@Override
	public District findDistrcit(String id) {
		try {
			return distrcitdao.findOne(id);
		} catch (Exception e) {
			throw new DataManipulationException(e.getMessage());
		}
	}

	@Override
	public List<Province> allProvinces() {
		try {
			return provincedao.findAll();
		} catch (Exception e) {
			throw new DataManipulationException(e.getMessage());
		}
	}

}
