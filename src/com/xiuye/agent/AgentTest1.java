package com.xiuye.agent;

import java.io.IOException;
import java.lang.management.ManagementFactory;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

public class AgentTest1 {

	public static void main(String[] args) {
		String name = ManagementFactory.getRuntimeMXBean().getName();
		System.out.println(name);
		String []s = name.split("@");
		String pid = s[0];

		VirtualMachine vm = null;
		try {
			vm = VirtualMachine.attach(pid);
			vm.loadAgent("agent1.jar","agent1");

		} catch (AttachNotSupportedException|AgentInitializationException|AgentLoadException | IOException e) {
			e.printStackTrace();
		}
		finally{
			if(vm != null){
				try {
					vm.detach();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("AgentTest1.main");

		//while(true);
	}

}
