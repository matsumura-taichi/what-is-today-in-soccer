package jp.co.nichiwasystem;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;

import org.junit.Test;

public class FindTest {

	@Test
	public void findData() {
		SearchController controller = new SearchController();
		controller.repository = mock(DaysRepository.class);
		when(controller.repository.findByMonthAndDay(1, 1)).thenReturn(Arrays.asList(new Days()));

		assertEquals(1, controller.findDataCount(1, 1));
    }

	@Test
	public void notFindData() {
		SearchController controller = new SearchController();
		controller.repository = mock(DaysRepository.class);
		when(controller.repository.findByMonthAndDay(2, 3)).thenReturn(Arrays.asList());

		assertEquals(0, controller.findDataCount(2, 3));
    }
}
