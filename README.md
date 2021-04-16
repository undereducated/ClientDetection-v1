# ClientDetection-v1

ClientDetection is a simple Spigot plugin to view a players client version and brand, reliant on the [ViaVersion API](https://www.spigotmc.org/resources/viaversion.19254/), and my custom API [UndereducatedAPI](https://gofile.io/d/DBl4Bu) used for my public, and upcoming plugins.

## Commands

ClientDetection is really simple and light weight, all you have to do is `/client [online player]` to view someones client version and brand. The prefix is completely customizable within the configuration, you can also reload the config by doing `/client reload`.

## Permissions

`clientdetection.use` - Allows you to view your own client brand<br>
`clientdetection.viewother` - Allows you to view other peoples client brand<br>
`clientdetection.*` - All ClientDetection permissions

### Errors upon boot

If you get an error upon boot, make sure the size of the files are correct to avoid corruption. 
- ClientDetection is `25.2k KB`
- UndereducatedUtils is `23.6 KB`
