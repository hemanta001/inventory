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
                                <h2>Weight <small>List</small><small><g:link action="create" controller="weight" class="btn btn-success btn-xs">Add</g:link></small></h2>
                                <ul class="nav navbar-right panel_toolbox">
                                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                    </li>

                                </ul>
                                <div class="clearfix"></div>
                            </div>

                            <div class="x_content">

                                %{--<p>Add class <code>bulk_action</code> to table for bulk actions options on row select</p>--}%

                                <div class="table-responsive">
                                    <table class="table table-striped jambo_table">
                                        <thead>
                                        <tr class="headings">
                                            <th>
                                                S.N.
                                            </th>
                                            <th class="column-title">weight</th>

                                            <th class="column-title no-link last"><span class="nobr">Action</span>
                                            </th>

                                        </tr>
                                        </thead>

                                        <tbody>
                                        <g:each in="${weightList}" var="list" status="i">
                                        <tr class="even pointer">
                                            <td class=" ">${i+1}</td>
                                            <td class=" ">${list.weightQuantityUnit}</td>

                                            <td class=" last"><g:link action="show" controller="weight" params="[identityWeightQuantityUnit:list.identityWeightQuantityUnit]">View</g:link>
                                            </td>
                                        </tr>
                                        </g:each>
                                        </tbody>
                                    </table>
                                </div>


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