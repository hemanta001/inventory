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
                            <th>stock-in</th>
                            <th>Total Wt</th>
                            <th>stock-out</th>
                            <th>Total Wt</th>
                            <th>Rem Stock</th>
                            <th>Total Wt</th>

                        </tr>
                        </thead>
                        <tbody>
                        <%
def totalStockIn=0,totalStockInWeight=0,totalStockOut=0,totalStockOutWeight=0,totalRemainingStock=0,totalRemainingStockWeight=0;




%>
                        <g:each in="${stockRemainingQuantity}" var="list" status="i">
                            <%
                                totalStockIn+=list.totalStockInNumber
                                totalStockInWeight+=(list.totalStockInNumber*list.weight)
                                totalStockOut+=(list.totalStockOutNumber)
                                totalStockOutWeight+=(list.totalStockOutNumber*list.weight)
                                totalRemainingStock+= (list.totalStockInNumber-list.totalStockOutNumber)
                                totalRemainingStockWeight+=((list.totalStockInNumber-list.totalStockOutNumber)*list.weight)
%>
                            <tr>
                                <td>${i+1}</td>
                                <td>${list.weight+" "+list.unit.unitName}</td>
                                <td>${list.totalStockInNumber}</td>
                                <td>${list.totalStockInNumber*list.weight+" "+list.unit.unitName}</td>
                                <td>${list.totalStockOutNumber}</td>
                                <td>${list.totalStockOutNumber*list.weight+" "+list.unit.unitName}</td>
                                <td>${list.totalStockInNumber-list.totalStockOutNumber}</td>
                                <td>${(list.totalStockInNumber-list.totalStockOutNumber)*list.weight+" "+list.unit.unitName}</td>

                            </tr>
                        </g:each>
                        <tr>
                            <td>Total</td>
                            <td></td>
                            <td>${totalStockIn}</td>
                            <td>${totalStockInWeight}</td>
                            <td>${totalStockOut}</td>
                            <td>${totalStockOutWeight}</td>
                            <td>${totalRemainingStock}</td>
                            <td>${totalRemainingStockWeight}</td>

                        </tr>
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