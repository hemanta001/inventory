<!DOCTYPE html>
<html lang="en">
<head>
  <meta name="layout" content="mainLayout">
</head>

<body>

        <!-- page content -->
            <div class="">
                <div class="clearfix"></div>

                <div class="row">




                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <h2>${materialInstance.materialName} <small>${stockType} List</small><small><g:link action="create" controller="stock" params="[stockType:stockType,identityMaterialName:materialInstance.identityMaterialName]" class="btn btn-success btn-xs">Add</g:link></small></h2>
                                <ul class="nav navbar-right panel_toolbox">
                                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                    </li>

                                </ul>
                                <div class="clearfix"></div>
                            </div>

                            <div class="x_content">

                                %{--<p>Add class <code>bulk_action</code> to table for bulk actions options on row select</p>--}%

                                <table id="datatable" class="table table-striped table-bordered">
                                    <thead>
                                    <tr>
                                        <th>SN</th>
                                        <th>Weight</th>
                                        <th>Unit</th>
                                        <th>Item</th>
                                        <th>Quantity No.</th>
                                        <th>Date</th>
                                        <th>Stock Type</th>
<th>Action</th>
                                    </tr>
                                    </thead>
                                <tbody>
                                <g:each in="${stockList}" var="list" status="i">
                                    <tr>
                                        <td>${i+1}</td>
                                        <td>${list.weight.weightQuantity}</td>
                                        <td>${list.weight.unit.unitName}</td>
                                        <td>${list.item.itemName}</td>
                                        <td>${list.quantityNumber}</td>
                                        <td>${list.date}</td>
                                        <td>${list.stockType}</td>

                                        <td><g:link action="show" controller="stock" params="[identityMaterialName:materialInstance.identityMaterialName,stock:list.id]" >View</g:link></td>
                                    </tr>
                                </g:each>
                                    </tbody>
                                </table>


                            </div>
                        </div>
                    </div>
                </div>
            </div>
        <!-- /page content -->

        <!-- footer content -->
        <!-- /footer content -->

<!-- jQuery -->

</body>
</html>