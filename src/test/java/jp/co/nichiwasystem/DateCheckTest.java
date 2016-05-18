package jp.co.nichiwasystem;

import static org.junit.Assert.*;

import org.junit.Test;

public class DateCheckTest {

	@Test
	public void validateDateOk() {
	    assertTrue(SearchController.DateCheck(2, 28));
	}

	@Test
	public void validateDateNg() {
	    assertFalse(SearchController.DateCheck(2, 31));
    }

	@Test
	public void validateMonthNull() {
	    assertFalse(SearchController.DateCheck(null, 31));
    }

	@Test
	public void validateDayNull() {
	    assertFalse(SearchController.DateCheck(1, null));
    }

	@Test
	public void validateDateNull() {
	    assertFalse(SearchController.DateCheck(null, null));
    }

}