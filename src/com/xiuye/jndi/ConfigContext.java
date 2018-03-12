package com.xiuye.jndi;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.naming.Binding;
import javax.naming.CompositeName;
import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NameClassPair;
import javax.naming.NameParser;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.spi.NamingManager;

public class ConfigContext implements Context {

	protected static final NameParser PARSER = new NameParserImpl();

	static class NameParserImpl implements NameParser {

		@Override
		public Name parse(String name) throws NamingException {

			return new CompositeName(name);
		}

	}

	private Hashtable env = new Hashtable();
	protected static final String SCHEMA = "config:";
	private SortedMap<String, Config> bindings = new TreeMap<String, Config>();
	private String prefix = "";

	public ConfigContext(Hashtable env) {
		this.env = env;
	}

	protected ConfigContext(String prefix, SortedMap<String, Config> subBindings) {
		this.prefix = prefix;
		this.bindings = subBindings;
	}

	public ConfigContext() {
	}

	@Override
	public Object lookup(Name name) throws NamingException {
		return this.lookup(name.toString());
	}

	@Override
	public Object lookup(String name) throws NamingException {
		String currentPath = null;
		if (!name.startsWith("/")) {
			currentPath = prefix + "/" + name;
		} else {
			currentPath = prefix + name;
		}
		Config config = bindings.get(currentPath);
		if (config != null) {
			return config;
		}
		SortedMap<String, Config> tailMap = bindings.tailMap(currentPath);
		if (!tailMap.isEmpty()) {
			SortedMap<String, Config> subBindings = new TreeMap<String, Config>();
			Iterator<String> it = tailMap.keySet().iterator();
			for (Map.Entry<String, Config> entry : tailMap.entrySet()) {
				String path = entry.getKey();
				if (path.startsWith(currentPath)) {
					subBindings.put(path, entry.getValue());
				}
			}
			if (!subBindings.isEmpty()) {
				return new ConfigContext(currentPath, subBindings);
			}
		}
		int pos = name.indexOf(":");
		if (pos > 0) {
			String scheme = name.substring(0, pos);
			Context ctx = NamingManager.getURLContext(scheme, env);
			if (ctx != null) {
				return ctx.lookup(name);
			}
		}
		return null;
	}

	@Override
	public void bind(Name name, Object obj) throws NamingException {
		bind(name.toString(), obj);
	}

	@Override
	public void bind(String name, Object obj) throws NamingException {
		if (!(obj instanceof Config)) {
			return;
		}
		String currentPath = null;
		if (!name.startsWith("/")) {
			currentPath = prefix + "/" + name;
		} else {
			currentPath = prefix + name;
		}
		bindings.put(currentPath, (Config) obj);
	}

	@Override
	public void rebind(Name name, Object obj) throws NamingException {
		bind(name, obj);
	}

	@Override
	public void rebind(String name, Object obj) throws NamingException {
		bind(name, obj);
	}

	@Override
	public void unbind(Name name) throws NamingException {
		unbind(name.toString());
	}

	@Override
	public void unbind(String name) throws NamingException {
		bindings.remove(name);
	}

	@Override
	public void rename(Name oldName, Name newName) throws NamingException {
		rename(oldName.toString(), newName.toString());
	}

	@Override
	public void rename(String oldName, String newName) throws NamingException {
		if (!bindings.containsKey(oldName)) {
			throw new NamingException("Name of " + oldName + " don't exist");
		}
		if (bindings.containsKey(newName)) {
			throw new NamingException("Name of " + newName + " has already exist.");
		}
		Config value = bindings.remove(oldName);
		bindings.put(newName, value);
	}

	@Override
	public NamingEnumeration<NameClassPair> list(Name name) throws NamingException {
		return null;
	}

	@Override
	public NamingEnumeration<NameClassPair> list(String name) throws NamingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NamingEnumeration<Binding> listBindings(Name name) throws NamingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NamingEnumeration<Binding> listBindings(String name) throws NamingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void destroySubcontext(Name name) throws NamingException {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroySubcontext(String name) throws NamingException {
		// TODO Auto-generated method stub

	}

	@Override
	public Context createSubcontext(Name name) throws NamingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Context createSubcontext(String name) throws NamingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object lookupLink(Name name) throws NamingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object lookupLink(String name) throws NamingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NameParser getNameParser(Name name) throws NamingException {
		return PARSER;
	}

	@Override
	public NameParser getNameParser(String name) throws NamingException {
		return PARSER;
	}

	@Override
	public Name composeName(Name name, Name prefix) throws NamingException {
		Name result = (Name) prefix.clone();
		result.addAll(name);

		return result;
	}

	@Override
	public String composeName(String name, String prefix) throws NamingException {
		CompositeName result = new CompositeName(prefix);
		result.addAll(new CompositeName(name));
		return result.toString();
	}

	@Override
	public Object addToEnvironment(String propName, Object propVal) throws NamingException {
		return this.env.put(propName,propVal);
	}

	@Override
	public Object removeFromEnvironment(String propName) throws NamingException {
		return this.env.remove(propName);
	}

	@Override
	public Hashtable<?, ?> getEnvironment() throws NamingException {
		return null;
	}

	@Override
	public void close() throws NamingException {
		// TODO Auto-generated method stub

	}

	@Override
	public String getNameInNamespace() throws NamingException {
		return "NameInNamespace";
	}

}
