plugins {
	java
	id("org.springframework.boot") version "3.2.1"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "com.medhead"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.seleniumhq.selenium:selenium-java:4.16.1")
	runtimeOnly("com.mysql:mysql-connector-j")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.testcontainers:junit-jupiter")
	testImplementation("org.testcontainers:mysql")
	implementation("org.testcontainers:selenium:1.19.4")
	implementation("org.seleniumhq.selenium:selenium-chrome-driver:4.17.0")

}

tasks.test {
	useJUnitPlatform()
	filter {
		excludeTestsMatching("*SafariTests")
		excludeTestsMatching("*EdgeTests")
		excludeTestsMatching("*MozillaTests")

	}
}
