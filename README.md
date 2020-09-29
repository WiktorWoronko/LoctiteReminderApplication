# LoctiteReminderApplication

Welcome!

This app was designed to controll products (adhesives) at risk of expiry. You can choose date when you want to be remind about products with short expiry date. 
The main advantage of this application is the ability to send a reminder email with a list of products whose expiry date is now or past - then you can do a few actions. For example phone to potential customers and offer this product with special price.
This app was designed for small company and is still developing.

To use this application you need to:

1. Configure your database in application.properties

spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/YOUR_DATABASE_?&serverTimezone=UTC
spring.datasource.username=YOUR_DATABASE_LOGIN
spring.datasource.password=YOUR_DATABASE_PASSWORD

2. For sending email you can use email: loctite.reminder@gmail.com   password: loctite123

You can use also your email, but you have to configure that.

3. Spring security

Login: woronko
Password: loctite
