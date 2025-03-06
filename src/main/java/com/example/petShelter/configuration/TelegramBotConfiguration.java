package com.example.petShelter.configuration;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.DeleteMyCommands;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

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
        InputStream pdfFile = new ClassPathResource("files/1.pdf").getInputStream();

        PDDocument document = PDDocument.load(pdfFile);

        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        String text = pdfTextStripper.getText(document);

        document.close();
        return text;
    }

    @Bean
    public String docForGetPet() throws IOException {
        InputStream pdfFile = new ClassPathResource("files/2.pdf").getInputStream();

        PDDocument document = PDDocument.load(pdfFile);

        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        String text = pdfTextStripper.getText(document);

        document.close();
        return text;
    }

    @Bean
    public String transportingFile() throws IOException {
        InputStream pdfFile = new ClassPathResource("files/3.pdf").getInputStream();

        PDDocument document = PDDocument.load(pdfFile);

        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        String text = pdfTextStripper.getText(document);

        document.close();
        return text;
    }

    @Bean
    public String dogHandlerAdvicesFile() throws IOException {
        InputStream pdfFile = new ClassPathResource("files/7.pdf").getInputStream();

        PDDocument document = PDDocument.load(pdfFile);

        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        String text = pdfTextStripper.getText(document);

        document.close();
        return text;
    }

    @Bean
    public String dogHandlersListFile() throws IOException {
        InputStream pdfFile = new ClassPathResource("files/8.pdf").getInputStream();

        PDDocument document = PDDocument.load(pdfFile);

        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        String text = pdfTextStripper.getText(document);

        document.close();
        return text;
    }

    @Bean
    public String rejectFile() throws IOException {
        InputStream pdfFile = new ClassPathResource("files/9.pdf").getInputStream();

        PDDocument document = PDDocument.load(pdfFile);

        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        String text = pdfTextStripper.getText(document);

        document.close();
        return text;
    }

    @Bean
    public String littlePetFile() throws IOException {
        InputStream pdfFile = new ClassPathResource("files/4.pdf").getInputStream();

        PDDocument document = PDDocument.load(pdfFile);

        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        String text = pdfTextStripper.getText(document);

        document.close();
        return text;
    }

    @Bean
    public String adultPetFile() throws IOException {
        InputStream pdfFile = new ClassPathResource("files/5.pdf").getInputStream();

        PDDocument document = PDDocument.load(pdfFile);

        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        String text = pdfTextStripper.getText(document);

        document.close();
        return text;
    }

    @Bean
    public String petWithDisabilitiesFile() throws IOException {
        InputStream pdfFile = new ClassPathResource("files/6.pdf").getInputStream();

        PDDocument document = PDDocument.load(pdfFile);

        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        String text = pdfTextStripper.getText(document);

        document.close();
        return text;
    }
}
