<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PMAY</title>
</head>
<body>
	<div class="center row">
		<div class="col-md-2"> </div>
		<div class="col-md-8">
			<div class="box box-primary">
				<div class="box-header" style="text-align: center;background-color: #f2c26a;">
					<h3 class="box-title">Beneficiary Form Appendix-A Submission Receipt</h3>
				</div>
				<div class="box-body">
					<div class="row" align="center">
							<h3 class="box-title">Nanded Waghala City Municipal Corporation</h3>
					</div>						
					<div class="row">
						<div class="col-xs-4">
							<img alt="nmc" src="resources/img/nwcmc.png">
						</div>
						<div class="col-xs-4">
							<img alt="nmc" src="resources/img/RAYLogo.jpg" style="width: 130px;position: relative;top: 10px;left: 40px;">
						</div>
						<div class="col-xs-4">
							<img src="data:image/png;base64,${imageAsBase64}" style="width: 150px;float: right;"/>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<table class="table table-bordered">
								<!-- <tr>
									<th> Name of Municipal Corporation </th>
									<th colspan="2"> Nanded Waghala City Municipal Corporation </th>
								</tr>
								<tr>
									<th> Name of Scheme </th>
									<th colspan="2"> Ramai Awaas Yojna </th>
								</tr>
								<tr>
									<th>Name of PMC </th>
									<th colspan="2"> Planedge Consultant Pvt. Ltd. </th>
								</tr> -->
								<tr>
									<th> Form Number </th>
									<th> ${application_no} </th>
									<th> UID No : ${uid}</th>
								</tr>
								<tr>
									<th> Name of Beneficiary </th>
									<th colspan="2">${applicant_name} </th>
								</tr>
								<tr>
									<th> Date of submission </th>
									<th colspan="2"> ${submission_date} </th>
								</tr>
								<tr>
									<th> ${zone} </th>
									<th> Area : ${area}</th>
									<th> Address : ${address}</th>
								</tr>
							</table>
						</div>
					</div>
					<br/>
					<br/>
					<div class="row">
						<div class="col-xs-12" align="right" style="font-weight : bold;">
							Sign by Superintendent
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12" align="right" style="font-weight : bold;">
							Planedge Consultant Pvt. Ltd.
						</div>
					</div>
				</div>
		</div>
	</div>
</div>
    <!-- jQuery 2.1.3 -->
    <script src="resources/plugins/jQuery/jQuery-2.1.3.min.js"></script>
	<!-- page script -->
	
	<script type="text/javascript">
		$(function() {
		});
	</script>
</body>
</html>