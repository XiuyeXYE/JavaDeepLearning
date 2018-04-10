package com.xiuye.bytecode;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JavaClassExecuter {

	public static String execute(byte[] classByte){
		HackSystem.clearBuffer();
		ClassModifier cm = new ClassModifier(classByte);
		byte[] modiBytes = cm.modifyUTF8Constant("java/lang/System", "com.xiuye.bytecode.HackSystem");

		HotSwapClassLoader loader = new HotSwapClassLoader();
		Class clazz = loader.loadByte(modiBytes);

		try {
			Method method = clazz.getMethod("main", new Class[]{String[].class});
			method.invoke(null, new String[]{null});
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return HackSystem.getBufferString();

	}

}
