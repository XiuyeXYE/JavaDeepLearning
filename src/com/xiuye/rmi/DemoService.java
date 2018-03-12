package com.xiuye.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DemoService extends Remote{

	String hello(String param) throws RemoteException;

}
