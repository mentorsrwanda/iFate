package rw.mentors.ifate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.mentors.ifate.dao.DomainDao;
import rw.mentors.ifate.domain.Domain;
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

}
