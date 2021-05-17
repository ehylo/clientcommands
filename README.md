# clientcommands
Adds several useful client-side commands to Minecraft

This fork removes restrictions, for example cbook can be used on any version and the book page limit is 1000000. 

When Earth adds the server check for quilt or whatever it ends up being, I will remove it because imo its so stupid. 

I will try to release within a day of when Earth does but if not just create an issue, also if you want something else unrestricted lmk and Ill see what I can do. 

## Discord
https://discord.gg/Jg7Bun7

## Installation
1. Download and run the [Fabric installer](https://fabricmc.net/use).
   - Click the "vanilla" button, leave the other settings as they are,
     and click "download installer".
   - Note: this step may vary if you aren't using the vanilla launcher
     or an old version of Minecraft.
1. Download the [Fabric API](https://minecraft.curseforge.com/projects/fabric)
   and move it to the mods folder (`.minecraft/mods`).
1. Download clientcommands from the [releases page](https://github.com/Earthcomputer/clientcommands/releases)
   and move it to the mods folder (`.minecraft/mods`).

## Contributing
1. Clone the repository
   ```
   git clone https://github.com/Earthcomputer/clientcommands
   cd clientcommands
   ```
1. Generate the Minecraft source code
   ```
   ./gradlew genSources
   ```
   - Note: on Windows, use `gradlew` rather than `./gradlew`.
1. Import the project into your preferred IDE.
   1. If you use IntelliJ (the preferred option), you can simply import the project as a Gradle project.
   1. If you use Eclipse, you need to `./gradlew eclipse` before importing the project as an Eclipse project.
1. Edit the code
1. After testing in the IDE, build a JAR to test whether it works outside the IDE too
   ```
   ./gradlew build
   ```
   The mod JAR may be found in the `build/libs` directory
1. [Create a pull request](https://help.github.com/en/articles/creating-a-pull-request)
   so that your changes can be integrated into clientcommands
   - Note: for large contributions, create an issue before doing all that
     work, to ask whether your pull request is likely to be accepted
