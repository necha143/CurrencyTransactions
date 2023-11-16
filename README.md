# CurrencyTransactions with REST API

## Оглавление
* [Главная задача](#главная-задача)
* [Функциональные требования](#функциональные-требования)
* [Запросы приложения](#http-запросы)
* [Swagger-Ui](#SwaggerUi)
* [Используемые технологии](#используемые-технологии)

## Главная задача
Разработать приложение для работы с финансовыми операциями и обращению к Web-сервису сайта ЦБ РФ </br>

## Функциональные требования
1) Посредством REST запросов на загрузку финансовых операций приложение получает и записывает в БД (работающую в памяти) сведения о рублевых финансовых операциях (дата операции, описание операции, сумма операции). </br>
2) При получении REST запроса на загрузку валют, а также автоматически дважды в день по расписанию в точно указанное в настройках приложения время, приложение получает курсы валют (USD и EUR) с сайта ЦБ РФ с помощью запроса к Web-сервису http://www.cbr.ru/DailyInfoWebServ/DailyInfo.asmx </br>
3) При получении REST запроса на получение отчета о финансовых операциях за период в заданной валюте, приложение возвращает JSON сообщение со списком финансовых операций за заданный период с суммами, пересчитанными по курсу заданной валюты. </br>

## Http-запросы
__/ruble/add__   
![1](https://github.com/necha143/CurrencyTransactions/assets/113212609/e7e1b5f3-4312-4cdf-8bee-f0f8eac4fe93)

</br></br>
__/ruble/{id}__   
![2](https://github.com/necha143/CurrencyTransactions/assets/113212609/92132717-c5a9-4da6-a7a6-99e74e0eb686)

</br></br>
__/ruble__   
![3](https://github.com/necha143/CurrencyTransactions/assets/113212609/c986806a-7eff-4171-865b-269b18482b8d)

</br></br>
__/exrate/add__   
![4 1](https://github.com/necha143/CurrencyTransactions/assets/113212609/365d7070-d487-458a-b523-3c8cab1da5e9)
</br>
![4 2](https://github.com/necha143/CurrencyTransactions/assets/113212609/c010be50-8127-4470-a7bb-b6ef90b6100f)

</br></br>
__/exrate__  
![5](https://github.com/necha143/CurrencyTransactions/assets/113212609/5e52be29-e755-47ee-be8d-fa09b9e2a1b0)

</br></br>
__/ruble/{currency}/{date}__   
![6](https://github.com/necha143/CurrencyTransactions/assets/113212609/e2dc89a2-2192-4eae-9cab-32c44feed534)

</br></br>
__/transactions/{currency}/{from}/{to}__   
![7](https://github.com/necha143/CurrencyTransactions/assets/113212609/b4775a64-ee99-4a5c-b8d3-9cc42b9de560)
</br>

## SwaggerUi
![8](https://github.com/necha143/CurrencyTransactions/assets/113212609/a85672b5-97fb-4a1e-9af5-bccb4ab5e379)

## Используемые технологии 
* Java (JDK 17)
* Spring Framework
* Hibernate
* Spring Boot
* Spring Data JPA
* Spring REST API
* Swagger-ui
* JUnit
* Maven
* ModelMapper
* H2 db
* Tomcat
