plugins {
  id("java-library")
}

project.version="0.0.0"
project.group="learn.java.compute"

project.java {

}

project.dependencies {
  testImplementation("org.junit.jupiter", "junit-jupiter-api", "5.11.4")
  testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine", "5.11.4")
}

project.tasks.test {
  this.useJUnitPlatform()
}