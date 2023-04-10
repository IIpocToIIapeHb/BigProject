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

                           <form method = "post" action = "controller?command=saveMedicine" enctype="multipart/form-data">

                                <div class="character-medicine-block">
                                <div class="medicine-card-label">
                                <label class = "medicine-card-label"><fmt:message key="cart.medicine.name"/></label>
                                </div>
                                <input class = "medicine-card-input" type="text" name="medicine-name">
                                </div>

                                 <div class="character-medicine-block">

                                 <div class="medicine-card-label">
                                 <label class = "medicine-card-label"><fmt:message key="cart.medicine.category"/></label>
                                 </div>

                                 <div class="medicine-card-input">
                                 <select name="medicine-category">
                                   <option value="medicament"><fmt:message key="medicine.category.medicament"/></option>
                                   <option value="beauty_and_care"><fmt:message key="medicine.category.beauty_and_care"/></option>
                                   <option value="hygiene"><fmt:message key="medicine.category.hygiene"/></option>
                                   <option value="vitamins_and_BADs"><fmt:message key="medicine.category.vitamins_and_BADs"/></option>
                                   <option value="health_and_sports"><fmt:message key="medicine.category.health_and_sports"/></option>
                                   <option value="for_kids"><fmt:message key="medicine.category.for_kids"/></option>
                                   <option value="medical_products"><fmt:message key="medicine.category.medical_products"/></option>
                                 </select>
                                 </div>
                                 </div>


                                 <div class="character-medicine-block">
                                 <div class="medicine-card-label">
                                  <label class = "medicine-card-label"><fmt:message key="card.medicine.add.file"/></label>
                                 </div>
                                 <div class="medicine-card-input">
                                   <input class = "medicine-card-input" type="file" name="medicine-image">
                                 </div>
                                 </div>

                                <div class="character-medicine-block">
                                <div class="medicine-card-label">
                                <label class = "medicine-card-label"><fmt:message key="cart.medicine.dosage"/></label>
                                </div>
                                <div class="medicine-card-input">
                                <input class = "medicine-card-input" type="text" name="medicine-dosage">
                                </div>
                                <div class="medicine-card-measure">
                                <p><fmt:message key="catalog.dosage.mg"/></p>
                                </div>
                                </div>

                                 <div class="character-medicine-block">

                                 <div class="medicine-card-label">
                                 <label class = "medicine-card-label"><fmt:message key="card.medicine.prescription.availability"/></label>
                                 </div>

                                 <div class="medicine-card-input">
                                 <select name="medicine-prescription-availability">
                                 <option value="yes"><fmt:message key="medicine.prescription.availability.yes"/></option>
                                 <option value="no"><fmt:message key="medicine.prescription.availability.no"/></option>
                                 </select>
                                 </div>
                                 </div>

                                  <div class="character-medicine-block">

                                  <div class="medicine-card-label">
                                   <label class = "medicine-card-label"><fmt:message key="card.medicine.form"/></label>
                                   </div>

                                   <div class="medicine-card-input">
                                   <select name="medicine-form">
                                   <option value="tablet"><fmt:message key="medicine.form.tablet"/></option>
                                    <option value="liquid"><fmt:message key="medicine.form.liquid"/></option>
                                    <option value="salve"><fmt:message key="medicine.form.salve"/></option>
                                   </select>
                                   </div>
                                   </div>

                                   <div class="character-medicine-block">
                                   <div class="medicine-card-label">
                                   <label class = "medicine-card-label"><fmt:message key="card.medicine.number"/></label>
                                   </div>
                                   <div class="medicine-card-input">
                                   <input class = "medicine-card-input" type="text" name="medicine-number">
                                   </div>
                                   </div>

                                   <div class="character-medicine-block">
                                    <div class="medicine-card-label">
                                    <label class = "medicine-card-label"><fmt:message key="card.medicine.packageAmount"/></label>
                                     </div>
                                     <div class="medicine-card-input">
                                      <input class = "medicine-card-input" type="text" name="medicine-package-amount">
                                      </div>
                                      </div>

                                   <div class="character-medicine-block">
                                    <div class="medicine-card-label">
                                    <label class = "medicine-card-label"><fmt:message key="card.medicine.price"/></label>
                                    </div>
                                    <div class="medicine-card-input">
                                    <input class = "medicine-card-input" type="text" name="medicine-price">
                                    </div>
                                    <div class="medicine-card-measure">
                                    <p><fmt:message key="catalog.price.rub"/></p>
                                    </div>
                                  </div>


                               <input class = "text" type="submit" value="<fmt:message key="card.medicine.save"/>">
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