<!DOCTYPE html>
<html lang="en">
<head>
   <meta name="layout" content="mainLayout">
</head>

<body>

<div class="col-md-12 col-xs-12">
    <div class="x_panel">
        <div class="x_title">
            <h2>${materialInstance.materialName}${stockType} add</h2>
            <ul class="nav navbar-right panel_toolbox">
                <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                </li>
            </ul>
            <div class="clearfix"></div>
        </div>
        <div class="x_content">
            <br />
            <form action="/stock/save" class="form-horizontal form-label-left" id="stock_form">
<g:hiddenField name="identityMaterialName" value="${materialInstance.identityMaterialName}"></g:hiddenField>
                <g:hiddenField name="stockType" value="${stockType}"></g:hiddenField>

                <div class="container">
                <g:render template="form"></g:render>
                </div>
                <div class="form-group">
                    <div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-3">
                        <button type="reset" class="btn btn-primary">Reset</button>
                        <g:submitButton name="save" value="Submit" class="btn btn-success" onclick="valueAdd();">Submit</g:submitButton>
                    </div>
                </div>
                <script>
                    function valueAdd() {

                        document.getElementById("nepaliDateReal").value=document.getElementById("nepaliDate3").value;
                    }
                </script>

            %{--<div class="form-group">--}%
                    %{--<label class="control-label col-md-3 col-sm-3 col-xs-12">Disabled Input </label>--}%
                    %{--<div class="col-md-9 col-sm-9 col-xs-12">--}%
                        %{--<input type="text" class="form-control" disabled="disabled" placeholder="Disabled Input">--}%
                    %{--</div>--}%
                %{--</div>--}%
                %{--<div class="form-group">--}%
                    %{--<label class="control-label col-md-3 col-sm-3 col-xs-12">Read-Only Input</label>--}%
                    %{--<div class="col-md-9 col-sm-9 col-xs-12">--}%
                        %{--<input type="text" class="form-control" readonly="readonly" placeholder="Read-Only Input">--}%
                    %{--</div>--}%
                %{--</div>--}%
                %{--<div class="form-group">--}%
                    %{--<label class="control-label col-md-3 col-sm-3 col-xs-12">Date Of Birth <span class="required">*</span>--}%
                    %{--</label>--}%
                    %{--<div class="col-md-9 col-sm-9 col-xs-12">--}%
                        %{--<textarea class="form-control" rows="3" placeholder="Date Of Birth"></textarea>--}%
                    %{--</div>--}%
                %{--</div>--}%
                %{--<div class="form-group">--}%
                    %{--<label class="control-label col-md-3 col-sm-3 col-xs-12">Password</label>--}%
                    %{--<div class="col-md-9 col-sm-9 col-xs-12">--}%
                        %{--<input type="password" class="form-control" value="passwordonetwo">--}%
                    %{--</div>--}%
                %{--</div>--}%
                %{--<div class="form-group">--}%
                    %{--<label class="control-label col-md-3 col-sm-3 col-xs-12">AutoComplete</label>--}%
                    %{--<div class="col-md-9 col-sm-9 col-xs-12">--}%
                        %{--<input type="text" name="country" id="autocomplete-custom-append" class="form-control col-md-10"/>--}%
                    %{--</div>--}%
                %{--</div>--}%
                %{--<div class="form-group">--}%
                    %{--<label class="control-label col-md-3 col-sm-3 col-xs-12">Select</label>--}%
                    %{--<div class="col-md-9 col-sm-9 col-xs-12">--}%
                        %{--<select class="form-control">--}%
                            %{--<option>Choose option</option>--}%
                            %{--<option>Option one</option>--}%
                            %{--<option>Option two</option>--}%
                            %{--<option>Option three</option>--}%
                            %{--<option>Option four</option>--}%
                        %{--</select>--}%
                    %{--</div>--}%
                %{--</div>--}%
                %{--<div class="form-group">--}%
                    %{--<label class="control-label col-md-3 col-sm-3 col-xs-12">Select Custom</label>--}%
                    %{--<div class="col-md-9 col-sm-9 col-xs-12">--}%
                        %{--<select class="select2_single form-control" tabindex="-1">--}%
                            %{--<option></option>--}%
                            %{--<option value="AK">Alaska</option>--}%
                            %{--<option value="HI">Hawaii</option>--}%
                            %{--<option value="CA">California</option>--}%
                            %{--<option value="NV">Nevada</option>--}%
                            %{--<option value="OR">Oregon</option>--}%
                            %{--<option value="WA">Washington</option>--}%
                            %{--<option value="AZ">Arizona</option>--}%
                            %{--<option value="CO">Colorado</option>--}%
                            %{--<option value="ID">Idaho</option>--}%
                            %{--<option value="MT">Montana</option>--}%
                            %{--<option value="NE">Nebraska</option>--}%
                            %{--<option value="NM">New Mexico</option>--}%
                            %{--<option value="ND">North Dakota</option>--}%
                            %{--<option value="UT">Utah</option>--}%
                            %{--<option value="WY">Wyoming</option>--}%
                            %{--<option value="AR">Arkansas</option>--}%
                            %{--<option value="IL">Illinois</option>--}%
                            %{--<option value="IA">Iowa</option>--}%
                            %{--<option value="KS">Kansas</option>--}%
                            %{--<option value="KY">Kentucky</option>--}%
                            %{--<option value="LA">Louisiana</option>--}%
                            %{--<option value="MN">Minnesota</option>--}%
                            %{--<option value="MS">Mississippi</option>--}%
                            %{--<option value="MO">Missouri</option>--}%
                            %{--<option value="OK">Oklahoma</option>--}%
                            %{--<option value="SD">South Dakota</option>--}%
                            %{--<option value="TX">Texas</option>--}%
                        %{--</select>--}%
                    %{--</div>--}%
                %{--</div>--}%
                %{--<div class="form-group">--}%
                    %{--<label class="control-label col-md-3 col-sm-3 col-xs-12">Select Grouped</label>--}%
                    %{--<div class="col-md-9 col-sm-9 col-xs-12">--}%
                        %{--<select class="select2_group form-control">--}%
                            %{--<optgroup label="Alaskan/Hawaiian Time Zone">--}%
                                %{--<option value="AK">Alaska</option>--}%
                                %{--<option value="HI">Hawaii</option>--}%
                            %{--</optgroup>--}%
                            %{--<optgroup label="Pacific Time Zone">--}%
                                %{--<option value="CA">California</option>--}%
                                %{--<option value="NV">Nevada</option>--}%
                                %{--<option value="OR">Oregon</option>--}%
                                %{--<option value="WA">Washington</option>--}%
                            %{--</optgroup>--}%
                            %{--<optgroup label="Mountain Time Zone">--}%
                                %{--<option value="AZ">Arizona</option>--}%
                                %{--<option value="CO">Colorado</option>--}%
                                %{--<option value="ID">Idaho</option>--}%
                                %{--<option value="MT">Montana</option>--}%
                                %{--<option value="NE">Nebraska</option>--}%
                                %{--<option value="NM">New Mexico</option>--}%
                                %{--<option value="ND">North Dakota</option>--}%
                                %{--<option value="UT">Utah</option>--}%
                                %{--<option value="WY">Wyoming</option>--}%
                            %{--</optgroup>--}%
                            %{--<optgroup label="Central Time Zone">--}%
                                %{--<option value="AL">Alabama</option>--}%
                                %{--<option value="AR">Arkansas</option>--}%
                                %{--<option value="IL">Illinois</option>--}%
                                %{--<option value="IA">Iowa</option>--}%
                                %{--<option value="KS">Kansas</option>--}%
                                %{--<option value="KY">Kentucky</option>--}%
                                %{--<option value="LA">Louisiana</option>--}%
                                %{--<option value="MN">Minnesota</option>--}%
                                %{--<option value="MS">Mississippi</option>--}%
                                %{--<option value="MO">Missouri</option>--}%
                                %{--<option value="OK">Oklahoma</option>--}%
                                %{--<option value="SD">South Dakota</option>--}%
                                %{--<option value="TX">Texas</option>--}%
                                %{--<option value="TN">Tennessee</option>--}%
                                %{--<option value="WI">Wisconsin</option>--}%
                            %{--</optgroup>--}%
                            %{--<optgroup label="Eastern Time Zone">--}%
                                %{--<option value="CT">Connecticut</option>--}%
                                %{--<option value="DE">Delaware</option>--}%
                                %{--<option value="FL">Florida</option>--}%
                                %{--<option value="GA">Georgia</option>--}%
                                %{--<option value="IN">Indiana</option>--}%
                                %{--<option value="ME">Maine</option>--}%
                                %{--<option value="MD">Maryland</option>--}%
                                %{--<option value="MA">Massachusetts</option>--}%
                                %{--<option value="MI">Michigan</option>--}%
                                %{--<option value="NH">New Hampshire</option>--}%
                                %{--<option value="NJ">New Jersey</option>--}%
                                %{--<option value="NY">New York</option>--}%
                                %{--<option value="NC">North Carolina</option>--}%
                                %{--<option value="OH">Ohio</option>--}%
                                %{--<option value="PA">Pennsylvania</option>--}%
                                %{--<option value="RI">Rhode Island</option>--}%
                                %{--<option value="SC">South Carolina</option>--}%
                                %{--<option value="VT">Vermont</option>--}%
                                %{--<option value="VA">Virginia</option>--}%
                                %{--<option value="WV">West Virginia</option>--}%
                            %{--</optgroup>--}%
                        %{--</select>--}%
                    %{--</div>--}%
                %{--</div>--}%
                %{--<div class="form-group">--}%
                    %{--<label class="control-label col-md-3 col-sm-3 col-xs-12">Select Multiple</label>--}%
                    %{--<div class="col-md-9 col-sm-9 col-xs-12">--}%
                        %{--<select class="select2_multiple form-control" multiple="multiple">--}%
                            %{--<option>Choose option</option>--}%
                            %{--<option>Option one</option>--}%
                            %{--<option>Option two</option>--}%
                            %{--<option>Option three</option>--}%
                            %{--<option>Option four</option>--}%
                            %{--<option>Option five</option>--}%
                            %{--<option>Option six</option>--}%
                        %{--</select>--}%
                    %{--</div>--}%
                %{--</div>--}%

                %{--<div class="control-group">--}%
                    %{--<label class="control-label col-md-3 col-sm-3 col-xs-12">Input Tags</label>--}%
                    %{--<div class="col-md-9 col-sm-9 col-xs-12">--}%
                        %{--<input id="tags_1" type="text" class="tags form-control" value="social, adverts, sales" />--}%
                        %{--<div id="suggestions-container" style="position: relative; float: left; width: 250px; margin: 10px;"></div>--}%
                    %{--</div>--}%
                %{--</div>--}%
                %{--<div class="form-group">--}%
                    %{--<label class="col-md-3 col-sm-3 col-xs-12 control-label">Checkboxes and radios--}%
                        %{--<br>--}%
                        %{--<small class="text-navy">Normal Bootstrap elements</small>--}%
                    %{--</label>--}%

                    %{--<div class="col-md-9 col-sm-9 col-xs-12">--}%
                        %{--<div class="checkbox">--}%
                            %{--<label>--}%
                                %{--<input type="checkbox" value=""> Option one. select more than one options--}%
                            %{--</label>--}%
                        %{--</div>--}%
                        %{--<div class="checkbox">--}%
                            %{--<label>--}%
                                %{--<input type="checkbox" value=""> Option two. select more than one options--}%
                            %{--</label>--}%
                        %{--</div>--}%
                        %{--<div class="radio">--}%
                            %{--<label>--}%
                                %{--<input type="radio" checked="" value="option1" id="optionsRadios1" name="optionsRadios"> Option one. only select one option--}%
                            %{--</label>--}%
                        %{--</div>--}%
                        %{--<div class="radio">--}%
                            %{--<label>--}%
                                %{--<input type="radio" value="option2" id="optionsRadios2" name="optionsRadios"> Option two. only select one option--}%
                            %{--</label>--}%
                        %{--</div>--}%
                    %{--</div>--}%
                %{--</div>--}%

                %{--<div class="form-group">--}%
                    %{--<label class="col-md-3 col-sm-3 col-xs-12 control-label">Checkboxes and radios--}%
                        %{--<br>--}%
                        %{--<small class="text-navy">Normal Bootstrap elements</small>--}%
                    %{--</label>--}%

                    %{--<div class="col-md-9 col-sm-9 col-xs-12">--}%
                        %{--<div class="checkbox">--}%
                            %{--<label>--}%
                                %{--<input type="checkbox" class="flat" checked="checked"> Checked--}%
                            %{--</label>--}%
                        %{--</div>--}%
                        %{--<div class="checkbox">--}%
                            %{--<label>--}%
                                %{--<input type="checkbox" class="flat"> Unchecked--}%
                            %{--</label>--}%
                        %{--</div>--}%
                        %{--<div class="checkbox">--}%
                            %{--<label>--}%
                                %{--<input type="checkbox" class="flat" disabled="disabled"> Disabled--}%
                            %{--</label>--}%
                        %{--</div>--}%
                        %{--<div class="checkbox">--}%
                            %{--<label>--}%
                                %{--<input type="checkbox" class="flat" disabled="disabled" checked="checked"> Disabled & checked--}%
                            %{--</label>--}%
                        %{--</div>--}%
                        %{--<div class="radio">--}%
                            %{--<label>--}%
                                %{--<input type="radio" class="flat" checked name="iCheck"> Checked--}%
                            %{--</label>--}%
                        %{--</div>--}%
                        %{--<div class="radio">--}%
                            %{--<label>--}%
                                %{--<input type="radio" class="flat" name="iCheck"> Unchecked--}%
                            %{--</label>--}%
                        %{--</div>--}%
                        %{--<div class="radio">--}%
                            %{--<label>--}%
                                %{--<input type="radio" class="flat" name="iCheck" disabled="disabled"> Disabled--}%
                            %{--</label>--}%
                        %{--</div>--}%
                        %{--<div class="radio">--}%
                            %{--<label>--}%
                                %{--<input type="radio" class="flat" name="iCheck3" disabled="disabled" checked> Disabled & Checked--}%
                            %{--</label>--}%
                        %{--</div>--}%
                    %{--</div>--}%
                %{--</div>--}%
                %{--<div class="form-group">--}%
                    %{--<label class="control-label col-md-3 col-sm-3 col-xs-12">Switch</label>--}%
                    %{--<div class="col-md-9 col-sm-9 col-xs-12">--}%
                        %{--<div class="">--}%
                            %{--<label>--}%
                                %{--<input type="checkbox" class="js-switch" checked /> Checked--}%
                            %{--</label>--}%
                        %{--</div>--}%
                        %{--<div class="">--}%
                            %{--<label>--}%
                                %{--<input type="checkbox" class="js-switch" /> Unchecked--}%
                            %{--</label>--}%
                        %{--</div>--}%
                        %{--<div class="">--}%
                            %{--<label>--}%
                                %{--<input type="checkbox" class="js-switch" disabled="disabled" /> Disabled--}%
                            %{--</label>--}%
                        %{--</div>--}%
                        %{--<div class="">--}%
                            %{--<label>--}%
                                %{--<input type="checkbox" class="js-switch" disabled="disabled" checked="checked" /> Disabled Checked--}%
                            %{--</label>--}%
                        %{--</div>--}%
                    %{--</div>--}%
                %{--</div>--}%


                %{--<div class="ln_solid"></div>--}%

            </form>
        </div>
    </div>
</div>


</body>
</html>