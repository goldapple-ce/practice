package com.example.webrtc.domain.webChat.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatDTO {
    public enum MessageType{
        Enter, TALK
    }

    private MessageType type;
    private String roomId;
    private String sender;
    private String message;
    private String time;
}
