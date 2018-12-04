public class News {
    private String id;
    private String body;
    private String topic;
    private String author;

    public News(String id, String body, String topic, String author) {
        this.id = id;
        this.body = body;
        this.topic = topic;
        this.author = author;
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

    public String getAuthor() {
        return author;
    }
}
