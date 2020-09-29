# LoctiteReminderApplication

Welcome!

This app was designed to controll products (adhesives) at risk of expiry. You can choose date when you want to be remind about products with short expiry date. 
The main advantage of this application is the ability to send a reminder email with a list of products whose expiry date is now or past - then you can do a few actions. For example phone to potential customers and offer this product with special price.
This app was designed for small company and is still developing.

<b>To use this application you need to:</b>

1. Configure your database in application.properties

spring.jpa.hibernate.ddl-auto=update <br>
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/<b>YOUR_DATABASE</b>?&serverTimezone=UTC<br>
spring.datasource.username=<b>YOUR_DATABASE_LOGIN</b><br>
spring.datasource.password=<b>YOUR_DATABASE_PASSWORD</b><br>

2. For sending email you can use email: 
<b>email:</b> loctite.reminder@gmail.com <br>  
<b>password:</b> loctite123

You can use also your email, but you have to configure that.

3. Spring security

<b>Login:</b> woronko <br>
<b>Password:</b> loctite

4. By default, the application runs on localhost:8080
