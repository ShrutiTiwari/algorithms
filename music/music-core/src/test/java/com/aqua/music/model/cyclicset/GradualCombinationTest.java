/**
 * 
 */
package com.aqua.music.model.cyclicset;

import org.junit.Test;

import com.aqua.music.model.cyclicset.GradualCombination.FrequencyGroup;

/**
 * @author "Shruti Tiwari"
 * 
 */
public class GradualCombinationTest {
	@Test
	public void test1() {
		for (GradualCombination sare : GradualCombination.values()) {
			System.out.println(sare.name());
			for (FrequencyGroup each : sare.frequencyGroup()) {
				System.out.println(each.print());
			}
		}
	}
}
