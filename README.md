
# The Asgard Game API

API for creating mini-games in minecraft.
Made by [The Asgard](https://asgrad.fun/) with love 💙 

[discord-invite]: https://discord.gg/QXSGvGrzDj
[discord-shield]: https://discord.com/api/guilds/646285836500860929/widget.png

[discord]: https://img.shields.io/badge/Our-discord-blue?style=for-the-badge&logo=discord

[version]: https://img.shields.io/badge/Version-UNSTABLE-success?style=for-the-badge&logo=wiki
[download]: #how-to-use

[wiki]: https://img.shields.io/badge/-Our%20wiki-yellow?style=for-the-badge&logo=wiki
[wiki-url]: https://github.com/TheAsgard/TAGA/wiki

[docs]: https://img.shields.io/badge/Our%20documentation-v1.2.0-important?style=for-the-badge&logo=wiki
[docs-url]: https://github.com/TheAsgard/TAGA/wiki/Documentation

[minecraft]: https://img.shields.io/badge/Minecraft-1.16.5+-red?style=for-the-badge&logo=mojang-studios
[minecraft-url]: https://www.minecraft.net/

[![version][]][download]
[![wiki][]][wiki-url]
[![docs][]][docs-url]
[![minecraft][]][minecraft-url]
[![discord][]][discord-invite]

[papermc-url]: https://papermc.io/
[spigotmc-url]: https://www.spigotmc.org/
[mohist-url]: https://mohistmc.com/
[bukkit-url]: https://getbukkit.org/

## How to use

Requires [**Minecraft 1.16.5**][minecraft-url] or **higher**.
Works with [PaperMC][papermc-url], [SpigotMC][spigotmc-url], [MohistMC][mohist-url], [CraftBukkit][bukkit-url] etc. 

> #### For Maven
```xml
<dependencies>
  <dependency>
    <groupId>fun.asgard</groupId>
    <artifactId>TAGA</artifactId>
    <version>UNSTABLE</version>
  </dependency>
</dependencies>  
```

____

> #### For Gradle
```gradle
repositories {
  mavenCentral()
}
```
```gradle
dependencies {
  implementation 'fun.asgard:TAGA:UNSTABLE'
}
```

## Brief Guide

**PLEASE READ [OUR DOCUMENTATION][docs-url] ALSO! THIS GUIDE IS NOT ALL THE POSSIBILITIES OF OUR API!**

### Initialization

```java
public TAGA taga = new TAGA(this);    
```

____

### Game manager

> ##### Create the Game
> ```java
> //                                              |Get the world|         |Game name|    |Game time|
> Game game = this.taga.getGameManager().createGame(Bukkit.getWorld("world"), "ExampleGame", 5 * 60 * 1000);
> 
> // If you want when a player is kicked, he is disconnected from the game ( Default is false )
> game.setKickOnLeave(true);
> ```

> ##### Get the Game
> ```java
> //                                          |Game name|
> Game game = this.taga.getGameManager().getGame("ExampleGame");
> ```

> ##### Get all games
> ```java
> //      |Name| |Game|
> HashMap<String, Game> games = this.taga.getGameManager().getGames();
> ```

____

### Game

> ##### Connect a player to the game
> ```java
> //               |The player|
> game.connectPlayer(player);
> ```

> ##### Disconnect a player from the game
> ```java
> //                  |The player|
> game.disconnectPlayer(player);
> ```

> ##### Get game players
> ```java
> game.getPlayers();
> ```

> ##### Start the Game
> ```java
> game.start()
> ```

> ##### Stop the Game
> ```java
> // Stop the game and save the world (Write false if you don't want to save the world)
> game.stop(true)
> ```

> ##### Shutdown the Game
> ```java
> // If you don't want the GameStopEvent to work
> game.shutdown()
> ```

> ##### Creating a task for the game
> ```java
> game.runGameTask(() -> {
>   game.getPlayers().forEach(player -> player.sendMessage("1 minute of the game has passed"))
> //|Delay| |Period|
> }, 1000, 60 * 1000)
> ```

**PLEASE READ [OUR DOCUMENTATION][docs-url] ALSO! THIS GUIDE IS NOT ALL THE POSSIBILITIES OF OUR API!**</br>
**If you have any problems, write to us in the** 
[ ![discord-shield][] ][discord-invite]
