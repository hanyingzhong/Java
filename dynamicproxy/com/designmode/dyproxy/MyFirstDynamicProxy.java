package com.designmode.dyproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 
 * @author yonghua.han
 *
 */

/**
 * 1)firstly, define a interface
 * 
 * @author yonghua.han
 *
 */

interface MyFirstInteraface {
	public String getInterfaceName();
}

/**
 * 2)firstly, define a concrete interface implementation class
 * 
 * @author yonghua.han
 *
 */

class MyFirstInterfaceImpl implements MyFirstInteraface {
	public String getInterfaceName() {
		String interfaceName = this.getClass().getName() + ".getInterfaceName";
		System.out.println(interfaceName);
		return interfaceName;
	}
}

/**
 * 3)firstly, define a proxy class implementing InvocationHandler
 * 
 * @author yonghua.han the most important knowledge point is how to use
 *         Method.invoke.........
 *
 */

class MyFirstInterfaceProxy implements InvocationHandler {
	public Object target;

	public MyFirstInterfaceProxy(Object target) {
		super();
		this.target = target;
	}

	public void setTarget(Object object) {
		target = object;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {		
		System.out.println(proxy.getClass().getName());
		return method.invoke(target, args);
	}
}

/**
 * 4) use Proxy.newProxyInstance to produce a new proxy instance as MyFirstInterfaceProxy
 * @author yonghua.han
 * from MyFirstInterfaceProxy, you can't visit the methods of proxy.target that's, use different target,
 * you can use differ method of target. So it's not necessary to pay attention to implmentation of target  
 */

public class MyFirstDynamicProxy {

	public static void main(String[] args) {
		MyFirstInterfaceImpl impl = new MyFirstInterfaceImpl();
		MyFirstInterfaceProxy proxy = new MyFirstInterfaceProxy(impl);
		InvocationHandler handler = proxy;
		
		System.out.println("impl    is " + impl);
		System.out.println("proxy   is " + proxy);
		System.out.println("handler is " + proxy);
		
		/**
		 * method-1
		 */

		// MyFirstInteraface obj = (MyFirstInteraface)
		// Proxy.newProxyInstance(impl.getClass().getClassLoader(),
		// impl.getClass().getInterfaces(), handler);

		/**
		 * method-2
		 */

		// MyFirstInteraface obj = (MyFirstInteraface)
		// Proxy.newProxyInstance(impl.getClass().getClassLoader(),
		// MyFirstInterfaceImpl.class.getInterfaces(), handler);

		/**
		 * method-3 use Thread.currentThread().getContextClassLoader() replace
		 * impl.getClass().getClassLoader()
		 */
		MyFirstInteraface obj = (MyFirstInteraface) Proxy.newProxyInstance(
				Thread.currentThread().getContextClassLoader(), MyFirstInterfaceImpl.class.getInterfaces(), handler);
		
		System.out.println("main ： " + obj.getClass().getName());
		obj.getInterfaceName();
	}

}
