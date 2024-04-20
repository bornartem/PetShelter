package com.example.petShelter.command.home_improvement_cat_buttons;

import com.example.petShelter.command.Command;
import com.example.petShelter.service.TelegramBotClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("/homeImprovementForCatWithDisabilities")
public class HomeImprovementForCatWithDisabilities implements Command {
    private final TelegramBotClient telegramBotClient;

    @Autowired
    public HomeImprovementForCatWithDisabilities(TelegramBotClient telegramBotClient) {
        this.telegramBotClient = telegramBotClient;
    }

    private final static String WITH_DISABILITIES = "Если у питомца проблемы со зрением, " +
            "рекомендуется как можно реже проводить перестановку мебели, так как это затруднит его ориентацию в пространстве." +
            " Также не стоит без особой необходимости менять местоположение мисок с водой и кормом, подстилки, лотка. " +
            "На полу и в пределах досягаемости нельзя оставлять вещи, в которых питомец может запутаться " +
            "(например, одежду, полиэтиленовые пакеты-майки и т.д.). Доступ к лестнице, камину, нагревательным приборам и т.д. " +
            "должен быть заблокирован." + " Уход за питомцами с ограниченной подвижностью: Для таких животных, в первую очередь, " +
            "необходимо обеспечить удобство передвижения по территории постоянного проживания. Если питомец волочит заднюю часть туловища, " +
            "необходимо убрать с пола ковры, которые могут препятствовать движению. При этом для защиты конечностей от образования " +
            "мозолей потребуется приобрести специальные фиксирующиеся накладки. Решить проблему туалета помогут специальные подгузники и " +
            "одноразовые пеленки.";

    @Override
    public void execute(Long chatId) {
        telegramBotClient.sendMessage(chatId, WITH_DISABILITIES);
    }
}
