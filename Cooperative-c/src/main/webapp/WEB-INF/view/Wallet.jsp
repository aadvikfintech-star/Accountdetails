<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<div class="row">
		<c:choose>
			<c:when test="${user.roleId != 0}">
				<div class="col-lg-3 col-xs-6">
					<div class="small-box bg-green">
						<div class="inner">
							<h3>${walletAmount}</h3>
							<p> Wallet Balance</p>
						</div>
						<div class="icon">
							<i class="ion ion-bag"></i>
						</div>
					</div>
				</div>
			</c:when>
			<c:when test="${user.roleId == 0}">
				<div class="col-lg-6 col-xs-6">
					<div class="box box-primary">
						<div class="box-header">
							<h3 class="box-title">Recharge Wallet</h3>
						</div>
						<form:form method="post" action="wallet">
							<div class="box-body">
								<div class="row">
									<div class="form-group col-xs-4">
										<label>Cooperative <span style="color: red;">*</span> </label> 	
										<select class="form-control" id="copId" name="copId" required="required">
											<option value="">Select</option>
											<c:forEach var="c" items="${lstCooperative}">
												<option value="${c.copId}">${c.copName}</option>
											</c:forEach>
										</select>	
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-6">
										<div class="col-xs-1" style="margin-top: -7px;font-size: 35px;cursor: pointer;">
											<a id="plusBtn"><i class="fa fa-plus" aria-hidden="true"></i></a>
										</div>
										<div class="col-xs-6">
											<input type="text" name="amount" id="amount" class="form-control" value="5000" required/>
										</div>
										<div class="col-xs-1"  style="margin-top: -16px;margin-left: -20px;font-size: 40px;cursor: pointer;">
											<a id="minusBtn"><i class="fa fa-minus" aria-hidden="true"></i></a>
										</div>
									</div>
								</div>	
								<div class="form-group">
									<button type="submit" class="btn btn-primary">Recharge</button>
								</div>
							</div>
						</form:form>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<div class="box">
							<div class="box-header">
								<h3 class="box-title">Wallet History</h3>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<table id="example1" class="table table-bordered table-striped">
									<thead>
										<tr>
											<th>Sr. No</th>
											<th>Cooperative</th>
											<th>Amount</th>
											<th>Balanced Amount</th>
										</tr>
									</thead>
									<tbody>
										<c:set var="srNo" scope="page" value="1"></c:set>
										<c:forEach var="var" items="${lstWallet}">
											<tr>
												<td><c:out value="${srNo}"></c:out> <c:set var="srNo" value="${srNo + 1}" scope="page" /></td>
												<td>${var.copId.copName}</td>
												<td>${var.amount}</td>
												<td>${var.balancedAmount}</td>
											</tr>
										</c:forEach>
									</tbody>			
								</table>
							</div>
						</div>
					</div>
				</div>	
			</c:when>	
			</c:choose>
			<c:choose>
				<c:when test="${user.roleId == 1}">
				<div class="row">
					<div class="col-xs-12">
						<div class="box">
							<div class="box-header">
								<h3 class="box-title">Wallet History</h3>
							</div>
							<div class="box-body">
								<table id="example1" class="table table-bordered table-striped">
									<thead>
										<tr>
											<th>Sr. No</th>
											<th>Date</th>
											<th>Amount</th>
											<th>Mode</th>
										</tr>
									</thead>
									<tbody>
										<c:set var="srNo" scope="page" value="1"></c:set>
										<c:forEach var="var" items="${lstWalletHistory}">
											<tr>
												<td><c:out value="${srNo}"></c:out> <c:set var="srNo" value="${srNo + 1}" scope="page" /></td>
												<td> <fmt:formatDate pattern="dd-MM-yyyy" value="${var.createdDate}" /></td>
												<td>${var.amount}</td>
												<td>${var.paymentMode}</td>
											</tr>
										</c:forEach>
									</tbody>			
								</table>
							</div>
						</div>
					</div>
				</div>	
			</c:when>	
		</c:choose>	
		
	</div>
</body>
 <script src="resources/plugins/jQuery/jQuery-2.1.3.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
    $('#plusBtn').on('click', function() {
    	 $('#amount').val(parseInt( $('#amount').val()) +500 );
    });

    $('#minusBtn').on('click', function() {
        if (parseInt( $('#amount').val()) > 0) {
        	 $('#amount').val(parseInt( $('#amount').val()) - 1000 );
        }
    });
});
</script>
</html>