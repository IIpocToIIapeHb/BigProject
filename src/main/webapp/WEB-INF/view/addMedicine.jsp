<%@ page contentType="text.html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib  uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!-- <meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pharmacy</title>
    <link rel="stylesheet" href="static/styles/style.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
</head>
<body>
    <jsp:include page="fragments/header.jsp"/>
    <fmt:setBundle basename="pagecontent"/>

    <main>

       <div class="medicine-card-page-main">
                       <div class="medicine-card-page-main-wrapper">

                           <form method = "post" action = "controller?command">
                                <div class="character-medicine-block">
                               <label class = "medicine-card-label"><fmt:message key="cart.medicine.name"/></label>
                               <input class = "medicine-card-input" type="text" name="medicine-name">
                                </dv>
                                 <div class="character-medicine-block">
                                 <label class = "medicine-card-label"><fmt:message key="cart.medicine.category"/></label>
                                 <input class = "medicine-card-input" type="text" name="medicine-category">
                                 </dv>

                               <input class = "text" type="submit" value="<fmt:message key="index.button.login"/>">
                           </form>
                             <div class = "error-message" style = "color:red"; >
                               ${errorMessage}
                             </div>
                       </div>
              </div>

    </main>

         <script src="static/js/main.js"></script>
    </body>
    </html>