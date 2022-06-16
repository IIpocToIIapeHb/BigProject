<%@ page contentType="text.html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <jsp:include page="header.jsp"/>

    <main class="cart-items">

         <section class="intro-cart">

              <div class="position-captain">
                <div class="position-captain-name">Medicine name</div>
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
                     <c:forEach var="position" items="${positions}">

                            <div class= "position-card">

                                <div class= "position-card-pic">
                                      <img src="${position.getMedicinePath()}" alt="" class = "position-card-thumb">
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


				                 <div class= "position-form-wrapper">
                                        <form method = "post" action = "controller?command=getRecipe">
                                        <input class = "medicine-id" type="hidden" name="medicine-id" value="${position.getMedicineId()}">
                                        <input class = "medicine-required-amount" type="hidden" name="medicine-required-amount" value="${position.getRequiredAmount()}">
                                        <input class = "medicine-recipe-status" type="hidden" name="medicine-recipe-status" value="${position.getRecipeStatus()}">
                                        <input class = "medicine-recipe-id" type="hidden" name="medicine-recipe-id" value="${position.getRecipeId()}">

                                        <div class= "get-recipe-button-wrapper">
                                         <c:if  test="${position.getStringMedicineWithRecipe().equals('yes')}">
                                         <c:if  test="${!position.getRecipeStatus().equals('approved') && !position.getRecipeStatus().equals('overdue') && !position.getRecipeStatus().equals('declined') }">

                                         <button class="get-recipe-button" type="submit">request recipe</button>
                                         </c:if>
                                          <c:if  test="${position.getRecipeStatus().equals('overdue') && position.getRequiredAmount()<=position.getRecipeAmount()}">
                                          <button class="get-recipe-button" type="submit">request extension</button>
                                          </c:if>
                                          </c:if>
                                          </div>

                                          </form>

                                           <c:if  test="${position.getStringMedicineWithRecipe().equals('no') || (position.getRecipeStatus().equals('approved') && position.getRequiredAmount()<=position.getRecipeAmount())}">
                                                  <div class= "check-icon-wrapper">
                                                  <img src="./static/img/icons-check.png" alt="" class="check-icon">
                                                   </div>
                                           </c:if>

                                                    <c:if  test="${position.getStringMedicineWithRecipe().equals('yes') && (!position.getRecipeStatus().equals('pending approval')) && (position.getRecipeStatus()!=null)  && (position.getRecipeStatus().equals('declined') || position.getRequiredAmount()>position.getRecipeAmount())}">
                                                       <div class= "cross-icon-wrapper">
                                                          <img src="./static/img/icons-cross.png" alt="" class="cross-icon">
                                                       </div>
                                                    </c:if>


                                </div>

                                <div class= "position-recipe-status-wrapper">
                                   <span class= "position-recipe-status"><c:out value="${position.getRecipeStatus()}"/></span>
                                </div>

                                 <div class= "position-recipe-status-wrapper">

                                      <span class= "position-recipe-status"><c:out value="${position.getRecipeValidUntil()}"/></span>

                                 </div>

                                 <div class= "position-recipe-number-wrapper">
                                      <c:if  test="${position.getStringMedicineWithRecipe().equals('yes') && position.getRecipeAmount()!=0 }">
                                           <span class= "position-recipe-number"><c:out value="${position.getRecipeAmount()}"/></span>
                                             <c:if  test="${position.getRequiredAmount()>position.getRecipeAmount()}">
                                                <div class = "error-message" style = "color:red"; >
                                                     ${errorMessage = "exceeding the limit"}
                                                </div>
                                             </c:if>
                                      </c:if>
                                 </div>

                             <div class= "position-medicine-total-price-wrapper">
                                 <span class= "position-medicine-total-price"><c:out value="${position.getTotal()}"/></span>
                              </div>

                              <div class= "position-delete-button-wrapper">
                              <form method = "post" action = "controller?command=deleteFromCart">
                              <input class = "medicine-id" type="hidden" name="order-medicine-id" value="${position.getId()}">
                              <input class = "medicine-id" type="hidden" name="recipe-id" value="${position.getRecipeId()}">
                              <input class = "medicine-id" type="hidden" name="recipe-status" value="${position.getRecipeStatus()}">

                              <button class="delete-button" type="submit">
                              <img src="./static/img/svg/icons-delete.svg" alt="" class="delete-icon">
                              </button>

                              </form>
                              </div>

                            </div>

                     </c:forEach>
                     </div>

                    <div class= "total-price">
                     Total price:
                     ${totalPrice}
                      rub
                    </div>

                    <div class="pay-button-wrapper">
                       ${errorPayMessage}
                     <form method = "post" action = "controller?command=payOrder">
                     <input class = "" type="hidden" name="order-positions" value="${positions}">
                     <button class="pay-button" type="submit" >
                         Pay
                     </button>

                     </form>
                    </div>

        </section>

    </main>

         <script src="static/js/main.js"></script>
    </body>
    </html>