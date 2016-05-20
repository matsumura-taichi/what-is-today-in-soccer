package jp.co.nichiwasystem;

import static org.junit.Assert.*;

import org.junit.Test;

public class TodayTest {

	@Test
	public void todayMonth() {
		assertEquals(5, new SearchController().getTodayMonth());
    }

	@Test
	public void todayDay() {
		assertEquals(20, new SearchController().getTodayDay());
    }
}
