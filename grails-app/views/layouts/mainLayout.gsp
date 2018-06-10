<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/ico" />

    <title>Jwellery Shop</title>

    <script src="${resource(dir: 'js', file: 'jquery.min.js')}" type="text/javascript"
            charset="utf-8"></script>
    <!-- Bootstrap -->
    <script src="${resource(dir: 'js', file: 'bootstrap.min.js')}" type="text/javascript"
            charset="utf-8"></script>

    <script src="${resource(dir: 'js', file: 'nepali.datepicker.v2.2.min.js')}" type="text/javascript"
            charset="utf-8"></script>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'bootstrap.min.css')}" type="text/css"
          media="all"/>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'bootstrapvaliator.min.css')}" type="text/css"
          media="all"/>

    <link rel="stylesheet" href="${resource(dir: 'css', file: 'nepali.datepicker.v2.2.min.css')}" type="text/css"
          media="all"/>
    <!-- Font Awesome -->
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'font-awesome.min.css')}" type="text/css"
          media="all"/>
    <!-- bootstrap-progressbar -->
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'bootstrap-progressbar-3.3.4.min.css')}" type="text/css"
          media="all"/>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'dataTables.bootstrap.min.css')}" type="text/css"
          media="all"/>

    <!-- Custom Theme Style -->
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'custom.min.css')}" type="text/css"
          media="all"/>

</head>

