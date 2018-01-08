package com.eiah.dao.impl;

import com.eiah.dao.IDao;

public abstract class BaseDaoHibernateImpl<T> implements IDao<T> {
//	private Session session;
//	private Class clazz;
//	public BaseDaoHibernateImpl(){//泛型的反射
//		Class clz1 = this.getClass();//CustomerDaoImpl
//		Type type = clz1.getGenericSuperclass();//获取带有泛型信息的父类型 --->BaseDaoHIbernateImpl<Customer>
//		ParameterizedType ptype = (ParameterizedType)type;
//		clazz = (Class)ptype.getActualTypeArguments()[0];
//	}
//	public void setSession(Session session){
//		this.session = session;
//	}
//	public User add(T t) {
//		session.save(t);//T Customer--->把类中的数据保存到Customer表中；Book------>保存到Book表中。ORM：前提，JavaBean的属性与数据库的字段保持一致
//	}
//
//	public void update(T t) {
//		session.update(t);//Customer类对象，根据他的id更新数据库表Customer表的信息
//	}
//
//	public void delete(Serializable pk) {//你只给了一个主键，从哪个表中删除呢？
//		Object obj = session.load(clazz, pk);//load方法返回的并不是clazz对应的对象类型。而是clazz对应类型的子类（动态代理），该子类只有一个属性，叫做主键。
//		session.delete(obj);
//	}
//
//	public T findOne(Serializable pk) {//你只给了一个主键，从哪个表中查询呢？
//		System.out.println(clazz.getName());
//		return (T)session.get(clazz, pk);
//	}

}
