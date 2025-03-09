# Java 2D Game

This is a small 2D game made from scratch in Java, without using any external libraries.

## Features
- Simple 2D gameplay
- Sound effects and background music
- Basic game mechanics implemented without third-party dependencies

## Requirements
- Java Development Kit (JDK) 8 or higher
- `make` (for build automation)

## How to Build and Run

### 1. Compile the Game
Run the following command to compile the game:
```sh
make
```

### 2. Run the Game
After compiling, start the game using:
```sh
make run
```

### 3. Create a JAR File
To package the game into a JAR file, use:
```sh
make create-jar
```

### 4. Run the JAR File
Once the JAR file is created, you can run the game with:
```sh
make run-jar
```

## Project Structure
```
project-root/
├── src/
│   ├── main/
│   │   ├── GamePanel.java
│   │   ├── Main.java
│   │   ├── Sound.java
│   │   ├── ... (other game files)
├── assets/
│   ├── Sound/
│   │   ├── BlueBoyAdventure.wav
│   │   ├── coin.wav
│   │   ├── powerup.wav
│   │   ├── unlock.wav
│   │   ├── fanfare.wav
├── Makefile
├── README.md
```

## Troubleshooting
- Ensure your Java version is up to date by running:
  ```sh
  java -version
  ```
- If you encounter issues with sound files, make sure they are located in the correct `assets/Sound/` folder.
- Check the `Makefile` for any incorrect paths or configurations.

## License
This project is free to use and modify. Feel free to enhance and expand it!

Enjoy the game! 🎮
