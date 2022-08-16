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

   <main class="users-storage">

    <div class="users-storage">
     <div class="search-user">

       <div class="users-storage-label">
        <p><fmt:message key="users.storage.surname"/></p>
       </div>

       <div>
         <form  method="POST" action="controller?command=searchUser">
           <input class="search"  type="search"  name="search-user"/>
           <button class="storage-search-button" type="submit">
            <fmt:message key="user.storage.search"/>
           </button>
        </form>
       </div>

     </div>
        <c:if  test="${usersStorage!=null}">

              <section class="found-users-in-storage">

                  <div class="user-storage-captain">
                     <div class="user-storage-captain-surname"><fmt:message key="user.storage.surname"/></div>
                     <div class="user-storage-captain-name"><fmt:message key="user.storage.name"/></div>
                     <div class="user-storage-captain-birth"><fmt:message key="user.storage.birth"/></div>
                     <div class="user-storage-captain-role"><fmt:message key="user.storage.role"/></div>
                     <div class="user-storage-captain-block"><fmt:message key="user.storage.block"/></div>
                   </div>

              <div class="storage-users-cards">


                 <c:forEach var="user" items="${usersStorage}">

                    <div class="storage-medicine-card">

                        <div class= "storage-user-surname-wrapper">
                          <span class="storage-user-surname">${user.surname}</span>
                         </div>

                         <div class= "storage-user-name-wrapper">
                           <span class="storage-user-name">${user.name}</span>
                         </div>

                          <div class= "storage-user-birth-wrapper">
                           <span class="storage-user-birth">${user.birth}</span>
                          </div>

                          <div class= "storage-user-role-wrapper">
                            <span class="storage-user-role">${user.role}</span>
                          </div>

                           <c:if  test="${user.isBlocked()}">
                           <div class= "storage-user-block-true-wrapper">
                           <span class="storage-user-block-true"><fmt:message key="user.storage.block.true"/></span>
                           </div>
                           </c:if>

                           <c:if  test="${!user.isBlocked()}">
                          <div class= "storage-user-block-false-wrapper">
                          <span class="storage-user-block-false"><fmt:message key="user.storage.block.false"/></span>
                          </div>
                          </c:if>


                            <div class="storage-medicine-button-wrapper">
                              <form method = "post" action = "controller?command=changeMedicine">
                                 <input  type="hidden" name="medicine-id" value="${user.id}">
                                 <button class="change-medicine-button" type="submit" > <fmt:message key="medicine.storage.change.button" /> </button>
                             </form>
                            </div>


                    </div>

                 </c:forEach>
               </div>
              </section>
</c:if>

         <div class = "error-message" style = "color:red"; >
            ${errorSearchUserMessage}
         </div>

</div>
   </main>

   <script src="static/js/main.js"></script>
 </body>
</html>