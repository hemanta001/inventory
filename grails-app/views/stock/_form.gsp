
<div class="form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12">Select Item</label>
    <div class="col-md-9 col-sm-9 col-xs-12">
        <g:select name="itemId" from="${Item.findAllByDelFlag(false)}" optionValue="itemName" optionKey="id" class="form-control" value="${stockInstance?.item?.id}"/>
    </div>
</div>
<div class="form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12">Select Weight</label>
    <div class="col-md-9 col-sm-9 col-xs-12">
        <g:select name="weightId" from="${Weight.findAllByDelFlag(false)}" optionValue="weightQuantityUnit" optionKey="id" class="form-control" value="${stockInstance?.weight?.id}"/>
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
    <div class="col-md-9 col-sm-9 col-xs-12">
        <g:textField name="date" class="form-control" placeholder="eg.2075/04/22" value="${stockInstance?.date}"/>
    </div>
</div>
