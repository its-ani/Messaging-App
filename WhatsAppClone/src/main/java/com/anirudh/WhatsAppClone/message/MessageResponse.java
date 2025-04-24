package com.anirudh.WhatsAppClone.message;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageResponse {
    private Long id;
    private String content;
    private MessageType type;
    private MessageState state;
    private String receiverId;
    private String senderId;
    private LocalDateTime createdAt;
    private byte[] media;
}
