package com.xiuye.jndi;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

import javax.naming.CompositeName;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;

public class ConfigInitialContextFactory implements InitialContextFactory {

	protected static final String PREFIX = "config.";
	protected static final String NAME_SUFFIX = ".name";
	protected static final String SOURCES_SUFFIX = ".sources";

	@Override
	public Context getInitialContext(Hashtable<?, ?> environment) throws NamingException {
		if (environment == null) {
			return new ConfigContext();
		}
		Map<String, Map<String, String>> configs = new HashMap<String, Map<String, String>>();
		Properties innerEnv = new Properties();
		for (Map.Entry entry : environment.entrySet()) {
			String key = (String) entry.getKey();
			if (!key.startsWith(PREFIX)) {
				continue;
			}
			int begin = key.indexOf(".");
			int end = key.lastIndexOf(".");
			if (begin == end) {
				continue;
			}
			String property = key.substring(end + 1);
			if (!Config.contains(property)) {
				continue;
			}
			// 将naming表示为类似于目录的路径,其实它可以为任意字符串.
			String name = key.substring(begin + 1, end).replaceAll("\\.", "/");
			Map<String, String> properties = configs.get(name);
			if (properties == null) {
				properties = new HashMap<String, String>();
				configs.put(name, properties);
			}
			String content = "";
			if (entry.getValue() != null) {
				content = entry.getValue().toString();
			}
			properties.put(property, content);
			innerEnv.put(name + "/" + property, content);
		}

		Context context = new ConfigContext();
		for (Map.Entry<String, Map<String, String>> entry : configs.entrySet()) {
			String name = entry.getKey();
			Config config = createConfig(name, entry.getValue());
			context.bind(name, config);
		}

		return context;
	}

	private Config createConfig(String name, Map<String, String> properties) {
		if (name == null) {
			throw new RuntimeException("config name cant be empty..");
		}
		Config config = new Config(name);
		String sources = properties.get("sources");
		if (sources != null) {
			config.setSources(sources);
		}
		// more properties setting..
		return config;
	}
	private static<T> void log(T t){
		System.out.println(t);
	}
	public static void main(String[] args) throws Exception {
        Properties env = new Properties();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.xiuye.jndi.ConfigInitialContextFactory");
        env.put("config.database.mysql.name", "mysql-jdbc");
        env.put("config.database.mysql.sources", "192.168.0.122:3306");
        Context context = new InitialContext(env);
        Config config = (Config) context.lookup("database/mysql");
        log(config);
        if (config != null) {
            System.out.println(config.getName() + "," + config.getSources());
        }
        Name name = new CompositeName("database/mysql");
        config = (Config) context.lookup(name);
        if (config != null) {
            System.out.println(config.getName() + "," + config.getSources());
        }
        Context subContext = (Context)context.lookup("database");
        config = (Config) subContext.lookup("mysql");
        if (config != null) {
            System.out.println(config.getName() + "," + config.getSources());
        }
    }
}
