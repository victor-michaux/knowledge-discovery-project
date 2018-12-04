import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;

public class SportOntologyManager {
    public void createOntology()
    {
        OntModel ontologyModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);

        String baseURI = "http://topic-ontology.com";

        ontologyModel.createOntology(baseURI);

        OntClass sportClass = ontologyModel.createClass(baseURI + "#sport");
        OntClass football = ontologyModel.createClass(baseURI + "#football");
        OntClass basketball = ontologyModel.createClass(baseURI + "#basketball");

        sportClass.addSubClass(football);
        sportClass.addSubClass(basketball);

        ontologyModel.write(System.out, "RDF/XML-ABBREV");
    }
}
