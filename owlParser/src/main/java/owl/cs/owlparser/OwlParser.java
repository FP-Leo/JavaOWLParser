/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package owl.cs.owlparser;

import com.clarkparsia.pellet.owlapiv3.PelletReasoner;
import com.clarkparsia.pellet.owlapiv3.PelletReasonerFactory;
import java.io.File;
import java.util.Set;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.util.InferredOntologyGenerator;
import org.swrlapi.core.SWRLRuleEngine;
import org.swrlapi.exceptions.SWRLBuiltInException;
import org.swrlapi.factory.SWRLAPIFactory;
import org.swrlapi.parser.SWRLParseException;

/**
 *
 * @author Burak
 */
public class OwlParser {
    //Calistirmak icin JAVA 11, OWL API, SWRL API ve Pellete API lazim.
    public static void main(String[] args) throws OWLOntologyCreationException, SWRLParseException, SWRLBuiltInException, OWLOntologyStorageException {
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        //Kaynak dosyayi managere yukliyoruz
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(new File("C:\\Users\\ec\\OneDrive\\Desktop\\Grup1\\Tüm Dosyalar\\movies.owl"));
        
        SWRLRuleEngine ruleEngine = SWRLAPIFactory.createSWRLRuleEngine(ontology);
        //Sonra daha fazla kurallar daha kolay eklemek istiyorsak, kurallarin sayi, isimlerini ve kurallari ayri degiskenlerde tutuyoruz.
        int numberOfRules = 13;
        String[] ruleNames ={
                                "composerWorksInRule", "directorWorksInRule", "distributionCompanyisFeaturedRule",
                                "LeadActorIsFeaturedRule", "SupportActorIsFeaturedRule", "filmEditorWorksInRule",
                                "marketingCompanyisFeaturedRule", "producerWorksInRule", "productionCompanyisFeaturedRule", 
                                "publicityCompanyisFeaturedRule", "screenwriterWorksInRule", "soundDesignerWorksInRule", 
                                "streamingCompanyisFeaturedRule"
                            };
        
        String[] rules ={  
                            "untitled-ontology-11:Composer(?comp) ^ untitled-ontology-11:Crew(?crew) ^ untitled-ontology-11:hasComposer(?c, ?comp) ^ untitled-ontology-11:CreativeTeam(?c) ^ untitled-ontology-11:BeenMadeBy(?m, ?crew) ^ untitled-ontology-11:Movie(?m) ^ untitled-ontology-11:hasCreativeTeam(?crew, ?c) -> untitled-ontology-11:employeeWorksIn(?comp, ?m)",
                            "untitled-ontology-11:Crew(?crew) ^ untitled-ontology-11:Director(?d) ^ untitled-ontology-11:CreativeTeam(?c) ^ untitled-ontology-11:hasDirector(?c, ?d) ^ untitled-ontology-11:BeenMadeBy(?m, ?crew) ^ untitled-ontology-11:Movie(?m) ^ untitled-ontology-11:hasCreativeTeam(?crew, ?c) -> untitled-ontology-11:employeeWorksIn(?d, ?m)",
                            "untitled-ontology-11:companyDistributesMovie(?c, ?m) -> untitled-ontology-11:companyIsFeatured(?c, ?m)", 
                            "untitled-ontology-11:LeadCast(?l) ^ untitled-ontology-11:featuredCharacter(?m, ?c) ^ untitled-ontology-11:Crew(?crew) ^ untitled-ontology-11:hasLeadCast(?crew, ?l) ^ untitled-ontology-11:actorPlaysCharacter(?a, ?c) ^ untitled-ontology-11:Actor(?a) ^ untitled-ontology-11:BeenMadeBy(?m, ?crew) ^ untitled-ontology-11:hasLeadActor(?l, ?a) ^ untitled-ontology-11:Character(?c) ^ untitled-ontology-11:Movie(?m) -> untitled-ontology-11:actorIsFeatured(?a, ?m)", 
                            "untitled-ontology-11:featuredCharacter(?m, ?c) ^ untitled-ontology-11:Crew(?crew) ^ untitled-ontology-11:hasSupportCast(?crew, ?l) ^ untitled-ontology-11:actorPlaysCharacter(?a, ?c) ^ untitled-ontology-11:Actor(?a) ^ untitled-ontology-11:SupportCast(?s) ^ untitled-ontology-11:BeenMadeBy(?m, ?crew) ^ untitled-ontology-11:hasSupportActor(?s, ?a) ^ untitled-ontology-11:Character(?c) ^ untitled-ontology-11:Movie(?m) -> untitled-ontology-11:actorIsFeatured(?a, ?m)",
                            "untitled-ontology-11:Film_Editor(?f) ^ untitled-ontology-11:Crew(?crew) ^ untitled-ontology-11:PostProduction(?pp) ^ untitled-ontology-11:hasFilmEditor(?pp, ?f) ^ untitled-ontology-11:hasPostProduction(?crew, ?pp) ^ untitled-ontology-11:BeenMadeBy(?m, ?crew) ^ untitled-ontology-11:Movie(?m) -> untitled-ontology-11:employeeWorksIn(?f, ?m)", 
                            "untitled-ontology-11:Company(?c) ^ untitled-ontology-11:Crew(?crew) ^ untitled-ontology-11:hasMarketingCompany(?p, ?c) ^ untitled-ontology-11:hasPostProduction(?crew, ?p) ^ untitled-ontology-11:PostProduction(?p) ^ untitled-ontology-11:BeenMadeBy(?m, ?crew) ^ untitled-ontology-11:Movie(?m) -> untitled-ontology-11:companyIsFeatured(?c, ?m)", 
                            "untitled-ontology-11:Crew(?crew) ^ untitled-ontology-11:Producer(?p) ^ untitled-ontology-11:hasProducer(?c, ?p) ^ untitled-ontology-11:CreativeTeam(?c) ^ untitled-ontology-11:BeenMadeBy(?m, ?crew) ^ untitled-ontology-11:Movie(?m) ^ untitled-ontology-11:hasCreativeTeam(?crew, ?c) -> untitled-ontology-11:employeeWorksIn(?p, ?m)",
                            "untitled-ontology-11:Company(?c) ^ untitled-ontology-11:Crew(?crew) ^ untitled-ontology-11:Production(?p) ^ untitled-ontology-11:hasProductionCompany(?p, ?c) ^ untitled-ontology-11:hasProduction(?crew, ?p) ^ untitled-ontology-11:BeenMadeBy(?m, ?crew) ^ untitled-ontology-11:Movie(?m) -> untitled-ontology-11:companyIsFeatured(?c, ?m)",
                            "untitled-ontology-11:Company(?c) ^ untitled-ontology-11:Crew(?crew) ^ untitled-ontology-11:hasPublicityCompany(?p, ?c) ^ untitled-ontology-11:hasPostProduction(?crew, ?p) ^ untitled-ontology-11:PostProduction(?p) ^ untitled-ontology-11:BeenMadeBy(?m, ?crew) ^ untitled-ontology-11:Movie(?m) -> untitled-ontology-11:companyIsFeatured(?c, ?m)",
                            "untitled-ontology-11:hasScreenWriter(?c, ?s) ^ untitled-ontology-11:Crew(?crew) ^ untitled-ontology-11:Screenwriter(?s) ^ untitled-ontology-11:CreativeTeam(?c) ^ untitled-ontology-11:BeenMadeBy(?m, ?crew) ^ untitled-ontology-11:Movie(?m) ^ untitled-ontology-11:hasCreativeTeam(?crew, ?c) -> untitled-ontology-11:employeeWorksIn(?s, ?m)", 
                            "untitled-ontology-11:Sound_Designer(?sd) ^ untitled-ontology-11:Crew(?crew) ^ untitled-ontology-11:PostProduction(?pp) ^ untitled-ontology-11:hasPostProduction(?crew, ?pp) ^ untitled-ontology-11:BeenMadeBy(?m, ?crew) ^ untitled-ontology-11:hasSoundDesigner(?pp, ?sd) ^ untitled-ontology-11:Movie(?m) -> untitled-ontology-11:employeeWorksIn(?sd, ?m)", 
                            "untitled-ontology-11:companyReleasedMovie(?c, ?m) -> untitled-ontology-11:companyIsFeatured(?c, ?m)", 
                        };
        
        //Kurallari ekliyoruz
        for(int i = 0; i < numberOfRules; i++){
            ruleEngine.createSWRLRule(ruleNames[i], rules[i]);
        }
        
        //Yeni kurallarla birlikte ontolojiyi kaydediyoruz. Bu file versionu tutmak icin. 
        //Ayni dosya'da kaydetmek istiyorsak manager.saveOntology(ontology);
        manager.saveOntology(ontology, IRI.create(new File("C:\\Users\\ec\\OneDrive\\Desktop\\Grup1\\Tüm Dosyalar\\moviesWithSWRLRules.owl")));
        
        //Ontolojiyi infer etmek icin Pellet Reasoner API kullaniyoruz.
        PelletReasonerFactory reasonerFactory = new PelletReasonerFactory();
        PelletReasoner reasoner = reasonerFactory.createReasoner(ontology);

        //Reason ve Infer
        reasoner.getKB().realize();
        reasoner.getKB().classify();
        
        //Yeni inferred data ontolojiye atiyoruz
        OWLOntology exportedOntology = manager.createOntology();
        InferredOntologyGenerator generator = new InferredOntologyGenerator( reasoner );
        generator.fillOntology(manager.getOWLDataFactory(), exportedOntology );
        //Restrictions, Domains, Range vs kaybetmemek icin yeni ontolojiye ekliyoruz.
        manager.addAxioms(exportedOntology, ontology.getAxioms());
        
        //Ontolojiyi kaydetiyoruz.
        manager.saveOntology(exportedOntology, IRI.create(new File("C:\\Users\\ec\\OneDrive\\Desktop\\Grup1\\Tüm Dosyalar\\moviesInferred.owl")));
        manager.saveOntology(exportedOntology, IRI.create(new File("C:\\Users\\ec\\OneDrive\\Desktop\\Grup1\\movies.owl")));
    }
}
