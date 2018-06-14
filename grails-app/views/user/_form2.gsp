<div class="form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12">Password</label>
    <div class="col-md-9 col-sm-9 col-xs-12">
        <g:passwordField name="password" class="form-control"/>
    </div>
</div>
<div class="form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12">Confirm Password</label>
    <div class="col-md-9 col-sm-9 col-xs-12">
        <g:passwordField name="confirmPassword" class="form-control"/>
    </div>
</div>

<div class="form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12">Mobile No</label>
    <div class="col-md-9 col-sm-9 col-xs-12">
        <g:textField name="contactNumber" class="form-control" value="${userInstance?.contactNumber}"/>
    </div>
</div>
<div class="form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12">Profile Image</label>
    <div class="col-md-9 col-sm-9 col-xs-12">
        <input type="file" class="form-control"  name="profileImageName" id="profileImageName"/>

    </div>
</div>
<div class="form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12">Role</label>
    <div class="col-md-9 col-sm-9 col-xs-12">
        <g:select name="role" from="${['admin': 'admin', 'member': 'member']}" optionKey="key" optionValue="value"  class="form-control" value="${userInstance?.role}"/>
    </div>
</div>
