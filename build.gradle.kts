plugins {
	java
	id("org.springframework.boot") version "3.4.3"
	id("io.spring.dependency-management") version "1.1.7"
	id("org.openapi.generator") version "7.0.1"
}

group = "org.kirill.space.cell"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(21))
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	// Spring Boot & WebFlux
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.boot:spring-boot-starter-security")

	// Database: R2DBC + PostgreSQL + Liquibase
	implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
	runtimeOnly("org.postgresql:r2dbc-postgresql")
	implementation("org.liquibase:liquibase-core:4.30.0")

	// Monitoring and DevTools
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("io.micrometer:micrometer-registry-prometheus")

	// Docker
	developmentOnly("org.springframework.boot:spring-boot-docker-compose")

	// JWT (authentication)
	implementation("io.jsonwebtoken:jjwt:0.12.3")

	// OpenAPI / Swagger
	implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:2.5.0")

	// Lombok
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	// MapStruct
	implementation("org.mapstruct:mapstruct:1.5.5.Final")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")

	// Testing
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
	testImplementation("org.springframework.security:spring-security-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

// OpenAPI Generator
openApiGenerate {
	generatorName.set("spring")
	inputSpec.set("$projectDir/src/main/resources/api/openapi.yaml")
	outputDir.set("$buildDir/generated")
	apiPackage.set("org.kirill.space.cell.api")
	modelPackage.set("org.kirill.space.cell.dto")

	configOptions.set(
		mapOf(
			"interfaceOnly" to "true",  // Generate only API interfaces
			"useTags" to "true",        // Use tags to generate API
			"dateLibrary" to "java8"    // Use Java 8 to work with dates
		)
	)
}

sourceSets {
	main {
		java {
			srcDir("$buildDir/generated/src/main/java")
		}
	}
}

// Generate API before compilation
tasks.named("compileJava").configure {
	dependsOn("openApiGenerate")
}

// Cleaning before generating OpenAPI
tasks.named("openApiGenerate") {
	dependsOn("clean")
}
