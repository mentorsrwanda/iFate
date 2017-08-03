package rw.mentors.ifate.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author NIYOMWUNGERI
 * @Date Aug 2, 2017
 * @Time 11:30:49 PM
 */
public enum EStatus {
	NEW, OPEN_COMPLAINT, CLOSED_COMPLAINT, INFORMATION, IRRELEVANT;

	public static List<EStatus> allCaseStatus() {
		List<EStatus> eStatus = new ArrayList<EStatus>();
		EStatus[] arr = EStatus.values();
		eStatus.addAll(Arrays.asList(arr));
		return eStatus;
	}
}
