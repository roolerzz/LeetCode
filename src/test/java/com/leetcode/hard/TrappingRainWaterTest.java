package com.leetcode.hard;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

/*@RunWith(Parameterized.class)*/
public class TrappingRainWaterTest {
	
	private static TrappingRainWater instance;
	
	private static int[] height;
	
	@BeforeClass
	public static void  setup() {
		instance = new TrappingRainWater();
		height = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
	}
	
	
	/*@Parameters
	public static void getData() {
		height = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
	}*/
	
	@Test
	public void testTrap() {
		assertEquals("Output is equal to 6",instance.trap(height), 6); 
	}
	
	
}
