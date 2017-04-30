package com.yiibai;

import java.util.ArrayList;
import java.util.HashSet;

import org.hibernate.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.*;

public class MainTest {
	public static void main(String[] args) {
		// ��5.1.0�汾�У�hibernate����������·�ʽ��ȡ��
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

		HashSet<String> set1=new HashSet<String>();
		set1.add("java is a programming language");
		set1.add("java is a platform");
		
		HashSet<String> set2=new HashSet<String>();
		set2.add("Servlet is an Interface");
		set2.add("Servlet is an API");
		
		Question question1=new Question();
		question1.setQname("What is Java?");
		question1.setAnswers(set1);
		
		Question question2=new Question();
		question2.setQname("What is Servlet?");
		question2.setAnswers(set2);
		
		session.persist(question1);
		session.persist(question2);
		
		t.commit();
		session.close();
		System.out.println("success");

	}
}