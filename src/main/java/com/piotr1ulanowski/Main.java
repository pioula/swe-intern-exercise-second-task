package com.piotr1ulanowski;

import com.piotr1ulanowski.Server.Server;

public class Main {
    public static void main(String[] args) {
        Server server = new Server(args[1]);
        server.startSimulation();
    }
}