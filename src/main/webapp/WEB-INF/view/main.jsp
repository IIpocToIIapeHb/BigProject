<%@ page contentType="text.html; charset=UTF-8" isELIgnored="false" %>
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

     <main class="main">
         <section class="intro">
           <div class="wrapper-intro">
            <h1 class="intro-text">
                       <fmt:message key="main.slogan"/>
            </h1>
            <form class="intro-button" method = "post" action = "controller?command=catalog">
                <button class="intro-button" type="submit" >
                         <fmt:message key="main.go"/>
                </button>
            </form>
          </div>
         </section>
        <jsp:include page="fragments/footer.jsp"/>
     </main>

     <script src="static/js/main.js"></script>
</body>
</html>
