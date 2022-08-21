<%@ page contentType="text.html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib  uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html>

<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="pagecontent" />


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
                                <a href="index.jsp" class="header-top-link">
                                    <img src="static/img/svg/user_icon.svg" alt="">
                                </a>
                            </li>
                            <li class="header-top-item">
                                 <a  href="controller?command=showCart" >
                                 <img class = "header-top-link bag" src="static/img/icon-bag.png" alt="">
                                 </a>
                            </li>
                        </ul>
                    </nav>

                    <div class="dropdown">
                        <button  class="dropbtn">
                            <img src="static/img/icon-language.png" alt="">
                            <div class="dropbtn-text"><fmt:message key="header.language"/>

                            </div>
                        </button>
                        <div class="dropdown-content">
                            <a class = "dropdown-content-link" href="controller?command=changeLang&lang=ru">RU</a>
                            <a class = "dropdown-content-link" href="controller?command=changeLang&lang=en">EN</a>
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

                     <a href="controller?command=mainPage" class="header-bottom-logos-text">
                            Dr.Health
                     </a>
                </div>

                <nav class="header-bottom-nav">
                    <ul class="header-bottom-list">
                       <c:if  test="${user.getRole().equals('user')}">
                        <li class="header-bottom-item">
                             <form  method="POST"  action="controller?command=catalog">
                                  <button class="header-bottom-link" type="submit" > <fmt:message key="header.catalog"/>
                            </button>
                             </form>
                        </li>
                        <li class="header-bottom-item">
                            <a href="#!" class="header-bottom-link">
                                <fmt:message key="header.delivery"/>
                            </a>
                        </li>
                        <li class="header-bottom-item">
                            <a href="#!" class="header-bottom-link">
                               <fmt:message key="header.contacts"/>
                            </a>
                        </li>
                        </c:if>
                         <c:if  test="${user.getRole().equals('doctor')}">
                            <li class="header-bottom-item">
                               <form  method="POST"  action="controller?command=ConfirmationRequestsPage">
                                     <button class="header-bottom-link" type="submit" > <fmt:message key="header.confirmation.requests"/>
                                     </button>
                               </form>
                             </li>
                               <li class="header-bottom-item">
                                   <form  method="POST"  action="controller?command=extensionRequestsPage">
                                     <button class="header-bottom-link" type="submit" > <fmt:message key="header.extension.requests"/>
                                     </button>
                                   </form>
                               </li>
                                   <li class="header-bottom-item">
                                   <form  method="POST"  action="controller?command=allPrescriptionsPage">
                                    <button class="header-bottom-link" type="submit" > <fmt:message key="header.all.prescriptions"/>
                                    </button>
                                    </form>
                                   </li>
                         </c:if>
                          <c:if  test="${user.getRole().equals('chemist')}">
                                                     <li class="header-bottom-item">
                                                      <a href="controller?command=showMedicineStoragePage"><fmt:message key="header.chemist.storage"/>
                                                      </li>

                                                        <li class="header-bottom-item">
                                                             <a href="controller?command=addMedicinePage"><fmt:message key="header.chemist.addMedicine"/>
                                                                                 </a>
                                                        </li>

                                                        <li class="header-bottom-item">
                                                        <form  method="POST"  action="controller?command=allOrders">
                                                         <button class="header-bottom-link" type="submit" > <fmt:message key="header.chemist.allOrders"/>
                                                         </button>
                                                         </form>
                                                        </li>

                          </c:if>
                          <c:if  test="${user.getRole().equals('admin')}">
                           <li class="header-bottom-item">
                            <a href="controller?command=showAdminUsersPage"><fmt:message key="header.admin.users.control"/>
                            </li>
                          </c:if>
                    </ul>
                </nav>



                <div class="header-bottom-mobile">
                    <div class="header-bottom-search-mobile">
                        <form  method="get" class="searchform" action="controller?command=searchProduct">

                            <input class="search"  type="search" placeholder="Search here..."  name="search-product"/>

                            <button class="button" type="submit" >

                            </button>

                        </form>
                    </div>
                <nav class="header-bottom-nav-mobile">
                    <ul class="header-bottom-list">

                        <li class="header-bottom-item">
                            <a href="#!" class="header-bottom-link">
                                <fmt:message key="header.catalog"/>
                            </a>
                        </li>
                        <li class="header-bottom-item">
                            <a href="#!" class="header-bottom-link">
                                <fmt:message key="header.delivery"/>
                            </a>
                        </li>
                        <li class="header-bottom-item">
                            <a href="#!" class="header-bottom-link">
                                <fmt:message key="header.contacts"/>
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
                    <form  method="POST" class="searchform" action="controller?command=searchProduct">

                        <input class="search"  type="search" placeholder= "<fmt:message key="header.search"/>" name="search-product"/>

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