package com.myapp.listener;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSession;

public class MyappServletRequestListener implements ServletRequestListener {

	// This method will be called on every request after the request destroyed
	@Override
	public void requestDestroyed(ServletRequestEvent event) {
		System.out.println("MyappServletRequestListener: requestDestroyed() called");
	}

	// This method will be called on every request once the request initialized
	@Override
	public void requestInitialized(ServletRequestEvent event) {
		System.out.println("MyappServletRequestListener: requestInitialized() called");
	}

}
