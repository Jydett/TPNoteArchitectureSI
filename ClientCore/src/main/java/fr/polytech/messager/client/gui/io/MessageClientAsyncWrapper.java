package fr.polytech.messager.client.gui.io;

import fr.polytech.messager.client.gui.model.Message;

import javax.swing.*;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Consumer;

public class MessageClientAsyncWrapper {

    private final ExecutorService executorService = new ThreadPoolExecutor(1, 1, 1000, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

    private final MessagerClient toWrap;

    public MessageClientAsyncWrapper(MessagerClient toWrap) {
        this.toWrap = toWrap;
    }

    public void register(String login, String psw, Consumer<Exception> exceptionHandler, Consumer<Void> callback) {
        executorService.submit(() -> {
            try {
                toWrap.register(login, psw);
                SwingUtilities.invokeLater(() -> callback.accept(null));
            } catch (Exception e) {
                SwingUtilities.invokeLater(() -> exceptionHandler.accept(e));
            }
        });
    }

    public void getAuthToken(String login, String psw, Consumer<Exception> exceptionHandler, Consumer<String> callback) {
        executorService.submit(() -> {
            try {
                final String authToken = toWrap.getAuthToken(login, psw);
                SwingUtilities.invokeLater(() -> callback.accept(authToken));
            } catch (Exception e) {
                SwingUtilities.invokeLater(() -> exceptionHandler.accept(e));
            }
        });
    }

    public void sendMessage(String authToken, String text, Consumer<Exception> exceptionHandler, Consumer<Void> callback) {
        executorService.submit(() -> {
            try {
                toWrap.sendMessage(authToken, text);
                SwingUtilities.invokeLater(() -> callback.accept(null));
            } catch (Exception e) {
                SwingUtilities.invokeLater(() -> exceptionHandler.accept(e));
            }
        });
    }

    public void getAllMessages(Consumer<Exception> exceptionHandler, Consumer<List<Message>> callback) {
        executorService.submit(() -> {
            try {
                final List<Message> allMessages = toWrap.getAllMessages();
                SwingUtilities.invokeLater(() -> callback.accept(allMessages));
            } catch (Exception e) {
                SwingUtilities.invokeLater(() -> exceptionHandler.accept(e));
            }
        });
    }

    public void getMyMessage(String authToken, Consumer<Exception> exceptionHandler, Consumer<List<Message>> callback) {
        executorService.submit(() -> {
            try {
                final List<Message> allMessages = toWrap.getMyMessage(authToken);
                SwingUtilities.invokeLater(() -> callback.accept(allMessages));
            } catch (Exception e) {
                SwingUtilities.invokeLater(() -> exceptionHandler.accept(e));
            }
        });
    }

    public void getMessageFrom(String username, Consumer<Exception> exceptionHandler, Consumer<List<Message>> callback) {
        executorService.submit(() -> {
            try {
                final List<Message> allMessages = toWrap.getMessageFrom(username);
                SwingUtilities.invokeLater(() -> callback.accept(allMessages));
            } catch (Exception e) {
                SwingUtilities.invokeLater(() -> exceptionHandler.accept(e));
            }
        });
    }

    public void delete(String authToken, Long messageId, Consumer<Exception> exceptionHandler, Consumer<Void> callback) {
        executorService.submit(() -> {
            try {
                toWrap.delete(authToken, messageId);
                SwingUtilities.invokeLater(() -> callback.accept(null));
            } catch (Exception e) {
                SwingUtilities.invokeLater(() -> exceptionHandler.accept(e));
            }
        });
    }
}
