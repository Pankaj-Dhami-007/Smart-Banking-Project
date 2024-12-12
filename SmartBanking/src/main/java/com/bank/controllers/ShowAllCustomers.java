package com.bank.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.bank.dao.CustomerDao;
import com.bank.dao.impl.CustomerDaoImpl;
import com.bank.models.Customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/allcustomers")
public class ShowAllCustomers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CustomerDao dao = new CustomerDaoImpl();
		List<Customer> custList = new ArrayList<Customer>();
		custList = dao.getAllCustomers();

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print(
				"""
						<!DOCTYPE html>
						<html lang="en">
						<head>
						<meta charset="UTF-8">
						<meta name="viewport" content="width=device-width, initial-scale=1">
						<title>All Customers</title>
						<link rel="stylesheet" href="https://unpkg.com/aos@next/dist/aos.css" />
						<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
						<script src="https://kit.fontawesome.com/02b7ef5319.js" crossorigin="anonymous"></script>
						</head>
						<body>
									""");

		out.print("""
				<h1 class='text-center'> All Customers List </h1>
				<div class='container'>
				<table border='1' class='table table-striped'>
				<thead class='table-dark'>
				<tr>
				""");
		String columns[] = dao.getAllColumns();
		for (int i = 0; i < columns.length; i++) {
			out.print("<th>" + columns[i] + "</th>");
		}
		out.print("<th>Actions</th>");

		out.print("</tr></thead><tbody>");

		for (Customer cust : custList) {
			out.print("<tr>");
			out.print("<td>" + cust.getEmpId() + "</td>");
			out.print("<td>" + cust.getName() + "</td>");
			out.print("<td>" + cust.getMobileNo() + "</td>");
			out.print("<td>" + cust.getSalary() + "</td>");
			out.print("<td>" + cust.getIsMarried() + "</td>");
			out.print("<td>" + cust.getDob() + "</td>");
			out.print("<td>" + cust.getJoinedAt() + "</td>");
			out.print("""
					<td>
						<button class='btn btn-primary' data-bs-toggle="modal" data-bs-target="#editModal" onclick="setModalData(this)">edit</button>
						<a href="deletecustomer?id=%d"><button class='btn btn-danger'>delete</button></a>
					</td>
					""".formatted(cust.getEmpId()));
			out.print("</tr>");
		}
		out.print("""
				</tbody>
				<tfoot class='table-dark'>
				<tr>
				""");
		for (int i = 0; i < columns.length; i++) {
			out.print("<th>" + columns[i] + "</th>");
		}
		out.print("<th>Actions</th>");
		out.print(
				"""
										</tr>
										</tfoot></table>
										</div>
						<!-- Modal -->
						<div class="modal fade" id="editModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
						  <div class="modal-dialog">
						    <div class="modal-content">
						      <div class="modal-header">
						        <h1 class="modal-title fs-5" id="staticBackdropLabel">Edit Customer</h1>
						        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						      </div>
						      <div class="modal-body">
						        <div class="container">
							<form class="row g-1 needs-validation" action="editcustomer" method="post">
						  <div class="col-md-12">
						    <label for="validationCustom01" class="form-label">Id</label>
						    <input type="text" class="form-control" id="validationCustom01" name="id" required disabled>
						    <input type="hidden" name="id">
						    <div class="valid-feedback">
						      Looks good!
						    </div>
						  </div>
						  <div class="col-md-12">
						    <label for="validationCustom02" class="form-label">Name</label>
						    <input type="text" class="form-control" id="validationCustom02" name="name" required>
						    <div class="valid-feedback">
						      Looks good!
						    </div>
						  </div>
						  <div class="col-md-12">
						    <label for="validationCustomUsername" class="form-label">Mobile No.</label>
						    <input type="text" class="form-control" id="validationCustomUsername" name="mobNo" required>
						      <div class="invalid-feedback">
						        Please choose a username.
						    </div>
						  </div>
						  <div class="col-md-12">
						    <label for="validationCustom05" class="form-label">Salary</label>
						    <input type="text" class="form-control" id="validationCustom05" name="salary" required>
						    <div class="invalid-feedback">
						      Please provide a valid zip.
						    </div>
						  </div>
						  <div class="row col-md-12 py-2">
						  <div class="col-md-4">Married ? : <br></div>
						  <div class="form-check col-md-4">
						  <input class="form-check-input" type="radio" name="isMarried" id="flexRadioDefault1" value="married">
						  <label class="form-check-label" for="flexRadioDefault1">
						    Married
						  </label>
						</div>
						<div class="form-check col-md-4">
						  <input class="form-check-input" type="radio" name="isMarried" id="flexRadioDefault2" value="ummarried">
						  <label class="form-check-label" for="flexRadioDefault2">
						    Unmarried
						  </label>
						</div>
						</div>
						  <div class="col-md-12">
						    <label for="validationCustom05" class="form-label">DOB</label>
						    <input type="date" class="form-control" id="validationCustom05" name="dob" required>
						    <div class="invalid-feedback">
						      Please provide a valid zip.
						    </div>
						  </div>
						  <div class="col-12">
						    <div class="form-check">
						      <input class="form-check-input" type="checkbox" value="" id="invalidCheck" required>
						      <label class="form-check-label" for="invalidCheck">
						        Agree to terms and conditions
						      </label>
						      <div class="invalid-feedback">
						        You must agree before submitting.
						      </div>
						    </div>
						  </div>
						</div>
						      </div>
						      <div class="modal-footer d-flex justify-content-center">
						        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
						        <button type="submit" class="btn btn-primary">Save</button>
						      </div>
						      </form>
						    </div>
						  </div>
						</div>




												</body>
												<script src="https://unpkg.com/aos@next/dist/aos.js"></script>
												<script>
												  AOS.init();
												</script>
												
												<script>
													function setModalData(e)
													{
														const parentNode=e.parentNode.parentNode;
														console.log(parentNode);
														console.dir(parentNode);
															
														const cells=parentNode.cells;
														
														for(let i=0;i<cells.length-1;i++)
														{
															console.log(cells[i].innerText);
														}
														document.getElementsByName("id")[0].value=cells[0].innerText;
														document.getElementsByName("id")[1].value=cells[0].innerText;
														document.getElementsByName("name")[0].value=cells[1].innerText;
														document.getElementsByName("mobNo")[0].value=cells[2].innerText;
														document.getElementsByName("salary")[0].value=cells[3].innerText;
														if(cells[4].innerText==="true")
														{
														document.getElementsByName("isMarried")[0].checked=true;
														}
														else
														{
														document.getElementsByName("isMarried")[1].checked=true;
														}
														document.getElementsByName("dob")[0].value=cells[5].innerText;
													}
												
												</script>
												
												
												<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
												<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
												</html>
														""");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
