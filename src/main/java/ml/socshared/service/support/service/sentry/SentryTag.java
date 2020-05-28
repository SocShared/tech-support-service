package ml.socshared.service.support.service.sentry;

public enum SentryTag {
    CreateQuestion("type", "create_question"),
    GetQuestions("type", "get_questions_page"),
    GetFullQuestion("type", "get_full_question"),
    AddComment("type", "add_comment"),
    RemoveQuestion("type", "remove_question"),
    RemoveComment("type", "remove_comment");

    SentryTag(String t, String tag) {
        type = t;
        sentryTag = tag;
    }

    public String type() {
        return type;
    }
    public String value() {
        return sentryTag;
    }

    private String sentryTag;
    private String type;
    public static final String service_name = "TSS";

}
