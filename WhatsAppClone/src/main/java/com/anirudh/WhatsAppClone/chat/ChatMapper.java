package com.anirudh.WhatsAppClone.chat;

import lombok.Builder;
import org.springframework.stereotype.Service;

@Service
@Builder
public class ChatMapper {
    public ChatResponse toChatResponse(Chat chat, String senderId) {

        return ChatResponse.builder()
                .id(chat.getId())
                .name(chat.getChatName(senderId))
                .unreadCount(chat.getUnreadMessages(senderId))
                .lastMessage(chat.getLastMessage())
                .isRecipientOnline(chat.getRecipient().isUserOnline())
                .senderId(chat.getSender().getId())
                .recieverId(chat.getRecipient().getId())
                .build();
    }
}
