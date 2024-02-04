package com.botanical.gardens.serverside.utils;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.FileDocumentSource;
import org.semanticweb.owlapi.io.FileDocumentTarget;
import org.semanticweb.owlapi.io.OWLOntologyDocumentSource;
import org.semanticweb.owlapi.io.OWLOntologyDocumentTarget;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.vocab.OWL2Datatype;

import java.io.File;
import java.util.Arrays;

public class OntologyManager {
    private final String ontologyName = "http://smaumosorteam.com/ontologies/2024/botanical_garden.owl";
    private final String ontologyFileName = "botanical_garden.owl";
    OWLOntologyManager m = OWLManager.createOWLOntologyManager();
    OWLDataFactory df = OWLManager.getOWLDataFactory();
    FileDocumentTarget sdt = new FileDocumentTarget(new File(ontologyFileName));

    public OWLOntology getOntologyFromFile(OntologyManager oh) throws OWLOntologyCreationException {
        return oh.readOntology(new FileDocumentSource(new File(ontologyFileName)));
    }

    public void saveOntologyToFile(OntologyManager oh, OWLOntology o) throws OWLOntologyStorageException {
        FileDocumentTarget sdt = new FileDocumentTarget(new File(ontologyFileName));
        oh.writeOntology(o, sdt);
    }

