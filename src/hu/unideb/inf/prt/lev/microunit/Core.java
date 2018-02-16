package hu.unideb.inf.prt.lev.microunit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import hu.unideb.inf.prt.lev.microunit.annotation.AfterClass;
import hu.unideb.inf.prt.lev.microunit.annotation.BeforeClass;
import hu.unideb.inf.prt.lev.microunit.annotation.*;

public class Core {

	private static ArrayList<Method> beforeClassMethods;
	private static ArrayList<Method> beforeMethods;
	private static ArrayList<Method> testMethods;
	private static ArrayList<Method> afterClassMethods;
	private static ArrayList<Method> afterMethods;

	public static void main(String[] args) {

		runTestClasses("TestTester");

	}

	private static void runTestClasses(String... classes)  {
		if (classes.length > 0) {
			for (String str : classes) {

				try {
					Class testClass = Class.forName(str);
					if (testClass.getAnnotation(TestClass.class) != null) {
						beforeClassMethods = new ArrayList<Method>();
						beforeMethods = new ArrayList<Method>();
						testMethods = new ArrayList<Method>();
						afterClassMethods = new ArrayList<Method>();
						afterMethods = new ArrayList<Method>();
						Method[] methods = testClass.getMethods();
						for (Method method : methods) {
							if (Modifier.isPublic(method.getModifiers()) && method.getParameterCount() == 0
									&& method.getReturnType().equals(Void.TYPE)) {
								if (method.getAnnotation(BeforeClass.class) != null) {
									beforeClassMethods.add(method);
								} else if (method.getAnnotation(Before.class) != null) {
									beforeMethods.add(method);
								} else if (method.getAnnotation(TestClass.class) != null) {

								} else if (method.getAnnotation(Test.class) != null) {
									testMethods.add(method);
								} else if (method.getAnnotation(After.class) != null) {
									afterMethods.add(method);
								}else if(method.getAnnotation(AfterClass.class) != null) {
									afterClassMethods.add(method);
								}
							}
						}
						
						try {
							Object testCase = testClass.getDeclaredConstructor().newInstance();
							
							invokeMethodFromList( beforeClassMethods, testCase );
							
							for (Method method : testMethods) {
								invokeMethodFromList(beforeMethods, testCase);
								method.invoke(testCase);
								invokeMethodFromList(afterClassMethods, testCase);
							}
							
						} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
								| InvocationTargetException | NoSuchMethodException | SecurityException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					} else {
						System.out.println("Az adott osuztály nem annotált a @TestClass annotációval. ");
					}
				} catch (ClassNotFoundException e) {
					System.out.println("Az osztály nem létezik. ");
				}
			}

		} else {
			System.out.println("Nincsenek osztályok. ");
		}
		

	}
	private static void invokeMethodFromList( List<Method> methodList, Object object ) {
		if(methodList)
	}

}
