# Boardgame Engine

This project is a board game engine that supports multiple board games, including Monopoly and Snakes and Ladders. It provides a modular framework for managing players, tiles, actions, and game logic.

## Features

- **Game Modes**: Supports Monopoly and Snakes and Ladders.
- **Player Management**: Add, remove, and manage players.
- **Tile System**: Customizable tiles with different actions (e.g., teleport, rent payment).
- **Dice Management**: Roll dice and manage multiple dice.
- **Data Persistence**: Save and load game boards and player data using JSON and CSV.
- **Graphical Interface**: JavaFX-based UI for game interaction.


## Requirements

- **Java**: JDK 21 or higher
- **Maven**: For dependency management and building the project
- **JavaFX**: For the graphical interface

## Setup and Run

1. Clone the repository:
   ```sh
   git clone https://github.com/Rask4arm/Boardgame-prog-2.git
   cd boardgame-prog-2
   ```

2. Build the project using Maven:
   ```sh
   mvn clean install
   ```

3. Run the JavaFX application:
   ```sh
   mvn javafx:run
   ```

4. To run the tests:
   ```sh
   mvn test
   ```

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.
