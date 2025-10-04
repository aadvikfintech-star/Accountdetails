<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
.loader {
  border: 10px solid #f3f3f3;
  border-radius: 50%;
  border-top: 10px solid blue;
  border-bottom: 10px solid blue;
  width: 40px;
  height: 40px;
  -webkit-animation: spin 2s linear infinite;
  animation: spin 2s linear infinite;
}

@-webkit-keyframes spin {
  0% { -webkit-transform: rotate(0deg); }
  100% { -webkit-transform: rotate(360deg); }
}

@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}

.datepicker {
     position: relative;
     /*width: 150px; height: 20px;*/
     color: white;
 }

.datepicker:before {
     position: absolute;
     top: 3px; left: 3px;
     content: attr(data-date);
     display: inline-block;
     color: black;
 }

 .datepicker::-webkit-datetime-edit, .datepicker::-webkit-inner-spin-button, .datepicker::-webkit-clear-button {
     display: none;
 }

 .datepicker::-webkit-calendar-picker-indicator {
     position: absolute;
     top: 3px;
     right: 0;
     color: black;
     opacity: 1;
 }
</style>
</head>
<body>
	<div class="center row">
		<div class="col-md-12">
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">Document Verification</h3>
				</div>
				<form:form method="post" action="document">
					<div class="box-body">
						<div class="row">
							<div class="form-group col-xs-4">
								<label>Document Type <span style="color: red;">*</span> </label> 	
								<select class="form-control" id="docTypeId" name="docTypeId">
									<option value="">Select</option>
									<c:forEach var="doc" items="${lstDocumentType}">
										<option value="${doc.id}">${doc.sysName}</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group col-xs-4">
								<label>Document Number <span style="color: red;">*</span> </label> <input type="text" id="docNumber"
									class="form-control" placeholder="Document Number"
									required="required" maxlength="15"/>
							</div>
							<div class="form-group col-xs-4" id="birthDateDiv" style="display: none;">
								<label>Birth Date</label> <input type="date" id="birthDate"
									class="form-control datepicker" data-date="" data-date-format="YYYY-MM-DD" value="1994-06-16"/>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-xs-1">
								<button type="button" id="btnVerify" class="btn btn-primary">Verify</button>
								<input type="hidden" id="eligible" value="${eligible}">
								<input type="hidden" id="walletAmount" value="${walletAmount}">
								<input type="hidden" id="clientId" value="${walletAmount}">
							</div>
							<div class="form-group col-xs-4">
								<div class="loader" id="loader" style="display: none;"></div>
							</div>
						</div>
					</div>
				</form:form>
			</div>
		</div>
		<div class="col-md-12" id="aadharotp" style="display:none;">
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">Document Details</h3>
				</div>
				<div class="box-body">
					<div class="row">
						<div class="form-group col-xs-4">
							<label>OTP</label> <input type="text" id="otp"
								class="form-control" placeholder="OTP"
								required="required" />
						</div>
						<div class="form-group col-xs-4">
							<button type="button" id="btnSubmitOtp" class="btn btn-primary">Verify</button>			
						</div>
					</div>
				</div>
			</div>	
		</div>
		<div class="col-md-12" id="docDetails" style="display:none;">
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">Document Details</h3>
				</div>
				<div class="box-body">
					<div class="row">
						<div class="form-group col-xs-1">
							<i  class="fa fa-check-circle" id="greenCheck" style="display:none;font-size:48px;color:green"></i>
							<i class="fa fa-times-circle-o" id="crossRed" style="display:none;font-size:48px;color:red"></i>
						</div>
						<div class="form-group col-xs-1" id="aadharProfile" style="display:none;">
							<img alt="Profile" id="imgProfile" src="" style="width: 100%">
						</div>
						<div class="form-group col-xs-9">
							<div id="api_response"></div>
						</div>
						<div class="form-group col-xs-1" style="font-size: 40px;"> 
							<a id="lnkPrint"><i class="fa fa-download" aria-hidden="true"></i></a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
    <script src="resources/plugins/jQuery/jQuery-2.1.3.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.3/moment.min.js"></script>
    
	<script type="text/javascript">
		$(function() {
			
			$("#birthDate").on("change", function() {
		        this.setAttribute(
		            "data-date",
		            moment(this.value, "YYYY-MM-DD")
		            .format( this.getAttribute("data-date-format") )
		        )
		    }).trigger("change")
		    
		    $('#docTypeId').on('change', function() {
		    	$("#docDetails").hide();
		    	$("#aadharotp").hide();
		    	$("#docNumber").val('');
		    });
		    
		    $('#lnkPrint').on('click', function() {
		    	var mywindow = window.open('', 'my div', 'height=400,width=600');
	    	    mywindow.document.write('<html><head><title></title>');
	    	    mywindow.document.write('<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />');  
	    	    mywindow.document.write('<style type="text/css">.test { color:red; } </style></head><body>');
	    	    
	    	    mywindow.document.write( "<div style='margin-left: 50px; margin-right: 50px;margin-bottom: 40px; border: 1px solid;padding: 50px;'>"+$("#docDetails").html()+"</div>");
	    	    mywindow.document.write( "<span style='margin-left: 50px;''> Copyright � 2020-2021, Aadvik Fintech Pvt. Ltd. All rights reserved.</span>  <br> <span style='margin-left: 50px;'>For any queries please call +91 8484956995 </span>");
	    	    mywindow.document.write('</body></html>');
	    	    mywindow.document.close();
	    	    mywindow.print();       
	    	    setTimeout(window.close, 0);
		    });
		    
		    $('#btnSubmitOtp').on('click', function() {
	    		$("#loader").show();
		    	$("#docDetails").hide();
		    	
		    	$.ajax({
					url : 'submitOTP',
					type : 'POST',
					data : {
						clientId : $("#clientId").val(),
						otp : $("#otp").val(),
						docNumber :  $("#docNumber").val(),
					},
					success : function(response) {
						var api_response = JSON.parse(response.response);
						
						if(api_response.status_code == 200){
				    		$("#aadharProfile").show();
							$("#greenCheck").show();
							$("#crossRed").hide();
							$("#imgProfile").attr('src', 'data:image/jpeg;base64,' +response.profile);
							
							$("#api_response").html(" <b> Name :"+api_response.data.full_name+" </b> " +
									" <br> <b> Aadhaar Number: </b>"+api_response.data.aadhaar_number+" " +
									" <br> <b> Date of birth: </b>" +api_response.data.dob+ " " + 
									" <br> <b> Gender : </b>"+api_response.data.gender+" " +
									" <br> <b> Address: </b>" +response.address+ " " +
									" <br> <b> ZIP Code : </b>" +api_response.data.zip+ " ");
							$("#docDetails").show();
							$("#aadharotp").hide();
						}else {
							alert('Enter correct OTP!');
						}
						$("#loader").hide();
					}
		    	});
		    });
		    
		    $('#docTypeId').on('change', function() {
		    	if ($("#docTypeId").val() == 4) 
		    		$("#birthDateDiv").show();	
		    	else
		    		$("#birthDateDiv").hide();	
		    });
		    
		    $('#docNumber').keyup(function(){
		        this.value = this.value.toUpperCase();
		    });
		    
		    $('#btnVerify').on('click', function() {
		    	
				var walletAmount = $("#walletAmount").val();
							    var eligible = $("#eligible").val();
							     if(eligible == "false" && walletAmount != null  && parseInt(walletAmount) < 15) {
									alert('Your wallet balance is '+walletAmount+'. Please recharge your wallet.');
								    return false;
								}
		    	
		    	if($('#docTypeId').val() == ''){
		    		alert ('Select document type!');
		    		return;
		    	}
		    	if($('#docNumber').val() == ''){
		    		alert ('Enter document number!');
		    		return;
		    	}
		    	
		    	if($('#docTypeId').val() == 4 && $('#birthDate').val() == ''){
		    		alert ('Select birth date!');
		    		return;
		    	}
		    	 
		    	
		    	if(eligible == "true" ) {
		    		$("#loader").show();
			    	$("#docDetails").hide();
			    	$("#aadharProfile").hide();
			    	$('#btnVerify').prop('disabled', true);
			    	$.ajax({
						url : 'loadDocumentDetails',
						type : 'POST',
						data : {
							docNumber : $("#docNumber").val(),
							docTypeId : $("#docTypeId").val(),
							birthDate : $('#birthDate').val()
						},
						success : function(response) {
							var api_response = JSON.parse(response.response);
							
							if(api_response.status_code == 200){
								$("#greenCheck").show();
								$("#crossRed").hide();
								
								if ($("#docTypeId").val() == 1) {
									$("#clientId").val(api_response.data.client_id);
								}else if ($("#docTypeId").val() == 2) {
									$("#api_response").html(" <b> Name :"+api_response.data.full_name+" </b> " +
											" <br> <b> PAN Number: </b>"+api_response.data.pan_number+" ");
								}else if ($("#docTypeId").val() == 3) {
									$("#api_response").html(" <b> Name : "+api_response.data.name+" </b> " +
											" <br> <b> Voter ID: </b>"+api_response.data.input_voter_id+" " +
											" <br> <b> Gender: </b>"+api_response.data.gender+" " +
											" <br> <b> Area: </b>"+api_response.data.area+" " +
											" <br> <b> District: </b>"+api_response.data.district+" " +
											" <br> <b> Marathi Name: </b>"+api_response.data.name_v1+" " +
											" <br> <b> Polling Station : </b>"+api_response.data.polling_station+"");
								}else if ($("#docTypeId").val() == 4) {
									$("#api_response").html(" <b> Name : "+api_response.data.name+" </b> " +
											" <br> <b> License Number: </b>"+api_response.data.license_number+" " +
											" <br> <b> Gender: </b>"+api_response.data.gender+" " +
											" <br> <b> Permanent Address: </b>"+api_response.data.permanent_address+" " +
											" <br> <b> State: </b>"+api_response.data.state+" " +
											" <br> <b> Ola Code: </b>"+api_response.data.ola_code+" " +
											" <br> <b> Father/Husband Name: </b>"+api_response.data.father_or_husband_name+" " +
											" <br> <b> Date of Birth: </b>"+api_response.data.dob+"" +
											" <br> <b> Date of Issue: </b>"+api_response.data.doi+"" +
											" <br> <b> Date of Expiry: </b>"+api_response.data.doe+"");
								}else if ($("#docTypeId").val() == 5) {
									$("#api_response").html(" <b> Name :"+api_response.data.full_name+" </b> " +
											" <br> <b> PAN Number: </b>"+api_response.data.pan_number+" " +
											" <br> <b> Gender: </b>"+api_response.data.gender+" " +
											" <br> <b> Date of Birth: </b>"+api_response.data.dob+"" +
											" <br> <b> Email: </b>"+api_response.data.email+" " +
											" <br> <b> Phone Number: </b>"+api_response.data.phone_number+" " +
											" <br> <b> Masked Aadhar: </b>"+api_response.data.masked_aadhaar+" " +
											" <br> <b> Address: </b>"+api_response.address+" " +
										+"");
								}
							} else {
								$("#greenCheck").hide();
								$("#crossRed").show();
								
								$("#api_response").html(" <b> Status :"+api_response.status_code+" </b> " +
										" <br> Document Number: "+api_response.data.aadhaar_number+" " +
										" <br> Message: "+api_response.message+" " +
										" <br> Remark : "+api_response.data.remarks+"");
							}
							$("#loader").hide();
							if ($("#docTypeId").val() == 1) {
								$("#aadharotp").show();
							}else {
								$("#docDetails").show();
								$('#btnVerify').prop('disabled', false);
							}
						}
					});
		    	}
		    	
		    });
		});
	</script>
</body>
</html>