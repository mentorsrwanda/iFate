package rw.mentors.ifate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.mentors.ifate.dao.CaseDao;
import rw.mentors.ifate.domain.Cases;

/**
 * @author NIYOMWUNGERI
 * @Date Aug 2, 2017
 * @Time 11:58:47 PM
 */
@Service
public class RecordCaseServiceImpl extends TransactionAware implements RecordCaseService {

	@Autowired
	private CaseDao caseDao;

	@Override
	public List<Cases> allCase() {
		return caseDao.findAll();
	}

}
