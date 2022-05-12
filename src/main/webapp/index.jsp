<%@ page contentType="text.html; charset=UTF-8" isELIgnored="false" %>
<html>
    <head>
    <link rel = "stylesheet" href = "static/styles/style.css"/>
    </head>
    <body>
        <div class = "container">
            <form method = "post" action = "controller?command=login">
                <label class = "login-row" for = "login">login</label>
                <input  class = "login-row" type = "text" name = "login"/>
                <label  class = "login-row" for = "password">password</label>
                <input  class = "login-row" type = "password" name = "password"/>
                <label  class = "login-row" for = "submit">go</label>
                <input  class = "login-row" type = "submit"/>
            </form>
            <div class = "error-message">
            ${errorMessage}
            </div>
        </div>
    </body>
</html>
