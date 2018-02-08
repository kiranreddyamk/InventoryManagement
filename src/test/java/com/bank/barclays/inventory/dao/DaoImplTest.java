package com.bank.barclays.inventory.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DaoImplTest {

	private DaoImpl daoImpl;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		daoImpl = new DaoImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPerformCommand() {
		String str[] = { "create", "pen", "1", "2" };
		daoImpl.performCommand(str);
	}

}
