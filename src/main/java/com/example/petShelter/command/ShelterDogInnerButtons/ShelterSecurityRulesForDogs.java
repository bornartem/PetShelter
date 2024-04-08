package com.example.petShelter.command.ShelterDogInnerButtons;

import com.example.petShelter.command.Command;
import com.example.petShelter.service.SheltersService;
import com.example.petShelter.service.TelegramBotClient;
import org.springframework.stereotype.Component;


/**
 * Class which shows the information about a dog shelter security rules on territory of shelter.
 * @author  Khilola Kushbakova
 */


@Component ("/ShelterSecurityRulesForDogs")
public class ShelterSecurityRulesForDogs implements Command {
    private final TelegramBotClient telegramBotClient;

    private final SheltersService sheltersService;

    private final static long DOG_SHELTER_ID = 1;
    private final static String DOG_SHELTER_RULES = "Запрещается:\n" +
            "Самостоятельно открывать выгулы и вольеры без разрешения работника приюта.\n" +
            "Кормить животных. Этим Вы можете спровоцировать драку. Угощения разрешены только постоянным опекунам и волонтерам, во время прогулок с животными на поводке.\n" +
            "Оставлять после себя мусор на территории приюта и прилегающей территории.\n" +
            "Подходить близко к вольерам и гладить собак через сетку на выгулах. Животные могут быть агрессивны!\n" +
            "Кричать, размахивать руками, бегать между будками или вольерами, пугать и дразнить животных.\n" +
            "Посещение приюта для детей дошкольного и младшего школьного возраста без сопровождения взрослых.\n" +
            "Нахождение на территории приюта детей среднего и старшего школьного возраста без  сопровождения взрослых или письменной справки-разрешения от родителей или законных представителей.\n" +
            "Самостоятельно заходить в кошатник без разрешения сотрудников приюта.\n" +
            "Подходить к лошади без разрешения работника приюта. Угощать лошадь можно только в присутствие работника приюта.\n" +
            "Посещение приюта в состоянии алкогольного, наркотического опьянения.\n" +
            "Если Вы являетесь постоянным волонтером приюта, опекуном или Вам разрешили прогулку с животным:\n" +
            "  \n" +
            "Пожалуйста, соблюдайте очередь - погулять хотят все!  Уточните, когда Вы можете выйти и сколько по времени будет длиться прогулка. Поводок и ошейник надевайте в вольере и снимайте их только после того, как зайдете в вольер и закроете за собой дверь. Гуляйте только в специально отведенном для этого месте.\n" +
            " \n" +
            "Во время прогулки запрещается:\n" +
            "Выводить животное без разрешение сотрудника приюта.\n" +
            "Покидать территорию приюта.\n" +
            "Наматывать поводок на руку. Обязательно одной рукой держаться за петлю поводка.\n" +
            "Допускать близкий контакт между собаками во время выгула во избежание драк.\n" +
            "Кормить собак костями, пищевыми отходами и сладостями.\n" +
            "Отпускать животных с поводка.\n" +
            "Разбрасывать поводки, игрушки, чесалки и пр.";

    public ShelterSecurityRulesForDogs(TelegramBotClient telegramBotClient,
                              SheltersService sheltersService) {
        this.telegramBotClient = telegramBotClient;
        this.sheltersService = sheltersService;
    }

    @Override
    public void execute(Long chatId) {
        String rules = sheltersService.showShelterRules(DOG_SHELTER_ID,DOG_SHELTER_RULES);
        telegramBotClient.sendMessage(chatId, rules);
    }
}
