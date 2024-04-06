package com.example.petShelter.command;

import com.example.petShelter.service.TelegramBotClient;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import org.springframework.stereotype.Component;

@Component("/info")
public class InfoCommand implements Command{
    private final static String INFO_MESSAGE = "Мы объединились для спасения и помощи нуждающимся животным с улиц, " +
            "лечили их, проходили долгий путь восстановления и искали для них семью. Стали присоединяться неравнодушные " +
            "люди! Нас становилось больше. Но, самое главное, росло буквально в геометрической прогрессии количество тех," +
            " кому удалось помочь! Они находили новых хозяев, а мы брали под опеку все новых и новых нуждающихся. " +
            "Сегодня можно смело сказать, что нами накоплен колоссальный опыт и знания, которые можно назвать " +
            "в некотором смысле «профессиональными». Налажено сотрудничество с клиниками, найдено много " +
            "единомышленников, что позволило принять решение о переходе на другой качественный уровень. ";

    private TelegramBotClient telegramBotClient;

    public InfoCommand (TelegramBotClient telegramBotClient) {
        this.telegramBotClient = telegramBotClient;
    }
    @Override
    public void execute(Message message, CallbackQuery callbackQuery) {
        telegramBotClient.sendMessage(message.chat().id(), INFO_MESSAGE);

    }
}
