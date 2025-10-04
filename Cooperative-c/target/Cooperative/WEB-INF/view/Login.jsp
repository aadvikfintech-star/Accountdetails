<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
  <head>
    <meta charset="UTF-8">
    <title><tiles:insertAttribute name="title" ignore="true" /></title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <!-- Bootstrap 3.3.2 -->
    <link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />    
    <!-- FontAwesome 4.3.0 -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <!-- Ionicons 2.0.0 -->
    <link href="http://code.ionicframework.com/ionicons/2.0.0/css/ionicons.min.css" rel="stylesheet" type="text/css" />    
    <!-- Theme style -->
    <link href="resources/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
    <!-- AdminLTE Skins. Choose a skin from the css/skins 
         folder instead of downloading all of them to reduce the load. -->
    <link href="resources/dist/css/skins/_all-skins.min.css" rel="stylesheet" type="text/css" />
    <!-- iCheck -->
    <link href="resources/plugins/iCheck/flat/blue.css" rel="stylesheet" type="text/css" />
    <!-- Morris chart -->
    <link href="resources/plugins/morris/morris.css" rel="stylesheet" type="text/css" />
    <!-- jvectormap -->
    <link href="resources/plugins/jvectormap/jquery-jvectormap-1.2.2.css" rel="stylesheet" type="text/css" />
    <!-- Date Picker -->
    <link href="resources/plugins/datepicker/datepicker3.css" rel="stylesheet" type="text/css" />
    <!-- Daterange picker -->
    <link href="resources/plugins/daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css" />
    <!-- bootstrap wysihtml5 - text editor -->
    <link href="resources/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css" rel="stylesheet" type="text/css" />
    <style type="text/css">
    	 img {
    	     max-width: 100%;
    height: auto;
    	 }
    </style>
</head>
<body class="skin-blue">
<div style="background-color: #f4f4f4;">
   <header class="main-header">
    <a style="width:250px !important;background-color: #3c8dbc;" href="login" class="logo"><b>Document Verification</b></a>
    <nav class="navbar navbar-static-top" role="navigation">
    </nav>
  </header>
      
<div style="min-height: 586px;">
   <section class="content">
		<div class="box box-primary" style="margin-bottom: 0px !important;">
			<div class="box-body">
				<form:form method="post" action="login">
					<div class="row">
						<div class="col-md-5">
							<img src="resources/img/login_logo.svg" alt="Image" class="img-fluid">
						</div>
						<div class="col-md-1"></div>
						<div class="col-md-4">
							<div class="form-group">
								<img src="resources/img/logo.jpg" class="user-image" alt="User Image"/>
							</div>
							<div class="form-group">
								<label>User Name</label> 
								<input type="text" name="userName" autofocus="autofocus"
									class="form-control" placeholder="Enter User Name" required="required"/>
							</div>
							<div class="form-group">
								<label>Password</label> <input type="password"
									name="password" class="form-control"
									placeholder="Enter Password" required="required" />
							</div>
							<div class="box-footer" style="text-align: center;">
								<button type="submit" class="btn btn-primary">Login</button>
							</div>
						</div>
					</div>
				</form:form>
			</div>
		</div>
</section>
      </div><!-- /.content-wrapper -->
      <footer class="main-footer" style="margin-left:0px !important;">
        <div class="pull-right hidden-xs">
        <!--   <b>Version</b> 2.0 -->
        </div>
         <strong>Copyright &copy; 2021-2022 Aadvik Fintech Pvt. Ltd.</strong> All rights reserved. 
         <br> <span>For any queries please call +91 8484956995 and mail us at aadvikfintech@gmail.com </span>
      </footer>
    </div><!-- ./wrapper -->
    <!-- jQuery 2.1.3 -->
    <script src="resources/plugins/jQuery/jQuery-2.1.3.min.js"></script>
    <!-- jQuery UI 1.11.2 -->
    <script src="http://code.jquery.com/ui/1.11.2/jquery-ui.min.js" type="text/javascript"></script>
    <!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
    <script>
      $.widget.bridge('uibutton', $.ui.button);
    </script>
    <!-- Bootstrap 3.3.2 JS -->
    <script src="resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>    
    <!-- Morris.js charts -->
    <script src="http://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
    <script src="resources/plugins/morris/morris.min.js" type="text/javascript"></script>
    <!-- Sparkline -->
    <script src="resources/plugins/sparkline/jquery.sparkline.min.js" type="text/javascript"></script>
    <!-- jvectormap -->
    <script src="resources/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js" type="text/javascript"></script>
    <script src="resources/plugins/jvectormap/jquery-jvectormap-world-mill-en.js" type="text/javascript"></script>
    <!-- jQuery Knob Chart -->
    <script src="resources/plugins/knob/jquery.knob.js" type="text/javascript"></script>
    <!-- daterangepicker -->
    <script src="resources/plugins/daterangepicker/daterangepicker.js" type="text/javascript"></script>
    <!-- datepicker -->
    <script src="resources/plugins/datepicker/bootstrap-datepicker.js" type="text/javascript"></script>
    <!-- Bootstrap WYSIHTML5 -->
    <script src="resources/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js" type="text/javascript"></script>
    <!-- iCheck -->
    <script src="resources/plugins/iCheck/icheck.min.js" type="text/javascript"></script>
    <!-- Slimscroll -->
    <script src="resources/plugins/slimScroll/jquery.slimscroll.min.js" type="text/javascript"></script>
    <!-- FastClick -->
    <script src='resources/plugins/fastclick/fastclick.min.js'></script>
    <!-- AdminLTE App -->
    <script src="resources/dist/js/app.min.js" type="text/javascript"></script>

    <!-- AdminLTE dashboard demo (This is only for demo purposes) -->
    <script src="resources/dist/js/pages/dashboard.js" type="text/javascript"></script>

    <!-- AdminLTE for demo purposes -->
    <script src="resources/dist/js/demo.js" type="text/javascript"></script>
  </body>
</html>