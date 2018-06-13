
<div class="form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12">Select Item</label>
    <div class="col-md-9 col-sm-9 col-xs-12">
        <g:select name="itemId" from="${Item.findAllByDelFlag(false)}" optionValue="itemName" optionKey="id" class="form-control" value="${stockInstance?.item?.itemName}"/>
    </div>
</div>
<div class="form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12">Weight</label>
    <div class="col-md-6 col-sm-6 col-xs-9">
        <g:textField name="weight" class="form-control" placeholder="eg.1,2 etc" value="${stockInstance?.weight}"/>

    </div>
<div class="col-md-3 col-sm-3 col-xs-3">

    <g:select name="unitId" from="${Unit.findAllByDelFlag(false)}" optionValue="unitName" optionKey="id" class="form-control" value="${stockInstance?.unit?.unitName}"/>
</div>
</div>
<div class="form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12">Quantity</label>
    <div class="col-md-9 col-sm-9 col-xs-12">
        <g:textField name="quantityNumber" class="form-control" placeholder="eg.1,2 etc" value="${stockInstance?.quantityNumber}"/>
    </div>
</div>
<div class="form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12">Date</label>
    <div class="col-md-3 col-sm-4 col-xs-4">
        <input name="date" type="text" placeholder="eg.2075-04-22" id="nepaliDate3" class="nepali-calendar form-control" value="${stockInstance?.date}"/>
    </div>
</div>
<script>
    $(document).ready(function() {
        $('#nepaliDate3').nepaliDatePicker({
            onFocus: false,
            npdMonth: true,
            npdYear: true,
            ndpTriggerButton: true,
            ndpTriggerButtonText: 'Date',
            ndpTriggerButtonClass: 'btn btn-primary btn-sm'
        });
    });
</script>
<style>
    .ndp-click-trigger{
        margin-left: 210px;
        margin-top: -59px;
    }

</style>
<script>
    $(document).ready(function() {
        $('#stock_form').bootstrapValidator({
            // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                itemId: {
                    validators: {
                        notEmpty: {
                            message: 'Please choose item'
                        }

                    }
                },
                weight: {
                    validators: {
                        notEmpty: {
                            message: 'Please supply weight'
                        },
                        remote: {
                            url: "${createLink(controller:'material', action:'checkFloat')}",
                            // Send { username: 'its value', email: 'its value' } to the back-end
                            data: function(validator, $field, value) {
                                return {
                                    weight: validator.getFieldElements('weight').val()

                                };

                            },
                            message: 'invalid weight',
                            type: 'POST'
                        }


                    }
                },
                unitId: {
                    validators: {
                        notEmpty: {
                            message: 'Please supply unit'
                        }

                    }
                },
                quantityNumber: {
                    validators: {
                        notEmpty: {
                            message: 'Please supply number of quantity'
                        },
                        remote: {
                            url: "${createLink(controller:'material', action:'checkInteger')}",
                            // Send { username: 'its value', email: 'its value' } to the back-end
                            data: function(validator, $field, value) {
                                return {
                                    quantityNumber: validator.getFieldElements('quantityNumber').val()

                                };

                            },
                            message: 'invalid quanity number',
                            type: 'POST'
                        }

                    }
                },
                date: {
                    validators: {
                        notEmpty: {
                            message: 'Please supply date'
                        },
                        remote: {
                            url: "${createLink(controller:'material', action:'checkDate')}",
                            // Send { username: 'its value', email: 'its value' } to the back-end
                            data: function(validator, $field, value) {
                                return {
                                    date: validator.getFieldElements('date').val()

                                };

                            },
                            message: 'invalid date format',
                            type: 'POST'
                        }


                    }
                }


            }
        })
            .on('success.form.bv', function(e) {
                $('#success_message').slideDown({ opacity: "show" }, "slow") // Do something ...
                $('#item_form').data('bootstrapValidator').resetForm();

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
