<%--
  Created by IntelliJ IDEA.
  User: nishant
  Date: 5/30/18
  Time: 4:13 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="mainLayout">
    <title></title>
</head>

<body>
<div class="col-md-12 col-xs-12">
    <div class="x_panel">
        <div class="x_title">
            <h2>Unit edit<g:link action="create" controller="unit"  class="btn btn-success btn-xs">Add</g:link><g:link action="list" controller="unit"  class="btn btn-success btn-xs">List</g:link></h2>
            <ul class="nav navbar-right panel_toolbox">
                <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                </li>


            </ul>
            <div class="clearfix"></div>
        </div>
        <div class="x_content">
            <br />
            <form action="/unit/save" class="form-horizontal form-label-left" id="unit_form">
                <g:hiddenField name="identityUnitName" value="${unit?.identityUnitName}"></g:hiddenField>
                <g:render template="form"></g:render>
                <div class="form-group">
                    <div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-3">
                        <g:submitButton name="update" value="Update" class="btn btn-success">Update</g:submitButton>
                    </div>
                </div>
                <script>
                    $(document).ready(function() {
                        $('#unit_form').bootstrapValidator({
                            // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
                            feedbackIcons: {
                                valid: 'glyphicon glyphicon-ok',
                                invalid: 'glyphicon glyphicon-remove',
                                validating: 'glyphicon glyphicon-refresh'
                            },
                            fields: {
                                unitName: {
                                    validators: {
                                        notEmpty: {
                                            message: 'Please supply item'
                                        },
                                        remote: {
                                            url: "${createLink(controller:'unit', action:'checkUnit')}",
                                            // Send { username: 'its value', email: 'its value' } to the back-end
                                            data: function(validator, $field, value) {
                                                return {
                                                    unitName: validator.getFieldElements('unitName').val()

                                                };

                                            },
                                            message: 'The unit is identical to previous one or already in use',
                                            type: 'POST'
                                        }

                                    }
                                }


                            }
                        })
                            .on('success.form.bv', function(e) {
                                $('#success_message').slideDown({ opacity: "show" }, "slow") // Do something ...
                                $('#unit_form').data('bootstrapValidator').resetForm();

                                // Prevent form submission
                                e.preventDefault();

                                // Get the form instance
                                var $form = $(e.target);

                                // Get the BootstrapValidator instance
                                var bv = $form.data('bootstrapValidator');

                                // Use Ajax to submit form data
                                $.post($form.attr('action'), $form.serialize(), function(result) {
                                    console.log(result);
                                }, 'json');
                            });
                    });


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