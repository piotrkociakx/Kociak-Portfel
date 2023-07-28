package pl.piotrkociakx.template.helpers;

import okhttp3.*;

public class WebhookHelper {

    private final String webhookUrl;

    public WebhookHelper(String webhookUrl) {
        this.webhookUrl = webhookUrl;
    }

    public void sendMessage(String message) {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create("{\"content\":\"" + message + "\"}", mediaType);
        Request request = new Request.Builder()
            .url(webhookUrl)
            .post(body)
            .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                System.out.println("Nie mozna wyslac webhoka: " + response.message());
            }
        } catch (Exception e) {
            System.out.println("Wystapil blad podczas wysylania webhooka: " + e.getMessage());
        }
    }

    public void sendEmbed(String title, String description, String colorHex) {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        String json = "{"
            + "\"embeds\":["
            + "{"
            + "\"title\":\"" + title + "\","
            + "\"description\":\"" + description + "\","
            + "\"color\":\"" + colorHex + "\""
            + "}"
            + "]"
            + "}";
        RequestBody body = RequestBody.create(json, mediaType);
        Request request = new Request.Builder()
            .url(webhookUrl)
            .post(body)
            .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                System.out.println("Nie mozna wyslac webhoka: " + response.message());
            }
        } catch (Exception e) {
            System.out.println("Wystapil blad podczas wysylania embeda w webhooku: " + e.getMessage());
        }
    }
}
