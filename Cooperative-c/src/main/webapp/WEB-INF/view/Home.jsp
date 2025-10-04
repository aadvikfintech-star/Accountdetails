<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
  <head>
    <meta charset="UTF-8">
    <title><tiles:insertAttribute name="title" ignore="true" /></title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <!-- Bootstrap 3.3.2 -->
    <link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />    
    <!-- FontAwesome 4.3.0 -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
     <!-- Font Awesome Icons -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <!-- Ionicons 2.0.0 -->
    <link href="http://code.ionicframework.com/ionicons/2.0.0/css/ionicons.min.css" rel="stylesheet" type="text/css" />  
     <!-- DATA TABLES -->
    <link href="resources/plugins/datatables/dataTables.bootstrap.css" rel="stylesheet" type="text/css" />  
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
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/css/bootstrap-select.min.css">
     
  </head>
  <body class="skin-blue">
    <div class="wrapper">
      
      <header class="main-header">
        <!-- Logo 
        <a href="index2.html" class="logo"><b>${user.copName}</b></a>-->
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top" role="navigation">
          <!-- Sidebar toggle button-->
          <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
          </a>
          <span class="logo" style="background-color: #3c8dbc !important; width: 1000px !important;">
           ${user.copName} 
           <c:choose>
	           <c:when test="${user.roleId == 2}">
	           		- Branch User	
	           </c:when>
           </c:choose>
           </span>
          <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
              <!-- User Account: style can be found in dropdown.less -->
              <li class="dropdown user user-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <img src="resources/img/logo.png" class="user-image" alt="User Image"/>
                  <span class="hidden-xs">${user.firstName} ${user.lastName}</span>
                </a>
                <ul class="dropdown-menu">
                  <!-- User image -->
                  <li class="user-header">
                    <img src="resources/img/logo.png" class="img-circle" alt="User Image" />
                    <p>
                     ${user.firstName} ${user.lastName} 
                    </p>
                  </li>
                  <!-- Menu Footer-->
                  <li class="user-footer">
                    <div class="pull-left">
                      <a href="#" class="btn btn-default btn-flat">Profile</a>
                    </div>
                    <div class="pull-right">
                      <a href="logout" class="btn btn-default btn-flat">Sign out</a>
                    </div>
                  </li>
                </ul>
              </li>
            </ul>
          </div>
        </nav>
      </header>
      <!-- Left side column. contains the logo and sidebar -->
      <aside class="main-sidebar" style="padding-top: 0px !important;">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
          <!-- Sidebar user panel -->
          <div class="user-panel">
            <div class="pull-left image">
              <img src="resources/img/logo.png" class="img-circle" alt="User Image" />
            </div>
            <div class="pull-left info">
              <p>${user.firstName} ${user.lastName} </p>

              <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
            </div>
          </div>
          <ul class="sidebar-menu">
            <li class="active treeview">
            	<a href="index">
                <i class="fa fa-dashboard"></i> <span>Dashboard</span> 
              </a>
            </li>
            <c:choose>
				<c:when test="${user.roleId == 0}">
					<li class="treeview">
	                  <a href="#" id="parent1">
		                <i class="fa fa-database"></i> <span>Master</span> <i class="fa fa-angle-left pull-right"></i>
		              </a>
		              <ul class="treeview-menu">
		                <li><a href="userRegistration"><i class="fa fa-circle-o"></i>User</a></li>
		              </ul>
		              <ul class="treeview-menu">
		                <li><a href="institution"><i class="fa fa-circle-o"></i>Institution</a></li>
		              </ul>
	              </li>
	               <li class="treeview">
		           	  <a href="wallet">
		                <i class="fa fa-inr"></i> <span>Wallet</span> 
		              </a>
		            </li>
	               <!-- <li class="treeview">
		           	  <a href="document">
		                <i class="fa fa-file"></i> <span>Document Verification</span> 
		              </a>
		            </li>
	               <li class="treeview">
		           	  <a href="profile">
		                <i class="fa fa-user"></i> <span>Profile Report</span> 
		              </a>
		            </li>
	               <li class="treeview">
		           	  <a href="cibil">
		                <i class="fa fa-calculator"></i> <span>Cibil Report</span> 
		              </a>
		            </li> -->
		              <li class="treeview">
		           	  <a href="accountdetails">
		                <i class="fa fa-user"></i> <span>Account Details</span> 
		              </a>
		            </li>
				</c:when>
				<c:when test="${user.roleId == 1}">
				  <li class="treeview">
		           	  <a href="wallet">
		               <i class="fa fa-inr"></i> <span>Wallet</span> 
		              </a>
		           </li>
		           <!-- <li class="treeview">
		           	  <a href="document">
		                <i class="fa fa-file"></i> <span>Document Verification</span> 
		              </a>
		          </li>
		          <li class="treeview">
		           	  <a href="profile">
		                <i class="fa fa-user"></i> <span>Profile Report</span> 
		              </a>
		            </li>
	               <li class="treeview">
		           	  <a href="cibil">
		                <i class="fa fa-calculator"></i> <span>Cibil Report</span> 
		              </a>
		            </li> -->
		            <li class="treeview">
		           	  <a href="accountdetails">
		                <i class="fa fa-user"></i> <span>Account Details</span> 
		              </a>
		            </li>
				</c:when>
				<c:when test="${user.roleId == 2}">
				  <li class="treeview">
		           	  <a href="wallet">
		                <i class="fa fa-inr"></i> <span>Wallet</span> 
		              </a>
		           </li>
		           <!-- <li class="treeview">
		           	  <a href="document">
		                <i class="fa fa-file"></i> <span>Document Verification</span> 
		              </a>
		          </li>
		          <li class="treeview">
		           	  <a href="profile">
		                <i class="fa fa-user"></i> <span>Profile Report</span> 
		              </a>
		            </li>
	               <li class="treeview">
		           	  <a href="cibil">
		                <i class="fa fa-calculator"></i> <span>Cibil Report</span> 
		              </a>
		            </li> -->
		             <li class="treeview">
		           	  <a href="accountdetails">
		                <i class="fa fa-user"></i> <span>Account Details</span> 
		              </a>
		            </li>
				</c:when>
			</c:choose>	
            </ul>
        </section>
        <!-- /.sidebar -->
      </aside>

      <!-- Right side column. Contains the navbar and content of the page -->
      <div class="content-wrapper" style="min-height: 586px;">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            <tiles:insertAttribute name="title" ignore="true" />
            <%-- <small><tiles:insertAttribute name="title" ignore="true" /></small> --%>
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> <tiles:insertAttribute name="menu" ignore="true" /></a></li>
            <li class="active"><tiles:insertAttribute name="title" ignore="true" /></li>
          </ol>
        </section>
        <!-- Main content changed by Umesh/Pradip-->
        <section class="content">
        	<tiles:insertAttribute name="body" /> 
		</section>
        
      </div><!-- /.content-wrapper -->
      <footer class="main-footer">
        <div class="pull-right hidden-xs">
          <b>Version</b> 1.0
        </div>
        <strong>Copyright &copy; 2020-2021 </strong> All rights reserved.
      </footer>
    </div> <!--./wrapper --> 

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
       <!-- DATA TABES SCRIPT -->
    <script src="resources/plugins/datatables/jquery.dataTables.js" type="text/javascript"></script>
    <script src="resources/plugins/datatables/dataTables.bootstrap.js" type="text/javascript"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/js/bootstrap-select.min.js"></script>
    
     <script src="resources/plugins/jQuery/jQuery-2.1.3.min.js"></script>
     
     <script type="text/javascript">
	     $(function() {
	    	 $('#parent1').on('click', function() {
	    		 if ($(this).parent('li').hasClass('active')) {
		    		 $(this).parent('li').removeClass('active');	
	    		 } else{
		    		 $(this).parent('li').addClass('active');	
	    		 }
			 });
		});
     
     </script>
		
  </body>
</html>