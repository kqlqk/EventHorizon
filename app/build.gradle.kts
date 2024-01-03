plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:3.2.1")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf:3.2.1")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.2.1")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.2.1")
    implementation("org.postgresql:postgresql:42.7.1")
    compileOnly("org.projectlombok:lombok:1.18.30")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

application {
    mainClass.set("event_horizon.Main")
}