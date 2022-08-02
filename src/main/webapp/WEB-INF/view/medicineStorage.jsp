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

   <main class="medicine-storage">

    <div class="medicine-storage">
     <div class="search-medicine">

       <div class="medicine-storage-label">
        <p><fmt:message key="medicine.storage.medicine"/></p>
       </div>

       <div>
         <form  method="POST" action="controller?command=searchProductInStorage">
           <input class="search"  type="search"  name="search-product"/>
           <button class="storage-search-button" type="submit">
            <fmt:message key="medicine.storage.search"/>
           </button>
        </form>
       </div>

     </div>
        <c:if  test="${medicinesStorage!=null}">

              <section class="found-medicines-in-storage">

                  <div class="storage-captain">
                     <div class="storage-captain-name"><fmt:message key="medicine.storage.medicine"/></div>
                     <div class="storage-captain-category"><fmt:message key="medicine.storage.category"/></div>
                     <div class="storage-captain-dosage"><fmt:message key="medicine.storage.dosage"/></div>
                     <div class="storage-captain-prescription"><fmt:message key="medicine.storage.prescription"/></div>
                     <div class="storage-captain-form"><fmt:message key="medicine.storage.form"/></div>
                     <div class="storage-captain-package-amount"><fmt:message key="medicine.storage.package.amount"/></div>
                     <div class="storage-captain-amount"><fmt:message key="medicine.storage.amount"/></div>
                     <div class="storage-captain-price"><fmt:message key="medicine.storage.price"/></div>
                   </div>

              <div class="storage-medicines-cards">


                 <c:forEach var="medicine" items="${medicinesStorage}">

                    <div class="storage-medicine-card">
                        <div class= "storage-medicine-card-wrapper">
                             <img src="${medicine.getFullPath()}" alt="" class = "position-card-thumb">
                         </div>

                         <div class= "storage-medicine-name-wrapper">
                           <span class="storage-medicine-name">${medicine.name}</span>
                         </div>

                         <div class="storage-medicine-category-wrapper">
                              <span class= "storage-medicine-category">${medicine.categoryName}</span>
                         </div>

                         <div class="storage-medicine-dosage-wrapper">
                           <span class= "storage-medicine-dosage">${medicine.dosage}</span>
                         </div>

                         <div class="storage-medicine-prescription-wrapper">
                             <span class= "storage-medicine-prescription">${medicine.getWithRecipeStatus()}</span>
                         </div>

                         <div class="storage-medicine-form-wrapper">
                             <span class= "storage-medicine-form">${medicine.form}</span>
                         </div>

                           <div class="storage-medicine-package-amount-wrapper">
                             <span class="storage-medicine-package-amount">${medicine.packageAmount}</span>
                           </div>

                          <div class="storage-medicine-amount-wrapper">
                               <span class= "storage-medicine-amount">${medicine.amount}</span>
                          </div>



                           <div class="storage-medicine-price-wrapper">
                             <span class= "storage-medicine-price">${medicine.price}</span>
                           </div>

                            <div class="storage-medicine-button-wrapper">
                              <form method = "post" action = "controller?command=changeMedicine">
                                 <input  type="hidden" name="medicine-id" value="${medicine.id}">
                                 <button class="change-medicine-button" type="submit" > <fmt:message key="medicine.storage.change.button" /> </button>
                             </form>
                            </div>


                    </div>

                 </c:forEach>
               </div>
              </section>
</c:if>

         <div class = "error-message" style = "color:red"; >
            ${errorSearchProductMessage}
         </div>

</div>
   </main>

   <script src="static/js/main.js"></script>
 </body>
</html>