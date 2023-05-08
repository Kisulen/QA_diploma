## ПРОЦЕДУРА ЗАПУСКА АВТОТЕСТОВ
1. Перед запуском тестов нужно проверить, есть ли у вас docker и образы MySQL и PostgreSQL.
2. Если нет, то нужно скачать docker по адресу https://www.docker.com/. Образы MySQL и PostgreSQL скачиваются по адресу https://hub.docker.com/. 
3. Открыть приложение docker и запустить в нём образ MySQL/PostgreSQL.
4. Открыть проект в IDEA
5. Открыть терминал
6. Запустить контейнер на докере командой docker compose up: запустится node.js, MySQL и PostgreSQL.
7. Открыть новое окно в терминале, в нем запустить jar файл командой java -jar artifacts/aqa-shop.jar. 
8. По умолчанию установлена поддержка базы MySQL. Если требуется PostgreSQL, нужно в файле application.properties в строке spring.datasource.url=jdbc:mysql://localhost:3306/app заменить mysql на postgresql и порт 3306 на порт 5432. 
8. Открыть третье окно в терминале, в нём запустить тесты командой ./gradlew clean test
9. После прогона тестов в том же окне запустить команду ./gradlew allureServe
10. В браузере откроется страница с отчетом по тестированию. 