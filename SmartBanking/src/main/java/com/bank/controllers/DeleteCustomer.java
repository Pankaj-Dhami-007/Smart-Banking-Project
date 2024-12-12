package com.bank.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.bank.dao.CustomerDao;
import com.bank.dao.impl.CustomerDaoImpl;

@WebServlet("/deletecustomer")
public class DeleteCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		CustomerDao dao=new CustomerDaoImpl();
		response.setContentType("text/html");
		boolean status = dao.deleteCustomer(Long.parseLong(request.getParameter("id")));
		if (status) 
		{
			request.getRequestDispatcher("allcustomers").forward(request, response);
		}
		else
		{
			response.getWriter().append("Unable to delete Customer!");
			request.getRequestDispatcher("allcustomers").include(request, response);
		}
	}
}
