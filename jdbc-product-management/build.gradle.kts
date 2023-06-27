plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api
    compileOnly("javax.servlet:javax.servlet-api:4.0.1")
    // https://mvnrepository.com/artifact/javax.servlet/jstl
    implementation("javax.servlet:jstl:1.2")
    // https://mvnrepository.com/artifact/mysql/mysql-connector-java
    implementation("mysql:mysql-connector-java:8.0.28")
}

tasks.test {
    useJUnitPlatform()
}