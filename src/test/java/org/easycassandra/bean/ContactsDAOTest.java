package org.easycassandra.bean;

import java.util.HashSet;

import junit.framework.Assert;

import org.easycassandra.bean.dao.PersistenceDao;
import org.easycassandra.bean.model.Contact;
import org.junit.Test;

public class ContactsDAOTest {
 private PersistenceDao<Contact,String> persistence=new PersistenceDao<Contact,String>(Contact.class);
    
    
    @Test
        public void insertTest() {
        Contact contacts = getContact();
        Assert.assertTrue(persistence.insert(contacts));
    }


    private Contact getContact() {
        Contact contacts = new Contact();
        contacts.setName("Shrek ");
        contacts.setCyte("far far away");
        contacts.setEmails(new HashSet<String>());
        contacts.getEmails().add("shreck@shrek.org");
        contacts.getEmails().add("shreck@farfaraway.far");
        return contacts;
    }
    
    
    @Test
    public void retrieveTest() {
     Contact contact=persistence.retrieve(getContact().getName());
     Assert.assertNotNull(contact);
     
    }
    @Test
    public void removeTest() {
        persistence.remove(getContact());
        Assert.assertNull(persistence.retrieve(getContact().getName()));   
    }
    
    
}
