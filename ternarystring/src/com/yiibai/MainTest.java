package com.yiibai;

import java.util.HashMap;
import org.hibernate.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.*;

public class MainTest {
	public static void main(String[] args) {
		// ��5.1.0�汾���ܣ�hibernate����������·�ʽ��ȡ��
		// 1. �������Ͱ�ȫ��׼����ע���࣬���ǵ�ǰӦ�õĵ������󣬲����޸ģ���������Ϊfinal
		// ��configure("cfg/hibernate.cfg.xml")�����У������ָ����Դ·����Ĭ������·����Ѱ����Ϊhibernate.cfg.xml���ļ�
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure("hibernate.cfg.xml").build();
		// 2. ���ݷ���ע���ഴ��һ��Ԫ������Դ����ͬʱ����Ԫ���ݲ�����Ӧ��һ��Ψһ�ĵ�session����
		SessionFactory sessionFactory = new MetadataSources(registry)
				.buildMetadata().buildSessionFactory();

		
		/**** ����������׼�������濪ʼ���ǵ����ݿ���� ******/
		Session session = sessionFactory.openSession();// �ӻỰ������ȡһ��session

		// creating transaction object
		Transaction t = session.beginTransaction();

		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("java is a programming language", "John Lee ");
		map1.put("java is a platform", "Ashok Su");

		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("servlet technology is a server side programming",
				"John Milton");
		map2.put("Servlet is an Interface", "Ashok Lee");
		map2.put("Servlet is a package", "Rahul Su");

		Question question1 = new Question("What is java?", "Alok", map1);
		Question question2 = new Question("What is servlet?", "Jai Dixit", map2);

		session.persist(question1);
		session.persist(question2);

		t.commit();
		session.close();
		System.out.println("successfully stored");
	}
}