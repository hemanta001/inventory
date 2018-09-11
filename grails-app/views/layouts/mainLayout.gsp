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
<g:if test="${session.adminUser}">

<div class="container body">
    <div class="main_container">
        <div class="col-md-3 left_col">
            <div class="left_col scroll-view ">
                <div class="navbar nav_title" style="border: 0;">
                    <g:link action="home" controller="home" class="site_title"><i class="fa fa-paw"></i> <span>Gentelella Alela!</span></g:link>
                </div>

                <div class="clearfix"></div>

                <!-- menu profile quick info -->

                <div class="profile clearfix">
                <g:if test="${userInstance?.profileImageName!=null}">
                    <div class="profile_pic">
                        <img src="${createLink(controller: 'user', action:'renderImage',params: [profileImageName:session?.adminUser?.profileImageName])}" class="img-circle profile_img">

                    </div>
                </g:if>
                <g:else>
                    <div class="profile_pic">
                        <img src="${resource(dir: 'images', file: 'blank.png')}" class="img-circle profile_img">

                    </div>
                </g:else>
                    <div class="profile_info">
                        <span>Welcome,</span>
                        <h2>${session.adminUser.firstName+" "+session.adminUser.lastName}</h2>
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
                                    <g:if test="${session.adminUser.role=="admin"}">
                                    <li><a>User<span class="fa fa-chevron-down"></span></a>
                                        <ul class="nav child_menu">
                                            <li><g:link action="create" controller="user">Add</g:link>
                                            </li>
                                            <li><g:link action="list" controller="user">List</g:link>
                                            </li>

                                        </ul>

                                    </li>
                                    </g:if>
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


                        </ul>
                    </div>

                </div>
                <!-- /sidebar menu -->

                <!-- /menu footer buttons -->
                <div class="sidebar-footer hidden-small">

                <g:link data-toggle="tooltip" data-placement="top" title="Logout" action="logout" controller="logout">
                        <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
                    </g:link>
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
    <g:if test="${userInstance?.profileImageName!=null}">
                                <img src="${createLink(controller: 'user', action:'renderImage',params: [profileImageName:session?.adminUser?.profileImageName])}">
    </g:if>
                                <g:else>
        <img src="${resource(dir: 'images', file: 'blank.png')}">
                                </g:else>
                                    ${session?.adminUser.firstName+" "+session?.adminUser.lastName}
                                <span class=" fa fa-angle-down"></span>
                            </a>
                            <ul class="dropdown-menu dropdown-usermenu pull-right">
                                <li><g:link action="profile" controller="home"> Profile</g:link></li>
                                <li><g:link action="changePassword" controller="home"> Change Password</g:link></li>
                                <li><g:link action="logout" controller="logout"><i class="fa fa-sign-out pull-right"></i> Log Out</g:link></li>
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
</g:if>
<g:else>
    <g:layoutBody></g:layoutBody>
</g:else>

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