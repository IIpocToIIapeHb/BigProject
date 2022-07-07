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

    <main class="all-prescriptions-items">

         <section class="intro-all-prescriptions">

              <div class="all-prescriptions-captain">
                <div class="all-prescriptions-number-captain"><fmt:message key="all.prescriptions.number"/></div>
                <div class="all-prescriptions-item-captain"><fmt:message key="all.prescriptions.surname"/></div>
                <div class="all-prescriptions-item-captain"><fmt:message key="all.prescriptions.name"/></div>
                <div class="all-prescriptions-item-captain"><fmt:message key="all.prescriptions.birth"/></div>
                <div class="all-prescriptions-item-captain"><fmt:message key="all.prescriptions.medicine"/></div>
                <div class="all-prescriptions-item-captain"><fmt:message key="all.prescriptions.amount"/></div>
                <div class="all-prescriptions-item-captain"><fmt:message key="all.prescriptions.period"/></div>
                <div class="all-prescriptions-item-captain"><fmt:message key="all.prescriptions.status"/></div>
              </div>

              <div class= "all-prescriptions-cards">
                     <c:forEach var="prescription" items="${prescriptionsInfo}">

                            <div class= "all-prescriptions-card">
                             <div class= "all-prescriptions-number-wrapper">
                             <span class= "all-prescriptions-number"><c:out value="${prescription.getCounterPlusOne()}" /></span>
                              </div>
                                <div class= "all-prescriptions-surname-wrapper">
                                 <span class= "all-prescriptions-item"><c:out value="${prescription.getUserSurname()}" /></span>
                                </div>

                                <div class= "all-prescriptions-name-wrapper">
                                  <span class= "all-prescriptions-item"><c:out value="${prescription.getUserName()}" /></span>
                                </div>

                                <div class= "all-prescriptions-birth-wrapper">
                                  <span class= "all-prescriptions-item"><c:out value="${prescription.getUserBirth()}"/></span>
                                </div>

                                <div class= "all-prescriptions-medicine-wrapper">
                                    <span class="all-prescriptions-item"><c:out value="${prescription.getMedicineName()}"/></span>
                                </div>

                                  <div class= "all-prescriptions-medicine-wrapper">
                                    <span class="all-prescriptions-item"><c:out value="${prescription.getPrescriptionMedicineAmount()}"/></span>
                                  </div>

                                   <div class= "all-prescriptions-medicine-wrapper">
                                      <span class="all-prescriptions-item"><c:out value="${prescription.getPrescriptionValidUntil()}"/></span>
                                   </div>
                                   <div class= "all-prescriptions-medicine-wrapper">
                                     <span class="all-prescriptions-item"><c:out value="${prescription.getPrescriptionStatus()}"/></span>
                                   </div>
                                   </div>
                     </c:forEach>
                     </div>


        </section>

    </main>

         <script src="static/js/main.js"></script>
    </body>
    </html>