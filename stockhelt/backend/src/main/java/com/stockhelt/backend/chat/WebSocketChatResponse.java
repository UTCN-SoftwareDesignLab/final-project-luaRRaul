package com.stockhelt.backend.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class WebSocketChatResponse {
    public String content;
}