    public void buildInitialRdf() throws OWLOntologyCreationException, OWLOntologyStorageException {
        OntologyManager oh = new OntologyManager();
        OWLOntology o = oh.createOntology(ontologyName);

        OWLClass user = oh.createClass(ontologyName + "#User");
        OWLDataProperty hasFirstName =
                oh.createDataProperty(ontologyName + "#hasFirstName");
        OWLDataProperty hasLastName =
                oh.createDataProperty(ontologyName + "#hasLastName");
        OWLDataProperty hasUserId =
                oh.createDataProperty(ontologyName + "#hasUserId");

        OWLClass attraction = oh.createClass(ontologyName + "#Attraction");
        OWLDataProperty hasAttractionName =
                oh.createDataProperty(ontologyName + "#hasAttractionName");
        OWLDataProperty hasAttractionType =
                oh.createDataProperty(ontologyName + "#hasAttractionType");
        OWLDataProperty hasAttractionDescription =
                oh.createDataProperty(ontologyName + "#hasAttractionDescription");

        OWLClass garden = oh.createClass(ontologyName + "#Garden");
        OWLDataProperty hasGardenName =
                oh.createDataProperty(ontologyName + "#hasGardenName");

        OWLClass comment = oh.createClass(ontologyName + "#Comment");
        OWLDataProperty hasCommentContent =
                oh.createDataProperty(ontologyName + "#hasCommentContent");

        OWLClass review = oh.createClass(ontologyName + "#Review");
        OWLDataProperty hasReviewContent =
                oh.createDataProperty(ontologyName + "#hasReviewContent");

        OWLClass tour = oh.createClass(ontologyName + "#Tour");
        OWLDataProperty hasTourName =
                oh.createDataProperty(ontologyName + "#hasTourName");
        OWLDataProperty hasTourDescription =
                oh.createDataProperty(ontologyName + "#hasTourDescription");
        OWLDataProperty hasTourGuideName =
                oh.createDataProperty(ontologyName + "#hasTourGuideName");
        OWLDataProperty hasTourTotalSeats =
                oh.createDataProperty(ontologyName + "#hasTourTotalSeats");
        OWLDataProperty hasTourStartHour =
                oh.createDataProperty(ontologyName + "#hasTourStartHour");
        OWLDataProperty hasTourEndHour =
                oh.createDataProperty(ontologyName + "#hasTourEndHour");

        OWLObjectProperty hasComment =
                oh.createObjectProperty(ontologyName + "#hasComment");
        OWLObjectProperty isCommentOf =
                oh.createObjectProperty(ontologyName + "#isCommentOf");
        OWLObjectProperty hasReview =
                oh.createObjectProperty(ontologyName + "#hasReview");
        OWLObjectProperty isReviewOf =
                oh.createObjectProperty(ontologyName + "#isReviewOf");
        OWLObjectProperty hasGiven =
                oh.createObjectProperty(ontologyName + "#hasGiven");
        OWLObjectProperty isGivenBy =
                oh.createObjectProperty(ontologyName + "#isGivenBy");
        OWLObjectProperty hasAttraction =
                oh.createObjectProperty(ontologyName + "#hasAttraction");
        OWLObjectProperty isAttractionOf =
                oh.createObjectProperty(ontologyName + "#isAttractionOf");
        OWLObjectProperty hasTour =
                oh.createObjectProperty(ontologyName + "#hasTour");
        OWLObjectProperty isTourOf =
                oh.createObjectProperty(ontologyName + "#isTourOf");
        OWLObjectProperty hasParticipant =
                oh.createObjectProperty(ontologyName + "#hasParticipant");
        OWLObjectProperty isParticipantOf =
                oh.createObjectProperty(ontologyName + "#isParticipantOf");

        oh.applyChange(
                oh.addDomainToDataProperty(o, user, hasFirstName),
                oh.addDomainToDataProperty(o, user, hasLastName),
                oh.addDomainToDataProperty(o, user, hasUserId),

                oh.addDomainToDataProperty(o, attraction, hasAttractionName),
                oh.addDomainToDataProperty(o, attraction, hasAttractionDescription),
                oh.addDomainToDataProperty(o, attraction, hasAttractionType),

                oh.addDomainToDataProperty(o, garden, hasGardenName),

                oh.addDomainToDataProperty(o, comment, hasCommentContent),

                oh.addDomainToDataProperty(o, review, hasReviewContent),

                oh.addDomainToDataProperty(o, tour, hasTourEndHour),
                oh.addDomainToDataProperty(o, tour, hasTourStartHour),
                oh.addDomainToDataProperty(o, tour, hasTourDescription),
                oh.addDomainToDataProperty(o, tour, hasTourName),
                oh.addDomainToDataProperty(o, tour, hasTourGuideName),
                oh.addDomainToDataProperty(o, tour, hasTourTotalSeats),

                oh.addInverseProperties(o, hasComment, isCommentOf),
                oh.addInverseProperties(o, isCommentOf, hasComment),
                oh.markAsAsymmetric(o, isCommentOf),
                oh.markAsIrreflexive(o, isCommentOf),
                oh.markAsInverseFunctional(o, isCommentOf),
                oh.markAsAsymmetric(o, hasComment),
                oh.markAsIrreflexive(o, hasComment),
                oh.markAsFunctional(o, hasComment),

                oh.addInverseProperties(o, isReviewOf, hasReview),
                oh.addInverseProperties(o, hasReview, isReviewOf),
                oh.markAsAsymmetric(o, isReviewOf),
                oh.markAsIrreflexive(o, isReviewOf),
                oh.markAsInverseFunctional(o, isReviewOf),
                oh.markAsAsymmetric(o, hasReview),
                oh.markAsIrreflexive(o, hasReview),
                oh.markAsFunctional(o, hasReview),

                oh.addInverseProperties(o, hasGiven, isGivenBy),
                oh.addInverseProperties(o, isGivenBy, hasGiven),
                oh.markAsAsymmetric(o, hasGiven),
                oh.markAsIrreflexive(o, hasGiven),
                oh.markAsInverseFunctional(o, hasGiven),
                oh.markAsAsymmetric(o, isGivenBy),
                oh.markAsIrreflexive(o, isGivenBy),
                oh.markAsFunctional(o, isGivenBy),

                oh.addInverseProperties(o, hasAttraction, isAttractionOf),
                oh.addInverseProperties(o, isAttractionOf, hasAttraction),
                oh.markAsAsymmetric(o, isAttractionOf),
                oh.markAsIrreflexive(o, isAttractionOf),
                oh.markAsInverseFunctional(o, isAttractionOf),
                oh.markAsAsymmetric(o, hasAttraction),
                oh.markAsIrreflexive(o, hasAttraction),
                oh.markAsFunctional(o, hasAttraction),

                oh.addInverseProperties(o, hasTour, isTourOf),
                oh.addInverseProperties(o, isTourOf, hasTour),
                oh.markAsAsymmetric(o, isTourOf),
                oh.markAsIrreflexive(o, isTourOf),
                oh.markAsInverseFunctional(o, isTourOf),
                oh.markAsAsymmetric(o, hasTour),
                oh.markAsIrreflexive(o, hasTour),
                oh.markAsFunctional(o, hasTour),

                oh.addInverseProperties(o, hasParticipant, isParticipantOf),
                oh.addInverseProperties(o, isParticipantOf, hasParticipant),
                oh.markAsAsymmetric(o, isParticipantOf),
                oh.markAsIrreflexive(o, isParticipantOf),
                oh.markAsInverseFunctional(o, isParticipantOf),
                oh.markAsAsymmetric(o, hasParticipant),
                oh.markAsIrreflexive(o, hasParticipant),
                oh.markAsFunctional(o, hasParticipant),

                oh.associateObjectPropertyWithClass(o, hasComment, tour, comment),
                oh.associateObjectPropertyWithClass(o, isCommentOf, comment, tour),
                oh.associateObjectPropertyWithClass(o, hasReview, tour, review),
                oh.associateObjectPropertyWithClass(o, isReviewOf, review, tour),
                oh.addDomainToObjectProperty(o, tour, hasComment),
                oh.addRangeToObjectProperty(o, comment, hasComment),
                oh.addDomainToObjectProperty(o, comment, isCommentOf),
                oh.addRangeToObjectProperty(o, tour, isCommentOf),
                oh.addDomainToObjectProperty(o, tour, hasReview),
                oh.addRangeToObjectProperty(o, review, hasReview),
                oh.addDomainToObjectProperty(o, review, isReviewOf),
                oh.addRangeToObjectProperty(o, tour, isReviewOf),

                oh.associateObjectPropertyWithClass(o, hasGiven, user, review),
                oh.associateObjectPropertyWithClass(o, hasGiven, user, comment),
                oh.associateObjectPropertyWithClass(o, isGivenBy, comment, user),
                oh.associateObjectPropertyWithClass(o, isGivenBy, review, user),
                oh.addDomainToObjectProperty(o, user, hasGiven),
                oh.addRangeToObjectProperty(o, comment, hasGiven),
                oh.addRangeToObjectProperty(o, review, hasGiven),
                oh.addRangeToObjectProperty(o, user, isGivenBy),
                oh.addDomainToObjectProperty(o, comment, isGivenBy),
                oh.addDomainToObjectProperty(o, review, isGivenBy),

                oh.associateObjectPropertyWithClass(o, hasAttraction, garden, attraction),
                oh.associateObjectPropertyWithClass(o, isAttractionOf, attraction, garden),
                oh.addRangeToObjectProperty(o, attraction, hasAttraction),
                oh.addDomainToObjectProperty(o, garden, hasAttraction),
                oh.addRangeToObjectProperty(o, garden, isAttractionOf),
                oh.addDomainToObjectProperty(o, attraction, isAttractionOf),

                oh.associateObjectPropertyWithClass(o, hasAttraction, tour, attraction),
                oh.associateObjectPropertyWithClass(o, isAttractionOf, attraction, tour),
                oh.addDomainToObjectProperty(o, tour, hasAttraction),
                oh.addRangeToObjectProperty(o, tour, isAttractionOf),

                oh.associateObjectPropertyWithClass(o, hasTour, garden, tour),
                oh.associateObjectPropertyWithClass(o, isTourOf, tour, garden),
                oh.addRangeToObjectProperty(o, tour, hasTour),
                oh.addDomainToObjectProperty(o, garden, hasTour),
                oh.addRangeToObjectProperty(o, garden, isTourOf),
                oh.addDomainToObjectProperty(o, tour, isTourOf),

                oh.associateObjectPropertyWithClass(o, hasParticipant, tour, user),
                oh.associateObjectPropertyWithClass(o, isParticipantOf, user, tour),
                oh.addRangeToObjectProperty(o, user, hasParticipant),
                oh.addDomainToObjectProperty(o, tour, hasParticipant),
                oh.addRangeToObjectProperty(o, tour, isParticipantOf),
                oh.addDomainToObjectProperty(o, user, isParticipantOf)
        );

        OWLIndividual orchid = oh.createIndividual(ontologyName + "#Orchid");
        OWLIndividual sunflower = oh.createIndividual(ontologyName + "#Sunflower");
        OWLIndividual tulip = oh.createIndividual(ontologyName + "#Tulip");
        OWLIndividual azalea = oh.createIndividual(ontologyName + "#Azalea");
        OWLIndividual chrysanthemum = oh.createIndividual(ontologyName + "#Chrysanthemum");
        OWLIndividual lotus = oh.createIndividual(ontologyName + "#Lotus");
        OWLIndividual iris = oh.createIndividual(ontologyName + "#Iris");
        OWLIndividual daisy = oh.createIndividual(ontologyName + "#Daisy");
        OWLIndividual hibiscus = oh.createIndividual(ontologyName + "#Hibiscus");
        OWLIndividual lavender = oh.createIndividual(ontologyName + "#Lavender");
        OWLIndividual cosmos = oh.createIndividual(ontologyName + "#Cosmos");
        OWLIndividual lantana = oh.createIndividual(ontologyName + "#Lantana");

        OWLIndividual iasiGarden = oh.createIndividual(ontologyName + "#IasiGarden");
        OWLIndividual newYorkGarden = oh.createIndividual(ontologyName + "#NewYorkGarden");

        OWLIndividual iasiTour1 = oh.createIndividual(ontologyName + "#IasiTour1");
        OWLIndividual iasiTour2 = oh.createIndividual(ontologyName + "#IasiTour2");
        OWLIndividual newYorkTour = oh.createIndividual(ontologyName + "#NewYorkTour");

        oh.applyChange(
                oh.associateIndividualWithClass(o, attraction, orchid),
                oh.addDataToIndividual(o, orchid, hasAttractionName, "Orchid"),
                oh.addDataToIndividual(o, orchid, hasAttractionDescription, "Plantae kingdom"),
                oh.addDataToIndividual(o, orchid, hasAttractionType, "Landscaping"),
                oh.associateIndividualWithClass(o, attraction, sunflower),
                oh.addDataToIndividual(o, sunflower, hasAttractionName, "Sunflower"),
                oh.addDataToIndividual(o, sunflower, hasAttractionDescription, "Plantae kingdom"),
                oh.addDataToIndividual(o, sunflower, hasAttractionType, "Landscaping"),
                oh.associateIndividualWithClass(o, attraction, tulip),
                oh.addDataToIndividual(o, tulip, hasAttractionName, "Tulip"),
                oh.addDataToIndividual(o, tulip, hasAttractionDescription, "Plantae kingdom"),
                oh.addDataToIndividual(o, tulip, hasAttractionType, "Landscaping"),
                oh.associateIndividualWithClass(o, attraction, azalea),
                oh.addDataToIndividual(o, azalea, hasAttractionName, "Azalea"),
                oh.addDataToIndividual(o, azalea, hasAttractionDescription, "Plantae kingdom"),
                oh.addDataToIndividual(o, azalea, hasAttractionType, "Landscaping"),
                oh.associateIndividualWithClass(o, attraction, chrysanthemum),
                oh.addDataToIndividual(o, chrysanthemum, hasAttractionName, "Chrysanthemum"),
                oh.addDataToIndividual(o, chrysanthemum, hasAttractionDescription, "Plantae kingdom"),
                oh.addDataToIndividual(o, chrysanthemum, hasAttractionType, "Landscaping"),
                oh.associateIndividualWithClass(o, attraction, lotus),
                oh.addDataToIndividual(o, lotus, hasAttractionName, "Lotus"),
                oh.addDataToIndividual(o, lotus, hasAttractionDescription, "Plantae kingdom"),
                oh.addDataToIndividual(o, lotus, hasAttractionType, "Landscaping"),
                oh.associateIndividualWithClass(o, attraction, iris),
                oh.addDataToIndividual(o, iris, hasAttractionName, "Iris"),
                oh.addDataToIndividual(o, iris, hasAttractionDescription, "Plantae kingdom"),
                oh.addDataToIndividual(o, iris, hasAttractionType, "Landscaping"),
                oh.associateIndividualWithClass(o, attraction, daisy),
                oh.addDataToIndividual(o, daisy, hasAttractionName, "Daisy"),
                oh.addDataToIndividual(o, daisy, hasAttractionDescription, "Plantae kingdom"),
                oh.addDataToIndividual(o, daisy, hasAttractionType, "Landscaping"),
                oh.associateIndividualWithClass(o, attraction, hibiscus),
                oh.addDataToIndividual(o, hibiscus, hasAttractionName, "Hibiscus"),
                oh.addDataToIndividual(o, hibiscus, hasAttractionDescription, "Plantae kingdom"),
                oh.addDataToIndividual(o, hibiscus, hasAttractionType, "Landscaping"),
                oh.associateIndividualWithClass(o, attraction, lavender),
                oh.addDataToIndividual(o, lavender, hasAttractionName, "Lavander"),
                oh.addDataToIndividual(o, lavender, hasAttractionDescription, "Plantae kingdom"),
                oh.addDataToIndividual(o, lavender, hasAttractionType, "Landscaping"),
                oh.associateIndividualWithClass(o, attraction, cosmos),
                oh.addDataToIndividual(o, cosmos, hasAttractionName, "Cosmos"),
                oh.addDataToIndividual(o, cosmos, hasAttractionDescription, "Plantae kingdom"),
                oh.addDataToIndividual(o, cosmos, hasAttractionType, "Landscaping"),
                oh.associateIndividualWithClass(o, attraction, lantana),
                oh.addDataToIndividual(o, lantana, hasAttractionName, "Lantana"),
                oh.addDataToIndividual(o, lantana, hasAttractionDescription, "Plantae kingdom"),
                oh.addDataToIndividual(o, lantana, hasAttractionType, "Landscaping"),

                oh.associateIndividualWithClass(o, garden, iasiGarden),
                oh.addDataToIndividual(o, iasiGarden, hasGardenName, "Iasi Garden"),
                oh.associateIndividualWithClass(o, garden, newYorkGarden),
                oh.addDataToIndividual(o, newYorkGarden, hasGardenName, "New York Garden"),

                oh.associateIndividualWithClass(o, tour, iasiTour1),
                oh.addDataToIndividual(o, iasiTour1, hasTourName, "Discover the beauty of Iasi"),
                oh.addDataToIndividual(o, iasiTour1, hasTourDescription, "Enjoy a tour of our beautiful flowers"),
                oh.addDataToIndividual(o, iasiTour1, hasTourStartHour, 11),
                oh.addDataToIndividual(o, iasiTour1, hasTourEndHour, 13),
                oh.addDataToIndividual(o, iasiTour1, hasTourGuideName, "Adrian Smau"),
                oh.addDataToIndividual(o, iasiTour1, hasTourTotalSeats, 10),
                oh.associateIndividualWithClass(o, tour, iasiTour2),
                oh.addDataToIndividual(o, iasiTour2, hasTourName, "Open gates night"),
                oh.addDataToIndividual(o, iasiTour2, hasTourDescription, "Free entry for all"),
                oh.addDataToIndividual(o, iasiTour2, hasTourStartHour, 21),
                oh.addDataToIndividual(o, iasiTour2, hasTourEndHour, 22),
                oh.addDataToIndividual(o, iasiTour2, hasTourGuideName, "Andrei Mosor"),
                oh.addDataToIndividual(o, iasiTour2, hasTourTotalSeats, 25),
                oh.associateIndividualWithClass(o, tour, newYorkTour),
                oh.addDataToIndividual(o, newYorkTour, hasTourName, "Grandest tour of 2024"),
                oh.addDataToIndividual(o, newYorkTour, hasTourDescription, "Jewel of New York"),
                oh.addDataToIndividual(o, newYorkTour, hasTourStartHour, 15),
                oh.addDataToIndividual(o, newYorkTour, hasTourEndHour, 16),
                oh.addDataToIndividual(o, newYorkTour, hasTourGuideName, "Ion Popa"),
                oh.addDataToIndividual(o, newYorkTour, hasTourTotalSeats, 110),

                oh.addObjectProperty(o, orchid, isAttractionOf, iasiGarden),
                oh.addObjectProperty(o, sunflower, isAttractionOf, iasiGarden),
                oh.addObjectProperty(o, chrysanthemum, isAttractionOf, iasiGarden),
                oh.addObjectProperty(o, daisy, isAttractionOf, iasiGarden),
                oh.addObjectProperty(o, iasiGarden, hasAttraction, orchid),
                oh.addObjectProperty(o, iasiGarden, hasAttraction, sunflower),
                oh.addObjectProperty(o, iasiGarden, hasAttraction, chrysanthemum),
                oh.addObjectProperty(o, iasiGarden, hasAttraction, daisy),

                oh.addObjectProperty(o, iasiTour1, hasAttraction, orchid),
                oh.addObjectProperty(o, iasiTour1, hasAttraction, sunflower),
                oh.addObjectProperty(o, iasiTour1, hasAttraction, chrysanthemum),
                oh.addObjectProperty(o, orchid, isAttractionOf, iasiTour1),
                oh.addObjectProperty(o, sunflower, isAttractionOf, iasiTour1),
                oh.addObjectProperty(o, chrysanthemum, isAttractionOf, iasiTour1),

                oh.addObjectProperty(o, iasiTour2, hasAttraction, orchid),
                oh.addObjectProperty(o, iasiTour2, hasAttraction, daisy),
                oh.addObjectProperty(o, orchid, isAttractionOf, iasiTour2),
                oh.addObjectProperty(o, daisy, isAttractionOf, iasiTour2),

                oh.addObjectProperty(o, tulip, isAttractionOf, newYorkGarden),
                oh.addObjectProperty(o, azalea, isAttractionOf, newYorkGarden),
                oh.addObjectProperty(o, lotus, isAttractionOf, newYorkGarden),
                oh.addObjectProperty(o, chrysanthemum, isAttractionOf, newYorkGarden),
                oh.addObjectProperty(o, orchid, isAttractionOf, newYorkGarden),
                oh.addObjectProperty(o, lantana, isAttractionOf, newYorkGarden),
                oh.addObjectProperty(o, newYorkGarden, hasAttraction, tulip),
                oh.addObjectProperty(o, newYorkGarden, hasAttraction, azalea),
                oh.addObjectProperty(o, newYorkGarden, hasAttraction, lotus),
                oh.addObjectProperty(o, newYorkGarden, hasAttraction, chrysanthemum),
                oh.addObjectProperty(o, newYorkGarden, hasAttraction, lantana),
                oh.addObjectProperty(o, newYorkGarden, hasAttraction, orchid),

                oh.addObjectProperty(o, newYorkTour, hasAttraction, tulip),
                oh.addObjectProperty(o, newYorkTour, hasAttraction, lantana),
                oh.addObjectProperty(o, newYorkTour, hasAttraction, chrysanthemum),
                oh.addObjectProperty(o, newYorkTour, hasAttraction, lotus),
                oh.addObjectProperty(o, tulip, isAttractionOf, newYorkTour),
                oh.addObjectProperty(o, lantana, isAttractionOf, newYorkTour),
                oh.addObjectProperty(o, chrysanthemum, isAttractionOf, newYorkTour),
                oh.addObjectProperty(o, lotus, isAttractionOf, newYorkTour),

                oh.addObjectProperty(o, newYorkGarden, hasTour, newYorkTour),
                oh.addObjectProperty(o, newYorkTour, isTourOf, newYorkGarden),
                oh.addObjectProperty(o, iasiGarden, hasTour, iasiTour1),
                oh.addObjectProperty(o, iasiTour1, isTourOf, iasiGarden),
                oh.addObjectProperty(o, iasiGarden, hasTour, iasiTour2),
                oh.addObjectProperty(o, iasiTour2, isTourOf, iasiGarden)
        );
        oh.writeOntology(o,sdt);
}

