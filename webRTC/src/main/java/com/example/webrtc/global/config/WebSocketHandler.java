package com.example.webrtc.global.config;

import com.example.webrtc.domain.webChat.dto.ChatRoom;
import com.example.webrtc.domain.webChat.service.ChatService;
import com.example.webrtc.domain.webChat.dto.ChatDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {
//    List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
    private final ObjectMapper mapper;
    private final ChatService chatService;

//    public void afterConnectionEstablished(WebSocketSession session){
//        sessions.add(session);
//    }


    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String payload = message.getPayload();
        log.info("payload {}", payload);

        ChatDTO chatMessage = mapper.readValue(payload, ChatDTO.class);
        log.info("session {}", chatMessage.toString());

        ChatRoom room = chatService.findRoomById(chatMessage.getRoomId());
        log.info("room {}", room.toString());

        room.handleAction(session, chatMessage, chatService);
    }
}
