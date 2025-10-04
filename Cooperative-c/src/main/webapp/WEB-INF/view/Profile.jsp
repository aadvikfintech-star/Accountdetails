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
.header2{
    font-size: 20px;
    margin: 10px;
}
</style>
</head>
<body>
	<div class="center row">
		<div class="col-md-12">
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">Profile Report</h3>
				</div>
				<form:form method="post" action="document">
					<div class="box-body">
						<div class="row">
							<div class="form-group col-xs-4">
								<label>Mobile Number</label> <span style="color: red;">*</span><input type="text" id="mobileNumber"
									class="form-control" placeholder="Mobile Number"
									required="required" maxlength="10"/>
							</div>
							<div class="form-group col-xs-4">
								<label>Name</label>  <span style="color: red;">*</span> <input type="text" id="name"
									class="form-control" placeholder="Name"
									required="required"/>
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
		
		<div class="col-md-12" id="profileReport" style="display:none;">
			<div class="box box-primary">
				<div class="box-body">
					<div class="row">
						<div class="col-md-1"></div>
						<div class="col-md-10" id="printDiv" style="border:1px solid black;">
							<div class="col-md-12" style="text-align: center;font-size: 30px;">
								<label> Profile Report </label>
							</div>
						    <label class="header2"><i class="fa fa-user"></i> Personal Information</label>
						    <p style="margin-left: 38px;">
						    <span> <b>Full Name:</b></span> <span id="fullName"></span> <br>
						    <span><b>Date of Birth:</b></span> <span id="dob"></span><br>
						    <span><b>Gender:</b></span> <span id="gender"></span><br>
						    <span><b>Age: </b></span> <span id="age"></span><br>
						    <span><b>Total Income:</b></span> <span id="totalIncome"></span></p>
						    
						    
						    <label class="header2"><i class="fa fa-credit-card"></i> PAN Details</label>
						    <ul id="panList">
						    </ul>
						    
						    <label class="header2"><i class="fa fa-phone-square"></i> Phone Numbers</label>
						    <ul id="phoneList">
						    </ul>
						    
						    <label class="header2"><i class="fa fa-envelope"></i> Email Address</label>
						    <ul id="emailsList">
						    </ul>
						    
						    <label class="header2"><i class="fa fa-map-marker"></i> Address Information</label>
						    <ul id="addressList">
						    </ul>
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
    
	<script type="text/javascript">
		$(function() {
			
			 $('#lnkPrint').on('click', function() {
			    	var mywindow = window.open('', 'my div', 'height=400,width=600');
		    	    mywindow.document.write('<html><head><title></title>');
		    	    mywindow.document.write('<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />');  
		    	    mywindow.document.write('<style type="text/css">.test { color:red; } </style></head><body>');
		    	    mywindow.document.write( "<div style='margin-left: 50px; margin-right: 50px;margin-bottom: 40px; border: 1px solid;padding: 50px;'>"+$("#printDiv").html()+"</div>");
		    	    mywindow.document.write( "<span style='margin-left: 50px;''> Copyright � 2020-2021, Aadvik Fintech Pvt. Ltd. All rights reserved.</span>  <br> <span style='margin-left: 50px;'>For any queries please call +91 8484956995 </span>");
		    	    mywindow.document.write('</body></html>');
		    	    mywindow.document.close();
		    	    mywindow.print();       
		    	    setTimeout(window.close, 0);
			    });
			 
		    $('#btnVerify').on('click', function() {
		    	
				var walletAmount = $("#walletAmount").val();
			    var eligible = $("#eligible").val();
			     if(eligible == "false" && walletAmount != null  && parseInt(walletAmount) < 15) {
					alert('Your wallet balance is '+walletAmount+'. Please recharge your wallet.');
				    return false;
				}
				
				if($('#mobileNumber').val() == ''){
		    		alert ('Enter Mobile Number!!');
		    		return;
		    	}
		    	$('#btnVerify').prop('disabled', true);
	    		$("#loader").show();
		    	
		    	$.ajax({
					url : 'loadProfileDetails',
					type : 'POST',
					data : {
						mobileNumber : $("#mobileNumber").val(),
						name : $("#name").val(),
					},
					success : function(response) {
			    		$("#profileReport").show();
	    				$("#loader").hide();
	    				
	    				if (response.response.status_code == '200'){
	    					var personalInfo = response.response.data.personal_info;
		    		        var addressInfoList = response.response.data.address_info;
		    		        var identityInfo = response.response.data.identity_info;
		    		        var phoneList = response.response.data.phone_info;
		    		        var emailList = response.response.data.email_info;
		    		        
		    		        $("#fullName").text(personalInfo.full_name);
		    		        $("#dob").text(personalInfo.dob);
		    		        $("#gender").text(personalInfo.gender);
		    		        $("#age").text(personalInfo.age);
		    		        $("#totalIncome").text(personalInfo.total_income);
		    		        
		    		        $.each(identityInfo.pan_number, function(index, pan) {
		    		            var panId = "<li><span>PAN " + (index + 1) + ":</span> " + pan.id_number + "</li>";
		    		            $("#panList").append(panId);
		    		        });
		    		        
		    		        $.each(phoneList, function(index, phone) {
		    		            var phone = "<li><span>Phone " + (index + 1) + ":</span> " + phone.number + "</li>";
		    		            $("#phoneList").append(phone);
		    		        });
		    		        
		    		        $.each(emailList, function(index, email) {
		    		            var email = "<li><span> Email " + (index + 1) + ":</span> " + email.email_address + "</li>";
		    		            $("#emailsList").append(email);
		    		        });
		    		        
		    		        $.each(addressInfoList, function(index, address) {
		    		            var addressItem = "<li><span>Address " + (index + 1) + ":</span> " + address.address + ", " + address.state + " - " + address.postal + "</li>";
		    		            $("#addressList").append(addressItem);
		    		        });
	    				} else if (response.response.status_code == '422'){
	    					  $("#fullName").text($("#name").val());
	    					  $("#phoneList").append("<li><span>Phone 1:</span> " +  $("#mobileNumber").val() + "</li>");
	    				} else if (response.response.status_code == '500'){
							  alert("Please check entered data again and enter correct information!!");
						} else {
							  alert("Please contact support!!");
						}
	    				
				    	$('#btnVerify').prop('disabled', false);
					}
				});
		    	
		    });
		});
	</script>
</body>
</html>