<body class="nav-md">
<div class="container body">
    <div class="main_container">
        <div class="col-md-3 left_col">
            <div class="left_col scroll-view ">
                <div class="navbar nav_title" style="border: 0;">
                    <a href="index.html" class="site_title"><i class="fa fa-paw"></i> <span>Gentelella Alela!</span></a>
                </div>

                <div class="clearfix"></div>

                <!-- menu profile quick info -->
                <div class="profile clearfix">
                    <div class="profile_pic">
                        <img src="${resource(dir: 'images', file: 'img.jpg')}" alt="..." class="img-circle profile_img">
                    </div>
                    <div class="profile_info">
                        <span>Welcome,</span>
                        <h2>John Doe</h2>
                    </div>
                </div>
                <!-- /menu profile quick info -->

                <br />

                <!-- sidebar menu -->
                <div id="sidebar-menu" class="main_menu_side hidden-print main_menu ">
                    <div class="menu_section">
                        <h3>General</h3>
                        <ul class="nav side-menu">
                            <li><g:link action="home" controller="home"><i class="fa fa-home"></i> Home</g:link>
                            </li>
                            <li><a><i class="fa fa-edit"></i> SetUp <span class="fa fa-chevron-down"></span></a>
                                <ul class="nav child_menu">
                                    <li><a>Material<span class="fa fa-chevron-down"></span></a>
                                        <ul class="nav child_menu">
                                            <li><g:link action="create" controller="material">Add</g:link>
                                            </li>
                                            <li><g:link action="list" controller="material">List</g:link>
                                            </li>

                                        </ul>
                                    </li>
                                    <li><a>Item<span class="fa fa-chevron-down"></span></a>
                                        <ul class="nav child_menu">
                                            <li><g:link action="create" controller="item">Add</g:link>
                                            </li>
                                            <li><g:link action="list" controller="item">List</g:link>
                                            </li>

                                        </ul>
                                    </li>
                                    <li><a>Unit<span class="fa fa-chevron-down"></span></a>
                                        <ul class="nav child_menu">
                                            <li><g:link action="create" controller="unit">Add</g:link>
                                            </li>
                                            <li><g:link action="list" controller="unit">List</g:link>
                                            </li>

                                        </ul>
                                    </li>

                                </ul>

                            </li>
                            <li><a><i class="fa fa-edit"></i> Stock In <span class="fa fa-chevron-down"></span></a>
                                <ul class="nav child_menu">
                                    <g:each in="${Material.findAllByDelFlag(false)}" var="list">
                                    <li><a>${list.materialName}<span class="fa fa-chevron-down"></span></a>
                                        <ul class="nav child_menu">
                                            <li><g:link action="create" params="[stockType:'stock-in',identityMaterialName:list.identityMaterialName]" controller="stock">Add</g:link>
                                            </li>
                                            <li><a>List<span class="fa fa-chevron-down"></span></a>

                                                <ul class="nav child_menu">
                                                <g:each in="${Item.findAllByDelFlag(false)}" var="itemList">

                                                    <li><g:link action="list" params="[stockType:'stock-in',identityMaterialName:list.identityMaterialName,identityItemName:itemList.identityItemName]" controller="stock">${itemList.itemName}</g:link>
                                                    </li>
                                                </g:each>

                                                </ul>
                                            </li>



                                        </ul>
                                    </li>
                                    </g:each>
                                </ul>
                            </li>
                            <li><a><i class="fa fa-edit"></i> Stock Out <span class="fa fa-chevron-down"></span></a>
                                <ul class="nav child_menu">
                                    <g:each in="${Material.findAllByDelFlag(false)}" var="list">
                                        <li><a>${list.materialName}<span class="fa fa-chevron-down"></span></a>
                                            <ul class="nav child_menu">
                                                <li><g:link action="create" params="[stockType:'stock-out',identityMaterialName:list.identityMaterialName]" controller="stock">Add</g:link>
                                                </li>
                                                <li><a>List<span class="fa fa-chevron-down"></span></a>

                                                    <ul class="nav child_menu">
                                                        <g:each in="${Item.findAllByDelFlag(false)}" var="itemList">

                                                            <li><g:link action="list" params="[stockType:'stock-out',identityMaterialName:list.identityMaterialName,identityItemName:itemList.identityItemName]" controller="stock">${itemList.itemName}</g:link>
                                                            </li>
                                                        </g:each>

                                                    </ul>
                                                </li>


                                            </ul>
                                        </li>
                                    </g:each>
                                </ul>
                            </li>
                            <li><a><i class="fa fa-edit"></i> Stock Remaining <span class="fa fa-chevron-down"></span></a>
                                <ul class="nav child_menu">
                                    <g:each in="${Material.findAllByDelFlag(false)}" var="list">
                                        <li><a>${list.materialName}<span class="fa fa-chevron-down"></span></a>

                                                    <ul class="nav child_menu">
                                                        <g:each in="${Item.findAllByDelFlag(false)}" var="itemList">

                                                            <li><g:link action="remainingStockList" params="[identityMaterialName:list.identityMaterialName,identityItemName:itemList.identityItemName]" controller="stock">${itemList.itemName}</g:link>
                                                            </li>
                                                        </g:each>





                                            </ul>
                                        </li>
                                    </g:each>
                                </ul>
                            </li>

                            <li><a><i class="fa fa-desktop"></i> UI Elements <span class="fa fa-chevron-down"></span></a>
                                <ul class="nav child_menu">
                                    <li><a href="general_elements.html">General Elements</a></li>
                                    <li><a href="media_gallery.html">Media Gallery</a></li>
                                    <li><a href="typography.html">Typography</a></li>
                                    <li><a href="icons.html">Icons</a></li>
                                    <li><a href="glyphicons.html">Glyphicons</a></li>
                                    <li><a href="widgets.html">Widgets</a></li>
                                    <li><a href="invoice.html">Invoice</a></li>
                                    <li><a href="inbox.html">Inbox</a></li>
                                    <li><a href="calendar.html">Calendar</a></li>
                                </ul>
                            </li>
                            <li><a><i class="fa fa-table"></i> Tables <span class="fa fa-chevron-down"></span></a>
                                <ul class="nav child_menu">
                                    <li><a href="tables.html">Tables</a></li>
                                    <li><a href="tables_dynamic.html">Table Dynamic</a></li>
                                </ul>
                            </li>
                            <li><a><i class="fa fa-bar-chart-o"></i> Data Presentation <span class="fa fa-chevron-down"></span></a>
                                <ul class="nav child_menu">
                                    <li><a href="chartjs.html">Chart JS</a></li>
                                    <li><a href="chartjs2.html">Chart JS2</a></li>
                                    <li><a href="morisjs.html">Moris JS</a></li>
                                    <li><a href="echarts.html">ECharts</a></li>
                                    <li><a href="other_charts.html">Other Charts</a></li>
                                </ul>
                            </li>
                            <li><a><i class="fa fa-clone"></i>Layouts <span class="fa fa-chevron-down"></span></a>
                                <ul class="nav child_menu">
                                    <li><a href="fixed_sidebar.html">Fixed Sidebar</a></li>
                                    <li><a href="fixed_footer.html">Fixed Footer</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                    <div class="menu_section">
                        <h3>Live On</h3>
                        <ul class="nav side-menu">
                            <li><a><i class="fa fa-bug"></i> Additional Pages <span class="fa fa-chevron-down"></span></a>
                                <ul class="nav child_menu">
                                    <li><a href="e_commerce.html">E-commerce</a></li>
                                    <li><a href="projects.html">Projects</a></li>
                                    <li><a href="project_detail.html">Project Detail</a></li>
                                    <li><a href="contacts.html">Contacts</a></li>
                                    <li><a href="profile.html">Profile</a></li>
                                </ul>
                            </li>
                            <li><a><i class="fa fa-windows"></i> Extras <span class="fa fa-chevron-down"></span></a>
                                <ul class="nav child_menu">
                                    <li><a href="page_403.html">403 Error</a></li>
                                    <li><a href="page_404.html">404 Error</a></li>
                                    <li><a href="page_500.html">500 Error</a></li>
                                    <li><a href="plain_page.html">Plain Page</a></li>
                                    <li><a href="login.html">Login Page</a></li>
                                    <li><a href="pricing_tables.html">Pricing Tables</a></li>
                                </ul>
                            </li>
                            <li><a><i class="fa fa-sitemap"></i> Multilevel Menu <span class="fa fa-chevron-down"></span></a>
                                <ul class="nav child_menu">
                                    <li><a href="#level1_1">Level One</a>
                                    <li><a>Level One<span class="fa fa-chevron-down"></span></a>
                                        <ul class="nav child_menu">
                                            <li class="sub_menu"><a href="level2.html">Level Two</a>
                                            </li>
                                            <li><a href="#level2_1">Level Two</a>

                                            </li>
                                            <li><a href="#level2_2">Level Two</a>
                                            </li>
                                        </ul>
                                    </li>
                                    <li><a href="#level1_2">Level One</a>
                                    </li>
                                </ul>
                            </li>
                            <li><a href="javascript:void(0)"><i class="fa fa-laptop"></i> Landing Page <span class="label label-success pull-right">Coming Soon</span></a></li>
                        </ul>
                    </div>

                </div>
                <!-- /sidebar menu -->

                <!-- /menu footer buttons -->
                <div class="sidebar-footer hidden-small">
                    <a data-toggle="tooltip" data-placement="top" title="Settings">
                        <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
                    </a>
                    <a data-toggle="tooltip" data-placement="top" title="FullScreen">
                        <span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
                    </a>
                    <a data-toggle="tooltip" data-placement="top" title="Lock">
                        <span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
                    </a>
                    <a data-toggle="tooltip" data-placement="top" title="Logout" href="login.html">
                        <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
                    </a>
                </div>
                <!-- /menu footer buttons -->
            </div>
        </div>

        <!-- top navigation -->
        <div class="top_nav">
            <div class="nav_menu">
                <nav>
                    <div class="nav toggle">
                        <a id="menu_toggle"><i class="fa fa-bars"></i></a>
                    </div>

                    <ul class="nav navbar-nav navbar-right">
                        <li class="">
                            <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                <img src="images/img.jpg" alt="">John Doe
                                <span class=" fa fa-angle-down"></span>
                            </a>
                            <ul class="dropdown-menu dropdown-usermenu pull-right">
                                <li><a href="javascript:;"> Profile</a></li>
                                <li>
                                    <a href="javascript:;">
                                        <span class="badge bg-red pull-right">50%</span>
                                        <span>Settings</span>
                                    </a>
                                </li>
                                <li><a href="javascript:;">Help</a></li>
                                <li><a href="login.html"><i class="fa fa-sign-out pull-right"></i> Log Out</a></li>
                            </ul>
                        </li>

                        <li role="presentation" class="dropdown">
                            <a href="javascript:;" class="dropdown-toggle info-number" data-toggle="dropdown" aria-expanded="false">
                                <i class="fa fa-envelope-o"></i>
                                <span class="badge bg-green">6</span>
                            </a>
                            <ul id="menu1" class="dropdown-menu list-unstyled msg_list" role="menu">
                                <li>
                                    <a>

                                        <span class="image"><img src="${resource(dir: 'images', file: 'img.jpg')}" alt="Profile Image" /></span>
                                        <span>
                                            <span>John Smith</span>
                                            <span class="time">3 mins ago</span>
                                        </span>
                                        <span class="message">
                                            Film festivals used to be do-or-die moments for movie makers. They were where...
                                        </span>
                                    </a>
                                </li>
                                <li>
                                    <a>

                                        <span class="image"><img src="${resource(dir: 'images', file: 'img.jpg')}" alt="Profile Image" /></span>
                                        <span>
                                            <span>John Smith</span>
                                            <span class="time">3 mins ago</span>
                                        </span>
                                        <span class="message">
                                            Film festivals used to be do-or-die moments for movie makers. They were where...
                                        </span>
                                    </a>
                                </li>
                                <li>
                                    <a>
                                        <span class="image"><img src="${resource(dir: 'images', file: 'img.jpg')}" alt="Profile Image" /></span>
                                        <span>
                                            <span>John Smith</span>
                                            <span class="time">3 mins ago</span>
                                        </span>
                                        <span class="message">
                                            Film festivals used to be do-or-die moments for movie makers. They were where...
                                        </span>
                                    </a>
                                </li>
                                <li>
                                    <a>

                                        <span class="image"><img src="${resource(dir: 'images', file: 'img.jpg')}" alt="Profile Image" /></span>
                                        <span>
                                            <span>John Smith</span>
                                            <span class="time">3 mins ago</span>
                                        </span>
                                        <span class="message">
                                            Film festivals used to be do-or-die moments for movie makers. They were where...
                                        </span>
                                    </a>
                                </li>
                                <li>
                                    <div class="text-center">
                                        <a>
                                            <strong>See All Alerts</strong>
                                            <i class="fa fa-angle-right"></i>
                                        </a>
                                    </div>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
        <!-- /top navigation -->

        <!-- page content -->
        <div class="right_col" role="main">
            <!-- top tiles -->
