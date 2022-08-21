<%@ page contentType="text.html; charset=UTF-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
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

     <main class="catalog-items">
        <section class="intro-catalog">
            <div class= "cards">
                 <c:forEach var="medicine" items="${foundProduct}">

                        <div class= "card">

                            <div class= "card-pic">
                                <img src="${medicine.getFullPath()}" alt="" class = "card-thumb">
                            </div>

                            <div class= "medicine-name-wrapper">
                              <span class= "medicine-name"><c:out value="${medicine.getName()}" /></span>
                            </div>

                            <div class= "medicine-dosage-and-number-wrapper">
                              <span class= "medicine-dosage">(</span>
                              <span class= "medicine-dosage"><c:out value="${medicine.getDosage()}"/></span>
                              <span class= "medicine-dosage"><fmt:message key="catalog.dosage.mg"/></span>
                              <span class= "medicine-dosage">,</span>
                              <c:if  test="${medicine.getPackageAmount()>1}">
                                  <span class= "medicine-dosage"><c:out value="${medicine.getPackageAmount()}"/></span>
                                  <span class= "medicine-dosage"><fmt:message key="catalog.number.n"/></span>
                              </c:if>
                              <span class= "medicine-dosage">)</span>
                            </div>

                            <div class= "medicine-price-wrapper">
                              <span class= "medicine-price"><c:out value="${medicine.getPrice()}"/><fmt:message key="catalog.price.rub"/></span>
                            </div>

                             <div class= "form-wrapper">
                                    <form method = "post" action = "controller?command=cart">
                                      <input class = "medicine-id" type="hidden" name="medicine-id" value="${medicine.getId()}">
                                       <input class = "medicine-id" type="hidden" name="medicine-with-recipe" value="${medicine.getWithRecipe()}">
                                      <div class= "medicine-number-wrapper">
                                      <input class = "medicine-number" type="number" name="medicine-number" value="1">
                                      </div>
                                      <div class= "cart_button-wrapper">
                                      <button class="cart_button" type="submit" > <fmt:message key="catalog.addToCart" /> </button>
                                      </div>
                                  </form>
                             </div>

                        </div>

                 </c:forEach>
            </div>
        </section>

        <div class = "error-message-search-product">
        ${errorSearchProductMessage}
        </div>

     </main>
     <jsp:include page="fragments/footer.jsp"/>
     <script src="static/js/main.js"></script>
</body>
</html>
