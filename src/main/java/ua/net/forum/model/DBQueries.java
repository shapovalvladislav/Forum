package ua.net.forum.model;

public class DBQueries {
    public static final String GET_SECTIONS = "getSections";
    public static final String GET_TOPICS = "getTopics";
    public static final String GET_MESSAGES = "getMessages";
    public static final String GET_PROFILES = "getProfiles";
    public static final String GET_USERS = "getUsers";
    public static final String GET_ROLES = "getRoles";
    public static final String GET_TOPICS_BY_SECTION = "getTopicsBySection";
    public static final String GET_MESSAGES_BY_TOPIC = "getMessagesByTopic";
    public static final String GET_TOP_PROFILES = "getTopProfiles";
    public static final String GET_PROFILE_BY_ID = "getProfileById";
    public static final String GET_USER_BY_LOGIN = "getUserByLogin";
    public static final String GET_USER_BY_LOGIN_AND_PASSWORD = "getUserByLoginAndPassword";
    public static final String GET_ROLE_BY_NAME = "getRoleByName";
    public static final String GET_TOP_PROC = "SELECT * FROM \"getTopUsers\"()";
    public static final String CAN_CREATE_TOPIC_PROC = "SELECT * FROM \"cancreatetopic\"(?)";
    public static final String GET_USER_ID_BY_PROFILE_ID_PROC = "SELECT * FROM findUserIdByProfileId(?)";
}
