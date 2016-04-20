package hu.horvathzoltan.main;

import hu.horvathzoltan.entity.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;


public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    private Main() {

    }

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CompanyProduct");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();


        List<String> phoneNumbers1 = new ArrayList<>();
        phoneNumbers1.add("178-234");
        phoneNumbers1.add("345633-3334");

        List<String> phoneNumbers2 = new ArrayList<>();
        phoneNumbers2.add("666-234");
        phoneNumbers2.add("34563663-666");

        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("Nagy Sándor", new Date(), phoneNumbers1));
        contacts.add(new Contact("Érték Elek", new Date(), phoneNumbers2));

        Address address = new Address("Hungary", "Budapest", "1088", "Műegyetem rakpart");
        Company company = new Company("AlphaBeta Kft.", contacts, address);

        for (Contact contact : contacts) {
            contact.setCompany(company);
        }

        List<Product> products = new ArrayList<>();
        products.add(new Furniture("Tiffany asztal", 500, company, 10.0, 20.0, 30.0, FurnitureColor.BEECH));
        products.add(new UpholsteryFabrics("Tünde plüss", 100, company, 140.0));

        company.setProducts(products);

        // TRANSACTION
        transaction.begin();
        entityManager.persist(company);
        transaction.commit();
        // END OF TRANSACTION

        // TRANSACTION
        transaction.begin();
        TypedQuery<Furniture> furnitureTypedQuery = entityManager.createNamedQuery("findAllFurnitures", Furniture.class);
        List<Furniture> furnitureList = furnitureTypedQuery.getResultList();
        LOGGER.info(furnitureList.toString());

        Query query = entityManager.createNamedQuery("findDistinctColors");
        LOGGER.info(query.getResultList().toString());

        Query productsQuery = entityManager.createNamedQuery("listOfProductsWhichPriceIsGreaterThan");
        productsQuery.setParameter("price", 20);
        LOGGER.info(productsQuery.getResultList().toString());


        TypedQuery<Contact> contactTypedQuery = entityManager.createNamedQuery("getContact", Contact.class);
        contactTypedQuery.setParameter("contactName", "Nagy Sándor");
        List<Contact> contact = contactTypedQuery.getResultList();
        LOGGER.info(contact.toString());

        query = entityManager.createNamedQuery("getContactByPhoneNumber");
        query.setParameter("phoneNumber", "178-234");
        LOGGER.info(query.getResultList().toString());

        transaction.commit();
        // END OF TRANSACTION

        entityManager.close();
    }
}
