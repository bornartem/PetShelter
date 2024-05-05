package com.example.petShelter.listener;


import com.example.petShelter.command.CommandContainer;
import com.example.petShelter.command.RegisterUserCommand;
import com.example.petShelter.command.ReportCommands;
import com.example.petShelter.model.Clients;
import com.example.petShelter.model.ConversationPeople;
import com.example.petShelter.model.DailyReports;
import com.example.petShelter.model.Volunteers;
import com.example.petShelter.service.*;
import com.example.petShelter.workingWithVolunteerService.workingWithVolunteerConversationService.ConversationServiceMain;
import com.example.petShelter.workingWithVolunteerService.workingWithVolunteerConversationService.FinishedVolunteerSingUp;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendPhoto;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * The class consists of logic of the project, which has
 * the methods to send messages with "TelegramBot"
 *
 * @author Maria Sinyavskaya
 */
@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    public static String COMMAND_PREFIX = "/";

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private final TelegramBot telegramBot;
    private final CommandContainer commandContainer;

    private final TelegramBotClient telegramBotClient;

    private final FinishedVolunteerSingUp finishedSingUp;
    private final VolunteersService volunteerService;
    private final ClientsService clientsService;
    private final ConversationPeopleService conversationPeopleService;
    private final ConversationServiceMain conversationServiceMain;
    private final RegisterUserCommand registerUser;
    private final ReportCommands reportCommands;

    public TelegramBotUpdatesListener(TelegramBot telegramBot,
                                      CommandContainer commandContainer,
                                      TelegramBotClient telegramBotClient,
                                      FinishedVolunteerSingUp finishedSingUp,
                                      VolunteersService volunteerService,
                                      ClientsService clientsService,
                                      ConversationPeopleService conversationPeopleService,
                                      ConversationServiceMain conversationServiceMain,
                                      RegisterUserCommand registerUser, ReportCommands reportCommands) {
        this.telegramBot = telegramBot;
        this.commandContainer = commandContainer;
        this.telegramBotClient = telegramBotClient;
        this.finishedSingUp = finishedSingUp;
        this.volunteerService = volunteerService;
        this.clientsService = clientsService;
        this.conversationPeopleService = conversationPeopleService;
        this.conversationServiceMain = conversationServiceMain;
        this.registerUser = registerUser;
        this.reportCommands = reportCommands;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            Message message = update.message();
            if (message != null) {

                if (message.text() != null) {
                    String userText = message.text();
                    Long chatId = update.callbackQuery() != null ?
                            update.callbackQuery().message().chat().id() : message.chat().id();

                    Volunteers volunteers = volunteerService.findFirstByChatId(chatId);
                    Clients clients = clientsService.findFirstByChatId(chatId);
                    ConversationPeople people = conversationPeopleService.findByChatId(chatId);

                    if (userText.startsWith("/ответ на отчет")) {
                        conversationServiceMain.reportConversation(chatId, userText);
                    } else if (userText.startsWith(COMMAND_PREFIX)) {
                        commandContainer.process(userText, chatId, null);
                    }

                    //проверка общается ли человек, и если это так, то нужно перенаправлять сообщения
                    else if (people != null) {
                        Long opponentChatId = people.getOpponentChatId();
                        telegramBotClient.sendMessage(opponentChatId, userText);
                    } //иначе если волонтер и он продолжает регистрироваться
                    else if (volunteers != null) {
                        finishedSingUp.singUp(chatId, userText, volunteers);
                    } else if (clients != null) {
                        if (clients.getName() == null && clients.getContact() == null) {
                            registerUser.continueReg(clients, chatId, userText);
                        }
                    }
                } else if (message.photo() != null && message.caption() != null) {
                    reportCommands.saveReport(message);
                } else {
                    telegramBotClient.sendMessage(message.chat().id(),
                            "Не понимаю Вас, напишите /volunteerHelp и наши волонтеры помогут Вам.");
                }
            } else if (update.callbackQuery() != null) {
                String userText = update.callbackQuery().data();
                commandContainer.process(userText, update.callbackQuery().message().chat().id(), Arrays.asList(update));
            } else if (update.message().photo() != null) {
                String photo = update.message().photo()[0].fileId();
                //здесь код по сохранению фотографии пользователя
                telegramBot.execute(new SendPhoto(update.message().chat().id(), photo));
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

}