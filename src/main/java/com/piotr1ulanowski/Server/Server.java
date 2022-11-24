package com.piotr1ulanowski.Server;

import com.piotr1ulanowski.Command.CommandI;
import com.piotr1ulanowski.Command.CommandJSONParser;
import com.piotr1ulanowski.FIleReader.FileReader;
import com.piotr1ulanowski.User.User;

import java.util.HashMap;

public class Server {
    private final CommandJSONParser parser = new CommandJSONParser();
    private final FileReader reader;
    private HashMap<String, User> users; // User ID maps to user

    public Server(final String path) {
        reader = new FileReader(path);
        users = new HashMap<>();
    }
    // Starts the simulation of receiving the requests.
    public void startSimulation() {
        while (reader.hasLine()) {
            CommandI command = parser.deserialize(reader.readLine());
            var broadcastMessage = command.execute(users);
            broadcastMessage.ifPresent(System.out::println);
        }
    }
}
