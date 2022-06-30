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

    <main class="cart-items">

         <section class="intro-cart">

              <div class="position-captain">
                <div class="position-captain-name"><fmt:message key="cart.medicine_name"/></div>
                <div class="position-captain-item">Price</div>
                <div class="position-captain-item">Number</div>
                <div class="position-captain-item">Recipe</div>
                <div class="position-captain-item"></div>
                <div class="position-captain-item">Recipe status</div>
                <div class="position-captain-item">Recipe activity</div>
                <div class="position-captain-item">Recipe number</div>
                <div class="position-captain-item">Total</div>
              </div>

              <div class= "position-cards">
                     <c:forEach var="prescription" items="${prescriptionsInfo}">

                            <div class= "position-card">

                                <div class= "position-medicine-name-wrapper">
                                                                 <span class= "position-medicine-name"><c:out value="${prescription.getUserName()}" /></span>
                                                               </div>

                                <div class= "position-medicine-name-wrapper">
                                  <span class= "position-medicine-name"><c:out value="${prescription.getUserSurname()}" /></span>
                                </div>

                                <div class= "position-medicine-price-wrapper">
                                  <span class= "position-medicine-price"><c:out value="${prescription.getUserBirth()}"/></span>
                                </div>

                                <div class= "position-medicine-required-amount-wrapper">
                                    <span class= "position-medicine-required-amount"><c:out value="${prescription.getMedicineName()}"/></span>
                                </div>





                     </c:forEach>
                     </div>


        </section>

    </main>

         <script src="static/js/main.js"></script>
    </body>
    </html>