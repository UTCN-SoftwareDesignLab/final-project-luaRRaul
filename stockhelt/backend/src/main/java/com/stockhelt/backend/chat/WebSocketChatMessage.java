package com.stockhelt.backend.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class WebSocketChatMessage {
    private String type;
    private String content;
    private long sender; //momentan las asa - in viitor trebe sa fie user/patient dto
}
