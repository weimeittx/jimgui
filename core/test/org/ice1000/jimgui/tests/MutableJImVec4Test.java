package org.ice1000.jimgui.tests;

import org.ice1000.jimgui.MutableJImVec4;
import org.ice1000.jimgui.util.JniLoader;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.ice1000.jimgui.tests.JImGuiTest.useAlternativeJniLibAndCheckHeadless;
import static org.junit.Assert.assertEquals;

public class MutableJImVec4Test {
	private static MutableJImVec4 vec;
	private static MutableJImVec4 vec2;

	@BeforeClass
	public static void loadJni() {
		JniLoader.load();
		vec = new MutableJImVec4();
		vec2 = new MutableJImVec4(0, 0, 0, 0);
	}

	@Test
	public void setW() {
		assertEquals(0, vec.getW(), 0.00001f);
		float newValue = (float) Math.random();
		vec.setW(newValue);
		assertEquals(newValue, vec.getW(), 0.00001f);
		assertEquals(0, vec2.getW(), 0.00001f);
		vec2.setW(newValue);
		assertEquals(newValue, vec2.getW(), 0.00001f);
	}

	@Test
	public void getX() {
		assertEquals(0, vec.getX(), 0.00001f);
		float newValue = (float) Math.random();
		vec.setX(newValue);
		assertEquals(newValue, vec.getX(), 0.00001f);
		assertEquals(0, vec2.getX(), 0.00001f);
		vec2.setX(newValue);
		assertEquals(newValue, vec2.getX(), 0.00001f);
	}

	@Test
	public void getY() {
		assertEquals(0, vec.getY(), 0.00001f);
		float newValue = (float) Math.random();
		vec.setY(newValue);
		assertEquals(newValue, vec.getY(), 0.00001f);
	}

	@Test
	public void getZ() {
		assertEquals(0, vec.getZ(), 0.00001f);
		float newValue = (float) Math.random();
		vec.setZ(newValue);
		assertEquals(newValue, vec.getZ(), 0.00001f);
	}

	@AfterClass
	public static void deallocate() {
		if (vec != null) {
			vec.close();
			vec2.close();
		}
	}
}