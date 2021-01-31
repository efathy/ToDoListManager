package com.equitativa.dataaccess.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionHandler {
    private static final String PERSISTENCE_UNIT_NAME = "PERSISTENCE";
    private static EntityManagerFactory factory;

    private static ConnectionHandler instance;

    private ConnectionHandler() {
    }

    public static synchronized void initialize() {
        if (instance == null) {
            instance = new ConnectionHandler();
        }
    }

    public static ConnectionHandler getInstance() {
        return instance;
    }

    public EntityManager getNewEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }

    private static EntityManagerFactory getEntityManagerFactory() {
        if (factory == null) {
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return factory;
    }

    //TODO shutdown entity manager
    private static void shutdown() {
        if (factory != null) {
            factory.close();
        }
    }
}
