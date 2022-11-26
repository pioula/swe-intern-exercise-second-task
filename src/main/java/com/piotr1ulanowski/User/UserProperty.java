package com.piotr1ulanowski.User;

import java.util.concurrent.Semaphore;

public class UserProperty {
    private String name;
    private transient Integer timestamp;
    private final transient Semaphore forUpdate;

    public UserProperty(String name, Integer timestamp) {
        this.name = name;
        this.timestamp = timestamp;
        this.forUpdate = new Semaphore(1);
    }

    public String getName() {
        return name;
    }

    public void setIfNewer(UserProperty newProperty) {
        try {
            forUpdate.acquire(1);
            if (this.timestamp < newProperty.timestamp) {
                this.name = newProperty.name;
                this.timestamp = newProperty.timestamp;
            }
            forUpdate.release(1);
        }
        catch (InterruptedException e) {
            // We do not expect interrupted threads.
            e.printStackTrace();
        }
    }
}
