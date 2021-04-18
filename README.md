# ClientDetection-v1

ClientDetection is a simple Spigot plugin to view a players client version and brand, reliant on the [ViaVersion API](https://www.spigotmc.org/resources/viaversion.19254/), and my custom API [UndereducatedAPI](http://rotf.lol/undereducateapi) used for my public, and upcoming plugins.

## Commands

ClientDetection is really simple and light weight, all you have to do is `/client [online player]` to view someones client version and brand. The prefix is completely customizable within the configuration, you can also reload the config by doing `/client reload`.

## Permissions

`clientdetection.use` - Allows you to view your own client brand<br>
`clientdetection.viewother` - Allows you to view other peoples client brand<br>
`clientdetection.*` - All ClientDetection permissions

## Placeholders

As of version 1.1, ClientDetection now has placeholders.<br>
`%client_version%` - Shows the client version of the player<br>
`%client_brand%` - Shows the client brand of the player<br><br>
![in game](https://cdn.kurwa.club/files/lXUeR.png "Placeholders in game")

### Errors upon boot

If you get an error upon boot, make sure the size of the files are correct to avoid corruption. 
- ClientDetection is `17 KB`
- UndereducatedUtils is `60 KB`
