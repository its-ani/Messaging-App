package com.anirudh.WhatsAppClone.message;

import com.anirudh.WhatsAppClone.chat.Chat;
import com.anirudh.WhatsAppClone.chat.ChatRepository;
import com.anirudh.WhatsAppClone.file.FileService;
import com.anirudh.WhatsAppClone.file.FileUtils;
import com.anirudh.WhatsAppClone.notification.Notification;
import com.anirudh.WhatsAppClone.notification.NotificationService;
import com.anirudh.WhatsAppClone.notification.NotificationType;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;
    private final MessageMapper mapper;
    private final FileService fileService;
    private final NotificationService notificationService;

    public void saveMessage(MessageRequest messageRequest) {
        Chat chat = chatRepository.findById(messageRequest.getChatId())
                                  .orElseThrow(() -> new EntityNotFoundException("Chat not found."));

        Message message = new Message();

        message.setContent(messageRequest.getContent());
        message.setChat(chat);
        message.setSenderId(messageRequest.getSenderId());
        message.setReceiverId(messageRequest.getReceiverId());
        message.setType(messageRequest.getType());
        message.setState(MessageState.SENT);

        messageRepository.save(message);

        Notification notification = Notification.builder()
                .chatId(chat.getId())
                .messageType(messageRequest.getType())
                .content(messageRequest.getContent())
                .senderId(messageRequest.getSenderId())
                .receiverId(messageRequest.getReceiverId())
                .type(NotificationType.MESSAGE)
                .chatName(chat.getTargetChatName(message.getSenderId()))
                .build();

        notificationService.sendNotification(messageRequest.getReceiverId(), notification);
    }

    public List<MessageResponse> findChatMessages(String chatId) {
        return messageRepository.findMessagesByChatId(chatId)
                .stream()
                .map(mapper::toMessageResponse)
                .toList();

    }

    @Transactional
    public void setMessagesToSeen(String chatId, Authentication authentication) {
        Chat chat = chatRepository.findById(chatId).orElseThrow(() -> new EntityNotFoundException("Chat not found."));

        final String recipientId = getRecipientId(chat, authentication);
        messageRepository.setMessagesToSeenByChatId(chatId, MessageState.SEEN);

        Notification notification = Notification.builder()
                .chatId(chat.getId())
                .type(NotificationType.SEEN)
                .receiverId(recipientId)
                .senderId(getSenderId(chat, authentication))
                .build();

        notificationService.sendNotification(recipientId, notification);
    }

    public void uploadMediaMessage(String chatId, MultipartFile file, Authentication authentication) {
        Chat chat = chatRepository.findById(chatId).orElseThrow(() -> new EntityNotFoundException("Chat not found."));

        final String senderId = getSenderId(chat, authentication);
        final String recipientId = getRecipientId(chat, authentication);
        final String contentType = file.getContentType();
        final String filename = file.getOriginalFilename();
        final String filePath = fileService.saveFile(file, senderId);
        assert contentType != null;
        MessageType type = getMessageType(contentType, filename);

        Message message = new Message();
        message.setChat(chat);
        message.setSenderId(senderId);
        message.setReceiverId(recipientId);
        message.setType(type);
        message.setMediaFilePath(filePath);
        message.setState(MessageState.SENT);
        messageRepository.save(message);

        Notification notification = Notification.builder()
                .chatId(chat.getId())
                .type(getNotificationType(contentType, filename))
                .messageType(type)
                .senderId(senderId)
                .receiverId(recipientId)
                .media(FileUtils.readFileFromLocation(filePath))
                .build();

        notificationService.sendNotification(recipientId, notification);

    }

    private NotificationType getNotificationType(String contentType, String filename) {

        NotificationType notificationType = null;

        if ((contentType.equals("image/jpeg") || contentType.equals("image/png"))
                || (filename.endsWith(".jpg") || filename.endsWith(".jpeg") || filename.endsWith(".png"))) {
            notificationType = NotificationType.IMAGE;
        }
        else if ((contentType.equals("audio/mpeg") || contentType.equals("audio/wav") || contentType.equals("audio/aac") || contentType.equals("audio/ogg"))
                || (filename.endsWith(".mp3") || filename.endsWith(".wav") || filename.endsWith(".aac") || filename.endsWith(".ogg"))) {
            notificationType = NotificationType.AUDIO;
        }
        else if ((contentType.equals("video/mp4") || contentType.equals("video/quicktime") || contentType.equals("video/x-matroska"))
                || (filename.endsWith(".mp4") || filename.endsWith(".mov") || filename.endsWith(".mkv"))) {
            notificationType = NotificationType.VIDEO;
        }
        else {
            notificationType = NotificationType.MESSAGE; // Default fallback for normal text messages or unknown file types
        }

        return notificationType;
    }

    private MessageType getMessageType(String contentType, String filename) {

        MessageType type = null;

        if ((contentType.equals("image/jpeg") || contentType.equals("image/png"))
                || (filename.endsWith(".jpg") || filename.endsWith(".jpeg") || filename.endsWith(".png"))) {
            type = MessageType.IMAGE;
        }
        else if ((contentType.equals("audio/mpeg") || contentType.equals("audio/wav") || contentType.equals("audio/aac") || contentType.equals("audio/ogg"))
                || (filename.endsWith(".mp3") || filename.endsWith(".wav") || filename.endsWith(".aac") || filename.endsWith(".ogg"))) {
            type = MessageType.AUDIO;
        }
        else if ((contentType.equals("application/pdf"))
                || (filename.endsWith(".pdf"))) {
            type = MessageType.DOCUMENT;
        }
        return type;
    }

    private String getSenderId(Chat chat, Authentication authentication) {
        if(chat.getSender().getId().equals(authentication.getName())){
            return chat.getSender().getId();
        }

        return chat.getRecipient().getId();
    }

    private String getRecipientId(Chat chat, Authentication authentication) {
        if(chat.getSender().getId().equals(authentication.getName())){
            return chat.getRecipient().getId();
        }

        return chat.getSender().getId();
    }
}
