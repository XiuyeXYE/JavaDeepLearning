package com.xiuye.util;

import java.io.IOException;
import java.lang.management.ManagementFactory;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

public class ProcessUtil {

	public static void attachJar(String jarFile,String args){
		String name = ManagementFactory.getRuntimeMXBean().getName();
		String currentPid = name.split("@")[0];
		VirtualMachine vm = null;
		try {
			vm = VirtualMachine.attach(currentPid);
			vm.loadAgent(jarFile,args);
		} catch (AttachNotSupportedException | IOException | AgentLoadException | AgentInitializationException e) {
			e.printStackTrace();
		}
		finally {
			if(vm != null){
				try {
					vm.detach();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}


	}
	public static void attachJar(String jarFile){
		attachJar(jarFile, null);
	}

}
