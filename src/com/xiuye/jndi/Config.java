package com.xiuye.jndi;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.Referenceable;
import javax.naming.StringRefAddr;

public class Config implements Referenceable, Serializable {
	private String name;
	private String sources;
	protected static Set<String> properties = new HashSet<String>();

	static{
		properties.add("name");
		properties.add("sources");
	}

	protected Config(String name){
		this.name = name;
	}

	public Config() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSources() {
		return sources;
	}

	public void setSources(String sources) {
		this.sources = sources;
	}

	public static boolean contains(String property){
		return properties.contains(property);
	}

	@Override
	public Reference getReference() throws NamingException {
		Reference ref = new Reference(Config.class.getName(), ConfigObjectFactory.class.getName(),null);
		ref.add(new StringRefAddr("name",this.name));
		ref.add(new StringRefAddr("sources",this.sources));

		return ref;
	}

}
