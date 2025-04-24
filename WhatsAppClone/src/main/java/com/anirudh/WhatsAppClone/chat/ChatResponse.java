package com.anirudh.WhatsAppClone.chat;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatResponse {
    private String id;
    private String name;
    private Long unreadCount;
    private String lastMessage;
    private LocalDateTime lastMessageTime;
    private Boolean isRecipientOnline;
    private String senderId;
    private String recieverId;

}
