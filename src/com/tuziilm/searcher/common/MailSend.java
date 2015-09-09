package com.tuziilm.searcher.common;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * �����ʼ�
 * @author <a href="tuziilm@gmail.com">tuziilm</a>
 *
 */
public class MailSend {
	//private String host = "smtp.163.com";
		private String host = Config.getParam("host");
		// �������������û���
		private String username = Config.getParam("username");
		// �����������
		private String password = Config.getParam("password");

		private String mail_head_name = Config.getParam("mail_head_name");

		private String mail_head_value = Config.getParam("mail_head_value");

		//private String mail_to = SysParam.getParam("mail_to");

		private String mail_from = Config.getParam("mail_from");

		private String mail_subject = Config.getParam("mail_subject");
			
		//private String mail_body = SysParam.getParam("mail_body");

		private String personalName = Config.getParam("personalName");

		public String send(String mail_to,String mail_body) throws Exception {
			String info = "";
			try {
				Properties props = new Properties(); // ��ȡϵͳ����
				Authenticator auth = new Email_Autherticator(); // �����ʼ��������û���֤
				props.put("mail.smtp.host", host);
				props.put("mail.smtp.auth", "true");
				Session session = Session.getDefaultInstance(props, auth);
				// ����session,���ʼ�����������ͨѶ��
				MimeMessage message = new MimeMessage(session);
				// message.setContent("foobar, "application/x-foobar"); // �����ʼ���ʽ
				message.setSubject(mail_subject); // �����ʼ�����
//				message.setText(mail_body); // �����ʼ�����
				message.setContent(mail_body, "text/html;charset = gbk");  // �����ʼ���ʽ
				message.setHeader(mail_head_name, mail_head_value); // �����ʼ�����
				message.setSentDate(new Date()); // �����ʼ���������
				Address address = new InternetAddress(mail_from, personalName);
				message.setFrom(address); // �����ʼ������ߵĵ�ַ
				//Address toAddress = new InternetAddress(mail_to); // �����ʼ����շ��ĵ�ַ
				InternetAddress[] toAddress = new InternetAddress().parse(mail_to);
				//message.addRecipient(Message.RecipientType.TO, toAddress);
				message.setRecipients(Message.RecipientType.TO, toAddress);
				Transport.send(message); // �����ʼ�
				System.out.println("send ok!");
				info = "success";
			} catch (Exception ex) {
				info = "error";
				ex.printStackTrace();
				throw new Exception(ex.getMessage());
			}
			return info;
		}

		/** */
		/**
		 * �������з��������û�����֤
		 */
		public class Email_Autherticator extends Authenticator {
			public Email_Autherticator() {
				super();
			}
			public Email_Autherticator(String user, String pwd) {
				super();
				username = user;
				password = pwd;
			}

			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		}
		public static void main(String[] args) {
			MailSend ms = new MailSend();
			try {
				ms.send("1462479893@qq.com","sfafd");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
