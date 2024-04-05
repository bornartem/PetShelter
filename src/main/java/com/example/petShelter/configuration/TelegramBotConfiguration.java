package com.example.petShelter.configuration;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.DeleteMyCommands;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The class consists of logic of the project, which has
 * "TelegramBot, token, name"
 * @author Maria Sinyavskaya
 */
@Configuration
public class TelegramBotConfiguration {

    @Value("${telegram.bot.name}")
    private String botName;

    @Value("${telegram.bot.token}")
    private String token;

    @Bean
    public TelegramBot telegramBot() {
        TelegramBot bot = new TelegramBot(token);
        bot.execute(new DeleteMyCommands());
        return bot;
    }

}
