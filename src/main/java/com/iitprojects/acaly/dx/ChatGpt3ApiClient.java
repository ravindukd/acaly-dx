package com.iitprojects.acaly.dx;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ChatGpt3ApiClient {

    // Your OpenAI API key or token
    private static final String API_KEY = "";

    // API endpoint for GPT-3
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    /**
     *
     * @param messages
     * @return
     * @throws IOException
     */
    public static String callGpt3ChatApi(List<ChatMessage> messages) throws IOException {
        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set the HTTP request properties
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        // Create the request body with the messages array
        StringBuilder requestBodyBuilder = new StringBuilder();
        requestBodyBuilder.append("{\n");
        requestBodyBuilder.append("    \"model\": \"gpt-3.5-turbo\",\n");
        requestBodyBuilder.append("    \"messages\": [\n");
        for (int i = 0; i < messages.size(); i++) {
            ChatMessage message = messages.get(i);
            requestBodyBuilder.append("        {\n");
            requestBodyBuilder.append("            \"role\": \"").append(message.getRole()).append("\",\n");
            requestBodyBuilder.append("            \"content\": \"").append(message.getContent()).append("\"\n");
            requestBodyBuilder.append("        }");
            if (i < messages.size() - 1) {
                requestBodyBuilder.append(",\n");
            }
        }
        requestBodyBuilder.append("\n    ]\n");
        requestBodyBuilder.append("}");

        System.out.println(requestBodyBuilder);

        // Send the request
        try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
            wr.writeBytes(requestBodyBuilder.toString());
            wr.flush();
        }

        // Get the response
        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = in.readLine()) != null) {
                    response.append(line);
                }

                return response.toString();
            }
        } else {
            throw new IOException("API call failed with response code: " + responseCode);
        }
    }

    public static void main(String[] args) {
        List<ChatMessage> messages = List.of(
                new ChatMessage("system", "You are a helpful assistant."),
                new ChatMessage("user", "Hello!")
        );

        try {
            String response = callGpt3ChatApi(messages);
            System.out.println("GPT-3 API response:\n" + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String extractFirstContent(String response) {
        // Find the starting index of the content
        int contentIndex = response.indexOf("\"content\":");

        if (contentIndex != -1) {
            // Find the starting and ending index of the content value
            int startIndex = response.indexOf("\"", contentIndex + 10); // Adding 10 to skip the "content" key
            int endIndex = response.indexOf("\"", startIndex + 1);

            if (startIndex != -1 && endIndex != -1) {
                // Extract the content value
                String content = response.substring(startIndex + 1, endIndex);
                return content;
            }
        }

        return null; // Content not found or response format is invalid
    }

}

class ChatMessage {

    private String role;
    private String content;

    public ChatMessage(String role, String content) {
        this.role = role;
        this.content = content;
    }

    public String getRole() {
        return role;
    }

    public String getContent() {
        return content;
    }
}
