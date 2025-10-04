<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User</title>
</head>
<body>
	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div class="box-header">
					<h3 class="box-title">Details</h3>
				</div>
				<div class="box-body">
					<table id="example1" class="table table-bordered table-striped">
						<thead>
							<tr>
								<th>Sr. No.</th>
								<c:if test="${user.roleId == 0}">
									<th>Institution</th>
								</c:if>
								<th>Date</th>
								<th>Document Type</th>
								<th>Document Number</th>
								<c:if test="${user.roleId == 0}">
									<th>Status</th>
									<!-- <th>Response</th> -->
								</c:if>
								<c:if test="${user.roleId != 2}"> 
								<th>Charges</th>
								</c:if>
								<th>User Name</th>
							</tr>
						</thead>
						<tbody>
							<c:set var="srNo" scope="page" value="1"></c:set>
							<c:forEach var="var" items="${lstAllDocuments}">
								<tr>
									<td><c:out value="${srNo}"></c:out> <c:set var="srNo" value="${srNo + 1}" scope="page" /></td>
									<c:if test="${user.roleId == 0}">
										<td>${var[7]}</td>
									</c:if>
									<td>${var[0]}</td>
									<td>${var[1]}</td>
									<td>${var[2]}</td>
									<c:if test="${user.roleId == 0}">
										<td>${var[3]}</td>
										<!-- <td>${var[4]}</td> -->
									</c:if>
									<c:if test="${user.roleId != 2}">
									<td>${var[5]}</td>
									</c:if>
									<td>${var[6]}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- /.box-body -->
			</div>
			<!-- /.box -->
		</div>
		<!-- /.col -->
	</div>
    <script src="resources/plugins/jQuery/jQuery-2.1.3.min.js"></script>
	<script type="text/javascript">
	</script>
</body>
</html>