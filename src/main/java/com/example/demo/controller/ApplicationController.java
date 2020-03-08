package com.example.demo.controller;

import com.example.demo.controller.utils.ControllerUtils;
import com.example.demo.domain.Message;
import com.example.demo.domain.User;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.FilterService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

import static com.example.demo.controller.utils.ControllerUtils.saveFileToDB;
import static com.example.demo.controller.utils.ControllerUtils.uploadFotoFromDb;

@Controller
@RequiredArgsConstructor
public class ApplicationController {

    private final UserService userService;
    private final FilterService filterService;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @GetMapping("/helloSweaterPage")
    public String helloSweaterPage(@AuthenticationPrincipal User user,
                                   Model model) {
        model.addAttribute("user", user);
        return "helloSweaterPage";
    }

    @PostMapping("/filter")
    public String filter(@AuthenticationPrincipal User user,
                         @RequestParam(required = false, defaultValue = "") String filter,
                         Model model) {

        Iterable<Message> messageByTag = filterService.findByTag(filter);

        uploadFotoFromDb(messageByTag);

        model.addAttribute("messages", messageByTag);
        model.addAttribute("user", user);
        return "messages";
    }

    @GetMapping("/messages")
    public String messages(@AuthenticationPrincipal User user,
                           Model model) {

        Iterable<Message> messages = filterService.findAll();

        uploadFotoFromDb(messages);

        model.addAttribute("messages", messages);
        model.addAttribute("user", user);

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
        model.addAttribute("user", user);

        return "messages";
    }

    @GetMapping("myMessages")
    public String myMessages(@AuthenticationPrincipal User user,
                             Model model) {

        List<Message> messageListByUserId = messageRepository.findAllMessagesByUserId(user.getId());
        uploadFotoFromDb(messageListByUserId);
        model.addAttribute("messages", messageListByUserId);
        model.addAttribute("user", user);

        model.addAttribute("subscriptionsCount", user.getSubscriptions().size());
        model.addAttribute("subscribersCount", user.getSubscribers().size());
        return "myMessages";
    }

    @GetMapping("/user-messages/{authorId}")
    public String userMessages(@AuthenticationPrincipal User user,
                               @PathVariable("authorId") Long id,
                               Model model) {
        List<Message> messageListByUserId = messageRepository.findAllMessagesByUserId(id);
        uploadFotoFromDb(messageListByUserId);
        User authorOfMessages = userRepository.findById(id).get();

        if (authorOfMessages.getSubscribers().contains(user)) {
            model.addAttribute("isSubscriber", true);
        } else {
            model.addAttribute("isSubscriber", false);
        }

        model.addAttribute("messages", messageListByUserId);

//        model.addAttribute("user", user);

        model.addAttribute("authorId", id);
        model.addAttribute("subscriptionsCount", authorOfMessages.getSubscriptions().size());
        model.addAttribute("subscribersCount", authorOfMessages.getSubscribers().size());

        model.addAttribute("user", authorOfMessages);
        return "myMessages";
    }

    @GetMapping("/editMessage/{userId}")
    public String editMessageFromAll(@PathVariable("userId") Long id,
                                     @RequestParam(required = false) Message message,
                                     Model model) {

        Message messageById = messageRepository.findById(message.getId()).get();

        uploadFotoFromDb(Collections.singletonList(messageById));

        User user = userRepository.findById(id).get();
        model.addAttribute("user", user);
        model.addAttribute("message", messageById);
        return "editMessage";
    }

    @PostMapping("/editMessage/{userId}")
    public String editOneMessage(@AuthenticationPrincipal User currentUser,
                                 @Valid Message message,
                                 BindingResult bindingResult,
                                 Model model,
                                 @RequestParam("file") MultipartFile file) throws IOException {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            Message byId = messageRepository.findById(message.getId()).get();
            uploadFotoFromDb(Collections.singletonList(byId));
            model.addAttribute("message", byId);
            return "editMessage";
        } else {
            currentUser.getMessageList().remove(message);

            message.setText(message.getText());
            message.setTag(message.getTag());

            saveFileToDB(message, file);

            currentUser.getMessageList().add(message);

            message.setAuthor(currentUser);
            messageRepository.save(message);
        }

        List<Message> messageListByUserId = messageRepository.findAllMessagesByUserId(currentUser.getId());
        uploadFotoFromDb(messageListByUserId);
        model.addAttribute("messages", messageListByUserId);
        model.addAttribute("user", currentUser);

        model.addAttribute("subscriptionsCount", currentUser.getSubscriptions().size());
        model.addAttribute("subscribersCount", currentUser.getSubscribers().size());

        return "myMessages";
    }

    @GetMapping("/deleteMessage/{messageId}")
    public String deleteMessage(@AuthenticationPrincipal User currentUser,
                                @PathVariable("messageId") Long id,
                                Model model) {

        Message byId = messageRepository.findById(id).get();

        User byUsername = userRepository.findByUsername(currentUser.getUsername());

        List<Message> messageList = byUsername.getMessageList();

        if (messageList.contains(byId)) {
            byUsername.getMessageList().remove(byId);
            byUsername.setMessageList(messageList);
        }

        userRepository.save(byUsername);
        messageRepository.delete(byId);

        model.addAttribute("delete", "Message deleted");

        List<Message> allMessagesByUserId = messageRepository.findAllMessagesByUserId(currentUser.getId());
        uploadFotoFromDb(allMessagesByUserId);

        model.addAttribute("messages", allMessagesByUserId);
        model.addAttribute("user", currentUser);

        model.addAttribute("subscriptionsCount", currentUser.getSubscriptions().size());
        model.addAttribute("subscribersCount", currentUser.getSubscribers().size());

        return "myMessages";
    }

    @GetMapping("/user/subscribe/{userId}")
    public String subscribe(@AuthenticationPrincipal User currentUser,
                            @PathVariable("userId") Long userId) {

        User findById = userRepository.findById(userId).get();
        userService.subscribe(currentUser, findById);
        return "redirect:/user-messages/" + userId;
    }


    @GetMapping("unsubscribe/user")
    public String unsubscribe(@AuthenticationPrincipal User currentUser,
                            @PathVariable User user) {
        userService.unsubscribe(currentUser, user);
        return "myMessages" + user.getId();
    }


}
