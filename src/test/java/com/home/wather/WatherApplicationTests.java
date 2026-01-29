package com.home.wather;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.home.wather.interfaces.WarmestDataStructureInterface;
import com.home.wather.services.WarmestDataStructureService;

@SpringBootTest
class WatherApplicationTests {

	 private WarmestDataStructureInterface warmestDataStructureService;

    @BeforeEach
    public void setUp() {
        warmestDataStructureService = new WarmestDataStructureService();
    }
	@Test
    public void testCompleteScenario() {
        // Complete test following all 21 test cases
        assertNull(warmestDataStructureService.getWarmest()); // 1
        assertNull(warmestDataStructureService.put("a", 100)); // 2
        assertEquals("a", warmestDataStructureService.getWarmest()); // 3
        assertEquals(100, warmestDataStructureService.put("a", 101)); // 4
        assertEquals(101, warmestDataStructureService.put("a", 101)); // 5
        assertEquals(101, warmestDataStructureService.get("a")); // 6
        assertEquals("a", warmestDataStructureService.getWarmest()); // 7
        assertEquals(101, warmestDataStructureService.remove("a")); // 8
        assertNull(warmestDataStructureService.remove("a")); // 9
        assertNull(warmestDataStructureService.getWarmest()); // 10
        assertNull(warmestDataStructureService.put("a", 100)); // 11
        assertNull(warmestDataStructureService.put("b", 200)); // 12
        assertNull(warmestDataStructureService.put("c", 300)); // 13
        assertEquals("c", warmestDataStructureService.getWarmest()); // 14
        assertEquals(200, warmestDataStructureService.remove("b")); // 15
        assertEquals("c", warmestDataStructureService.getWarmest()); // 16
        assertEquals(300, warmestDataStructureService.remove("c")); // 17
        assertEquals("a", warmestDataStructureService.getWarmest()); // 18
        assertEquals(100, warmestDataStructureService.remove("a")); // 19
        assertNull(warmestDataStructureService.getWarmest()); // 20
        assertNull(warmestDataStructureService.remove("a")); // 21
    }
}
