package com.xiuye.classloader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MyClassLoader1 extends ClassLoader {

	// private boolean checkName(String className) {
	//
	// if (className == null) {
	// return false;
	// }
	// if ("java.lang.Object".equalsIgnoreCase(className)) {
	// return false;
	// }
	// return true;
	// }

	/**
	 * 主要重写此方法 For what : 在ClassLoader中 loadClass方法包含了findClass的,ClassLoader中的
	 * findClass方法其实相当于是个空方法:
	 *
	 * protected Class<?> findClass(String name) throws ClassNotFoundException {
	 * throw new ClassNotFoundException(name); }
	 *
	 *
	 * protected Class<?> loadClass(String name, boolean resolve) throws
	 * ClassNotFoundException { synchronized (getClassLoadingLock(name)) { //
	 * First, check if the class has already been loaded Class<?> c =
	 * findLoadedClass(name); if (c == null) { long t0 = System.nanoTime(); try
	 * { if (parent != null) { c = parent.loadClass(name, false); } else { c =
	 * findBootstrapClassOrNull(name); } } catch (ClassNotFoundException e) { //
	 * ClassNotFoundException thrown if class not found // from the non-null
	 * parent class loader }
	 *
	 * if (c == null) { // If still not found, then invoke findClass in order //
	 * to find the class. long t1 = System.nanoTime();
	 *
	 * //此句可以看到findClass被调用 c = findClass(name);
	 *
	 * // this is the defining class loader; record the stats
	 * sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
	 * sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
	 * sun.misc.PerfCounter.getFindClasses().increment(); } } if (resolve) {
	 * resolveClass(c); } return c; } }
	 */
	@Override
	protected Class<?> findClass(String className) throws ClassNotFoundException {

		Class<?> c = findLoadedClass(className);
		try {
			if (c == null) {
				ClassLoader parent = this.getParent();
				c = parent.loadClass(className);// 父类加载器找不到时候会抛异常所以用了try...catch
			}
		} catch (Exception e) {
			// 父类加载器找不到时执行此处
			if (c == null) {
				// 自定义类路径加载处
				String classPath = "D:/programming/Java/";
				c = this.loadClass(classPath, className);
			}
		}

		return c;

	}

	private byte[] readClassFile(String classPath, String className) {

		byte[] data = null;

		className = className.replaceAll("\\.", "/");

		classPath += className + ".class";

		try (FileInputStream fis = new FileInputStream(classPath);) {
			data = new byte[fis.available()];
			fis.read(data);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;
	}

	public Class<?> loadClass(String classPath, String className) {

		byte[] classData = this.readClassFile(classPath, className);
		System.out.println("load class data length: " + classData.length);
		// defineClass方法,我觉得此方法很关键,是类定义到实现具有实际意义的一重要步骤.
		return defineClass(className, classData, 0, classData.length);

	}

	/**
	 * 此方法尽量不重写 ClassLoader中已经写好了逻辑过程,想知道大概可以看看源代码
	 */
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		return this.findClass(name);
	}

	public static void main(String[] args) {

		// URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
		// for (int i = 0; i < urls.length; i++) {
		// System.out.println(urls[i].toExternalForm());
		// }

		MyClassLoader1 mcl = new MyClassLoader1();
		try {
			// 自定义类加载器加载com.xiuye.classloader.FirstPrinter(其实父类加载的)
			String className = "com.xiuye.classloader.FirstPrinter";
			Class<?> c = mcl.loadClass(className);
			IPrinter ip = (IPrinter) c.newInstance();
			ip.println("I am FirstPrinter!");
			System.out.println("FirstPrinter ClassLoader = " + ip.getClass().getClassLoader());
			System.out.println("IPrinter ClassLoader = " + IPrinter.class.getClassLoader());

			// 自定义类加载器加载com.xiuye.classloader.SecondPrinter(自定义类加载器加载的,路径在'C:/Users/Administrator/Desktop/'下)
			c = mcl.loadClass("com.xiuye.classloader.SecondPrinter");
			ip = (IPrinter) c.newInstance();
			ip.println("Hello,I am Second Printer!");
			System.out.println("SecondPrinter ClassLoader = " + ip.getClass().getClassLoader());
			System.out.println("IPrinter ClassLoader = " + IPrinter.class.getClassLoader());

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

	}

}
