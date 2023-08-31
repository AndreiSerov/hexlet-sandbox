plugins {
    id("com.github.ben-manes.versions") version "0.39.0"
    id("com.adarshr.test-logger") version "3.1.0"
    id("org.springframework.boot") version "2.7.0"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.liquibase.gradle") version "2.0.4"
    id("org.siouan.frontend-jdk11") version "6.0.0"
    id("org.springdoc.openapi-gradle-plugin") version "1.4.0"
    java
    checkstyle
    jacoco
    application
}

val jwt = "0.11.5"

object Dependencies {
    val queryDsl = "com.querydsl:querydsl-jpa:5.0.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

application {
    mainClass.set("org.example.Main")
}

// Liquibase plugin configuration
liquibase {
    activities.register("main") {
        arguments = mapOf(
            "logLevel" to "info",
            "changeLogFile" to "src/main/resources/db/changelog/changelog-master.yml",
            "url" to "jdbc:h2:./db",
            "username" to "sa",
            "password" to "sa",
            "referenceUrl" to "hibernate:spring:org.example.domain" +
                // Указываем диалект
                "?dialect=org.hibernate.dialect.H2Dialect" +
                // Указываем правила именования таблиц и столбцов,
                // чтобы они соответствовали правилам Spring
                "&hibernate.physical_naming_strategy=org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy"
        )
    }
    runList = "main"
}

dependencies {
    implementation("org.jetbrains:annotations:24.0.1")
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.data:spring-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.liquibase:liquibase-gradle-plugin:2.1.1")
    implementation("org.postgresql:postgresql:42.5.0")
    implementation("org.mapstruct:mapstruct:1.5.3.Final")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("io.jsonwebtoken:jjwt-api:$jwt")
    implementation("io.jsonwebtoken:jjwt-jackson:$jwt")
    implementation(Dependencies.queryDsl)
    implementation("org.springdoc:springdoc-openapi-ui:1.6.12")
    implementation("com.rollbar:rollbar-spring-boot-webmvc:1.8.1")

    annotationProcessor("org.hibernate.javax.persistence:hibernate-jpa-2.1-api:1.0.2.Final")
    annotationProcessor("com.querydsl:querydsl-apt:5.0.0:jpa")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.3.Final")

    implementation("org.liquibase:liquibase-core:4.17.1")
    implementation("org.liquibase.ext:liquibase-hibernate5:4.17.0")
    liquibaseRuntime(sourceSets.getByName("main").output)
    liquibaseRuntime(sourceSets.getByName("main").runtimeClasspath)

    runtimeOnly("com.h2database:h2:2.1.214")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:$jwt")

    testImplementation("org.junit.jupiter:junit-jupiter:5.9.0")
    testImplementation("org.mockito:mockito-inline:4.8.1")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

}

tasks {
    create("stage").dependsOn("build", "installDist")

    installDist {
        mustRunAfter(clean)
    }


    diffChangeLog {
        dependsOn("compileJava")
    }

    withType<JavaCompile> {
        options.release.set(17)
        options.compilerArgs.add("-Aproject=${project.group}/${project.name}")
    }

    withType<Checkstyle>().configureEach {

        reports {
            xml.required.set(true)
            html.required.set(true)
        }
    }

    withType<Test> {
        useJUnitPlatform()
        finalizedBy(jacocoTestReport)
    }

    jacocoTestReport {
        dependsOn(test)
        reports {
            xml.required.set(true)
            csv.required.set(false)
            html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
        }
    }
}

/*
// Сборка фронтенда.
// Выполняется только если ничего не изменилось.
// *****************************************************************************
frontend {
    nodeVersion.set("16.13.1")
    installScript.set("install --without-ssl --insecure i ethers")
    assembleScript.set("run build")
    packageJsonDirectory.set(file("${projectDir}/frontend"))
}

val configFrontendTasks = closureOf<Task> {
    inputs.files(fileTree("$projectDir/frontend").exclude("build", "node_modules"))
    outputs.dir("$buildDir/resources/main/static")
}

tasks.assembleFrontend {
    configure(configFrontendTasks)

    doLast {
        copy {
            from("$projectDir/frontend/build")
            into("$buildDir/resources/main/static")
        }
    }
}

tasks.installFrontend {
    configure(configFrontendTasks)
}

 */