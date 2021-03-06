package ru.kpfu.itis.repositories;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import ru.kpfu.itis.exceptions.ConnectionLostException;
import ru.kpfu.itis.exceptions.WrongVacancyParamException;
import ru.kpfu.itis.models.Vacancy;
import ru.kpfu.itis.utils.APIWrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class VacanciesRepositoryAPIImpl implements VacanciesRepository{
    private static final String[] params = {"experience", "employment", "schedule", "text", "salary", "only_with_salary"};
    private final String LINK = "https://hh.ru/vacancy/" ;

    @Override
    public Optional<List<Vacancy>> getVacanciesByParam(Vacancy vacancy) throws ConnectionLostException, WrongVacancyParamException {
        JsonObject vacanciesData = getVacanciesData(vacancy);
        return Optional.of(getVacanciesFromJsonObject(vacanciesData));
    }

    private JsonObject getVacanciesData(Vacancy vacancy) throws ConnectionLostException, WrongVacancyParamException {
        URLConnection conn = APIWrapper.getVacanciesConn(getRequestParam(vacancy));
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))){
            Gson gson = new Gson();
            return gson.fromJson(reader, JsonObject.class);
        }
        catch (IOException ex) {
            throw new ConnectionLostException("Can't access hh.ru", ex);
        }
    }

    private List<Vacancy> getVacanciesFromJsonObject(JsonObject vacanciesData){
        List<Vacancy> vacancies = new ArrayList<>();
        JsonArray vacArr = vacanciesData.getAsJsonArray("items");
        for(JsonElement vacItem : vacArr) {
            String areaStr = null;
            String requirement = null;
            String responsibility = null;
            String scheduleStr = null;

            JsonObject vacItemObj = vacItem.getAsJsonObject();
            Integer number = vacItemObj.get("id").getAsInt();
            String name = vacItemObj.get("name").getAsString();

            JsonObject area = getAsJsonObject(vacItemObj,"area");
            if(area != null) {
                areaStr = getAsString(area, "name");
            }

            JsonObject salary = getAsJsonObject(vacItemObj, "salary");
            String salaryStr = getSalary(salary);

            JsonObject snippet = getAsJsonObject(vacItemObj, "snippet");
            if(snippet != null){
                requirement = getAsString(snippet, "requirement");
                responsibility = getAsString(snippet, "responsibility");
            }

            JsonObject schedule = getAsJsonObject(vacItemObj, "schedule");
            if(schedule != null){
                scheduleStr = getAsString(schedule, "name");
            }

            vacancies.add(new Vacancy(number, name, areaStr, scheduleStr,
                    salaryStr, requirement, responsibility, LINK + number));
        }
        return vacancies;
    }

    private String getRequestParam(Vacancy vacancy) throws WrongVacancyParamException {
        if(vacancy == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Class vacancyClass = vacancy.getClass();

        for(String param: params) {
            try {
                Field field = param.equals("only_with_salary")?
                        vacancyClass.getDeclaredField("onlyWithSalary"): vacancyClass.getDeclaredField(param);
                field.setAccessible(true);
                String value = String.valueOf(field.get(vacancy));
                if (value != null && !value.equals("null") && !value.equals("")) {
                    sb.append(param)
                            .append("=")
                            .append(URLEncoder.encode(value, StandardCharsets.UTF_8))
                            .append("&");
                }
            } catch (IllegalAccessException | NoSuchFieldException ex) {
                throw new WrongVacancyParamException("Enable to access the parameters of vacancy", ex);
            }
        }
        return sb.toString();
    }

    private String getSalary(JsonObject salary){
        String from = null;
        String to = null;
        if(salary != null) {
            from = getAsString(salary, "from");
            to = getAsString(salary, "to");
        }
        if(from == null && to == null){
            return "???? ??????????????";
        } else if(from == null){
            return "???? " + to + "??????.";
        } else if(to == null){
            return "?? " + from + "??????.";
        } else {
            return from + "-" + to + "??????.";
        }
    }

    private String getAsString(JsonObject object, String str){
        return object.get(str).isJsonNull()? null : object.get(str).getAsString();
    }

    private JsonObject getAsJsonObject(JsonObject object, String str){
        return object.get(str).isJsonNull()? null: object.get(str).getAsJsonObject();
    }
}
