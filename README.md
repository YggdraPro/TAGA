# The Asgard Game API

API for creating mini-games in minecraft.
Made by [The Asgard](https://asgrad.fun/) with love 💙 

[discord-invite]: https://discord.gg/QXSGvGrzDj
[discord-shield]: https://discord.com/api/guilds/646285836500860929/widget.png
[version]: https://img.shields.io/static/v1?label=Version&message=v1.0.0&color=blue
[download]: #how-to-use
[ ![discord-shield][] ][discord-invite]
[ ![version][] ][download]

## How to use

> ###### For Maven
```xml
<dependencies>
  ...
  <dependency>
    <groupId>fun.asgard</groupId>
    <artifactId>TAGA</artifactId>
    <version>1.0-SNAPSHOT</version>
  </dependency>
</dependencies>  
```
____

> ###### For Gradle
```gradle
repositories {
  mavenCentral()
}
```
```gradle
dependencies {
  ..
  implementation 'fun.asgard:TAGA:1.0-SNAPSHOT'
}
```

## Brief documentation

#### Create the Game

```java
//                     |We get the world|       |Game name|   |Game time|
Game game = new Game(Bukkit.getWorld("world"), "ExampleGame", 5 * 60 * 20);
TAGA.addGame(game);
```

#### Start the Game

```java
//                   |Game name|
TAGA.getGames().get("ExampleGame").start()
```

#### Connect a player to the game

```java
//                   |Game name|          |The player|
TAGA.getGames().get("ExampleGame").addPlayer(player);
```

#### Disconnect a player from the game

```java
//                   |Game name|          |The player|
TAGA.getGames().get("ExampleGame").removePlayer(player);
```

#### Get game players

```java
//                   |Game name|
TAGA.getGames().get("ExampleGame").getPlayers();
```