    public IRI convertStringToIRI(String ns) {
        return IRI.create(ns);
    }

    public OWLOntology createOntology(String iri) throws OWLOntologyCreationException {
        return createOntology(convertStringToIRI(iri));
    }

    public OWLOntology createOntology(IRI iri) throws OWLOntologyCreationException {
        return m.createOntology(iri);
    }

    public void writeOntology(OWLOntology o, OWLOntologyDocumentTarget documentTarget)
            throws OWLOntologyStorageException {
        m.saveOntology(o, documentTarget);
    }

    public OWLOntology readOntology(OWLOntologyDocumentSource source)
            throws OWLOntologyCreationException {
        return m.loadOntologyFromOntologyDocument(source);
    }

    public OWLClass createClass(String iri) {
        return createClass(convertStringToIRI(iri));
    }

    public OWLClass createClass(IRI iri) {
        return df.getOWLClass(iri);
    }

    public OWLClass getClass(String className) {
        return df.getOWLClass(ontologyName + "#" + className);
    }

    public OWLAxiomChange createSubclass(OWLOntology o, OWLClass subclass, OWLClass superclass) {
        return new AddAxiom(o, df.getOWLSubClassOfAxiom(subclass, superclass));
    }

    public void applyChange(OWLAxiomChange... axiom) {
        applyChanges(axiom);
    }

