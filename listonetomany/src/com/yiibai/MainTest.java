package com.yiibai;

import java.util.ArrayList;

import org.hibernate.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.*;

public class MainTest {
	public static void main(String[] args) {
		// ����5.1.0�汾���ܣ�hibernate����������·�ʽ��ȡ��
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

		Answer ans1 = new Answer();
		ans1.setAnswername("java is a programming language");
		ans1.setPostedBy("Ravi Su");

		Answer ans2 = new Answer();
		ans2.setAnswername("java is a platform");
		ans2.setPostedBy("Sudhir Wong");

		Answer ans3 = new Answer();
		ans3.setAnswername("Servlet is an Interface");
		ans3.setPostedBy("Jai Li");

		Answer ans4 = new Answer();
		ans4.setAnswername("Servlet is an API");
		ans4.setPostedBy("Arun");

		ArrayList<Answer> list1 = new ArrayList<Answer>();
		list1.add(ans1);
		list1.add(ans2);

		ArrayList<Answer> list2 = new ArrayList<Answer>();
		list2.add(ans3);
		list2.add(ans4);

		Question question1 = new Question();
		question1.setQname("What is Java?");
		question1.setAnswers(list1);

		Question question2 = new Question();
		question2.setQname("What is Servlet?");
		question2.setAnswers(list2);

		session.persist(question1);
		session.persist(question2);

		t.commit();
		session.close();
		System.out.println("success");

	}
}