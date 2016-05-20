package jp.co.nichiwasystem;

import static org.junit.Assert.*;

import org.junit.Test;

public class FindTest {

	@Test
	public void findData() {
		assertEquals(1, new SearchController().findDataCount(1, 1));
    }

	@Test
	public void notFindData() {
		assertEquals(0, new SearchController().findDataCount(2, 3));
    }
}
