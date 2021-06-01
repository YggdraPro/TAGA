# The Asgard Game API

API for games on the game server [The Asgard](https://asgrad.fun/)

How to use
===========

###### For Maven
```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>
```
```xml
<dependencies>
  <dependency>
    <groupId>com.github.TheAsgard</groupId>
    <artifactId>TAGA</artifactId>
    <version>master-SNAPSHOT</version>
  </dependency>
</dependencies>  
```
____

###### For Gradle
```gradle
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```
```gradle
dependencies {
  implementation 'com.github.TheAsgard:TAGA:master-SNAPSHOT'
}
```
