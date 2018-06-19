<!DOCTYPE html>
<html lang="en">
<head>
   <meta name="layout" content="mainLayout">
</head>

<body class="login">
<div>
    <a class="hiddenanchor" id="signup"></a>
    <a class="hiddenanchor" id="signin"></a>

    <div class="login_wrapper">
        <div class="animate form login_form">
            <section class="login_content">
                <form method="post" action="/login/login">
                    <h1>Login Form</h1>
                    <g:if test="${flash.messageSuccess}">
                        <div class="alert alert-success fade in">

                            <strong>!</strong>${flash.messageSuccess}

                        </div>
                    </g:if>
                    <g:if test="${flash.messageError}">

                        <div class="alert alert-danger fade in">


                            <strong>Warning!</strong>${flash.messageError}

                        </div>
                    </g:if>

                    <div>
                        <input name="emailOrUserName" type="text" class="form-control" placeholder="Username or email" required="" />
                    </div>
                    <div>
                        <input  name="password" type="password" class="form-control" placeholder="Password" required="" />
                    </div>
                    <div>
                        <button type="submit" class="btn btn-default submit">Log in</button>
                    </div>

                    <div class="clearfix"></div>

                </form>
            </section>
        </div>

    </div>
</div>
</body>
</html>