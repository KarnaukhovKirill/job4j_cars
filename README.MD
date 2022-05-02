# job4j_cars

## О проекте

В данном проекте реализована площадка по продаже машин. 

![HTML5](https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white)
![JavaScript](https://img.shields.io/badge/javascript-%23323330.svg?style=for-the-badge&logo=javascript&logoColor=%23F7DF1E)
![jQuery](https://img.shields.io/badge/jquery-%230769AD.svg?style=for-the-badge&logo=jquery&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Hibernate](https://a11ybadges.com/badge?logo=hibernate)
![TravisCI](https://img.shields.io/badge/travis%20ci-%232B2F33.svg?style=for-the-badge&logo=travis&logoColor=white)

## Запуск приложения
1. Скачайте исходные файлы
2. Создайте в Postgres базу данных todo
3. В файле src/main/resources/hibernate.cfg.xml измените username, password базы данных.
4. Из корневой папки вызовите команду
````
mvn install
````
5. Файл target/job4j_todo.war поместите в контейнер сервлетов (Apache Tomcat, например)

## Как пользоваться

Если пользователь не зарегистирован в приложении, он увидит окно входа
![alt text](https://github.com/KarnaukhovKirill/job4j_cars/blob/main/image/1.Login.png)

По кнопке "Регистрация" можно перейти на форму регистрации нового пользователя
![alt text](https://github.com/KarnaukhovKirill/job4j_cars/blob/main/image/2.Registration.png)

Главная страница, на которой отображены все объявления
![alt text](https://github.com/KarnaukhovKirill/job4j_cars/blob/main/image/3.MainPage.png)

На странице представленны фильтры объявлений.
Фильтр для поиска машин марки 'Renault'.
![alt text](https://github.com/KarnaukhovKirill/job4j_cars/blob/main/image/4.RenaultFilter.png)

Фильтр для поиска машин в кузове 'Седан'.
![alt text](https://github.com/KarnaukhovKirill/job4j_cars/blob/main/image/5.BodyFilter.png)

Страница пользовательских объявлений.
![alt text](https://github.com/KarnaukhovKirill/job4j_cars/blob/main/image/6.MyPosts.png)

По кнопке "Создать объявление" переходим к форме нового объявления
![alt text](https://github.com/KarnaukhovKirill/job4j_cars/blob/main/image/7.NewPost.png)

## TODO
- [ ] добавить личный кабинет покупателя и продавца
- [ ] добавить разделение объявлений по городам и странам
- [ ] добавить филььр по городам
- [ ] ...

## Контакты
Есть вопросы по проекту? Свяжитесь со мной в [![alt text](https://badges.aleen42.com/src/telegram.svg)](https://t.me/Tur_Boyama)

Или пишите на почту: karnaukhov.kirill23@gmail.com

[![Build Status](https://app.travis-ci.com/KarnaukhovKirill/job4j_cars.svg?branch=main)](https://app.travis-ci.com/KarnaukhovKirill/job4j_cars)
