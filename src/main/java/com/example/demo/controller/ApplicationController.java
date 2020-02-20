package com.example.demo.controller;

import com.example.demo.controller.utils.ControllerUtils;
import com.example.demo.domain.Message;
import com.example.demo.domain.User;
import com.example.demo.repository.MessageRepository;
import com.example.demo.service.FilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.demo.controller.utils.ControllerUtils.saveFileToDB;
import static com.example.demo.controller.utils.ControllerUtils.uploadFotoFromDb;

@Controller
@RequiredArgsConstructor
public class ApplicationController {

    private final FilterService filterService;
    private final MessageRepository messageRepository;

    @GetMapping("/helloSweaterPage")
    public String helloSweaterPage(@AuthenticationPrincipal User user,
                                   Model model) {
        model.addAttribute("user", user);
        return "helloSweaterPage";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam(required = false, defaultValue = "") String filter,
                         Model model) {

        Iterable<Message> messageByTag = filterService.findByTag(filter);

        uploadFotoFromDb(messageByTag);

        model.addAttribute("messages", messageByTag);
        return "messages";
    }

    @GetMapping("/messages")
    public String messages(Model model) {

        Iterable<Message> messages = filterService.findAll();

        uploadFotoFromDb(messages);

        model.addAttribute("messages", messages);

        return "messages";
    }

    @PostMapping("/messages")
    public String addMessages(@AuthenticationPrincipal User user,
                              @Valid Message message,
                              BindingResult bindingResult,
                              Model model,
                              @RequestParam("file") MultipartFile file) throws IOException {

        List<Message> messageList = new ArrayList<>();

        if (bindingResult.hasErrors()) {

            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("message", message);
        } else {
            saveFileToDB(message, file);

            messageList.add(message);
            user.setMessageList(messageList);

            message.setAuthor(user);
            messageRepository.save(message);
        }

        Iterable<Message> messages = messageRepository.findAll();

        uploadFotoFromDb(messages);
        model.addAttribute("messages", messages);

        return "messages";
    }




}
