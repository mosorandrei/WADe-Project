package com.botanical.gardens.serverside.rdf;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.OWLOntologyDocumentSource;
import org.semanticweb.owlapi.io.OWLOntologyDocumentTarget;
import org.semanticweb.owlapi.io.StringDocumentTarget;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.vocab.OWL2Datatype;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class OntologyGenerator {
    private final String ontologyName = "http://smaumosorteam.com/ontologies/2024/botanical_garden.owl";
    OWLOntologyManager m = OWLManager.createOWLOntologyManager();
    OWLDataFactory df = OWLManager.getOWLDataFactory();

    public String buildInitialRdf() throws OWLOntologyCreationException, OWLOntologyStorageException {
        OntologyGenerator oh = new OntologyGenerator();
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
        OWLDataProperty hasAttractionId =
                oh.createDataProperty(ontologyName + "#hasAttractionId");

        OWLClass garden = oh.createClass(ontologyName + "#Garden");
        OWLDataProperty hasGardenName =
                oh.createDataProperty(ontologyName + "#hasGardenName");
        OWLDataProperty hasGardenId =
                oh.createDataProperty(ontologyName + "#hasGardenId");

        OWLClass comment = oh.createClass(ontologyName + "#Comment");
        OWLDataProperty hasCommentContent =
                oh.createDataProperty(ontologyName + "#hasCommentContent");
        OWLDataProperty hasCommentId =
                oh.createDataProperty(ontologyName + "#hasCommentId");

        OWLClass review = oh.createClass(ontologyName + "#Review");
        OWLDataProperty hasReviewContent =
                oh.createDataProperty(ontologyName + "#hasReviewContent");
        OWLDataProperty hasReviewId =
                oh.createDataProperty(ontologyName + "#hasReviewId");

        OWLClass tour = oh.createClass(ontologyName + "#Tour");
        OWLDataProperty hasTourId =
                oh.createDataProperty(ontologyName + "#hasTourId");
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
                oh.addDomainToDataProperty(o, attraction, hasAttractionId),
                oh.addDomainToDataProperty(o, attraction, hasAttractionDescription),
                oh.addDomainToDataProperty(o, attraction, hasAttractionType),

                oh.addDomainToDataProperty(o, garden, hasGardenId),
                oh.addDomainToDataProperty(o, garden, hasGardenName),

                oh.addDomainToDataProperty(o, comment, hasCommentId),
                oh.addDomainToDataProperty(o, comment, hasCommentContent),

                oh.addDomainToDataProperty(o, review, hasReviewId),
                oh.addDomainToDataProperty(o, review, hasReviewContent),

                oh.addDomainToDataProperty(o, tour, hasTourEndHour),
                oh.addDomainToDataProperty(o, tour, hasTourStartHour),
                oh.addDomainToDataProperty(o, tour, hasTourDescription),
                oh.addDomainToDataProperty(o, tour, hasTourId),
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

        StringDocumentTarget sdt = new StringDocumentTarget();
        oh.writeOntology(o, sdt);

        return sdt.toString();
    }

    public IRI convertStringToIRI(String ns) {
        return IRI.create(ns);
    }

    public void dumpOWL(OWLOntology ontology) {
        try {
            StringDocumentTarget sdt = new StringDocumentTarget();
            writeOntology(ontology, sdt);
            System.out.println(sdt);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

    public OWLAxiomChange addExactCardinality(OWLOntology o, OWLClass myClass, OWLObjectProperty property, int cardinality) {
        return new AddAxiom(o, df.getOWLSubClassOfAxiom(myClass, df.getOWLObjectExactCardinality(cardinality, property, myClass)));
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