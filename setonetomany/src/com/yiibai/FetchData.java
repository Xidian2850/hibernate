package com.yiibai;

import java.util.*;

import org.hibernate.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.*;

public class FetchData {
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

		Query query = session.createQuery("from Question");
		List<Question> list = query.list();

		Iterator<Question> itr = list.iterator();
		while (itr.hasNext()) {
			Question q = itr.next();
			System.out.println("Question Name: " + q.getQname());

			// printing answers
			Set<Answer> set = q.getAnswers();
			Iterator<Answer> itr2 = set.iterator();
			while (itr2.hasNext()) {
				Answer an = (Answer)itr2.next();
				System.out.println(an.getAnswername());
			}

		}
		session.close();
		System.out.println("success");
		
	}
}