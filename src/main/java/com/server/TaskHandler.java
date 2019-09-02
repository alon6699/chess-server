package com.server;

import java.lang.reflect.Method;
import java.util.HashMap;
import javax.json.Json;
import javax.json.JsonArrayBuilder;

public class TaskHandler {
    public static final HashMap<String, Method> taskList = new HashMap<>();

    @TaskAnnotation("start")
    public static String processTaskOne() {
        Object[][] x = new Object[8][8];
        JsonArrayBuilder boardBuilder = Json.createArrayBuilder();
        for (Object[] row: x) {
            JsonArrayBuilder rowBuilder = Json.createArrayBuilder();
            for (Object colum: row) {
                rowBuilder.add(Json.createObjectBuilder()
                .add("name", "queen")
                .add("isWhite", false).build());
            }
            boardBuilder.add(rowBuilder);
        }
        return Json.createObjectBuilder().add("board", boardBuilder).build().toString();
    }

    @TaskAnnotation("task2")
    public static void processTaskTwo() {
        System.out.println("task2");
    }
} 