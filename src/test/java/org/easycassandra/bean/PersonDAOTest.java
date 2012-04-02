package org.easycassandra.bean;

import java.io.File;

import org.easycassandra.bean.dao.PersistenceDao;
import org.easycassandra.bean.model.Address;
import org.easycassandra.bean.model.Person;
import org.easycassandra.bean.model.Sex;
import org.junit.Assert;
import org.junit.Test;

/**
*
* @author otavio
*/
public class PersonDAOTest {
	private PersistenceDao<Person> dao = new PersistenceDao<>(Person.class);
	    @Test
	    public void insertTest() {
	        System.out.println("Inserindo exemplo");
	        Person person = getPerson();
	        person.setName("otavio teste");
	        person.setId(4l);
	        Address address = getAddress();
	        person.setAddress(address);

	        Assert.assertTrue(dao.insert(person));
	    }

	    @Test
	    public void retrieveTest() {
	        Person person = dao.retrieve(4l);
	        Assert.assertEquals(person.getName(), "otavio teste");
	    }

	    @Test
	    public void overrideTest() {

	        Person person = getPerson();
	        person.setId(1l);
	        Assert.assertTrue(dao.insert(person));
	    }

	    @Test
	    public void removefromRowKeyTest() {

	        Assert.assertTrue(dao.removeFromRowKey(new Long(2)));

	    }

	    @Test
	    public void removeTest() {
	        Person person = getPerson();
	        person.setId(1l);
	        Assert.assertTrue(dao.remove(person));

	    }

	    @Test
	    public void cantRetrieve() {
	        Person person = dao.retrieve(new Long(1));
	        Assert.assertNull(person.getName());
	    }

	    @Test
	    public void listTest() {
	        Person person = getPerson();
	        person.setId(1l);
	        dao.insert(person);
	        Assert.assertTrue(dao.listAll().size() > 0);

	    }
	    
	    @Test
	    public void insertFileTest() {
	        System.out.println("Inserindo exemplo");
	        Person person = getPerson();
	        person.setName("otavio teste");
	        person.setId(4l);
	        Address address = getAddress();
	        person.setAddress(address);
	        person.setPersonalFile(new File("readme.txt"));

	        Assert.assertTrue(dao.insert(person));
	    }

	    @Test
	    public void retrieveFileTest() {
	        Person person = dao.retrieve(4l);
	        Assert.assertEquals(person.getPersonalFile().length(), new File("readme.txt").length());
	    }
	    
	    @Test
	    public void countNotNullTest(){
	    	
	    	Assert.assertNotNull(dao.count());
	    }
	    
	    @Test
	    public void countTest(){
	    	Assert.assertEquals(Long.valueOf(dao.listAll().size()), dao.count());
	    }

	    @Test
	    public void inKeyTest(){
	    	for(int index=10;index<15;index++){
	    		Person person=getPerson();
	    		person.setId(Long.valueOf(10l));
	    		person.setAddress(getAddress());
	    		dao.insert(person);
	    	}
	    	Assert.assertEquals(dao.findKeyIn(11l,12l,13l,14,15l).size(), 5);
	    }
	   
	    @Test
	    public void executeUpdateCqlTest(){
	    	Assert.assertTrue(dao.executeUpdateCql("select * from Person"));
	    }
	    
	    @Test
	    public void executeUpdateCqlFailTest(){
	    	Assert.assertFalse(dao.executeUpdateCql("selectErrorSyntax * from Person "));
	    }
	    
	    @Test
	    public void executeCqlTest(){
	    	Assert.assertNotNull(dao.executeCql("select * from person"));
	    }
	    private Address getAddress() {
	        Address address = new Address();
	        address.setCep("40243-543");
	        address.setCity("Salvaor");
	        address.setState("Bahia");
	        return address;

	    }

	    private Person getPerson() {
	        Person person = new Person();
	        person.setYear(10);
	        person.setName("Name Person ");
	        person.setSex(Sex.MALE);

	        return person;

	    }
	    
	    
	    
	    
    
    
}