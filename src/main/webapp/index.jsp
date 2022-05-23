<%@ page pageEncoding="UTF-8" contentType="text.html; charset=UTF-8" isELIgnored="false" %>
<html>
  <html>
  <head>
      <meta charset="UTF-8">
      <meta name = "viewport" content="width-device-width, initial-scale-1.0">
      <title>Pharmacy</title>
      <link rel="stylesheet" href="static/styles/style.css">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
  </head>

  <body>
      <header  class="hello-page-header">
          <div class="header-bottom-logos">
              <a href="/" class="header-bottom-logos-icon">
                  <img src="./static/img/icons-health.png" alt="pharmacy">
              </a>

               <a href="/" class="header-bottom-logos-text">
                      Dr.Health
               </a>

          </div>
      </header>


          <main>
              <div class="hello-page-main">
                  <div class="hello-page-main-wrapper">
                      <img src="./static/img/icon-user.png" alt="" class="hello-page-main-icon">
                      <form method = "post" action = "controller?command=login">

                          <label class = "form-labels" for="login">Login</label>
                          <input class = "text" type="text" name="login" placeholder="Ваш логин...">

                          <label class = "form-labels" for="password">Пароль</label>
                          <input class = "text" type="password" name="password" placeholder="Ваш пароль...">

                          <label class = "form-labels" for="role">Роль</label>
                          <select class="text" name="role">
                              <option value="user">Пользователь</option>
                              <option value="chemist">Фармацевт</option>
                              <option value="doctor">Доктор</option>
                          </select>
                          <input class = "text" type="submit" value="Войти">
                      </form>
                  </div>
                   <div class = "error-message">
                              ${errorMessage}
                   </div>
              </div>


          </main>
      </body>
          </html>
</html>
