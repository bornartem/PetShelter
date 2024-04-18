package com.example.petShelter.commandTests;

import com.example.petShelter.command.InfoCommand;
import com.example.petShelter.service.TelegramBotClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class InfoCommandTest {

    @Mock
    private TelegramBotClient telegramBotClient;

    private InfoCommand infoCommand;

    private final static String INFO_MESSAGE = "Мы объединились для спасения и помощи нуждающимся животным с улиц, " +
            "лечили их, проходили долгий путь восстановления и искали для них семью. Стали присоединяться неравнодушные " +
            "люди! Нас становилось больше. Но, самое главное, росло буквально в геометрической прогрессии количество тех," +
            " кому удалось помочь! Они находили новых хозяев, а мы брали под опеку все новых и новых нуждающихся. " +
            "Сегодня можно смело сказать, что нами накоплен колоссальный опыт и знания, которые можно назвать " +
            "в некотором смысле «профессиональными». Налажено сотрудничество с клиниками, найдено много " +
            "единомышленников, что позволило принять решение о переходе на другой качественный уровень. ";

    @Test
    void executeMethodShouldSendMessage() {
        Long chatId = 123456L;
        infoCommand = new InfoCommand(telegramBotClient);

        infoCommand.execute(chatId);

        verify(telegramBotClient, times(1)).sendMessage(chatId, INFO_MESSAGE);

    }
}
