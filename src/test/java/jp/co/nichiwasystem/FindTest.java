package jp.co.nichiwasystem;

import static org.junit.Assert.*;

import org.junit.Test;

public class FindTest {

	@Test
	public void FindData() {
		assertEquals(1, SearchController.FindDataCount(1, 1));
    }

	@Test
	public void NotFindData() {
		assertEquals(0, SearchController.FindDataCount(1, 3));
    }
}
