PROBLEM SOLUTION 

PROBLEM[PL]:
Napisać aplikację webową, która będzie pobierała listę walut z adresu:
https://openexchangerates.org/api/currencies.json
A następnie będzie wyświetlała dla dwóch dowolnie wybranych przez użytkownika 
aktualny kurs wymiany z serwisu alpha vantage.
Aplikacja ma także pokazywać wykres liniowy zmian tygodniowych, miesięcznych i rocznych dla wybranych walut.
Ma być także możliwość wyświetlenia linii trendu.
User ma zaznaczyć w aplikacji, czy chce widzieć te linie czy też nie.

PROBLEM[ENG]
Write a web application that downloads a list of currencies from the following address:
https://openexchangerates.org/api/currencies.json
And then it displays for two freely selected by the user
current exchange rate from alpha vantage.
The application is also to show a line chart of weekly, monthly and yearly changes for selected currencies.
It should also be possible to display the trend line.
The user should indicate in the application whether he wants to see these lines or not.

Endpoints:
/currency-exchange
/chart

Spring Boot
