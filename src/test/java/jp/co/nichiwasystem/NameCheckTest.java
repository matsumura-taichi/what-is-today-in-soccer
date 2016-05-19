package jp.co.nichiwasystem;

import static org.junit.Assert.*;

import org.junit.Test;

public class NameCheckTest {

	@Test
	public void NameString() {
	    assertTrue(SearchController.NameCheck("何かの日"));
    }

	@Test
	public void NameStringAndWhiteSpace() {
	    assertTrue(SearchController.NameCheck(" 何かの日 "));
    }

	@Test
	public void NameNull() {
	    assertFalse(SearchController.NameCheck(null));
    }

	@Test
	public void NameStringEmpty() {
	    assertFalse(SearchController.NameCheck(""));
    }

	@Test
	public void NameWhiteSpace() {
	    assertFalse(SearchController.NameCheck(" "));
    }

}
