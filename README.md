# Дипломный проект профессии «Тестировщик»
[![Build status](https://ci.appveyor.com/api/projects/status/q615l08cgf9bece5?svg=true)](https://ci.appveyor.com/project/Cryofbb/netology-qacourse)

## Документы
* [План автоматизации](https://github.com/Cryofbb/Netology_QAcourse/blob/master/artifacts/Plan.md)
* [Отчет по итогам тестирования](https://github.com/Cryofbb/Netology_QAcourse/blob/master/artifacts/Report.md)
* [Отчет по итогам автоматизации](https://github.com/Cryofbb/Netology_QAcourse/blob/master/artifacts/ReportAutomatization.md)

Дипломный проект представляет собой автоматизацию тестирования комплексного сервиса, взаимодействующего с СУБД и API Банка.

## Описание приложения

Приложение представляет из себя веб-сервис "Путешествие дня".

Приложение предлагает купить тур по определённой цене с помощью двух способов:
1. Обычная оплата по дебетовой карте
1. Уникальная технология: выдача кредита по данным банковской карты

Само приложение не обрабатывает данные по картам, а пересылает их банковским сервисам:
* сервису платежей (далее - Payment Gate)
* кредитному сервису (далее - Credit Gate)

Приложение должно в собственной СУБД сохранять информацию о том, каким способом был совершён платёж и успешно ли он был совершён (при этом данные карт сохранять не допускается).


## Запуск тестов

* склонировать репозиторий `git clone`
* для запуска контейнеров с MySql, PostgreSQL и Node.js использовать команду `docker-compose up -d --build` (необходим установленный Docker); чтобы образ не пересобирался каждый раз необходимо убрать флаг --build
* запуск приложения:
    * для запуска под MySQL использовать команду
    ```
    java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar ./artifacts/aqa-shop.jar
    ```
    * для запуска под PostgreSQL использовать команду
    ```
    java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app -jar ./artifacts/aqa-shop.jar
    ```
* запуск тестов:
  * MySQL
   ```
   gradlew -Ddb.url=jdbc:mysql://localhost:3306/app clean test -Dselenide.headless=true
   ```
  * PostgreSQL
   ```
   gradlew -Ddb.url=jdbc:postgresql://localhost:5432/app clean test -Dselenide.headless=true
   ```
* для получения отчета Allure ввести команду `gradlew allureReport`
* после окончания тестов завершить работу приложения (Ctrl+C), удалить контейнеры командой `docker-compose down`

## Текущий статус
- [x] Написание плана автоматизации
- [x] Подготовка тестовой среды, Докер и БД
- [x] Создание классов и объектов
- [x] Написание тестов
- [x] Создание Issue по итогам тестов
- [x] Итоговый отчет
- [x] Попытки прикрутить CI
