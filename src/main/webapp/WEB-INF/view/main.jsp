<%@ page contentType="text.html; charset=UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name = "viewport" content="width-device-width, initial-scale-1.0">
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
                        <img src="./img/svg/icons-facebook.svg" alt="facebook">
                    </a>
                    <a href="/" class="header-top-logos-instagram">
                        <img src="./img/svg/icons-instagram.svg" alt="instagram">
                    </a>
                    <a href="/" class="header-top-logos-telegram">
                        <img src="./img/svg/icons-telegram.svg" alt="telegram">
                    </a>
                </div>
<div class="header-top-nav-items">
                <nav class="header-top-nav">
                    <ul class="header-top-list">
                        <li class="header-top-item">
                            <a href="#!" class="header-top-link">
                                <img src="./img/icon-user.png" alt="telegram">
                            </a>
                        </li>
                        <li class="header-top-item">
                            <a href="#!" class="header-top-link">
                                <img src="./img/icon-bag.png" alt="telegram">
                            </a>
                        </li>
                    </ul>
                </nav>

                <div class="dropdown">
                    <button  class="dropbtn">
                        <img src="./img/icon-language.png" alt="">
                        <div class="dropbtn-text">RU</div>
                    </button>
                    <div class="dropdown-content">
                        <a class = "dropdown-content-link" href="#">EN</a>
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
                        <img src="./img/icons-health.png" alt="pharmacy">
                    </a>

                     <a href="/" class="header-bottom-logos-text">
                            Dr.Health
                     </a>

                </div>

                <nav class="header-bottom-nav">
                    <ul class="header-bottom-list">
                        <li class="header-bottom-item">
                            <a href="#!" class="header-bottom-link">
                                Каталог
                            </a>
                        </li>
                        <li class="header-bottom-item">
                            <a href="#!" class="header-bottom-link">
                                Доставка
                            </a>
                        </li>
                        <li class="header-bottom-item">
                            <a href="#!" class="header-bottom-link">
                                Контакты
                            </a>
                        </li>


                    </ul>
                </nav>

                <div class="header-bottom-search">
                    <form  method="get" class="searchform" action="https://fito.by/">

                        <input class="search"  type="search" placeholder="Искать здесь..."  name="search-product"/>

                        <button class="button" type="submit" >

                        </button>

                    </form>
        </div>


     </header>

     <main class="main">
         <section class="intro">
             <div class="wrapper-intro">
            <h1 class="intro-text">
                Ваше здоровье - в наших рука
            </h1>
            <form class="intro-button" method = "post" action = "controller?command=catalog">
                <button class="intro-button" type="submit" >
                         Перейти
                </button>
            </form>
        </div>
         </section>
         <div class="footer">
            <p  class="footer-text">Copyright © 2022 Dr.Health Inc. All rights reserved.</p>
        </div>
     </main>
</body>
</html>