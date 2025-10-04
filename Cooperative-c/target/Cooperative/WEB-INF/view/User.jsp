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
	<div class="center row">
		<div class="col-md-12">
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">User Master</h3>
				</div>
				<!-- /.box-header -->
				<!-- form start -->
				<form:form method="post" action="userRegistration">
					<div class="box-body">
						<div class="row">
							<div class="form-group col-xs-4">
								<label>Cooperative</label> 	
								<select class="form-control" id="copId" name="copId">
									<option value="">Select</option>
									<c:forEach var="c" items="${lstCooperative}">
										<option value="${c.copId}">${c.copName}</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group col-xs-4">
								<label>Role</label> 	
								<select class="form-control" id="roleId" name="roleId">
									<option value="">Select</option>
									<c:forEach var="role" items="${lstRole}">
										<option value="${role.roleId}">${role.roleName}</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group col-xs-4">
								<label>User Name</label> <input type="text" name="userName"
									class="form-control" placeholder="User Name ..."
									required="required" />
							</div>
							<div class="form-group col-xs-4">
								<label>Password</label> <input type="password"
									name="password" class="form-control"
									placeholder="Password ..." required="required" />
							</div>
						</div>
						<div class="row">
						<div class="form-group col-xs-4">
							<label>First Name</label> <input type="text" name="firstName"
								class="form-control" placeholder="First Name ..."
								required="required" />
						</div>
						<div class="form-group col-xs-4">
							<label>Middle Name</label> <input type="text" name="middleName"
								class="form-control" placeholder="Enter ..." />
						</div>
						<div class="form-group col-xs-4">
							<label>Last Name</label> <input type="text" name="lastName"
								class="form-control" placeholder="Enter ..." required="required" />
						</div>
						</div>
						<div class="row">
						<div class="form-group col-xs-4">
							<label>Email</label> <input type="email" name="email"
								class="form-control" placeholder="Email ..." />
						</div>
						<div class="form-group col-xs-4">
							<label>Mobile</label> <input type="text" name="mobileNo"
								class="form-control" placeholder="Mobile ..." />
						</div>
						</div>
						<input type="hidden" name="userId">
						<input type="hidden" name="msg" value="${msg}">
						<button type="submit" class="btn btn-primary">Submit</button>
					</div>
					
					<!-- <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
					  <div class="modal-dialog" role="document">
					    <div class="modal-content">
					      <div class="modal-header">
					        <h5 class="modal-title" id="exampleModalLabel">Image Preview</h5>
					        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
					          <span aria-hidden="true">&times;</span>
					        </button>
					      </div>
					      <div class="modal-body">
					        <img src="" class="img-fluid" id="preview-image" alt="Image Preview">
					      </div>
					    </div>
					  </div>
					</div> -->
					<!-- /.box-body -->
					<div class="box-footer">
					</div>
				</form:form>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div class="box-header">
					<h3 class="box-title">User List</h3>
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<table id="example1" class="table table-bordered table-striped">
						<thead>
							<tr>
								<th>SrNo</th>
								<th>Cooperative</th>
								<th>Role</th>
								<th>User Name</th>
								<th>Password</th>
								<th>First Name</th>
								<th>Middle Name</th>
								<th>Last Name</th>
								<th>Email</th>
								<th>Mobile</th>
								<th>Active/Inactive</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<c:set var="srNo" scope="page" value="1"></c:set>
							<c:forEach var="var" items="${lstUser}">
								<tr>
									<td><c:out value="${srNo}"></c:out> <c:set var="srNo" value="${srNo + 1}" scope="page" /></td>
									<td>${var.copId.copName}</td>
									<td>${var.roleId.roleName}</td>
									<td>${var.userName}</td>
									<td>${var.password}</td>
									<td>${var.firstName}</td>
									<td>${var.middleName}</td>
									<td>${var.lastName}</td>
									<td>${var.email}</td>
									<td>${var.mobileNo}</td>
									<td  align="center">
									<c:choose>
									<c:when test="${fn:containsIgnoreCase(var.isActive, 'Y')}">
										<input type="checkbox" class="mychk"  
											onchange="activeInactive(${var.userId})" checked>	
								   </c:when>
								
									<c:otherwise>
										<c:choose>
											<c:when test="${fn:containsIgnoreCase(var.isActive, 'N')}">
										<input type="checkbox" class=""  
											onchange="activeInactive(${var.userId})" >
									</c:when>
									<c:otherwise>
										<input type="checkbox" class=""  disabled="disabled"
											onchange="activeInactive(${var.userId})">
									</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
                    				</td>
									<td><a href="#" onclick="editUser(${var.userId})">
									<i class="fa fa-edit"></i></a></td>
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
	<!-- /.row -->


    <!-- jQuery 2.1.3 -->
    <script src="resources/plugins/jQuery/jQuery-2.1.3.min.js"></script>
	<!-- page script -->
	<script type="text/javascript">
	
		function editUser(userId){
			$.ajax({
				url : 'loadUserDetails',
				type : 'GET',
				data : {
					userId :userId
				},
				success : function(response) {
					var iModel = response.userModel;
					$("[name=userName]").val(iModel.userName);
					$("[name=password]").val(iModel.password);
					$("[name=roleId]").val(iModel.roleId.roleId);
					$("[name=copId]").val(iModel.copId.copId);
					$("[name=firstName]").val(iModel.firstName);
					$("[name=middleName]").val(iModel.middleName);
					$("[name=lastName]").val(iModel.lastName);
					$("[name=email]").val(iModel.email);
					$("[name=mobileNo]").val(iModel.mobileNo);
					$("[name=userId]").val(iModel.userId);
				}
			});
		}
		function activeInactive(userId){
			$.ajax({
				url : 'activateDeactivateUser',
				type : 'POST',
				data : {
					userId : userId
				},
				success : function(response) {
					alert(response.message);
				}
			});
		}
		$(function() {
		
			if($("[name=msg]").val() != ""){
				alert($("[name=msg]").val());
			}
			
			$('#example1').dataTable();
		});
	</script>

</body>
</html>