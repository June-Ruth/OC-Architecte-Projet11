plugins {
	java
	id("org.springframework.boot") version "3.2.0"
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

extra["springCloudVersion"] = "2023.0.0"
extra["testcontainers.version"] = "1.19.3"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.apache.commons:commons-csv:1.10.0")
	implementation("commons-io:commons-io:2.15.1")
	implementation("org.json:json:20231013")
	implementation("com.google.guava:guava:33.0.0-jre")
	implementation("com.graphhopper:graphhopper-core:8.0")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")
	runtimeOnly("com.mysql:mysql-connector-j")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	testImplementation("org.testcontainers:junit-jupiter")
	testImplementation("org.testcontainers:mysql")
}

tasks.withType<Test> {
	useJUnitPlatform()
	minHeapSize = "512m"
	maxHeapSize = "3096m"
}
