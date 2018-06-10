<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="layout" content="mainLayout">
</head>

<body>

<div class="col-md-12 col-xs-12">
    <div class="x_panel">
        <div class="x_title">
            <h2>Material show<g:link action="create" controller="material"  class="btn btn-success btn-xs">Add</g:link>
                <g:link action="list" controller="material" class="btn btn-success btn-xs">List</g:link></h2>
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
                    <label class="control-label col-md-3 col-sm-3 col-xs-12">Material</label>
                    <div class="col-md-9 col-sm-9 col-xs-12">
                        <g:textField name="materialName" class="form-control" disabled="disabled" placeholder="eg.gold,silver etc" value="${material?.materialName}"/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-3">
                        <g:link action="delete" controller="material" params="[identityMaterialName:material?.identityMaterialName]" class="btn btn-danger btn-xs">Delete</g:link>
                        <g:link action="edit" controller="material" params="[identityMaterialName:material?.identityMaterialName]" class="btn btn-success btn-xs">Edit</g:link>
                    </div>
                </div>


            </form>
        </div>
    </div>
</div>


</body>
</html>