    private void applyChanges(OWLAxiomChange... axioms) {
        m.applyChanges(Arrays.asList(axioms));
    }

    public OWLIndividual createIndividual(String iri) {
        return createIndividual(convertStringToIRI(iri));
    }

    private OWLIndividual createIndividual(IRI iri) {
        return df.getOWLNamedIndividual(iri);
    }

    public OWLAxiomChange associateIndividualWithClass(OWLOntology o,
                                                       OWLClass clazz,
                                                       OWLIndividual individual) {
        return new AddAxiom(o, df.getOWLClassAssertionAxiom(clazz, individual));
    }

    public OWLObjectProperty createObjectProperty(String iri) {
        return createObjectProperty(convertStringToIRI(iri));
    }

    public OWLObjectProperty createObjectProperty(IRI iri) {
        return df.getOWLObjectProperty(iri);
    }

    public OWLAxiomChange associateObjectPropertyWithClass(OWLOntology o,
                                                           OWLObjectProperty property,
                                                           OWLClass refHolder,
                                                           OWLClass refTo) {
        OWLClassExpression hasSomeRefTo = df.getOWLObjectSomeValuesFrom(property, refTo);
        OWLSubClassOfAxiom ax = df.getOWLSubClassOfAxiom(refHolder, hasSomeRefTo);
        return new AddAxiom(o, ax);
    }

