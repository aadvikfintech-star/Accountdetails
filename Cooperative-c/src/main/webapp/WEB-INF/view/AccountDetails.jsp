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
  animation: spin 2s linear infinite;
}
@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}
.header2 {
    font-size: 20px;
    margin: 10px 0;
}
.account-table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 15px;
}
.account-table th, .account-table td {
    border: 1px solid #ccc;
    padding: 8px 12px;
    text-align: left;
}
.account-table th {
    background-color: #f2f2f2;
}
</style>
</head>
<body>
<div class="center row">
    <!-- Input Form -->
    <div class="col-md-12">
        <div class="box box-primary">
            <div class="box-header">
                <h3 class="box-title">Account Details Report</h3>
            </div>
            <form:form method="post" action="document">
                <div class="box-body">
                    <div class="row">
                        <div class="form-group col-xs-4">
                            <label>Mobile Number</label> <span style="color: red;">*</span>
                            <input type="text" id="mobileNumber" class="form-control" placeholder="Mobile Number" required maxlength="10"/>
                        </div>
                        <div class="form-group col-xs-4">
                            <label>Name</label>  <span style="color: red;">*</span>
                            <input type="text" id="name" class="form-control" placeholder="Name" required/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-xs-1">
                            <button type="button" id="btnVerify" class="btn btn-primary">Verify</button>
                            <input type="hidden" id="eligible" value="${eligible}">
                            <input type="hidden" id="walletAmount" value="${walletAmount}">
                        </div>
                        <div class="form-group col-xs-4">
                            <div class="loader" id="loader" style="display: none;"></div>
                        </div>
                    </div>
                </div>
            </form:form>
        </div>
    </div>

    <!-- Account Details Report -->
    <div class="col-md-12" id="accountDetailsReport" style="display:none;">
        <div class="box box-primary">
            <div class="box-body">
                <div class="col-md-12" id="printDiv" style="border:1px solid black; padding: 20px;">
                    <div style="text-align: center; font-size: 30px; margin-bottom: 20px;">
                        <label>Account Details Report</label>
                    </div>
                    <label class="header2"><i class="fa fa-credit-card"></i> Accounts Details</label>
                    <table class="account-table" id="accountList">
                        <thead>
                            <tr>
                                <th>Account Holder</th>
                                <th>Account Holder Name</th>
                                <th>Account No</th>
                                <th>IFSC</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- Accounts appended dynamically -->
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <!-- Print and Download Buttons -->
        <div style="margin-top: 15px;">
            <button type="button" id="lnkPrint" class="btn btn-success">
                <i class="fa fa-print"></i> Print
            </button>
            <button type="button" id="btnDownload" class="btn btn-info">
                <i class="fa fa-download"></i> Download PDF
            </button>
        </div>
    </div>
</div>

<!-- Scripts -->
<script src="resources/plugins/jQuery/jQuery-2.1.3.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/1.4.1/html2canvas.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.5.1/jspdf.umd.min.js"></script>

<script type="text/javascript">
$(function() {

    // Verify Button
    $('#btnVerify').on('click', function() {
        var walletAmount = $("#walletAmount").val();
        var eligible = $("#eligible").val();
        if(eligible == "false" && walletAmount && parseInt(walletAmount) < 15) {
            alert('Your wallet balance is '+walletAmount+'. Please recharge your wallet.');
            return false;
        }

        if($('#mobileNumber').val() == ''){
            alert('Enter Mobile Number!!');
            return;
        }

        $('#btnVerify').prop('disabled', true);
        $("#loader").show();

        $.ajax({
            url : 'loadAccountsDetails',
            type : 'POST',
            data : {
                mobileNumber : $("#mobileNumber").val(),
                name : $("#name").val(),
            },
            success : function(data) {
                $("#accountDetailsReport").show();
                $("#loader").hide();

                var response = data.response;
                
                if (response.status_code == 200) {
                    var accounts = response.data.details;
                    $("#accountList tbody").empty();
                    if(accounts && accounts.length > 0) {
                        $.each(accounts, function(index, account) {
                            $("#accountList tbody").append(
                                "<tr>" +
                                "<td>Account Holder " + (index+1) + "</td>" +
                                "<td>" + account.full_name + "</td>" +
                                "<td>" + account.account_number + "</td>" +
                                "<td>" + account.ifsc + "</td>" +
                                "</tr>"
                            );
                        });
                    } else {
                        $("#accountList tbody").html("<tr><td colspan='4'>No accounts found</td></tr>");
                    }
                } else if (response.status_code == 422) {
                    $("#accountList tbody").html("<tr><td colspan='4'>No accounts found for this number</td></tr>");
                } else if (response.status_code == 500) {
                    alert("Please check entered data again and enter correct information!!");
                } else {
                    alert("Please contact support!!");
                }

                $('#btnVerify').prop('disabled', false);
            },
            error: function() {
                $("#loader").hide();
                $('#btnVerify').prop('disabled', false);
                alert("Error connecting to server!");
            }
        });
    });

    // Print Button
    $('#lnkPrint').on('click', function() {
        var mywindow = window.open('', 'Print', 'height=600,width=800');
        mywindow.document.write('<html><head><title>Account Details</title>');
        mywindow.document.write('<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />');  
        mywindow.document.write('<style>table { width: 100%; border-collapse: collapse; } th, td { border: 1px solid #ccc; padding: 8px; text-align: left; } th { background-color: #f2f2f2; }</style>');
        mywindow.document.write('</head><body>');
        mywindow.document.write("<div style='margin:50px; padding:20px;'>"+$("#printDiv").html()+"</div>");
        mywindow.document.write('</body></html>');
        mywindow.document.close();
        mywindow.print();
        setTimeout(function(){ mywindow.close(); }, 0);
    });

    // Download PDF Button
    $('#btnDownload').on('click', function() {
        const { jsPDF } = window.jspdf;

        html2canvas(document.querySelector("#printDiv"), {
            scale: 2,       // Higher resolution for clear text
            useCORS: true   // Load external images/css correctly
        }).then(canvas => {
            var imgData = canvas.toDataURL('image/png');
            var pdf = new jsPDF('p', 'pt', 'a4');
            var pageWidth = pdf.internal.pageSize.getWidth();
            var pageHeight = pdf.internal.pageSize.getHeight();
            
            // Calculate height to maintain aspect ratio
            var imgWidth = pageWidth - 40; // margin 20 each side
            var imgHeight = canvas.height * imgWidth / canvas.width;

            pdf.addImage(imgData, 'PNG', 20, 20, imgWidth, imgHeight);
            pdf.save("Account_Details_Report.pdf");
        });
    });


});
</script>
</body>
</html>
