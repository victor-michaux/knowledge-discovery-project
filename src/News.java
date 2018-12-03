public class News {
    private String id;
    private String body;
    private String topic;

    public News(String id, String body, String topic) {
        this.id = id;
        this.body = body;
        this.topic = topic;
    }

    public String getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public String getTopic() {
        return topic;
    }
}
