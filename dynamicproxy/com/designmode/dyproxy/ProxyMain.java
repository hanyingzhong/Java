package com.designmode.dyproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface ProxyInterface {
	public void proxyMethod();
}

class TargetObject implements ProxyInterface {

	public void proxyMethod() {
		System.out.println("我被代理了，哈哈！");
	}
}

class ProxyObject implements InvocationHandler {
	// 代码的对象
	public Object targetObject;

	public void setTargetObject(Object targetObject) {
		this.targetObject = targetObject;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// 调用，传入一个目标对象，和对应的对象参数
		return method.invoke(targetObject, args);
	}
}

public class ProxyMain {
	public static void main(String[] args) {
		// 代理的目标对象
		ProxyInterface proxyInterface = new TargetObject();
		// 代理器
		ProxyObject proxyObject = new ProxyObject();
		proxyObject.setTargetObject(proxyInterface);

		// 转换成InvocationHandler
		InvocationHandler handler = proxyObject;
		// 执行代码任务
		Object proxy = Proxy.newProxyInstance(proxyInterface.getClass().getClassLoader(),
				proxyInterface.getClass().getInterfaces(), handler);
		// 转换成目标对象，调用目标对象的方法
		((ProxyInterface) proxy).proxyMethod();
		
		System.out.println(proxy.getClass().getName());
	}
}
