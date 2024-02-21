plugins {
	java
	id("org.springframework.boot") version "3.2.2"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "com.study"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
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
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation ("org.springframework.boot:spring-boot-devtools")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-web")
	compileOnly("org.projectlombok:lombok")
	runtimeOnly("org.mariadb.jdbc:mariadb-java-client")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")


}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<JavaCompile> {
	options.compilerArgs.add("-parameters")
	sourceCompatibility = JavaVersion.VERSION_17.toString()
	sourceCompatibility = JavaVersion.VERSION_17.toString()
}


//tasks.withType<JavaCompile> {
//	options.compilerArgs.add("-parameters")
//	sourceCompatibility = '11.0.13'
//	targetCompatibility = '11.0.13'
//}
//compileJava {
//	options.compilerArgs += ['-parameters']
//	sourceCompatibility = '11.0.13' // Java 버전에 맞게 조정
//	targetCompatibility = '11.0.13' // Java 버전에 맞게 조정
//}

//tasks.withType(JavaCompile) {
//	options.compilerArgs << "-parameters"
//	sourceCompatibility = '11.0.13' // 사용 중인 Java 버전에 맞게 조정
//	targetCompatibility = '11.0.13' // 사용 중인 Java 버전에 맞게 조정
//}