package hello;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.data.gemfire.CacheFactoryBean;
import org.springframework.data.gemfire.LocalRegionFactoryBean;
import org.springframework.integration.annotation.ServiceActivator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gemstone.gemfire.cache.GemFireCache;

import hello.repository.PersonDBRepository;
import hello.repository.PersonRepository;
import hello.vo.Person;
import hello.vo.PersonDB;

@SuppressWarnings("unused")
@EnableBinding(Sink.class)
public class CustomSinkConfiguration {
    private static final Log logger = LogFactory.getLog(CustomSinkConfiguration.class);
 
    PersonDBRepository personDBRepository;
    PersonRepository personRepository;
	public PersonDBRepository getPersonDBRepository() {
		return personDBRepository;
	}

	@ServiceActivator(inputChannel=Sink.INPUT)
    public void loggerSink(String payload) {
		try{
			
		ObjectMapper om = new ObjectMapper();
        if(payload!=null){
    		Person person = om.readValue((String)payload, Person.class);
    		PersonDB personDB = om.readValue((String)payload, PersonDB.class);
    		personRepository.save(person);
    		personDBRepository.save(personDB);
    		}
    	
        else{
        	logger.error("Invalid Payload");
        }
    		
    	}
    		catch(Exception ex){
    			logger.error("Error Occured", ex);
    		}
    	}
	@Autowired
	public void setPersonDBRepository(PersonDBRepository personDBRepository) {
		this.personDBRepository = personDBRepository;
	}
	public PersonRepository getPersonRepository() {
		return personRepository;
	}
	@Autowired
	public void setPersonRepository(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
}
