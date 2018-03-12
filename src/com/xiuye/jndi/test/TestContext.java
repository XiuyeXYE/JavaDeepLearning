package com.xiuye.jndi.test;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NameClassPair;
import javax.naming.NameParser;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

public class TestContext implements Context {

	private Map<String,Object> bindings = new HashMap<>();

	@Override
	public Object lookup(Name name) throws NamingException {
		return this.lookup(name.toString());
	}

	@Override
	public Object lookup(String name) throws NamingException {
		return this.bindings.get(name);
	}

	@Override
	public void bind(Name name, Object obj) throws NamingException {

		this.bind(name.toString(),obj);

	}

	@Override
	public void bind(String name, Object obj) throws NamingException {
		this.bindings.put(name, obj);
	}

	@Override
	public void rebind(Name name, Object obj) throws NamingException {
		this.rebind(name.toString(),obj);
	}

	@Override
	public void rebind(String name, Object obj) throws NamingException {
		this.bind(name, obj);
	}

	@Override
	public void unbind(Name name) throws NamingException {
		this.unbind(name.toString());
	}

	@Override
	public void unbind(String name) throws NamingException {
		this.bindings.remove(name);
	}

	@Override
	public void rename(Name oldName, Name newName) throws NamingException {
		this.rebind(oldName.toString(), newName.toString());
	}

	@Override
	public void rename(String oldName, String newName) throws NamingException {
		Object v = this.bindings.get(oldName);
		this.bindings.remove(oldName);
		this.bindings.put(newName, v);
	}

	@Override
	public NamingEnumeration<NameClassPair> list(Name name) throws NamingException {
		return null;
	}

	@Override
	public NamingEnumeration<NameClassPair> list(String name) throws NamingException {
		return null;
	}

	@Override
	public NamingEnumeration<Binding> listBindings(Name name) throws NamingException {
		return null;
	}

	@Override
	public NamingEnumeration<Binding> listBindings(String name) throws NamingException {
		return null;
	}

	@Override
	public void destroySubcontext(Name name) throws NamingException {

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NameParser getNameParser(String name) throws NamingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Name composeName(Name name, Name prefix) throws NamingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String composeName(String name, String prefix) throws NamingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object addToEnvironment(String propName, Object propVal) throws NamingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object removeFromEnvironment(String propName) throws NamingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Hashtable<?, ?> getEnvironment() throws NamingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close() throws NamingException {
		// TODO Auto-generated method stub

	}

	@Override
	public String getNameInNamespace() throws NamingException {
		// TODO Auto-generated method stub
		return null;
	}

}
