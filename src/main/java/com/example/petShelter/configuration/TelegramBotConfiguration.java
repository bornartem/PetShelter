package com.example.petShelter.configuration;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.DeleteMyCommands;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

/**
 * The class consists of logic of the project, which has
 * "TelegramBot, token, name"
 *
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

    @Bean
    public String meetingFile() throws Exception {
        File pdfFile = new File(".//src//main//resources//files//1.pdf");

        PDDocument document = PDDocument.load(pdfFile);

        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        String text = pdfTextStripper.getText(document);

        document.close();
        return text;
    }

    @Bean
    public String docForGetPet() throws IOException {
        File pdfFile = new File(".//src//main//resources//files//2.pdf");

        PDDocument document = PDDocument.load(pdfFile);

        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        String text = pdfTextStripper.getText(document);

        document.close();
        return text;
    }

    @Bean
    public String transportingFile() throws IOException {
        File pdfFile = new File(".//src//main//resources//files//3.pdf");

        PDDocument document = PDDocument.load(pdfFile);

        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        String text = pdfTextStripper.getText(document);

        document.close();
        return text;
    }

    @Bean
    public String dogHandlerAdvicesFile() throws IOException {
        File pdfFile = new File(".//src//main//resources//files//7.pdf");

        PDDocument document = PDDocument.load(pdfFile);

        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        String text = pdfTextStripper.getText(document);

        document.close();
        return text;
    }

    @Bean
    public String dogHandlersListFile() throws IOException {
        File pdfFile = new File(".//src//main//resources//files//8.pdf");

        PDDocument document = PDDocument.load(pdfFile);

        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        String text = pdfTextStripper.getText(document);

        document.close();
        return text;
    }

    @Bean
    public String rejectFile() throws IOException {
        File pdfFile = new File(".//src//main//resources//files//9.pdf");

        PDDocument document = PDDocument.load(pdfFile);

        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        String text = pdfTextStripper.getText(document);

        document.close();
        return text;
    }

    @Bean
    public String littlePetFile() throws IOException {
        File pdfFile = new File(".//src//main//resources//files//4.pdf");

        PDDocument document = PDDocument.load(pdfFile);

        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        String text = pdfTextStripper.getText(document);

        document.close();
        return text;
    }

    @Bean
    public String adultPetFile() throws IOException {
        File pdfFile = new File(".//src//main//resources//files//5.pdf");

        PDDocument document = PDDocument.load(pdfFile);

        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        String text = pdfTextStripper.getText(document);

        document.close();
        return text;
    }

    @Bean
    public String petWithDisabilitiesFile() throws IOException {
        File pdfFile = new File(".//src//main//resources//files//6.pdf");

        PDDocument document = PDDocument.load(pdfFile);

        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        String text = pdfTextStripper.getText(document);

        document.close();
        return text;
    }
}
