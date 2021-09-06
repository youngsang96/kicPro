package controller;


import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ControllerExt
 */

public class Action extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processPro(request, response);
	}

	public void processPro(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String command = request.getRequestURI();
		System.out.println("1:" + command);
		if (command.indexOf(request.getContextPath()) == 0) {
			command = command.substring(request.getContextPath().length());
			command = command.substring(command.lastIndexOf("/")+1);
		}
		System.out.println("2:" + command);
		String viewPage = null;
		Class[] cParam = {HttpServletRequest.class, HttpServletResponse.class};
		try {
			
			viewPage = (String) this.getClass()
					.getMethod(command, cParam)
					.invoke(this, request, response);
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		if (viewPage != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		} else {
			
			throw new ServletException("no exsiting method "+ this.getClass().getName()+"."+command+"() ");
			
		}	}
	
	

}
