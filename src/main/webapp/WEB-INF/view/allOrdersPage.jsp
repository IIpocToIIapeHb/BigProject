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

    <main class="all-orders-items">

         <section class="intro-all-orders">

              <div class="all-orders-captain">
                <div class="all-orders-id-captain"><fmt:message key="all.orders.id-number"/></div>
                <div class="all-orders-surname-captain"><fmt:message key="all.orders.surname"/></div>
                <div class="all-orders-name-captain"><fmt:message key="all.orders.name"/></div>
                <div class="all-orders-birth-captain"><fmt:message key="all.orders.birth"/></div>
                <div class="all-orders-date-captain"><fmt:message key="all.orders.date"/></div>
              </div>

              <div class= "all-orders-cards">
                     <c:forEach var="order" items="${ordersInfo}">

                            <div class= "all-orders-card">
                             <div class= "all-orders-number-wrapper">
                             <span class= "all-orders-item-number">${order.getId()}</span>
                              </div>

                                <div class= "all-orders-surname-wrapper">
                                 <span class= "all-orders-item">${order.userSurname}</span>
                                </div>

                                <div class= "all-orders-name-wrapper">
                                  <span class= "all-orders-item">${order.userName}</span>
                                </div>

                                <div class= "all-orders-birth-wrapper">
                                <span class= "all-orders-item">${order.userBirth}</span>
                                </div>

                                <div class= "all-orders-date-wrapper">
                                  <span class= "all-orders-item">${order.creationDate}</span>
                                </div>

                               <c:if  test="${order.deliveryStatus.equals('performed')}">
                                  <div class= "all-orders-performed-wrapper">
                                  <span class= "all-orders-performed-item"><fmt:message key="all.orders.done"/></span>
                                </div>

                               </c:if>

                                  <c:if  test="${order.deliveryStatus.equals('not_performed')}">
                                    <div class="all-orders-button-wrapper">
                                       <form method = "post" action = "controller?command=performOrderDetails">
                                          <input  type="hidden" name="order-id" value="${order.getId()}">
                                          <button class="details-order-button" type="submit" > <fmt:message key="all.orders.perform.button" /> </button>
                                      </form>
                                    </div>

                               </c:if>
                                   </div>
                     </c:forEach>
                     </div>

        </section>

    </main>


    </body>
    </html>