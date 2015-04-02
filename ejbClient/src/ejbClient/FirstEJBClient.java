package ejbClient;

import java.util.Hashtable;
import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import sy.ejb.myinterface.Caculator;

import com.sy.ejbInterface.MyStateless;
//import sy.ejb01.FirstEJB;


public class FirstEJBClient {


	
	public static void main(String[] args)
	{

		final Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY, org.jboss.naming.remote.client.InitialContextFactory.class.getName());
		env.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		env.put(Context.SECURITY_PRINCIPAL, "xiaoming");
		env.put(Context.SECURITY_CREDENTIALS, "123");
        env.put("jboss.naming.client.ejb.context", true);
		
		InitialContext context=null;
		try {
			context = new InitialContext(env);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Caculator firstEJB=null;
//		try {
//			firstEJB=(Caculator)context.lookup("maven-ejb-spike-1.0/CaculatorBean!sy.ejb.myinterface.Caculator");
//		} catch (NamingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}	
//		
//		System.out.println(firstEJB.add(1, 2));
//		
//		MyStateless mybean=null;
//		try {
//			mybean=(MyStateless)context.lookup("myEJB-1.0/MyStatelessBean!com.sy.ejbInterface.MyStateless");
//	} catch (NamingException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}	
//		
//		System.out.println(mybean.getResult());
		
		
		System.out.println("abc");
		QueueConnectionFactory fac=null;
		try {
			fac=(QueueConnectionFactory) context.lookup("jms/RemoteConnectionFactory");
			System.out.println("abc");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		QueueConnection conn=null;
		try {
			conn= fac.createQueueConnection("xiaoqiang","123");
			System.out.println("def");
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		QueueSession session=null;
		try {
			session=conn.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Queue myQueue=null;
		
		try {
			myQueue=(Queue)context.lookup("java:/jms/queue/myTestQueue");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TextMessage msg=null;
		try {
			msg=session.createTextMessage("xiaoming nihao");
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		QueueSender sender=null;
		
		try {
			sender=session.createSender(myQueue);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			sender.send(msg);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			session.close();
			System.out.println("session connection...");
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn.close();
			System.out.println("close connection...");
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
