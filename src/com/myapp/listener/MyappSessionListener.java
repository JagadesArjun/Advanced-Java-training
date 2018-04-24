package com.myapp.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MyappSessionListener implements HttpSessionListener {

	// This method will be called when session is created
	@Override
	public void sessionCreated(HttpSessionEvent event) {

		HttpSession session = event.getSession();

		System.out.println("MyappSessionListener: sessionCreated() Id:" + session.getId());
		System.out.println("MyappSessionListener: sessionCreated() createTime:" + session.getCreationTime());

	}

	// This method will be called when session got destroyed
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();

		System.out.println("MyappSessionListener: sessionDestroyed() Id:" + session.getId());
	}

}
