<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
  /* Custom styling for file input */
  .custom-file-input::-webkit-file-upload-button {
    visibility: hidden;
  }
  .custom-file-input::before {
    content: 'Select Image';
    display: inline-block;
    background: linear-gradient(top, #f9f9f9, #e3e3e3);
    border: 1px solid #999;
    border-radius: 3px;
    padding: 5px 8px;
    outline: none;
    white-space: nowrap;
    -webkit-user-select: none;
    cursor: pointer;
    text-shadow: 1px 1px #fff;
    font-weight: 700;
    font-size: 10pt;
  }
  .custom-file-input:hover::before {
    border-color: black;
  }
  .custom-file-input:active::before {
    background: -webkit-linear-gradient(top, #e3e3e3, #f9f9f9);
  }
  .custom-file-input::after {
    /* content: 'Choose File'; */
    display: inline-block;
    background: #007bff;
    border-radius: 3px;
    color: white;
    padding: 5px 8px;
    outline: none;
    white-space: nowrap;
    -webkit-user-select: none;
    cursor: pointer;
    font-weight: 700;
    font-size: 10pt;
  }
  .custom-file-input:hover::after {
    background: #0056b3;
  }
  .custom-file-input:active::after {
    background: #0056b3;
  }
</style>

<style>
  /* Custom styling for file input */
  .custom-file1 {
    position: relative;
    overflow: hidden;
  }
  .custom-file-input1 {
    position: absolute;
    left: 0;
    top: 0;
    height: 100%;
    width: 100%;
    opacity: 0;
    cursor: pointer;
  }
  .custom-file-label1 {
  	cursor: pointer;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    padding: 5px;
    border: 1px solid black;
    border-radius: 9px;
    background-color: #3c8dbc;
    color: white;
    
  }
</style>
<title>User</title>
</head>
<body>
	<div class="center row">
		<div class="col-md-12">
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">Institution</h3>
				</div>
				<form:form enctype="multipart/form-data" method="post" action="institution">
					<div class="box-body">
						<div class="row">
						  
					    </div>
						<div class="row">
						 	<!-- <div class="col-md-4">
								<label>Logo</label> 	
						      <img src="https://via.placeholder.com/150" class="img-fluid img-thumbnail browse-img" data-toggle="modal" data-target="#exampleModal" alt="Image 1">
						    </div> -->
						 </div>   
						<div class="row">
							<div class="col-md-2">
						     <div class="custom-file1">
						        <img style="width: 100%" src="https://via.placeholder.com/150" class="img-fluid" id="selectedImage1" alt="Selected Image">
						        <input type="file" name="logo" class="custom-file-input1" id="imageInput1"> <br>
						        <label class="custom-file-label1" for="imageInput1">Select Logo</label>
						     </div>
						  </div>
							<div class="form-group col-xs-3">
								<label>Registration Number</label> <input type="text" name="copRegNo"
									class="form-control" placeholder="Registration Number"
									required="required" />
							</div>
							<div class="form-group col-xs-3">
								<label>Name</label> <input type="text" name="copName"
									class="form-control" placeholder="Coopertaive Name"
									required="required" />
							</div>
							<div class="form-group col-xs-3">
								<label>Address</label> <textarea  name="copAddress" class="form-control"  required="required"> </textarea>
							</div>
						</div>
						<input type="hidden" name="copId">
						<input type="hidden" name="msg" value="${msg}">
						<button type="submit" class="btn btn-primary">Submit</button>
					</div>
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
					<h3 class="box-title">Institutions</h3>
				</div>
				<div class="box-body">
					<table id="example1" class="table table-bordered table-striped">
						<thead>
							<tr>
								<th>Sr.No</th>
								<th>Logo</th>
								<th>Registration Number</th>
								<th>Institution Name</th>
								<th>Address</th>
								<th>Active/Inactive</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<c:set var="srNo" scope="page" value="1"></c:set>
							<c:forEach var="var" items="${lstCooperative}">
								<tr>
									<td><c:out value="${srNo}"></c:out> <c:set var="srNo" value="${srNo + 1}" scope="page" /></td>
									<td>
										<c:if test="${not empty var.logoBytes}">
											<img style="width: 50px;" src="data:image/jpeg;base64,${var.logoBytes}">
										</c:if>
									</td>
									<td>${var.copRegNo}</td>
									<td>${var.copName}</td>
									<td>${var.copAddress}</td>
									<td  align="center">
									<c:choose>
										<c:when test="${fn:containsIgnoreCase(var.isActive, 'Y')}">
											<input type="checkbox" class="mychk"  
												onchange="activeInactive(${var.copId})" checked>	
									   	</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${fn:containsIgnoreCase(var.isActive, 'N')}">
													<input type="checkbox" class=""  
													onchange="activeInactive(${var.copId})" >
												</c:when>
												<c:otherwise>
													<input type="checkbox" class=""  disabled="disabled"
														onchange="activeInactive(${var.copId})">
												</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose>
                    				</td>
									<td><a href="#" onclick="editUser(${var.copId})">
									<i class="fa fa-edit"></i></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

    <script src="resources/plugins/jQuery/jQuery-2.1.3.min.js"></script>
    
    <script>
	  $(document).ready(function(){
	    $('#imageInput1').on('change', function(e){
	      var file = e.target.files[0];
	      var reader = new FileReader();
	      
	      reader.onload = function(e){
	        $('#selectedImage1').attr('src', e.target.result);
	        $('#selectedImage1').css('display', 'block');
	      }
	
	      reader.readAsDataURL(file);
	
	      // Update label to display selected filename
	      var fileName = $(this).val().split('\\').pop();
	      $(this).next('.custom-file-label1').html(fileName);
	    });
	  });
	</script>
    	
    <script>
    $(document).ready(function(){
        $('#imageInput').on('change', function(e){
          var file = e.target.files[0];
          var reader = new FileReader();
          
          reader.onload = function(e){
            $('#selectedImage').attr('src', e.target.result);
            $('#selectedImage').css('display', 'block');
          }

          reader.readAsDataURL(file);

          // Update label to display selected filename
          var fileName = $(this).val().split('\\').pop();
          $(this).next('.custom-file-label').html(fileName);
        });
      });
	  
	  $(document).ready(function(){
	    $('.browse-img').on('click', function(){
	      var src = $(this).attr('src');
	      $('#preview-image').attr('src', src);
	    });
	  });
	
	</script>
    
	<script type="text/javascript">
		function editUser(copId){
			$.ajax({
				url : 'loadCooperativeDetails',
				type : 'GET',
				data : {
					copId :copId
				},
				success : function(response) {
					var model = response.copModel;
					$("[name=copAddress]").val(model.copAddress);
					$("[name=copRegNo]").val(model.copRegNo);
					$("[name=copName]").val(model.copName);
					$("[name=copId]").val(model.copId);
				}
			});
		}
		function activeInactive(copId){
			$.ajax({
				url : 'activateDeactivateCoperative',
				type : 'POST',
				data : {
					copId : copId
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