package com.anirudh.WhatsAppClone.chat;

import com.anirudh.WhatsAppClone.user.User;
import com.anirudh.WhatsAppClone.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;
    private final ChatMapper mapper;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<ChatResponse> getChatsByReceiverId(Authentication currentUser){
        final String userId = currentUser.getName();
        return chatRepository.findChatsBySenderId(userId)
                             .stream()
                             .map(c -> mapper.toChatResponse(c, userId))
                             .toList();
    }

    public String createChat(String senderId, String receiverId){
        Optional<Chat> existingChat = chatRepository.findChatsByReceiverIdAndSender(senderId, receiverId);
        if(existingChat.isPresent()){
            return existingChat.get().getId();
        }
        User sender = userRepository.findByPublicId(senderId)
                                              .orElseThrow(() -> new EntityNotFoundException("User with id "+senderId +" not found"));

        User receiver = userRepository.findByPublicId(receiverId)
                .orElseThrow(() -> new EntityNotFoundException("User with id "+ receiverId +" not found"));

        Chat chat = new Chat();
        chat.setSender(sender);
        chat.setRecipient(receiver);
        Chat savedChat = chatRepository.save(chat);

        return savedChat.getId();
    }
}
