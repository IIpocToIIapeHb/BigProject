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

       <div class="order-details-main">

         <div class="order-details-info">

            <div class="order-details-info-number-block">
                <div class="order-details-info-number-label">
                <span class= "order-details-info-number-text"><fmt:message key="order.details.number"/></span>
                </div>
                <div class="order-details-info-number-wrapper">
                <span class= "order-details-info-number">${orderInfo.getId()}</span>
                </div>
            </div>




            <div class="order-details-info-surname-block">
               <div class="order-details-info-surname-label">
               <span class= "order-details-info-surname-text"><fmt:message key="order.details.surname"/></span>
               </div>
               <div class="order-details-info-surname-wrapper">
               <span class= "order-details-info-surname">${orderInfo.userSurname}</span>
               </div>

            </div>



            <div class="order-details-info-name-block">
               <div class="order-details-info-name-label">
               <span class= "order-details-info-name-text"><fmt:message key="order.details.name"/></span>
               </div>
               <div class="order-details-info-name-wrapper">
               <span class= "order-details-info-name">${orderInfo.userName}</span>
               </div>
            </div>

            <div class="order-details-info-birth-block">
               <div class="order-details-info-birth-label">
               <span class= "order-details-info-birth-text"><fmt:message key="order.details.birth"/></span>
               </div>
               <div class="order-details-info-birth-wrapper">
               <span class= "order-details-info-birth">${orderInfo.userBirth}</span>
               </div>
            </div>

            <div class="order-details-info-address-block">
               <div class="order-details-info-address-label">
               <span class= "order-details-info-address-text"><fmt:message key="order.details.address"/></span>
               </div>
               <div class="order-details-info-address-wrapper">
               <span class= "order-details-info-address">${orderInfo.userAddress}</span>
               </div>
            </div>

            <div class="order-details-info-creation-date-block">
               <div class="order-details-info-creation-date-label">
               <span class= "order-details-info-creation-date-text"><fmt:message key="order.details.creation.date"/></span>
               </div>
               <div class="order-details-info-creation-date-wrapper">
               <span class= "order-details-info-creation-date">${orderInfo.creationDate}</span>
               </div>
            </div>



            <hr>

         </div>

<section class="intro-order-details">

              <div class="position-captain">
                <div class="position-captain-name">Name</div>
                <div class="position-captain-item">Price</div>
                <div class="position-captain-item">Number</div>
                <div class="position-captain-item">Recipe</div>
                <div class="position-captain-item">Recipe status</div>
                <div class="position-captain-item">Total</div>
              </div>

              <div class= "position-cards">
                     <c:forEach var="position" items="${positions}">

                            <div class= "position-card">

                                <div class= "position-card-pic">
                                      <img src="${position.getFullMedicinePath()}" alt="" class = "position-card-thumb">
                                </div>

                                <div class= "position-medicine-name-wrapper">
                                  <span class= "position-medicine-name"><c:out value="${position.getMedicineName()}" /></span>
                                </div>

                                <div class= "position-medicine-price-wrapper">
                                  <span class= "position-medicine-price"><c:out value="${position.getMedicinePrice()}"/></span>
                                </div>

                                <div class= "position-medicine-required-amount-wrapper">
                                    <span class= "position-medicine-required-amount"><c:out value="${position.getRequiredAmount()}"/></span>
                                </div>

                                <div class= "position-medicine-recipe-status-wrapper">
                                    <span class= "position-medicine-recipe-status "><c:out value="${position.getStringMedicineWithRecipe()}"/></span>
                                </div>




                                <div class= "position-recipe-status-wrapper">

                                   <span class= "position-recipe-status">
                                    <c:if  test="${position.getRecipeStatus()!=null}">


                                   <fmt:message key="${position.getRecipeStatus()}"/>
                                     </c:if>
                                   </span>
                                </div>





                             <div class= "position-medicine-total-price-wrapper">
                                 <span class= "position-medicine-total-price"><c:out value="${position.getTotal()}"/></span>
                              </div>


                            </div>

                     </c:forEach>
                     </div>

                    <div class= "total-price">
                     Total price:
                     ${totalPrice}
                      rub
                    </div>



        </section>

         <div class="perform-order-button-wrapper">

                             <form method = "post" action = "controller?command=performOrder">
                             <input class = "" type="hidden" name="order-id" value="${orderInfo.getId()}">
                             <button class="perform-order-button" type="submit" >
                                 Perform
                             </button>

                             </form>
                            </div>

       </div>
    </main>

         <script src="static/js/main.js"></script>
    </body>
    </html>