package com.example.webrtc.domain.webChat.Controller;

import com.example.webrtc.domain.webChat.dto.ChatRoom;
import com.example.webrtc.domain.webChat.service.ChatService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;

    @PostMapping
    public ChatRoom createRoom(@RequestParam String name){
        return chatService.createRoom(name);
    }

//    @GetMapping
//    public List<ChatRoom> findAllRooms(){
//        return chatService.findAllRoom();
//    }
}
