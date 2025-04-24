package com.anirudh.WhatsAppClone.file;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileService {
    public String saveFile(MultipartFile file, String senderId) {
        return null;
    }
}