    public OWLAxiomChange addDisjointClass(OWLOntology o, OWLClass a, OWLClass b) {
        OWLDisjointClassesAxiom expression = df.getOWLDisjointClassesAxiom(a, b);
        return new AddAxiom(o, expression);
    }

    public OWLAxiomChange addDomainToObjectProperty(OWLOntology o, OWLClass myClass, OWLObjectProperty property) {
        return new AddAxiom(o, df.getOWLObjectPropertyDomainAxiom(property, myClass));
    }

    public OWLAxiomChange addDomainToDataProperty(OWLOntology o, OWLClass myClass, OWLDataProperty property) {
        return new AddAxiom(o, df.getOWLDataPropertyDomainAxiom(property, myClass));
    }

    public OWLAxiomChange addRangeToObjectProperty(OWLOntology o, OWLClass myClass, OWLObjectProperty property) {
        return new AddAxiom(o, df.getOWLObjectPropertyRangeAxiom(property, myClass));
    }

    public OWLAxiomChange markAsInverseFunctional(OWLOntology o, OWLObjectProperty property) {
        return new AddAxiom(o, df.getOWLInverseFunctionalObjectPropertyAxiom(property));
    }

    public OWLAxiomChange markAsAsymmetric(OWLOntology o, OWLObjectProperty property) {
        return new AddAxiom(o, df.getOWLAsymmetricObjectPropertyAxiom(property));
    }

