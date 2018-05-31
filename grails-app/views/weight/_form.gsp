<div class="form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12">Weight</label>
    <div class="col-md-9 col-sm-9 col-xs-12">
        <g:textField name="weightQuantity" class="form-control" placeholder="eg.0.5,0.75 etc" value="${weight?.weightQuantity}"/>
    </div>
</div>
<div class="form-group">
<label class="control-label col-md-3 col-sm-3 col-xs-12">Select</label>
<div class="col-md-9 col-sm-9 col-xs-12">
<g:select name="unitId" from="${Unit.findAllByDelFlag(false)}" optionValue="unitName" optionKey="id" class="form-control" value="${weight?.unit?.id}"/>
</div>
</div>