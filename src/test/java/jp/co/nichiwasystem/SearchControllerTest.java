package jp.co.nichiwasystem;

import java.text.DateFormat;

import org.junit.Test;

public class SearchControllerTest {

//	@Test
//	public boolean validateDatesOk() {
//		String strDate = "2016" + "/" + String.format("%1$02d", month) + "/" + String.format("%1$02d", day);
//	    DateFormat format = DateFormat.getDateInstance();
//
//	    // 日付/時刻解析を厳密に行うかどうかを設定する。
//	    format.setLenient(false);
//	    format.parse(strDate);
//	}

	@Test
	public void validateDatesOk() {
	    DateFormat format = DateFormat.getDateInstance();
	    format.setLenient(false);
//	    format.parse(new Days("2016/02/31"));
	}

	@Test
	public void validateDatesNg() {
	    DateFormat format = DateFormat.getDateInstance();
	    format.setLenient(false);
//	    format.parse(new Days("2016/08/31"));
	}

//	@Test
//	public boolean validateDates(Integer month, Integer day) {
//
//	    if (month == null || day == null) {
//
//	    }
//
//		String strDate = "2016" + "/" + String.format("%1$02d", month) + "/" + String.format("%1$02d", day);
//	    DateFormat format = DateFormat.getDateInstance();
//
//	    // 日付/時刻解析を厳密に行うかどうかを設定する。
//	    format.setLenient(false);
//	    try {
//	        format.parse(strDate);
//	        return true;
//	    } catch (Exception e) {
//	        return false;
//	    }
//	}


}