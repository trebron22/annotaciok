package hu.unideb.inf.prt.lev.microunit;

import hu.unideb.inf.prt.lev.microunit.annotation.*;

@TestClass
@FixMethodOrder(MethodSorter.ACTIVE)
public class TestTester {
	
	@Before
	public void before1() {
		System.out.println("Before 1");
	}
	
	@Before
	public void before2() {
		System.out.println("Before 2");
	}
	
	@Test
	public void teszt1() {
		System.out.println("Teszt 1");
	}
	
	@Test
	public void teszt2() {
		System.out.println("Teszt 2");
	}
	
	@After
	public void after1() {
		System.out.println("After 1");
	}
	
	@BeforeClass
	public void beforeClass1() {
		System.out.println("BeforeClass 1");
	}
	
	@AfterClass
	public void valami() {
		System.out.println("AfterClass 1");
	}
	

}
