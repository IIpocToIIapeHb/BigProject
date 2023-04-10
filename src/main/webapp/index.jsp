<%@ page pageEncoding="UTF-8" contentType="text.html; charset=UTF-8" isELIgnored="false" %>
<%@taglib  uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<%@taglib  uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<html lang="en">
  <head>
      <meta charset="UTF-8">
      <meta name = "viewport" content="width-device-width, initial-scale-1.0">
      <title>Pharmacy</title>
      <link rel="stylesheet" href="static/styles/style.css">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
  </head>

  <body>
      <fmt:setLocale value="en" scope="session"/>
      <fmt:setBundle basename="pagecontent" />

      <header  class="hello-page-header">
          <div class="header-bottom-logos">
              <a href="/" class="header-bottom-logos-icon">
                  <img src="./static/img/icons-health.png" alt="pharmacy">
              </a>

               <a href="/" class="header-bottom-logos-text">
                      Dr.Health
               </a>

          </div>
      </header>


     <main>
             <div class="hello-page-main">
                  <div class="hello-page-main-wrapper">
                      <img src="./static/img/icon-user.png" alt="" class="hello-page-main-icon">
                      <form method = "post" action = "controller?command=login">

                          <label class = "form-labels" for="login"><fmt:message key="index.login"/></label>
                          <input class = "text" type="text" name="login" placeholder= "<fmt:message key="index.login.placeholder"/>">

                          <label class = "form-labels" for="password"><fmt:message key="index.password"/></label>
                          <input class = "text" type="password" name="password" placeholder="<fmt:message key="index.password.placeholder"/>">

                          <input class = "text" type="submit" value="<fmt:message key="index.button.login"/>">
                      </form>
                        <div class = "error-message" style = "color:red"; >
                          ${errorMessage}
                        </div>
                  </div>
         </div>
     </main>
  </body>
</html>
