package com.daoleen.social.vk;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 3/22/14.
 */
public class VkParser {
    private final Logger logger = Logger.getLogger(VkParser.class);
    private final VkReader reader = new VkReader();
    private final Config config = Config.getInstance();

    public void getPeoplesProfiles() {
        logger.info("Sending request to " + config.getUsersSearchUri());
        String usersByUniversity = reader.read(config.getUsersSearchUrl(), config.getUsersSearchParams());
        List<Long> userIds = getUserIdsFromUsersSearchJson(usersByUniversity);
        logger.info("Found " + userIds.size() + " users");

        if(userIds.size() > 0) {
            String peoplesProfileJson = getPeoplesProfileJson(userIdsToString(userIds));
            writeToDisk(peoplesProfileJson);
        }
    }

    public void writeToDisk(String content) {
        String filepath = String.format("src/main/resources/%s.json", config.getBasename());
        try(FileWriter writer = new FileWriter(new File(filepath))) {
            writer.write(content);
        }
        catch (IOException e) {
            System.out.println("Возникла ошибка при записи результата в файл");
            e.printStackTrace();
        }
    }

    protected List<Long> getUserIdsFromUsersSearchJson(String json) {
        List<Long> ids = new ArrayList<>(Config.getInstance().getCount());

        try {
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(json);
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            JsonObject response = jsonObject.getAsJsonObject("response");
            JsonArray items = response.getAsJsonArray("items");

            if(items != null && items.size() > 0) {
                for(JsonElement item : items) {
                    JsonObject jsonObjectUser = item.getAsJsonObject();
                    Long id = jsonObjectUser.get("id").getAsLong();
                    ids.add(id);
                }
            }
        }
        catch (NullPointerException exc) {
            logger.info("A NullPointerException was occurred");
        }

        return ids;
    }


    private String getPeoplesProfileJson(String userIds) {
        String userFields = "sex,bdate,city,country,photo_50,photo_100,photo_200_orig,photo_200,photo_400_orig," +
                "photo_max,photo_max_orig,online,online_mobile,lists,domain,has_mobile,contacts,connections,site," +
                "education,universities,schools,can_post,can_see_all_posts,can_see_audio,can_write_private_message,status," +
                "last_seen,common_count,relation,relatives,counters";

        config.setFields(userFields);
        logger.info("Sending request to " + config.getUserProfileUri(userIds));
        return reader.read(config.getUserProfileUrl(), config.getUserProfileParams(userIds));
    }

    private String userIdsToString(List<Long> userIds) {
        StringBuilder builder = new StringBuilder(userIds.size()*5);
        userIds.stream().forEach( id -> {
            builder.append(id);
            builder.append(',');
        });

        builder.deleteCharAt(builder.length()-1);
        return builder.toString();
    }
}
