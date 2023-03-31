package com.example.webrtc.domain.webChat.dto;

import com.example.webrtc.domain.webChat.service.ChatService;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Data
public class ChatRoom {
    private String roomId;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChatRoom(String roomId,String name){
        this.roomId = roomId;
        this.name = name;
    }

    public void handleAction(WebSocketSession session, ChatDTO message, ChatService chatService){
        if(message.getType().equals(ChatDTO.MessageType.Enter)){
            sessions.add(session);

            message.setMessage(message.getSender() + " 님이 입장하셨습니다.");
            sendMessage(message,chatService);
        }
    }

    private <T> void sendMessage(T message, ChatService chatService) {
        sessions.parallelStream().forEach(session -> chatService.sendMessage(session,message));
    }
}
