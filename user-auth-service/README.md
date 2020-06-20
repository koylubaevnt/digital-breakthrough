# Работа с пользователями

### Как собратьб проект

Зайти в директорию проекта **digital-breakthrough**.
Запустить команду:
 в Linux: "./gradlew clean build"
 в Windows: "gradlew.bat clean build"

Результатом работы данной команды будут собранные артефакты в папке
 **user-auth-service\build\libs** 

### Как запустить приложение

Для запуска приложения достаточно выполнить команду:
java -jar user-auth-service.jar

Запуск в демо-режиме:
java -jar user-auth-service-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev 