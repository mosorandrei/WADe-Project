package com.botanical.gardens.serverside;

import com.botanical.gardens.serverside.utils.OntologyManager;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import org.semanticweb.owlapi.io.OWLOntologyDocumentSource;
import org.semanticweb.owlapi.io.StreamDocumentSource;
import org.semanticweb.owlapi.io.StringDocumentSource;
import org.semanticweb.owlapi.io.StringDocumentTarget;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import uk.ac.manchester.cs.jfact.JFactFactory;

import java.util.*;
import java.util.stream.Collectors;

import static org.testng.Assert.*;

public class OntologyManagerTest {
    private final String ontologyName = "http://smaumosorteam.com/ontologies/2024/test.owl";

    @Test
    public void createOntology() throws OWLException {
        OntologyManager oh = new OntologyManager();
        IRI iri = oh.convertStringToIRI(ontologyName);
        OWLOntology ontology = oh.createOntology(iri);

        assertNotNull(ontology);
        assertEquals(iri, ontology.getOntologyID().getOntologyIRI().or(() -> java.util.Optional.ofNullable(oh.convertStringToIRI("false"))).get());
    }

    @DataProvider
    Object[][] readDataProvider() {
        return new Object[][]{{
                new StreamDocumentSource(Objects.requireNonNull(this.getClass().getResourceAsStream("/test.owl"))),
                ontologyName
        }};
    }

    @Test(dataProvider = "readDataProvider")
    public void readOntology(OWLOntologyDocumentSource source, String baseIRI) throws OWLException {
        OntologyManager oh = new OntologyManager();
        OWLOntology ontology = oh.readOntology(source);
        assertNotNull(ontology);
        assertEquals(oh.convertStringToIRI(baseIRI),
                ontology.getOntologyID().getOntologyIRI().or(() -> java.util.Optional.ofNullable(oh.convertStringToIRI("false"))).get());
    }

    @Test
    public void writeOntology() throws OWLException {
        OntologyManager oh = new OntologyManager();
        OWLOntology ontology = oh.createOntology(ontologyName);
        StringDocumentTarget sdt = new StringDocumentTarget();
        oh.writeOntology(ontology, sdt);

        StringDocumentSource sds = new StringDocumentSource(sdt.toString());
        OWLOntology o = oh.readOntology(sds);
        assertEquals(oh.convertStringToIRI(ontologyName),
                o.getOntologyID().getOntologyIRI().or(() -> java.util.Optional.ofNullable(oh.convertStringToIRI("false"))).get());
    }

    @Test
    public void testAddClassesSimple() throws OWLOntologyCreationException {
        OntologyManager oh = new OntologyManager();
        OWLOntology o = oh.createOntology(ontologyName);
        OWLClass person = oh.createClass(ontologyName + "#Person");
        OWLClass fireman = oh.createClass(ontologyName + "#Fireman");
        OWLAxiomChange axiom = oh.createSubclass(o, fireman, person);
        oh.applyChange(axiom);

        Set<String> superclasses = o.getSubClassAxiomsForSubClass(fireman)
                .stream()
                .map(ax -> ax.getSuperClass().asOWLClass().toStringID())
                .collect(Collectors.toSet());
        assertEquals(1, superclasses.size());
        assertTrue(superclasses.contains(person.toStringID()));
    }

    @Test
    public void testAddClassesMoreComplex() throws OWLOntologyCreationException {
        OntologyManager oh = new OntologyManager();
        OWLOntology o = oh.createOntology(ontologyName);
        OWLClass person = oh.createClass(ontologyName + "#Person");
        OWLClass robot = oh.createClass(ontologyName + "#Robot");
        OWLClass cyborg = oh.createClass(ontologyName + "#Cyborg");

        oh.applyChange(oh.createSubclass(o, cyborg, person),
                oh.createSubclass(o, cyborg, robot));

        Set<String> superclasses = o.getSubClassAxiomsForSubClass(cyborg)
                .stream()
                .map(ax -> ax.getSuperClass().asOWLClass().toStringID())
                .collect(Collectors.toSet());
        assertEquals(2, superclasses.size());
        assertTrue(superclasses.contains(person.toStringID()));
        assertTrue(superclasses.contains(robot.toStringID()));
    }

    @Test
    public void addSimpleIndividual() throws Exception {
        OntologyManager oh = new OntologyManager();
        OWLOntology o = oh.createOntology(ontologyName);
        OWLClass person = oh.createClass(ontologyName + "#Person");
        OWLIndividual individual = oh.createIndividual(ontologyName + "#Individual");
        oh.applyChange(oh.associateIndividualWithClass(o, person, individual));
        final OWLReasoner reasoner = new JFactFactory().createReasoner(o);
        try (final AutoCloseable ignored = reasoner::dispose) {
            ListMultimap<String, String> classInstanceMap = ArrayListMultimap.create();
            o.getClassesInSignature()
                    .forEach(clazz ->
                            reasoner.getInstances(clazz, true)
                                    .getFlattened()
                                    .forEach(i ->
                                            classInstanceMap.put(clazz.toStringID(), i.toStringID())));
            // should have one class
            assertEquals(classInstanceMap.keySet().size(), 1);
            Set<String> people = new HashSet<>();
            people.add(individual.toStringID());
            assertEquals(classInstanceMap.asMap().get(person.toStringID()), people);
        }
    }

    @Test
    public void addTerminatorIndividuals() throws Exception {
        OntologyManager oh = new OntologyManager();
        OWLOntology o = oh.createOntology(ontologyName);
        OWLClass person = oh.createClass(ontologyName + "#Person");
        OWLClass robot = oh.createClass(ontologyName + "#Robot");
        OWLClass cyborg = oh.createClass(ontologyName + "#Cyborg");

        oh.applyChange(oh.createSubclass(o, cyborg, person),
                oh.createSubclass(o, cyborg, robot));

        OWLIndividual individual = oh.createIndividual(ontologyName + "#Individual");
        OWLIndividual tank = oh.createIndividual(ontologyName + "#Tank");
        OWLIndividual android = oh.createIndividual(ontologyName + "#Android");
        oh.applyChange(oh.associateIndividualWithClass(o, person, individual),
                oh.associateIndividualWithClass(o, robot, tank),
                oh.associateIndividualWithClass(o, cyborg, android));

        final OWLReasoner reasoner = new JFactFactory().createReasoner(o);
        try (final AutoCloseable ignored = reasoner::dispose) {
            ListMultimap<String, String> classInstanceMap = ArrayListMultimap.create();
            o.getClassesInSignature()
                    .forEach(clazz ->
                            reasoner.getInstances(clazz, false)
                                    .getFlattened()
                                    .forEach(i ->
                                            classInstanceMap.put(clazz.toStringID(), i.toStringID())));

            // should have three classes
            assertEquals(classInstanceMap.keySet().size(), 3);

            Set<String> people = new HashSet<>(Arrays.asList(individual.toStringID(), android.toStringID()));
            Set<String> robots = new HashSet<>(Arrays.asList(android.toStringID(), tank.toStringID()));
            Set<String> terminators = new HashSet<>(Collections.singletonList(android.toStringID()));

            assertEquals(new HashSet<>(classInstanceMap.asMap().get(person.toStringID())), people);
            assertEquals(new HashSet<>(classInstanceMap.asMap().get(robot.toStringID())), robots);
            assertEquals(new HashSet<>(classInstanceMap.asMap().get(cyborg.toStringID())), terminators);
        }
    }

    @Test
    public void simpleParentage() throws Exception {
        OntologyManager oh = new OntologyManager();
        OWLOntology o = oh.createOntology(ontologyName);
        OWLClass human = oh.createClass(ontologyName + "#Human");
        OWLClass male = oh.createClass(ontologyName + "#Male");
        OWLClass female = oh.createClass(ontologyName + "#Female");
        OWLObjectProperty hasFather =
                oh.createObjectProperty(ontologyName + "#hasFather");
        OWLObjectProperty hasMother =
                oh.createObjectProperty(ontologyName + "#hasMother");
        oh.applyChange(
                oh.createSubclass(o, male, human),
                oh.createSubclass(o, female, human),
                oh.addDisjointClass(o, female, male),
                oh.addDisjointClass(o, male, female),
                oh.associateObjectPropertyWithClass(o, hasFather, human, male),
                oh.associateObjectPropertyWithClass(o, hasMother, human, female)
        );

        OWLIndividual barry = oh.createIndividual(ontologyName + "#barry");
        OWLIndividual shirley = oh.createIndividual(ontologyName + "#shirley");
        OWLIndividual thomas = oh.createIndividual(ontologyName + "#thomas");
        OWLIndividual michael = oh.createIndividual(ontologyName + "#michael");
        OWLIndividual vicki = oh.createIndividual(ontologyName + "#vicki");
        OWLIndividual joseph = oh.createIndividual(ontologyName + "#joseph");
        OWLIndividual mary = oh.createIndividual(ontologyName + "#mary");
        OWLIndividual samuel = oh.createIndividual(ontologyName + "#samuel");
        OWLIndividual andrew = oh.createIndividual(ontologyName + "#andrew");
        OWLIndividual jonathan = oh.createIndividual(ontologyName + "#jonathan");

        oh.applyChange(
                oh.associateIndividualWithClass(o, male, barry),
                oh.associateIndividualWithClass(o, male, thomas),
                oh.associateIndividualWithClass(o, male, michael),
                oh.associateIndividualWithClass(o, male, joseph),
                oh.associateIndividualWithClass(o, male, samuel),
                oh.associateIndividualWithClass(o, male, andrew),
                oh.associateIndividualWithClass(o, male, jonathan),
                oh.associateIndividualWithClass(o, female, shirley),
                oh.associateIndividualWithClass(o, female, vicki),
                oh.associateIndividualWithClass(o, female, mary),
                oh.addObjectProperty(o, michael, hasMother, shirley),
                oh.addObjectProperty(o, michael, hasFather, thomas),
                oh.addObjectProperty(o, vicki, hasMother, shirley),
                oh.addObjectProperty(o, vicki, hasFather, thomas),
                oh.addObjectProperty(o, joseph, hasMother, shirley),
                oh.addObjectProperty(o, joseph, hasFather, barry),
                oh.addObjectProperty(o, andrew, hasMother, mary),
                oh.addObjectProperty(o, andrew, hasFather, samuel),
                oh.addObjectProperty(o, jonathan, hasMother, vicki),
                oh.addObjectProperty(o, jonathan, hasFather, andrew)
        );

        OWLReasoner reasoner = new JFactFactory().createReasoner(o);
        try (final AutoCloseable ignored = reasoner::dispose) {
            assertTrue(reasoner.isConsistent());
        }
        oh.applyChange(oh.associateIndividualWithClass(o, female, jonathan));
        reasoner = new JFactFactory().createReasoner(o);
        try (final AutoCloseable ignored = reasoner::dispose) {
            assertFalse(reasoner.isConsistent());
        }
    }

    @Test
    public void addDataToIndividual() throws Exception {
        OntologyManager oh = new OntologyManager();
        OWLOntology o = oh.createOntology(ontologyName);
        OWLClass cyborg = oh.createClass(ontologyName + "#Terminator");
        OWLDataProperty killsHumans =
                oh.createDataProperty(ontologyName + "#killsHumans");
        OWLIndividual android = oh.createIndividual(ontologyName + "#Android");
        OWLIndividual pops = oh.createIndividual(ontologyName + "#pops");
        oh.applyChange(
                oh.associateIndividualWithClass(o, cyborg, pops),
                oh.associateIndividualWithClass(o, cyborg, android),
                oh.addDataToIndividual(o, android, killsHumans, true),
                oh.addDataToIndividual(o, pops, killsHumans, false)
        );
    }
}
