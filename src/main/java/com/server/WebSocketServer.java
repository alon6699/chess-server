package com.server;

import java.io.*;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/game")
public class WebSocketServer 
{
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("onOpen::" + session.getId());     
    }
    @OnClose
    public void onClose(Session session) {
        System.out.println("onClose::" +  session.getId());
    }
    
    @OnMessage
    public void onMessage(String message, Session session) {
        Object[][] x = new Object[8][8];
        System.out.println("onMessage::From=" + session.getId() + " Message=" + message);
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
        try {
            session.getBasicRemote().sendText(Json.createObjectBuilder().add("board", boardBuilder).build().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @OnError
    public void onError(Throwable t) {
        System.out.println("onError::" + t.getMessage());
    }
}
