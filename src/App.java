public class App {
    public static void main(String args[]) {
        String ontolgiePath = "news.rdf";
        RdfBuilder rdfBuilder = new RdfBuilder();

        rdfBuilder.createResourcesFromFilePath("news.txt");
        rdfBuilder.writeRDF(ontolgiePath);

        DataExplorer dataExplorer = new DataExplorer();
        dataExplorer.buildDataModel(ontolgiePath);

        String queryString =
                "PREFIX dc: <http://purl.org/dc/elements/1.1/>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "SELECT ?author ?topic\n "+
                "WHERE { ?article dc:creator ?author." +
                "?article dc:subject \"BASKET BALL\".}";

        dataExplorer.executeQuery(queryString);
    }
}
