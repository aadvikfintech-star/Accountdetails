<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PMAY</title>
</head>
<body>
     <div class="row">
       <div class="col-lg-3 col-xs-6">
         <div class="small-box bg-aqua">
           <div class="inner">
             <a name="lnkDetails" type="A" style="cursor: pointer;color: white;">
             <h3>${total}</h3>
             <p>Total Documents Verified</p>
             </a>
           </div>
           <div class="icon">
             <i class="ion ion-bag"></i>
           </div>
         </div>
       </div><!-- ./col -->
       <div class="col-lg-3 col-xs-6">
         <div class="small-box bg-green">
           <div class="inner">
           	<a name="lnkDetails" type="S" style="cursor: pointer;color: white;">
             <h3>${total_success}</h3>
             <p>Successful</p>
            </a> 
           </div>
           <div class="icon">
             <i class="ion ion-stats-bars"></i>
           </div>
         </div>
       </div><!-- ./col -->
       <div class="col-lg-3 col-xs-6">
         <div class="small-box bg-red">
           <div class="inner">
             <a name="lnkDetails" type="F" style="cursor: pointer;color: white;">
	             <h3>${total_failed}</h3>
	             <p>Total Failed</p>
            </a> 
           </div>
           <div class="icon">
             <i class="ion ion-person-add"></i>
           </div>
         </div>
       </div>
     </div>
     <c:choose>
     <c:when test="${user.roleId == 0 || user.roleId == 1}">
     	<div class="row">
	     	<div class="col-lg-6 col-xs-6">
				<div class="box box-primary">
					<div class="box-header">
						<h3 class="box-title">Institution Summary</h3>
					</div>
					<div class="box-body">
						<table id="example1" class="table table-bordered table-striped">
							<thead>
								<tr>
									<th>#</th>
									<th>Institution</th>
									<th>Count</th>
								</tr>
							</thead>
							<tbody>
								<c:set var="srNo" scope="page" value="1"></c:set>
								<c:forEach var="var" items="${lstCorp}">
									<tr>
										<td><c:out value="${srNo}"></c:out> <c:set var="srNo" value="${srNo + 1}" scope="page" /></td>
										<td>${var[0]}</td>
										<td>${var[1]}</td>
									</tr>
								</c:forEach>
							</tbody>			
						</table>
					</div>
				</div>
			</div>
			<c:choose>
			 <c:when test="${user.roleId == 1}">
				<div class="col-lg-6 col-xs-6">
					<div class="box box-primary">
						<div class="box-header">
							<h3 class="box-title">User Summary</h3>
						</div>
						<div class="box-body">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th>#</th>
										<th>User Name</th>
										<th>Count</th>
									</tr>
								</thead>
								<tbody>
									<c:set var="srNo" scope="page" value="1"></c:set>
									<c:forEach var="var" items="${lstUser}">
										<tr>
											<td><c:out value="${srNo}"></c:out> <c:set var="srNo" value="${srNo + 1}" scope="page" /></td>
											<td>${var[0]}</td>
											<td>${var[1]}</td>
										</tr>
									</c:forEach>
								</tbody>			
							</table>
						</div>
					</div>
				</div>
			</c:when>	
			</c:choose>
	     </div>
     </c:when>
     </c:choose>
</body>

<script src="resources/plugins/jQuery/jQuery-2.1.3.min.js"></script>
    
    <script>
	  $(document).ready(function(){
	    $('[name=lnkDetails]').on('click', function(e){
	    	var type = $(this).attr('type');
	    	window.location.href = "report?type="+type;
	    });
	  });
  </script>
</html>