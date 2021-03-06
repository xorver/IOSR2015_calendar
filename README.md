About
===

Kalendarz z geolokacją, zintegrowany z facebookiem, Dostępny na: https://calendarplus.herokuapp.com/

Getting Started
===

Lokalna baza danych
======

1. Pobierz MongoDB ze strony:
https://www.mongodb.org/downloads

2. Stwórz katalog na dane /data/db lub C:\data\db (Windows) - sciezka może być zmieniona za pomoca opcji --dbpath

3. Dodaj mongo/bin do PATH

5. Uruchom mongo za pomoca komendy:
```bash
   $ mongod
```

6. Polacz sie z baza i utworz uzytkownika:
```bash
   $ mongo heroku_app35228146
   $ db.createUser(
     {
       user: "admin",
       pwd: "admin",
       roles: [ { role: "userAdmin", db: "heroku_app35228146" } ]
     }
   )
```

7. Uruchamiając aplikację ze Springowym profilem dev (patrz run configuration Redis), 
aplikacja połączy się z bazą pod adresem localhost:27017, adres ten można zmienić modyfikując plik src/main/resources/mongo.dev.config
 
Serwer
======

Aplikacja integruje się z IDE Intellij jako projekt typu SpringMVC.

W celu uruchomienia serwera lokalnie należy wykonać:

```bash
   $ mvn package
   $ java -jar target/dependency/webapp-runner.jar target/*.war
```
Dokładniejsze instrukcje znajdują się na stronie:
https://devcenter.heroku.com/articles/java-webapp-runner

Na heroku aplikacja deploy'uje się automatycznie po zmianie brancha master.

Aplikacja webowa
======

Należy pobrać i zainstalować NodeJS (http://nodejs.org/).
Następnie należy wykonać z głównego katalogu aplikacji komendę:

```bash
   $ npm install
```

Powoduje ona:
* zainstalowanie transpilera coffeescript oraz bowera w katalogu node_modules
* pobranie wymaganych zależności i umieszczenie ich w katalogu /src/main/webapp/bower_components
* skompilowanie istniejacych plików coffee.

W celu samodzielnego pobrania zależności wystarczy wykonać komendę:

```bash
   $ ../../../node_modules/bower/bin/bower install
```
z katalogu, w którym znajduje sie plik bower.json (/src/main/webapp).

Należy pobrać i zainstalować IntelliJ (https://www.jetbrains.com/idea/download/) oraz zainstalować wtyczki:
* AngularJS
* File Watchers
* NodeJS

W celu automatycznej kompilacji plików .coffee wymagane jest skonfigurowanie watchera CoffeeScript. W tym celu należy:
- wybrać z menu File/Settings/File Watchers
- kliknąć '+' i wybrać 'CoffeeScript'
- pole 'Program' powinno zawierać ścieżkę do zainstalowanego wcześniej transpilera. Jeżeli nie - należy podać odpowiednią ścieżkę.

Redis
======

W celu umożliwienia współdzielenia sesji na różnych nodach klastra, serwer korzysta 
z bazy Redis. Informacje połączenia pobierane są ze zmiennej środowiskowej
exportowanej przez heroku: REDISTOGO_URL. 

Na potrzeby developmentu i szybkiego uruchamiania aplikacji na jednym węźle,
można skorzystać z wbudowanej bazy Redis, która aktywuje się po włączeniu profilu
springowego "dev". Można to zrobić np. poprzez dodanie:

```
   Intellij / Run / Edit Configurations... / Tomcat Server / <config_name> / Startup/Connection / Environment Variables / Add
   spring.profiles.active=dev
```

Facebook
======

Po uruchomieniu aplikacji z profilem Springowym "dev", aby można było zalogować się
z użyciem facebooka, server musi znajdować się pod adresem: http://localhost:8081

W przypadku heroku konfiguracja facebook wskazuje na inną aplikację, która posiada
poprawny adres: https://calendarplus.herokuapp.com/
