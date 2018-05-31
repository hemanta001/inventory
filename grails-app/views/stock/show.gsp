<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="layout" content="mainLayout">
</head>

<body>

<div class="col-md-12 col-xs-12">
    <div class="x_panel">
        <div class="x_title">
            <h2>${materialInstance.materialName} <small>${stockInstance.stockType} show</small><small><g:link action="create" controller="stock" params="[stockType:stockInstance.stockType,identityMaterialName:materialInstance.identityMaterialName]" class="btn btn-success btn-xs">Add</g:link></small>
                <small><g:link action="list" controller="stock" params="[stockType:stockInstance.stockType,identityMaterialName:identityMaterialName]" class="btn btn-success btn-xs">List</g:link></small></h2>
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
                    <label class="control-label col-md-3 col-sm-3 col-xs-12">Select Item</label>
                    <div class="col-md-9 col-sm-9 col-xs-12">
                        <g:textField name="itemId" class="form-control" value="${stockInstance?.item?.itemName}" disabled="disabled"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12">Weight</label>
                    <div class="col-md-9 col-sm-9 col-xs-12">
                        <g:textField name="weightId"  class="form-control" value="${stockInstance?.weight?.weightQuantityUnit}" disabled="disabled"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12">Quantity</label>
                    <div class="col-md-9 col-sm-9 col-xs-12">
                        <g:textField name="quantityNumber" class="form-control" placeholder="eg.1,2 etc" value="${stockInstance?.quantityNumber}" disabled="disabled"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12">Date</label>
                    <div class="col-md-9 col-sm-9 col-xs-12">
                        <g:textField name="date" class="form-control" placeholder="eg.2075/04/22" value="${stockInstance?.date}" disabled="disabled"/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-3">
                        <small><g:link action="delete" controller="stock" params="[identityMaterialName:materialInstance.identityMaterialName,stock:stockInstance.id]" class="btn btn-danger btn-xs">Delete</g:link></small>
                        <small><g:link action="edit" controller="stock" params="[identityMaterialName:materialInstance.identityMaterialName,stock:stockInstance.id]" class="btn btn-success btn-xs">Edit</g:link></small>
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>


</body>
</html>