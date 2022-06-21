// Burger handler
(function () {
    const burgerItem = document.querySelector('.burger');
    const menu = document.querySelector('.header-bottom-mobile');
    const menuCloseItem = document.querySelector('.header-nav-close');
    console.log(burgerItem);
    burgerItem.addEventListener('click', () => {
        menu.classList.add('header-bottom-mobile_active');
    });
    menuCloseItem.addEventListener('click', () => {
        menu.classList.remove('header-bottom-mobile_active');
    });
}());