import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.DC_11;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RdfBuilder {

    private static final String BASE_URL = "https://boukari-lachambre-michaux.io/news/";

    private Model model;

    public RdfBuilder()
    {
        this.model = ModelFactory.createDefaultModel();
    }

    public void createResourcesFromFilePath(String path)
    {
        ArrayList<News> newsCollection = this.getNewsFromFilePath(path);

        for (News news : newsCollection) {
            buildResourceFromNews(news);
        }
    }

    private void buildResourceFromNews(News news)
    {
        Resource resource = this.model.createResource(BASE_URL + news.getId());

        resource.addProperty(DC_11.description, news.getBody());
        resource.addProperty(DC_11.subject, news.getTopic());
        resource.addProperty(DC_11.creator, news.getAuthor());
        resource.addProperty(DC_11.date, news.getDate());
    }

    private ArrayList<News> getNewsFromFilePath(String path)
    {
        List<String> fileLines = Collections.emptyList();

        try {
            fileLines = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<News> newsCollection = new ArrayList<>();

        for(int i = 0; i < fileLines.size(); i+=5) {
            String id = fileLines.get(i);
            String body = fileLines.get(i+1);
            String topic = fileLines.get(i+2);
            String author = fileLines.get(i+3);
            String date = fileLines.get(i+4);
            News news = new News(id, body, topic, author, date);
            newsCollection.add(news);
        }

        return newsCollection;
    }

    public void writeRDF(String outputPath)
    {
        try (FileWriter out = new FileWriter(outputPath)) {
            this.model.write(out, "RDF/XML-ABBREV");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // ignore
    }

}
