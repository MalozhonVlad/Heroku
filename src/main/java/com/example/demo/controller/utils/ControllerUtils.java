package com.example.demo.controller.utils;

import com.example.demo.domain.Message;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ControllerUtils {

    public static Map<String, String> getErrors(BindingResult bindingResult) {
        Collector<FieldError, ?, Map<String, String>> collector = Collectors.toMap(
                fieldError -> fieldError.getField() + "Error", // на view будет подаватся textError, usernameError і т.д.
                FieldError::getDefaultMessage
        );

        return bindingResult.getFieldErrors().stream().collect(collector);
    }

    public static void uploadFotoFromDb(Iterable<Message> messages) {
        for (Message message1 : messages) {
            if (message1.getBytes() != null) {
                byte[] unloadBytes = new byte[message1.getBytes().length];
                int count = 0;
                for (byte b : message1.getBytes()) unloadBytes[count++] = b;
                byte[] imgBytesAsBase64 = Base64.encodeBase64(unloadBytes);
                String imgDataAsBase64 = new String(imgBytesAsBase64);
                String imgAsBase64 = "data:image/png;base64," + imgDataAsBase64;
                message1.setFilename(imgAsBase64);
            }
        }
    }

    public static void saveFileToDB(@Valid Message message,
                              @RequestParam("file") MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            Byte[] fileToLoadBytes = new Byte[file.getBytes().length];
            int count = 0;
            for (byte b : file.getBytes()) fileToLoadBytes[count++] = b;

            message.setBytes(fileToLoadBytes);
        }
    }

}
