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
    public void fullTest() {
        
        assertNull(warmestDataStructureService.getWarmest());
        assertNull(warmestDataStructureService.put("a", 100));
        assertEquals("a", warmestDataStructureService.getWarmest());
        assertEquals(100, warmestDataStructureService.put("a", 101));
        assertEquals(101, warmestDataStructureService.put("a", 101));
        assertEquals(101, warmestDataStructureService.get("a"));
        assertEquals("a", warmestDataStructureService.getWarmest());
        assertEquals(101, warmestDataStructureService.remove("a"));
        assertNull(warmestDataStructureService.remove("a"));
        assertNull(warmestDataStructureService.getWarmest());
        assertNull(warmestDataStructureService.put("a", 100));
        assertNull(warmestDataStructureService.put("b", 200));
        assertNull(warmestDataStructureService.put("c", 300));
        assertEquals("c", warmestDataStructureService.getWarmest());
        assertEquals(200, warmestDataStructureService.remove("b"));
        assertEquals("c", warmestDataStructureService.getWarmest());
        assertEquals(300, warmestDataStructureService.remove("c"));
        assertEquals("a", warmestDataStructureService.getWarmest());
        assertEquals(100, warmestDataStructureService.remove("a")); 
        assertNull(warmestDataStructureService.getWarmest());
        assertNull(warmestDataStructureService.remove("a"));
    }
}
