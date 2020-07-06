package pl.creazy.webapp01;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

@Component("externalApiJsonFetcher")
public final class ExternalApiJsonFetcher implements JsonFetcher{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ExternalApiJsonFetcher() {}

    @Override
    public JSONObject readJsonFromUrl(String apiUrl) {
        return handleRequest(apiUrl);
    }

    private JSONObject handleRequest(String apiUrl) {
        JSONObject jsonObject;

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode:" + responseCode);
            } else {
                try (Scanner sc = new Scanner(url.openStream()) ) {
                    StringBuilder sb = new StringBuilder();
                    while (sc.hasNext()) {
                        sb.append(sc.nextLine());
                    }
                    return new JSONObject(String.valueOf(sb));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e ) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
