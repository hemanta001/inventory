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
                    <h2>${identityMaterialName}-${identityItemName} Stock remaining</h2>
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
                            <th>weight</th>
                            <th>total stock-in</th>
                            <th>total stock-out</th>
                            <th>Remaining Quantity</th>
                        </tr>
                        </thead>
                        <tbody>
                        <g:each in="${stockRemainingQuantity}" var="list" status="i">
                            <tr>
                                <td>${i+1}</td>
                                <td>${list.weight+" "+list.unit.unitName}</td>
                                <td>${list.totalStockInNumber}</td>
                                <td>${list.totalStockOutNumber}</td>
                                <td>${list.totalStockInNumber-list.totalStockOutNumber}</td>
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