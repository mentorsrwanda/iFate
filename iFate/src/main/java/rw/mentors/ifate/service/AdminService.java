package rw.mentors.ifate.service;

import java.util.List;

import rw.mentors.ifate.domain.Domain;

/**
 * @author NIYOMWUNGERI
 * @Date Aug 3, 2017
 * @Time 3:01:52 AM
 */
public interface AdminService {

	String addDomain(Domain domain);

	List<Domain> allDomain();
}
