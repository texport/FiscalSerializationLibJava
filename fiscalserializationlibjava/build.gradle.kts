plugins {
    id("java")
}

group = "com.mybrain.kkmlib"
version = "1.0"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    implementation("com.github.texport:kgdkkmproto:dev-SNAPSHOT")
    implementation("com.google.protobuf:protobuf-java:4.28.2")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}