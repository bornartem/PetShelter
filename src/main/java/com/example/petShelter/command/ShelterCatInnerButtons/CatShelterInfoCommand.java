package com.example.petShelter.command.ShelterCatInnerButtons;

import com.example.petShelter.command.Command;
import com.example.petShelter.service.SheltersService;
import com.example.petShelter.service.TelegramBotClient;
import org.springframework.stereotype.Component;

/**
 * Class which shows the information about a cat shelter after clicking the button in Telegram to info.
 * @author  Khilola Kushbakova
 */



@Component("/getCatInfo")
public class CatShelterInfoCommand implements Command {
    private final SheltersService sheltersService;
    private final static long CAT_SHELTER_ID = 1;
    private final static String CAT_INFO = "Наш приют Котодом Мурлыка был основан в сентябре 2016 года на энтузиазме людей, неравнодушных к проблемам бездомных животных. Котодом не спонсируется государством и в данный момент он существует исключительно за счет благотворительной помощи людей и личных средств его владельцев. Помещение приюта взято в аренду и требует ежемесячной оплаты как самой аренды, так и коммунальных услуг. Так же в Котодоме есть карантинное помещение, где содержатся тяжелые животные, за которыми осуществляется профессиональный медицинский уход с последующей реабилитацией, вакцинацией и стерилизацией, что так же требует немалых затрат.\n" +
            "\n" +
            "Являясь временным домом для питомцев, Котодом ставит перед собой задачи по оказанию необходимой помощи котам и кошкам, находящимся у него на попечении, такие как содержание, уход, кормление, и конечно пристройство в семьи или к людям, готовым стать друзьями для хвостатых с непростой судьбой.";
    private TelegramBotClient telegramBotClient;

    public CatShelterInfoCommand(SheltersService sheltersService, TelegramBotClient telegramBotClient) {
        this.sheltersService = sheltersService;
        this.telegramBotClient = telegramBotClient;
    }

    @Override
    public void execute(Long chatId) {
        String catShelterInfo = sheltersService.showAnimalInfoById(CAT_SHELTER_ID, CAT_INFO);
        telegramBotClient.sendMessage(chatId, catShelterInfo);
    }
}
