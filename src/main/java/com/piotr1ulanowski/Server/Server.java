package com.piotr1ulanowski.Server;

import com.piotr1ulanowski.Command.CommandJSONParser;
import com.piotr1ulanowski.Command.CommandUpdate;
import com.piotr1ulanowski.FIleReader.FileReader;
import com.piotr1ulanowski.User.*;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private final CommandJSONParser commandParser;
    private final UserJSONParser userParser;
    private final FileReader reader;
    private final ConcurrentHashMap<String, User> users; // User ID maps to user
    private ArrayList<Thread> threads;

    public Server(final String path) {
        reader = new FileReader(path);
        users = new ConcurrentHashMap<>();
        threads = new ArrayList<>();
        commandParser = new CommandJSONParser();
        userParser = new UserJSONParser();
    }

    // Starts the simulation of receiving the requests.
    public void startSimulation() {
        while (reader.hasLine()) {
            CommandUpdate command = commandParser.deserialize(reader.readLine());
            command.setUsers(users);
            threads.add(new Thread(command));
            threads.get(threads.size() - 1).start();
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace(); // We do not expect interruption of threads.
        }

        System.out.println(userParser.serialize(users));
    }
}
