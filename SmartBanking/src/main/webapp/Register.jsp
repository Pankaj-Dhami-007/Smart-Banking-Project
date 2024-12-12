<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Register</title>
<jsp:include page="components/allcdns.jsp" />
</head>
<body>

	<div class="d-flex justify-content-center align-items-center">
		<h1>
			<%
			String msg = (String) request.getAttribute("msg");
			if (msg != null) {
				out.print(msg);
			}
			%>
		</h1>
		<div class="container mt-3">
			<form class="row g-3 needs-validation" action="register"
				method="post">
				<div class="col-md-6">
					<label for="validationCustom01" class="form-label">Id</label> <input
						type="text" class="form-control" id="validationCustom01" name="id"
						required>
					<div class="valid-feedback">Looks good!</div>
				</div>
				<div class="col-md-6">
					<label for="validationCustom02" class="form-label">Name</label> <input
						type="text" class="form-control" id="validationCustom02"
						name="name" required>
					<div class="valid-feedback">Looks good!</div>
				</div>
				<div class="col-md-6">
					<label for="validationCustomUsername" class="form-label">Mobile
						No.</label> <input type="text" class="form-control"
						id="validationCustomUsername" name="mobNo" required>
					<div class="invalid-feedback">Please choose a username.</div>
				</div>
				<div class="col-md-6">
					<label for="validationCustom05" class="form-label">Salary</label> <input
						type="text" class="form-control" id="validationCustom05"
						name="salary" required>
					<div class="invalid-feedback">Please provide a valid zip.</div>
				</div>
				<div class="row col-md-6 py-5">
					<div class="col-md-3">
						Married ? : <br>
					</div>
					<div class="form-check col-md-4">
						<input class="form-check-input" type="radio" name="isMarried"
							id="flexRadioDefault1" value="married"> <label class="form-check-label"
							for="flexRadioDefault1"> Married </label>
					</div>
					<div class="form-check col-md-5">
						<input class="form-check-input" type="radio" name="isMarried"
							id="flexRadioDefault2" value="unmarried"> <label
							class="form-check-label" for="flexRadioDefault2">
							Unmarried </label>
					</div>
				</div>
				<div class="col-md-6">
					<label for="validationCustom05" class="form-label">DOB</label> <input
						type="date" class="form-control" id="validationCustom05"
						name="dob" required>
					<div class="invalid-feedback">Please provide a valid zip.</div>
				</div>
				<div class="col-12">
					<div class="form-check">
						<input class="form-check-input" type="checkbox" value=""
							id="invalidCheck" required> <label
							class="form-check-label" for="invalidCheck"> Agree to
							terms and conditions </label>
						<div class="invalid-feedback">You must agree before
							submitting.</div>
					</div>
				</div>
				<div class="col-12 d-flex justify-content-center">
					<button class="btn btn-primary" type="submit">Submit form</button>
				</div>
			</form>
		</div>
	</div>

	<script type="text/javascript">
//Example starter JavaScript for disabling form submissions if there are invalid fields
(() => {
  'use strict'

  // Fetch all the forms we want to apply custom Bootstrap validation styles to
  const forms = document.querySelectorAll('.needs-validation')

  // Loop over them and prevent submission
  Array.from(forms).forEach(form => {
    form.addEventListener('submit', event => {
      if (!form.checkValidity()) {
        event.preventDefault()
        event.stopPropagation()
      }

      form.classList.add('was-validated')
    }, false)
  })
})()
</script>
</body>
<jsp:include page="components/allscripts.jsp" />
</html>