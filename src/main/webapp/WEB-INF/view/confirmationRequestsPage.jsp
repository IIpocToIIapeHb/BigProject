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

    <main class="confirmation-requests-items">

         <section class="intro-confirmation-requests">

              <div class="confirmation-requests-captain">
                <div class="confirmation-requests-number"><fmt:message key="confirmation.requests.number"/></div>
                <div class="confirmation-requests-item"><fmt:message key="confirmation.requests.surname"/></div>
                <div class="confirmation-requests-item"><fmt:message key="confirmation.requests.name"/></div>
                <div class="confirmation-requests-item"><fmt:message key="confirmation.requests.birth"/></div>
                <div class="confirmation-requests-item"><fmt:message key="confirmation.requests.medicine"/></div>
                <div class="confirmation-requests-item"><fmt:message key="confirmation.requests.amount"/></div>
                <div class="confirmation-requests-item"><fmt:message key="confirmation.requests.prescription.period"/></div>
              </div>

              <div class= "confirmation-requests-cards">
                     <c:forEach var="prescription" items="${prescriptionsInfo}">

                            <div class= "confirmation-requests-card">

                                 <div class= "confirmation-requests-number-wrapper">
                                    <span class= "confirmation-requests-numbers"><c:out value="${prescription.getCounterPlusOne()}" /></span>
                                 </div>

                                 <div class= "confirmation-requests-surname-wrapper">
                                    <span class= "confirmation-requests-item-info"><c:out value="${prescription.getUserSurname()}" /></span>
                                 </div>

                                 <div class= "confirmation-requests-name-wrapper">
                                     <span class= "confirmation-requests-item-info"><c:out value="${prescription.getUserName()}" /></span>
                                 </div>

                                 <div class= "confirmation-requests-birth-wrapper">
                                     <span class= "confirmation-requests-item-info"><c:out value="${prescription.getUserBirth()}"/></span>
                                 </div>

                                 <div class= "confirmation-requests-medicine-wrapper">
                                     <span class="confirmation-requests-item-info"><c:out value="${prescription.getMedicineName()}"/></span>
                                 </div>

                                 <form method = "post" action = "controller?command=confirmPrescription">
                                    <input  type="hidden" name="prescription-id" value="${prescription.getId()}">
                                    <div class= "confirmation-request-wrapper">
                                        <div class= "confirmation-request">
                                            <input class = "input-text-doctor" type="text" name="prescription-medicine-amount" placeholder= "<fmt:message key="confirmation.requests.amount"/>" >
                                        </div>
                                        <div  class= "confirmation-request">
                                            <input class = "input-text-doctor" type="text" name="prescription-term" placeholder="<fmt:message key="confirmation.requests.prescription.period.days"/>">
                                        </div>
                                        <button class="confirm-doctor-button" type="submit" > <fmt:message key="confirmation.requests.button.confirm" /> </button>
                                    </div>
                                 </form>

                                 <form method = "post" action = "controller?command=refusePrescription">
                                 <input  type="hidden" name="prescription-id" value="${prescription.getId()}">
                                      <div class="confirmation-request">
                                            <button class="refuse-doctor-button" type="submit" > <fmt:message key="confirmation.requests.button.refuse" /> </button>
                                      </div>
                                 </form>

                                 <div class = "doctor-error-message">
                                     ${doctorErrorMessage}
                                 </div>

                                 <c:if  test="${doctorErrorMessage!=null}">
                                    <div class = "error-message" style = "color:red"; >
                                        ${doctorErrorMessage = null}
                                     </div>
                                 </c:if>

                                <div class = "doctor-error-message">
                                     ${doctorErrorMessage}
                                </div>

                            </div>

                     </c:forEach>
              </div>

        </section>

    </main>

<script src="static/js/main.js"></script>
</body>
</html>