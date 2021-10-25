package ru.kpfu.itis.repositories;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import ru.kpfu.itis.exceptions.ConnectionLostException;
import ru.kpfu.itis.utils.APIWrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

public class DictionariesRepositoryAPIImpl implements DictionariesRepository{

    @Override
    public Map<String, String> getExperience() throws ConnectionLostException {
        return getDictionaries("experience");
    }

    @Override
    public Map<String, String> getEmployment() throws ConnectionLostException {
        return getDictionaries("employment");
    }

    @Override
    public Map<String, String> getSchedule() throws ConnectionLostException {
        return getDictionaries("schedule");
    }

    private Map<String, String> getDictionaries(String dictionaryName) throws ConnectionLostException {
        JsonObject  dictionariesData = getDictionariesData();
        Map<String, String> dictionary = new LinkedHashMap<>();
        JsonArray dictionariesArray = dictionariesData.getAsJsonArray(dictionaryName);
        if(dictionariesArray != null) {
            fillMap(dictionariesArray, dictionary);
        }
        return dictionary;
    }

    private void fillMap(JsonArray dictionariesArray, Map<String, String> dictionary){
        for (JsonElement dictionaryItem : dictionariesArray) {
            JsonObject dictionaryItemObj = dictionaryItem.getAsJsonObject();
            String id = dictionaryItemObj.get("id").getAsString();
            String name = dictionaryItemObj.get("name").getAsString();
            dictionary.put(id, name);
        }
    }

    private JsonObject getDictionariesData() throws ConnectionLostException {
        URLConnection conn = APIWrapper.getDictionariesConn();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))){;
            Gson gson = new Gson();
            return gson.fromJson(reader, JsonObject.class);
        }
        catch (IOException ex) {
            throw new ConnectionLostException("Can't access hh.ru", ex);
        }
    }
}
