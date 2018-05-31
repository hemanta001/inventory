<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="layout" content="mainLayout">
</head>

<body>

<div class="col-md-12 col-xs-12">
    <div class="x_panel">
        <div class="x_title">
            <h2>Weight <small>show</small><small><g:link action="create" controller="weight"  class="btn btn-success btn-xs">Add</g:link></small>
                <small><g:link action="list" controller="weight" class="btn btn-success btn-xs">List</g:link></small></h2>
            <ul class="nav navbar-right panel_toolbox">
                <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                </li>


            </ul>
            <div class="clearfix"></div>
        </div>
        <div class="x_content">
            <br />
            <form class="form-horizontal form-label-left">
                <div class="form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12">Weight</label>
                    <div class="col-md-9 col-sm-9 col-xs-12">
                        <g:textField name="weightQuantity" class="form-control" placeholder="eg.0.5,0.75 etc" value="${weight?.weightQuantity}" disabled="disabled"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12">Unit</label>
                    <div class="col-md-9 col-sm-9 col-xs-12">
                        <g:textField name="unitId" class="form-control" placeholder="eg.mg,gm etc" value="${weight?.unit?.unitName}" disabled="disabled"/>

                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-3">
                        <small><g:link action="delete" controller="weight" params="[identityWeightQuantityUnit:weight?.identityWeightQuantityUnit]" class="btn btn-danger btn-xs">Delete</g:link></small>
                        <small><g:link action="edit" controller="weight" params="[identityWeightQuantityUnit:weight?.identityWeightQuantityUnit]" class="btn btn-success btn-xs">Edit</g:link></small>
                    </div>
                </div>


            </form>
        </div>
    </div>
</div>


</body>
</html>