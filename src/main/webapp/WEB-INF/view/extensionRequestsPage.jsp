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

    <main class="extension-requests-items">

         <section class="intro-extension-requests">

              <div class="extension-requests-captain">
                <div class="extension-requests-number"><fmt:message key="confirmation.requests.number"/></div>
                <div class="extension-requests-item"><fmt:message key="confirmation.requests.surname"/></div>
                <div class="extension-requests-item"><fmt:message key="confirmation.requests.name"/></div>
                <div class="extension-requests-item"><fmt:message key="confirmation.requests.birth"/></div>
                <div class="extension-requests-item"><fmt:message key="confirmation.requests.medicine"/></div>
                <div class="extension-requests-item"><fmt:message key="confirmation.requests.amount"/></div>
                <div class="extension-requests-item"><fmt:message key="extension.requests.valid.until"/></div>
                <div class="extension-requests-item"><fmt:message key="confirmation.requests.prescription.period"/></div>
              </div>

              <div class= "extension-requests-cards">
                     <c:forEach var="prescription" items="${prescriptionsInfo}">

                            <div class= "extension-requests-card">
                             <div class= "extension-requests-number-wrapper">
                             <span class= "extension-requests-number"><c:out value="${prescription.getCounterPlusOne()}" /></span>
                              </div>
                                <div class= "extension-requests-surname-wrapper">
                                 <span class= "extension-requests-item"><c:out value="${prescription.getUserSurname()}" /></span>
                                </div>

                                <div class= "extension-requests-name-wrapper">
                                  <span class= "extension-requests-item"><c:out value="${prescription.getUserName()}" /></span>
                                </div>

                                <div class= "extension-requests-birth-wrapper">
                                  <span class= "extension-requests-item"><c:out value="${prescription.getUserBirth()}"/></span>
                                </div>

                                <div class= "extension-requests-medicine-wrapper">
                                    <span class="extension-requests-item"><c:out value="${prescription.getMedicineName()}"/></span>
                                </div>

                                  <div class= "extension-requests-amount-wrapper">
                                        <span class="extension-requests-item"><c:out value="${prescription.getPrescriptionMedicineAmount()}"/></span>
                                   </div>

                                 <div class= "extension-requests-valid-date-wrapper">

                                       <span class="extension-requests-item"><c:out value="${prescription.getPrescriptionValidUntil()}"/></span>
                                  </div>


                                 <form method = "post" action = "controller?command=confirmPrescription">
                                  <input  type="hidden" name="prescription-id" value="${prescription.getId()}">
                                  <div class= "extension-request-wrapper">

                                 <div  class= "extension-request">
                                 <input class = "input-text-doctor" type="text" name="prescription-term" placeholder="<fmt:message key="confirmation.requests.prescription.period.days"/>">
                                 </div>

                                  <button class="confirm-doctor-button" type="submit" > <fmt:message key="confirmation.requests.button.confirm" /> </button>


                              </div>
                              </form>

                                <form method = "post" action = "controller?command=RefusePrescription">


                                  <div class="confirmation-request">
                                  <button class="refuse-doctor-button" type="submit" > <fmt:message key="confirmation.requests.button.refuse" /> </button>
                                </div>

                                 </form>


                      <div class = "doctor-error-message" >
                       ${doctorErrorMessage}
                       </div>




                     </c:forEach>
                     </div>


        </section>

    </main>

         <script src="static/js/main.js"></script>
    </body>
    </html>