package rw.mentors.ifate.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author NIYOMWUNGERI
 * @Date Aug 2, 2017
 * @Time 11:41:01 PM
 */
public enum EMessageOrigin {
	SMS, USSD, WHATSAPP, WEB;

	public static List<EMessageOrigin> getMessageOrigine() {
		List<EMessageOrigin> list = new ArrayList<EMessageOrigin>();
		EMessageOrigin[] arr = EMessageOrigin.values();
		list.addAll(Arrays.asList(arr));
		return list;
	}
}