<g:layoutBody/>
        </div>
        <!-- /page content -->

        <!-- footer content -->
        <footer>

            <div class="clearfix"></div>
        </footer>
        <!-- /footer content -->
    </div>
</div>
<!-- jQuery -->

<!-- bootstrap-progressbar -->
<script src="${resource(dir: 'js', file: 'bootstrap-progressbar.min.js')}" type="text/javascript"
        charset="utf-8"></script>
<script src="${resource(dir: 'js', file: 'jquery.dataTables.min.js')}" type="text/javascript"
        charset="utf-8"></script>
<script src="${resource(dir: 'js', file: 'dataTables.bootstrap.min.js')}" type="text/javascript"
        charset="utf-8"></script>
<script src="${resource(dir: 'js', file: 'dataTables.fixedHeader.min.js')}" type="text/javascript"
        charset="utf-8"></script>
<script src="${resource(dir: 'js', file: 'dataTables.keyTable.min.js')}" type="text/javascript"
        charset="utf-8"></script>
<script src="${resource(dir: 'js', file: 'dataTables.responsive.min.js')}" type="text/javascript"
        charset="utf-8"></script>
<script src="${resource(dir: 'js', file: 'dataTables.scroller.min.js')}" type="text/javascript"
        charset="utf-8"></script>
<script src="${resource(dir: 'js', file: 'bootstrapvalidator.min.js')}" type="text/javascript"
        charset="utf-8"></script>

<!-- Custom Theme Scripts -->
<script src="${resource(dir: 'js', file: 'custom.min.js')}" type="text/javascript"
        charset="utf-8"></script>
</body>
</html>