plugins {
    java
    application
    id("io.ebean") version "12.13.0"
}

group = "org.example"
version = "1.0.0-SNAPSHOT"


repositories {
    mavenCentral()
}

dependencies {
    runtimeOnly("com.h2database:h2:2.1.212")
    annotationProcessor("io.ebean:querybean-generator:12.15.0")

    implementation("org.jetbrains:annotations:22.0.0")
    implementation("io.javalin:javalin:4.3.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.12.1")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.3.0-rc1")

    implementation("org.postgresql:postgresql:42.3.1")
    implementation("io.ebean:ebean:12.14.1")
    implementation("io.ebean:ebean-annotation:7.4")
    implementation("io.ebean:ebean-migration:12.13.0")
    implementation("io.ebean:ebean-ddl-generator:12.14.1")
    implementation("io.ebean:ebean-querybean:12.14.1")
    implementation("org.glassfish.jaxb:jaxb-runtime:2.3.5")
    implementation("org.slf4j:slf4j-simple:2.0.6")

    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
    testImplementation("io.ebean:ebean-test:12.12.1")
    testImplementation("org.assertj:assertj-core:3.22.0")
    testImplementation("com.konghq:unirest-java:3.11.09")
}

tasks {

    create("stage").dependsOn("build", "installDist")

    installDist {
        mustRunAfter(clean)
    }

    withType<JavaCompile> {
        options.release.set(17)
    }

    withType<Test> {
        useJUnitPlatform()
    }
}

val generateMigrations by tasks.registering(JavaExec::class) {
    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set("org.example.config.MigrationGenerator")
}

application {
    mainClass.set("org.example.Main")
}
