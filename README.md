## ADS-site.app ##
# Проект платформы по перепродаже вещей #


**Команда Java-разработчиков:**

[Александр Харитонов](https://github.com/Alexsand74 "Alexsand74")\
[Валерий Крятов](https://github.com/ValeriyKryatov "ValeriyKryatov")


## Backend-часть проекта реализует следующий функционал: ##

- Авторизация и аутентификация пользователей.
- Распределение ролей между пользователями: пользователь и администратор.
- CRUD для объявлений на сайте: администратор может удалять или редактировать все объявления, а пользователи — только свои.
- Возможность для пользователей оставлять комментарии под каждым объявлением.
- Поиск объявлений по заголовку.
- Показ и сохранение картинок объявлений, а также аватарок пользователей.


## Спецификация ##

https://github.com/ValeriyKryatov/SitesForResaleOfThings/blob/main/openapi.yaml


**Чтобы запустить frontend с помощью установленного Docker, нужно открыть командную строку (или терминал) и выполнить следующую команду:**

docker run -p 3000:3000 ghcr.io/dmitry-bizin/front-react-avito:v1.21

**После выполнения команды frontend запустится на порту 3000 и можно будет зайти на него через браузер по адресу:**

http://localhost:3000


## Использован следующий стек технологий: ##

Java11\
Spring Boot\
SpringDoc\
Swagger UI\
Hibernate\
PostgreSQL\
Lombok\
JUnit\
Mockito\
Maven\
Liquibase\
Spring Security\
Docker


## Структура базы данных: ##

![](https://github.com/ValeriyKryatov/SitesForResaleOfThings/blob/feature-Valera/Структура%20БД.jpg)