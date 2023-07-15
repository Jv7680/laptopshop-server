package com.tanphi.laptopshop.service;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.generics.UpdatesHandler;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.LongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service

public class MyTelegramBot  extends TelegramLongPollingBot {
	 @Value("${telegram.bot.username}")
	    private String botUsername;

	    @Value("${telegram.bot.token}")
	    private String botToken;

	    @Override
	    public void onUpdateReceived(Update update) {
	        // Xử lý các tin nhắn nhận được từ Telegram tại đây
	    }

	    @Override
	    public String getBotUsername() {
	        return botUsername;
	    }

	    @Override
	    public String getBotToken() {
	        return botToken;
	    }

	    public void sendTelegramMessage(String message) {
	        SendMessage sendMessage = new SendMessage();
	        sendMessage.setChatId("-855376004"); // Thay CHAT_ID bằng ID của chat mà bạn muốn gửi tin nhắn tới

	        sendMessage.setText(message);

	        try {
	            execute(sendMessage);
	        } catch (TelegramApiException e) {
	            e.printStackTrace();
	        }
	    }
	}

