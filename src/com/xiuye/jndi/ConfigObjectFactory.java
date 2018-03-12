package com.xiuye.jndi;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.Reference;
import javax.naming.StringRefAddr;
import javax.naming.spi.ObjectFactory;

public class ConfigObjectFactory implements ObjectFactory {

	@Override
	public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment)
			throws Exception {
		if (!(obj instanceof Reference)) {
			return null;
		}
		Reference reference = (Reference) obj;
		// 类型检测
		if (!Config.class.getName().equalsIgnoreCase(reference.getClassName())) {
			return null;
		}
		Map<String, String> properties = new HashMap<String, String>();
		for (String property : Config.properties) {
			StringRefAddr addr = (StringRefAddr) reference.get(property);
			if (addr != null) {
				properties.put(property, addr.getContent().toString());
			}
		}
		// build
		Config config = new Config();
		config.setName(properties.get("name"));
		config.setSources(properties.get("sources"));
		return config;
	}

	public static void main(String[] args) throws Exception {
		Reference reference = new Reference(Config.class.getName(), ConfigObjectFactory.class.getName(), null);
		reference.add(new StringRefAddr("name", "mysql-jdbc"));
		reference.add(new StringRefAddr("sources", "192.168.0.122:3306"));
		Config config = (Config) new ConfigObjectFactory().getObjectInstance(reference, null, null, null);
		System.out.println(config.getName() + "<>" + config.getSources());
	}

}
