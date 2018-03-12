package com.xiuye.rpc;

import java.io.Serializable;

public interface DemoService extends Serializable {
	String service(String str);
}
