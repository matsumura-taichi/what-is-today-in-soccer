package jp.co.nichiwasystem;

import static org.junit.Assert.*;

import org.junit.Test;

public class DateCheckTest {

	@Test
	public void validateDatesOk() {
	    assertTrue(SearchController.DateCheck(2, 28));
	}

	@Test
	public void validateDatesNg() {
	    assertFalse(SearchController.DateCheck(2, 31));
    }

}