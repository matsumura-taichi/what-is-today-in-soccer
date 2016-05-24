package jp.co.nichiwasystem;

import static org.junit.Assert.*;

import org.junit.Test;

public class NameCheckTest {

	@Test
	public void NameString() {
	    assertTrue(SearchController.nameCheck("何かの日"));
    }

	@Test
	public void NameStringAndWhiteSpace() {
	    assertTrue(SearchController.nameCheck(" 何かの日 "));
    }

	@Test
	public void NameNull() {
	    assertFalse(SearchController.nameCheck(null));
    }

	@Test
	public void NameStringEmpty() {
	    assertFalse(SearchController.nameCheck(""));
    }

	@Test
	public void NameWhiteSpace() {
	    assertFalse(SearchController.nameCheck(" "));
    }

}
