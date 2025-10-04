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

</style>
</head>
<body>
	<div class="center row">
		<div class="col-md-12">
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">Cibil Report</h3>
				</div>
				<form:form method="post" action="cibil">
					<div class="box-body">
						<div class="row">
							<div class="form-group col-xs-4">
								<label>Report Type <span style="color: red;">*</span></label> 	
								<select class="form-control" id="docTypeId" name="docTypeId" required="required">
									<option value="">Select</option>
									<option value="1">Equifax Report</option>
									<option value="2">Other Report</option>
								</select>
							</div>
							<div class="form-group col-xs-4">
								<label>PAN Number <span style="color: red;">*</span></label><span style="font-size: 12px;"> (Enter PAN number in correct format, ex. ABCDE1234F) </span>
								 <input type="text" id="panNumber"
									class="form-control" placeholder="PAN Number"
									required="required" maxlength="10"/>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-xs-4">
								<label>Name <span style="color: red;">*</span> </label> <input type="text" id="name"
									class="form-control" placeholder="Name"
									required="required" maxlength="50"/>
							</div>
							<div class="form-group col-xs-4">
								<label>Mobile Number <span style="color: red;">*</span> </label> <input type="text" id="mobileNumber"
									class="form-control" placeholder="Mobile Number"
									required="required" maxlength="10"/>
							</div>
							<div class="form-group col-xs-4">
								<label>Email</label> <input type="text" id="email"
									class="form-control" placeholder="Emailid"
									required="required" maxlength="50"/>
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
						<div class="row">
							<div id="pdfContainer" style="text-align: center;"></div>
						</div>
					</div>
				</form:form>
			</div>
		</div>
		
		<div class="col-md-12" id="cibilReport" style="display:none;">
			<div class="box box-primary">
				<div class="box-body">
					<div class="row">
						<div class="col-md-1"></div>
						<div class="col-md-10" id="printDiv">
							<div class="col-md-12" style="text-align: center;font-size: 30px;">
								<label> Cibil Report </label>
							</div>
							<label style="font-size: 25px;"> Score Value :  </label>
							<label style="font-size: 30px;">-1 </label><br>
							
						    <label class="header2"><i class="fa fa-user"></i> Personal Information</label>
						    <p style="margin-left: 38px;"><span> <b>Full Name:</b></span> <span id="fullName"></span>  </p><br>
						    
						    <label class="header2"><i class="fa fa-credit-card"></i> PAN Details</label>
						    <ul id="panList">
						    </ul>
						    
						    <label class="header2"><i class="fa fa-phone-square"></i> Contact Details </label>
						    <ul id="phoneList">
						    </ul>
						    
						    <label class="header2"><i class="fa fa-map-marker"></i> Address Information</label>
						    <ul id="addressList">
						    </ul>
						    
						     <label class="header2"><i class="fa fa-map-marker"></i> Retail Account Summary </label>
						     <br> - 
						     
						     <label class="header2"><i class="fa fa-map-marker"></i> Retail Accounts </label>
						     <br> - 
						     
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
			
			$('#panNumber').keyup(function(){
		        this.value = this.value.toUpperCase();
		    });
			
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
		    	var panPattern = /^[A-Z]{5}[0-9]{4}[A-Z]{1}$/;
		    	if (!panPattern.test($("#panNumber").val())) {
                    alert('Enter Valid PAN number');
                    return;
                } 
		    	 
		    	if($('#docTypeId').val() == ''){
		    		alert ('Select document type!');
		    		return;
		    	}
		    	$('#btnVerify').prop('disabled', true);
	    		$("#loader").show();
		    	
		    	$.ajax({
					url : 'loadCibilReport',
					type : 'POST',
					data : {
						pan : $("#panNumber").val(),
						mobile : $("#mobileNumber").val(),
						name : $("#name").val(),
					},
					success : function(response) {
						if (response.response.status_code == '200'){
							$("#pdfContainer").html('<embed src="' + response.response.data.credit_report_link + '" type="application/pdf" width="1000px" height="1000px">');
						}else if (response.response.status_code == '422'){
							$("#fullName").text($("#name").val());
	    					$("#phoneList").append("<li><span>Phone 1:</span> " +  $("#mobileNumber").val() + "</li>");
	    					$("#panList").append("<li><span>PAN 1:</span> " +  $("#panNumber").val() + "</li>");
	    					$("#addressList").append("<li><span>Address 1:</span>  - </li>");
							$("#cibilReport").show();
						} else if (response.response.status_code == '500'){
							alert("Please check entered data again and enter correct information!!");
						} else {
							alert("Please contact support!!");
						}
						$("#loader").hide();
				    	$('#btnVerify').prop('disabled', false);
					}
				});
		    });
		});
	</script>
</body>
</html>