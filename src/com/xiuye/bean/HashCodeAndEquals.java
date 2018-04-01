package com.xiuye.bean;

public class HashCodeAndEquals {

	private int id;
	private String name;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + /*null => hashVal = 0*/((name == null) ? 0 : name.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		//自反性:引用实现
		if (this == obj)
			return true;
		//=null false
		if (obj == null)
			return false;
		//一致性  类型不同
		if (getClass() != obj.getClass())
			return false;
		//没有用instanceOf
		HashCodeAndEquals other = (HashCodeAndEquals) obj;
		//基本类型比较
		if (id != other.id)
			return false;
		//字符字段判断 先判断null
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}


	public void setName(String name) {
		this.name = name;
	}


	public static void main(String[] args) {

	}

}