    public OWLAxiomChange markAsIrreflexive(OWLOntology o, OWLObjectProperty property) {
        return new AddAxiom(o, df.getOWLIrreflexiveObjectPropertyAxiom(property));
    }

    public OWLAxiomChange markAsFunctional(OWLOntology o, OWLObjectProperty property) {
        return new AddAxiom(o, df.getOWLFunctionalObjectPropertyAxiom(property));
    }

    public OWLAxiomChange addInverseProperties(OWLOntology o, OWLObjectPropertyExpression a, OWLObjectPropertyExpression b) {
        OWLInverseObjectPropertiesAxiom expression = df.getOWLInverseObjectPropertiesAxiom(a, b);
        return new AddAxiom(o, expression);
    }

    public OWLAxiomChange addObjectProperty(OWLOntology o, OWLIndividual target, OWLObjectProperty property, OWLIndividual value) {
        OWLObjectPropertyAssertionAxiom prop = df.getOWLObjectPropertyAssertionAxiom(property, target, value);
        return new AddAxiom(o, prop);
    }

    public OWLDataProperty createDataProperty(String iri) {
        return createDataProperty(convertStringToIRI(iri));
    }

    public OWLDataProperty createDataProperty(IRI iri) {
        return df.getOWLDataProperty(iri);
    }

    public OWLDataProperty getDataProperty(String dataPropertyName) {
        return df.getOWLDataProperty(ontologyName + "#" + dataPropertyName);
    }

    public OWLAxiomChange addDataToIndividual(OWLOntology o, OWLIndividual individual, OWLDataProperty property, String value) {
        OWLLiteral literal = df.getOWLLiteral(value, OWL2Datatype.XSD_STRING);
        return new AddAxiom(o, df.getOWLDataPropertyAssertionAxiom(property, individual, literal));
    }

    public OWLAxiomChange addDataToIndividual(OWLOntology o, OWLIndividual individual, OWLDataProperty property, boolean value) {
        OWLLiteral literal = df.getOWLLiteral(value);
        return new AddAxiom(o, df.getOWLDataPropertyAssertionAxiom(property, individual, literal));
    }

    public OWLAxiomChange addDataToIndividual(OWLOntology o, OWLIndividual individual, OWLDataProperty property, int value) {
        OWLLiteral literal = df.getOWLLiteral(value);
        return new AddAxiom(o, df.getOWLDataPropertyAssertionAxiom(property, individual, literal));
    }
}