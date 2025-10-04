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
<form:form method="post" action="formCList" id="myForm" modelAttribute="model">
	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div class="box-header">
					<h3 class="box-title">Inbox</h3>
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<table id="example1" class="table table-bordered table-striped">
						<thead>
							<tr>
								<th>SrNo</th>
								<th>ID</th>
								<th>Application Number</th>
								<th>Name</th>
								<th>Mouje</th>
								<th>Address</th>
								<th>Mobile</th>
								<th>Aadhar Number</th>
								<!-- <th>Active/Inactive</th> -->
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<c:set var="srNo" scope="page" value="1"></c:set>
							<c:forEach var="var" items="${lstFormC}" varStatus="status">
								<c:set var="bgcolor" scope="page" value=""></c:set>
								<c:if test="${var.approve_reject == 'Y'}">
									<c:set var="bgcolor" value="#cce8cc"></c:set>
								</c:if>
								<c:if test="${var.approve_reject == 'N'}">
									<c:set var="bgcolor" value="#f2c6c6"></c:set>
								</c:if>
								
								<tr style="background-color: ${bgcolor}">
									<td><c:out value="${srNo}"></c:out> <c:set var="srNo" value="${srNo + 1}" scope="page" /></td>
									<td>${var.id}</td>
									<td><span id="appNo_${status.index}"> ${var.application_number} </span> </td>
									<td>${var.applicant_fname} ${var.applicant_mname} ${var.applicant_lname}</td>
									<td>${var.mouje_id.mouje_name}</td>
									<td>${var.address}</td>
									<td>${var.mobile_number}</td>
									<td>${var.aadhar_no}</td>
									<%-- <td  align="center">
									<c:choose>
									<c:when test="${fn:containsIgnoreCase(var.is_active, 'Y')}">
										<input type="checkbox" class="mychk"  
											onchange="activeInactive(${var.id})" checked>	
								   </c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${fn:containsIgnoreCase(var.is_active, 'N')}">
										<input type="checkbox" class=""  
											onchange="activeInactive(${var.id})" >
									</c:when>
									<c:otherwise>
										<input type="checkbox" class=""  disabled="disabled"
											onchange="activeInactive(${var.id})">
									</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
                    				</td> --%>
									<td> 
										<input type="hidden" value="${var.id}" id="appId_${status.index}"/>
										<select id="${status.index}" name="userAction">
											<option value="">Select</option>
											<c:forEach items="${mapActions}" var="map">
													<c:if test="${empty var.approve_reject}">
														<option value="${map.value}">${map.key}</option>
													</c:if>
													<c:if test="${not empty var.approve_reject && (map.value != 1 && map.value != 3 && map.value != 4)}">
														<option value="${map.value}">${map.key}</option>
													</c:if>
											</c:forEach>
										</select>
                  					</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					
					<div class="modal fade hide" id="myModal" aria-modal="true" role="dialog" style="padding-right: 16px; display: block;">
				        <div class="modal-dialog">
				          <div class="modal-content bg-success">
				            <div class="modal-header">
				              <h4 id="modal-header" class="modal-title">Success Modal</h4>
				              <button type="button" class="close" data-dismiss="modal" aria-label="Close" style="margin-top: -29px;color: #000;">
				                <span aria-hidden="true">x</span>
				              </button>
				            </div>
				            <div class="modal-body">
				             	 <div class="row">
						        	<div class="form-group col-xs-4">
					             	 	<input id="appId" type="hidden">
					             	 	<input id="actionId" type="hidden">
					             	 	<label> Application No : </label> <span id="appNo"></span>
					             	 </div>	
				             	 </div>
				             	 <div class="row">
						        	<div class="form-group col-xs-4">
										<label>Remark<span style="color:red">*</span></label> <textarea id="actionRemark" rows="4" cols="40"></textarea>
									</div>
						        </div>
				            </div>
				            <div class="modal-footer justify-content-between">
				              <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				              <button type="button" id="btnSaveAction" class="btn btn-success">Save changes</button>
				            </div>
				          </div>
				        </div>
				      </div>
					
				</div>
			</div>
		</div>
	</div>
</form:form>
<script src="resources/plugins/jQuery/jQuery-2.1.3.min.js"></script>
<script type="text/javascript">
		
		$(document).on('change', "[name=userAction]", function() {
			var actionId = $(this).val();
			var appId = $("#appId_"+this.id).val();

			$("#appId").val(appId);
			$("#actionId").val(actionId);

			if(actionId == 1){
				window.location.href="formA?id="+appId;
			}else if(actionId == 2){
				window.location.href="printFormA?id="+appId;
			}else if(actionId == 3){
				$("#modal-header").text("Approve Application");
				$("#appNo").text($("#appNo_"+this.id).text());
				$('#myModal').removeClass('hide');
				$('#myModal').modal('toggle');
			}else if(actionId == 4){
				$("#modal-header").text("Reject Application");
				$("#appNo").text($("#appNo_"+this.id).text());
				$('#myModal').removeClass('hide');
				$('#myModal').modal('toggle');
			}else if(actionId == 5){
				window.location.href="printReceipt?id="+appId;
			}
		});
		
		$(function() {
			$("#example1").dataTable();

			
			$("#btnSaveAction").click(function(){
				if($("#actionRemark").val() == ""){
					alert("Please enter remark!");
				}else{
					$.ajax({
						url : 'saveAction',
						type : 'POST',
						data : {
							appId : $("#appId").val(),
							remark : $("#actionRemark").val(),
							actionId : $("#actionId").val()	
						},
						success : function(response) {
							alert(response.message);
							window.location.href="Inbox";
						}
					});
				}
			});
		});

		function activeInactive(id){
			$.ajax({
				url : 'activateDeactivateFormC',
				type : 'POST',
				data : {
					id : id
				},
				success : function(response) {
					alert(response.message);
				}
			});
		}
</script>
</body>
</html>