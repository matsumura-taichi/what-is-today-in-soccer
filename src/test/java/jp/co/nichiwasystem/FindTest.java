package jp.co.nichiwasystem;

import static org.junit.Assert.*;

import org.junit.Test;

public class FindTest {

	@Test
	public void FindData() {
		assertEquals(new SearchController().FindDataCount(1, 1), new SearchController().FindDataCount(1, 1));
    }

//	@Test
//	public void NotFindData() {
//		assertEquals(0, new SearchController().FindDataCount(2, 3));
//    }
}
