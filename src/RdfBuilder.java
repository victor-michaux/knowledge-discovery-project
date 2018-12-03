import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

public class RdfBuilder {

    private static final String INPUT_FILE_PATH = "news.txt";
    private static final String OUTPUT_FILE_PATH = "news.rdf";
    private static final String BASE_URL = "https://boukari-lachambre-michaux.io/news/";

    private Model model;

    public static void main(String args[]) {
        RdfBuilder rdfBuilder = new RdfBuilder();

        File inputFile = new File(INPUT_FILE_PATH);
        rdfBuilder.createResourceFromFile(inputFile);

        rdfBuilder.writeRDF();
    }

    private RdfBuilder()
    {
        this.model = ModelFactory.createDefaultModel();
    }

    private Resource createResourceFromFile(File inputFile)
    {
        Property document = model.createProperty(BASE_URL, "document");
        Property documentID = model.createProperty(BASE_URL, "documentID");
        Property isAbout = model.createProperty(BASE_URL, "isAbout");

        News news = this.getNewsFromFile(inputFile);

        Resource resource = this.model.createResource(BASE_URL + news.getId());

        resource.addProperty(documentID, news.getId());
        resource.addProperty(document, news.getBody());
        resource.addProperty(isAbout, news.getTopic());

        return resource;
    }

    private News getNewsFromFile(File inputFile)
    {
        String id = null;
        String body = null;
        String topic = null;

        String[] propertyNameArray = {"documentID", "document", "isAbout"};

        BufferedReader b;
        try {
            b = new BufferedReader(new FileReader(inputFile));
            String readLine;
            int i = 0;

            while ((readLine = b.readLine()) != null && i < 3) {
                switch (i) {
                    case 0:
                        id = readLine;
                        break;
                    case 1:
                        body = readLine;
                        break;
                    case 2:
                        topic = readLine;
                        break;
                    default:
                        break;
                }
                i++;
            }
        } catch (Exception e) {
            Scanner sc = new Scanner(System.in);
            for (int i = 0; i < 3; i++) {
                System.out.println("Please enter the " + propertyNameArray[i] + " property");
                switch (i) {
                    case 0:
                        id = sc.nextLine();
                        break;
                    case 1:
                        body = sc.nextLine();
                        break;
                    case 2:
                        topic = sc.nextLine();
                        break;
                    default:
                        break;
                }
            }
            sc.close();
        }

        return new News(id, body, topic);
    }

    private void writeRDF()
    {
        try (FileWriter out = new FileWriter(OUTPUT_FILE_PATH)) {
            this.model.write(out, "RDF/XML-ABBREV");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // ignore
    }

}
