package com.xiuye.agent;

import java.lang.instrument.Instrumentation;

public class Agent1 {

	public static void premain(String args, Instrumentation ins) {
		//ins.appendToBootstrapClassLoaderSearch(jarfile);
		System.out.println("Agent.premain(String args, Instrumentation ins);args="+args);
	}

	public static void premain(String args) {
		System.out.println("Agent.premain(String args);args="+args);
	}

	public static void agentmain(String args) {
		System.out.println("Agent.agentmain(String args);args="+args);
	}
	public static void agentmain(String args, Instrumentation ins) {
		System.out.println("Agent.agentmain(String args, Instrumentation ins);args="+args);
	}
}
