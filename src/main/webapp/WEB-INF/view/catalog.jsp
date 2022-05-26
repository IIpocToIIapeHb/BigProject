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
    <header id="header" class="header">
        <div class="header-top-wrapper">
            <div id="top-bar" class="header-top">
                <div class="header-top-logos">
                    <a href="/" class="header-top-logos-facebook">
                        <img src="static/img/svg/icons-facebook.svg" alt="facebook">
                    </a>
                    <a href="/" class="header-top-logos-instagram">
                        <img src="static/img/svg/icons-instagram.svg" alt="instagram">
                    </a>
                    <a href="/" class="header-top-logos-telegram">
                        <img src="static/img/svg/icons-telegram.svg" alt="telegram">
                    </a>
                </div>
               <div class="header-top-nav-items">
                    <nav class="header-top-nav">
                        <ul class="header-top-list">
                            <li class="header-top-item">
                                <a href="#!" class="header-top-link">
                                    <img src="static/img/svg/user_icon.svg" alt="">
                                </a>
                            </li>
                            <li class="header-top-item">
                                <a href="#!" class="header-top-link bag">
                                    <img class = "bag" src="static/img/icon-bag.png" alt="">
                                </a>
                            </li>
                        </ul>
                    </nav>

                    <div class="dropdown">
                        <button  class="dropbtn">
                            <img src="static/img/icon-language.png" alt="">
                            <div class="dropbtn-text">EN</div>
                        </button>
                        <div class="dropdown-content">
                            <a class = "dropdown-content-link" href="#">RU</a>
                            <a  class = "dropdown-content-link" href="#">FR</a>
                        </div>
                    </div>

                </div>
            </div>

        </div>

        <div class="header-bottom-wrapper">

            <div id="top-bar" class="header-bottom">

                <div class="header-bottom-logos">
                    <a href="/" class="header-bottom-logos-icon">
                        <img src="static/img/icons-health.png" alt="pharmacy">
                    </a>

                     <a href="/" class="header-bottom-logos-text">
                            Dr.Health
                     </a>
                </div>

                <nav class="header-bottom-nav">
                    <ul class="header-bottom-list">
                        <li class="header-bottom-item">
                             <form  method="POST"  action="controller?command=catalog">
                                  <button class="header-bottom-link" type="submit" > Catalog
                            </button>
                             </form>
                        </li>
                        <li class="header-bottom-item">
                            <a href="#!" class="header-bottom-link">
                                Delivery
                            </a>
                        </li>
                        <li class="header-bottom-item">
                            <a href="#!" class="header-bottom-link">
                                Contacts
                            </a>
                        </li>
                    </ul>
                </nav>



                <div class="header-bottom-mobile">
                    <div class="header-bottom-search-mobile">
                        <form  method="get" class="searchform" action="https://fito.by/">

                            <input class="search"  type="search" placeholder="Search here..."  name="search-product"/>

                            <button class="button" type="submit" >

                            </button>

                        </form>
                    </div>
                <nav class="header-bottom-nav-mobile">
                    <ul class="header-bottom-list">

                        <li class="header-bottom-item">
                            <a href="#!" class="header-bottom-link">
                                Catalog
                            </a>
                        </li>
                        <li class="header-bottom-item">
                            <a href="#!" class="header-bottom-link">
                                Delivery
                            </a>
                        </li>
                        <li class="header-bottom-item">
                            <a href="#!" class="header-bottom-link">
                                Contacts
                            </a>
                        </li>

                    </ul>
                </nav>

                <div class="header-nav-close">
                    <span class="header-nav-close-line"></span>
                    <span class="header-nav-close-line"></span>
                </div>

            </div>



                <div class="header-bottom-search">
                    <form  method="get" class="searchform" action="https://fito.by/">

                        <input class="search"  type="search" placeholder="Search here..."  name="search-product"/>

                        <button class="button" type="submit" >

                        </button>

                    </form>
                </div>

                <div class="header_burger burger">
                    <span class="burger_line burger_line_first"></span>
                    <span class="burger_line burger_line_second"></span>
                    <span class="burger_line burger_line_third"></span>
                </div>

            </div>
        </div>




    </header>

     <main class="intro-text">
        <ul>
                    <c:forEach var="medicine" items="${medicines}">
                        <li><c:out value="${medicine.getName()}" /></li>
                    </c:forEach>
        </ul>
     </main>

     <script src="static/js/main.js"></script>
</body>
</html>
