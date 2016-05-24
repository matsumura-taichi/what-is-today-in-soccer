package jp.co.nichiwasystem;

import static org.junit.Assert.*;

import org.junit.Test;

public class DateCheckTest {

	@Test
	public void validateDateOk() {
	    assertTrue(SearchController.dateCheck(2, 28));
	}

	@Test
	public void validateDateNg() {
	    assertFalse(SearchController.dateCheck(2, 31));
    }

	@Test
	public void validateMonthNull() {
	    assertFalse(SearchController.dateCheck(null, 31));
    }

	@Test
	public void validateDayNull() {
	    assertFalse(SearchController.dateCheck(1, null));
    }

	@Test
	public void validateDateNull() {
	    assertFalse(SearchController.dateCheck(null, null));
    }

}