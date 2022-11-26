package com.piotr1ulanowski.Server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.piotr1ulanowski.Command.CommandJSONParser;
import com.piotr1ulanowski.Command.CommandUpdate;
import com.piotr1ulanowski.FIleReader.FileReader;
import com.piotr1ulanowski.User.User;
import com.piotr1ulanowski.User.UserProperty;
import com.piotr1ulanowski.User.UserPropertySerializer;
import com.piotr1ulanowski.User.UserSerializer;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private final CommandJSONParser parser;
    private final FileReader reader;
    private final ConcurrentHashMap<String, User> users; // User ID maps to user
    private ArrayList<Thread> threads;

    public Server(final String path) {
        reader = new FileReader(path);
        users = new ConcurrentHashMap<>();
        threads = new ArrayList<>();
        parser = new CommandJSONParser();
    }

    // Starts the simulation of receiving the requests.
    public void startSimulation() {
        while (reader.hasLine()) {
            CommandUpdate command = parser.deserialize(reader.readLine());
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

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(User.class, new UserSerializer())
                .registerTypeAdapter(UserProperty.class, new UserPropertySerializer())
                .create();

        System.out.println(gson.toJson(users));
    }
}
