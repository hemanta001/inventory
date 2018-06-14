<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="layout" content="mainLayout">
</head>

<body>

<div class="col-md-12 col-xs-12">
    <div class="x_panel">
        <div class="x_title">
            <h2>User show<g:link action="create" controller="user"  class="btn btn-success btn-xs">Add</g:link>
                <g:link action="list" controller="user" class="btn btn-success btn-xs">List</g:link></h2>
            <ul class="nav navbar-right panel_toolbox">
                <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                </li>


            </ul>
            <div class="clearfix"></div>
        </div>
        <div class="col-sm-12" style="color: #26B99A">${flash.message}</div>
        <div class="x_content">
            <br />
            <form class="form-horizontal form-label-left">
                <div class="form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12">First Name</label>
                    <div class="col-md-9 col-sm-9 col-xs-12">
                        <g:textField name="firstName" class="form-control" value="${userInstance?.firstName}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12">Last Name</label>
                    <div class="col-md-9 col-sm-9 col-xs-12">
                        <g:textField name="lastName" class="form-control" value="${userInstance?.lastName}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12">Email</label>
                    <div class="col-md-9 col-sm-9 col-xs-12">
                        <g:textField name="email" class="form-control" value="${userInstance?.email}" disabled="disabled"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12">username</label>
                    <div class="col-md-9 col-sm-9 col-xs-12">
                        <g:textField name="userName" class="form-control" value="${userInstance?.userName}" disabled="disabled"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12">Mobile No</label>
                    <div class="col-md-9 col-sm-9 col-xs-12">
                        <g:textField name="contactNumber" class="form-control" value="${userInstance?.contactNumber}"/>
                    </div>
                </div>
                <g:if test="${userInstance?.profileImageName}">
                <div class="form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12">Profile Image</label>
                    <div class="col-md-9 col-sm-9 col-xs-12">
                        <img src="${createLink(controller: 'user', action:'renderImage',params: [profileImageName:userInstance?.profileImageName])}" width="100" class="img-responsive">

                    </div>
                </div>
                </g:if>
                <div class="form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12">Role</label>
                    <div class="col-md-9 col-sm-9 col-xs-12">
                        <g:select name="role" from="${['admin': 'admin', 'member': 'member']}" optionKey="key" optionValue="value"  class="form-control" value="${userInstance?.role}"/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-3">
                        <g:link action="delete" controller="user" params="[userName:userInstance?.userName]" class="btn btn-danger btn-xs">Delete</g:link>
                        <g:link action="edit" controller="user" params="[userName:userInstance?.userName]" class="btn btn-success btn-xs">Edit</g:link>
                    </div>
                </div>


            </form>
        </div>
    </div>
</div>


</body>
</html>