# The Asgard Game API

API for creating mini-games in minecraft.
Made by [The Asgard](https://asgrad.fun/) with love 💙 

[discord-invite]: https://discord.gg/QXSGvGrzDj
[discord-shield]: https://discord.com/api/guilds/646285836500860929/widget.png
[version]: https://img.shields.io/static/v1?label=Version&message=v1.0.1&color=blue
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
    <version>v1.0.1</version>
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
  implementation 'fun.asgard:TAGA:v1.0.1'
}
```

## Brief documentation

#### Initialization

```java
public static TAGA taga;

@Override
public void onEnable() {
  taga = new TAGA(this);
}  
        
```

#### Create the Game

```java
//                            |We get the world|       |Game name|    |Game time|
Game game = taga.createGame(Bukkit.getWorld("world"), "ExampleGame", 5 * 60 * 1000);

// If you want when a player is kicked, he is disconnected from the game ( Default is false )
game.setKickOnLeave(true);
```

#### Start the Game

```java
//                   |Game name|
taga.getGames().get("ExampleGame").start()
```

#### Creating a task for the game

```java
//                   |Game name|
taga.getGames().get("ExampleGame").runGameTask(() -> {
  Game game = taga.getGames().get("ExampleGame");
  game.getPlayers().forEach(player -> player.sendMessage("1 minute of the game has passed"))
// |Delay| |Period|
},  1000,   60 * 1000)
```

#### Connect a player to the game

```java
//                   |Game name|              |The player|
taga.getGames().get("ExampleGame").connectPlayer(player);
```

#### Disconnect a player from the game

```java
//                   |Game name|                |The player|
taga.getGames().get("ExampleGame").disconnectPlayer(player);
```

#### Get game players

```java
//                   |Game name|
taga.getGames().get("ExampleGame").getPlayers();
```
