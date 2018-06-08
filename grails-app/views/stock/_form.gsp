
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
        <input type="text" disabled="disabled" placeholder="eg.2075-04-22" id="nepaliDate3" class="nepali-calendar form-control" value="${stockInstance?.date}"/>
        <g:hiddenField name="date" class="form-control" id="nepaliDateReal"/>
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