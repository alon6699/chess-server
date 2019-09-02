package com.server;
 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.glassfish.tyrus.server.Server;

public class Main {
    public static final HashMap<String, Method> taskList = new HashMap<>();
    public static void main(String[] args) {
        setTaskHandler();
        runServer();
    }

    private static void setTaskHandler() {
        Method[] classMethods = TaskHandler.class.getDeclaredMethods();
        for (Method method : classMethods) { 
            TaskAnnotation annotation = method.getAnnotation(TaskAnnotation.class);
            if (annotation != null) {                
                TaskHandler.taskList.put(annotation.value(), method);
            }
        }
    }
 
    private static void runServer() {
        Server server = new Server("localhost", 8025, "/websockets", WebSocketServer.class);
        try {
            server.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Please press a key to stop the server.");
            reader.readLine();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            server.stop();
        }
    }
}
