import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;

import java.io.InputStream;

public class DataExplorer {

    private Model model;

    public void buildDataModel(String ontologiePath) {
        Model model = ModelFactory.createDefaultModel();

        InputStream in = FileManager.get().open(ontologiePath);

        model.read(in, null);

        this.model = model;
    }

    public void executeQuery(String queryString) {
        Query queryObject = QueryFactory.create(queryString) ;
        try (QueryExecution queryExecution = QueryExecutionFactory.create(queryObject, this.model)) {
            ResultSet rs = queryExecution.execSelect();
            ResultSetFormatter.out(System.out, rs, queryObject);
        }
    }
}
