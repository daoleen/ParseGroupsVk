package com.daoleen.social.vk;

/**
 * Created by alex on 3/22/14.
 */
public class Config {
    private static Config config = null;

    private final int appId = 4259940;
    private final String appSecret = "WzsYjd4e75TyINxCGIVe";
    private int offset = 0;
    private int count = 1000;
    private String fields = "screen_name";
    private int university = 94448;
    private String accessToken = "a87bea9f690b77819acb263b77843b07d5a86c187ee7b771daf44b100c4255d66999e2e9ff79a4e381252";
    private String basename = "vkusers";
    private int recordsPerFile = 1000;
    private final String usersSearchUrl = "https://api.vk.com/method/users.search";
    private final String userProfileUrl = "https://api.vk.com/method/users.get";

    private Config() {
    }

    public static Config getInstance() {
        if(config == null) {
            config = new Config();
        }
        return config;
    }

    public String getUsersSearchUri() {
        return String.format("https://api.vk.com/method/users.search" +
                "?offset=%d&count=%d&fields=%s&university=%d&access_token=%s&v=5.16",
                getOffset(), getCount(), getFields(), getUniversity(), getAccessToken());
    }

    public String getUsersSearchUrl() {
        return usersSearchUrl;
    }

    public String getUsersSearchParams() {
        return String.format("offset=%d&count=%d&fields=%s&university=%d&access_token=%s&v=5.16",
                getOffset(), getCount(), getFields(), getUniversity(), getAccessToken());
    }

    public String getUserProfileUri(String userIds) {
        return String.format("https://api.vk.com/method/users.get" +
                "?user_ids=%s&fields=%s&name_case=nom&access_token=%s&v=5.16",
                userIds, fields, accessToken);
    }

    public String getUserProfileUrl() {
        return userProfileUrl;
    }

    public String getUserProfileParams(String userIds) {
        return String.format("user_ids=%s&fields=%s&name_case=nom&access_token=%s&v=5.16",
                userIds, fields, accessToken);
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public int getUniversity() {
        return university;
    }

    public void setUniversity(int university) {
        this.university = university;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getBasename() {
        return basename;
    }

    public void setBasename(String basename) {
        this.basename = basename;
    }

    public int getRecordsPerFile() {
        return recordsPerFile;
    }

    public void setRecordsPerFile(int recordsPerFile) {
        this.recordsPerFile = recordsPerFile;
    }
}
