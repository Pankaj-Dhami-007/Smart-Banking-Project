package com.bank.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import com.bank.dao.CustomerDao;
import com.bank.dao.impl.CustomerDaoImpl;
import com.bank.models.Customer;

@WebServlet("/editcustomer")
public class EditCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

   		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		double salary = Double.parseDouble(request.getParameter("salary"));
		String value = request.getParameter("isMarried");
		long mobNo = Long.parseLong(request.getParameter("mobNo"));
		LocalDate dob =LocalDate.parse(request.getParameter("dob"));
		String createdAt=new Date().toString();
		
		response.setContentType("text/html");
		
		boolean isMarried=value.equals("married") ? true : false;
		
		Customer cust=new Customer(id, name, mobNo, salary, isMarried, dob, createdAt);
		System.out.println(cust);
		
   		CustomerDao dao=new CustomerDaoImpl();
   		boolean status=dao.updateCustomer(cust);
   		if (status) {
			request.setAttribute("msg","Customer Added Succesfully!");
			request.getRequestDispatcher("allcustomers").forward(request, response);
		} else {
			response.getWriter().append("Unable to Add Customer!");
			request.getRequestDispatcher("allcustomers").include(request, response);
		}
   	
   	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
