package pl.creazy.webapp01;

import org.json.JSONObject;

public interface JsonFetcher {
    JSONObject readJsonFromUrl(String apiUrl);
}
