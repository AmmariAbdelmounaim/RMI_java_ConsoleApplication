# RMI Parking Management System

This project is an implementation of a Java RMI (Remote Method Invocation) system for managing a parking lot. It includes a server capable of handling parking spot reservations with three primary functions: `search`, `reserve`, and `cancel`. The system architecture allows clients to interact remotely with the server to perform these operations.

## Project Structure

The project is structured as follows:

- `Client/`: This directory contains the RMI client implementation that interacts with the parking server.
- `ParkingManager/`: Contains the RMI interface definition for the parking service.
- `ParkingManagerImpl/`: Contains the implementation of the RMI interface, providing the logic for managing parking reservations.
- `Reservation/`: This package should contain classes related to reservation data such as `Id_reservation`, `numero_place`, `nom_client`, `date_reservation`.
- `Server/`: This directory holds the RMI server setup and binding code.

## Prerequisites
- Java JDK 1.8 or later.
- IntelliJ IDEA or any Java IDE with RMI support for ease of development.

## Setup and Running

1. **Compile the source code**: Use your Java IDE or the command line to compile the source code in the `src/` directory.
2. **Start the RMI registry**: Run the RMI registry on the command line using `rmiregistry` or through the IDE.
3. **Start the server**: Run the `Server` module to initialize the RMI server and register the parking service.
4. **Run the client**: Execute the `Client` module to interact with the parking service.

## Usage

The system defines three operations:

- **Search (`search`)**: Clients can search for available parking spots.
- **Reserve (`reserve`)**: Clients can reserve an available parking spot.
- **Cancel (`cancel`)**: Clients can cancel an existing reservation.
