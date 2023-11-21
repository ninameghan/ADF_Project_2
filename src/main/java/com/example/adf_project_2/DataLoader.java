package com.example.adf_project_2;

import com.example.adf_project_2.entities.Property;
import com.example.adf_project_2.entities.Tenant;
import com.example.adf_project_2.entities.User;
import com.example.adf_project_2.repositories.IPropertyRepository;
import com.example.adf_project_2.repositories.ITenantRepository;
import com.example.adf_project_2.repositories.IUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {
    private final ITenantRepository tenantRepository;
    private final IUserRepository userRepository;
    private final IPropertyRepository propertyRepository;

    public DataLoader(ITenantRepository tenantRepository, IUserRepository userRepository, IPropertyRepository propertyRepository) {
        this.tenantRepository = tenantRepository;
        this.userRepository = userRepository;
        this.propertyRepository = propertyRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Generating 5 sample Property instances
        Property property1 = propertyRepository.save(new Property("123 Main St", "EIR123", 5, 1500.0));
        Property property2 = propertyRepository.save(new Property("456 Oak St", "EIR456", 8, 2000.0));
        Property property3 = propertyRepository.save(new Property("789 Pine St", "EIR789", 10, 2500.0));
        Property property4 = propertyRepository.save(new Property("101 Maple Ave", "EIR101", 6, 1800.0));
        Property property5 = propertyRepository.save(new Property("202 Cedar Blvd", "EIR202", 12, 3000.0));

        // Generating sample Tenant data
        Tenant tenant1 = tenantRepository.save(new Tenant("John Doe", "john.doe@email.com", "555-1234", property1));
        Tenant tenant2 = tenantRepository.save(new Tenant("Jane Smith", "jane.smith@email.com", "555-5678", property2));
        Tenant tenant3 = tenantRepository.save(new Tenant("Bob Johnson", "bob.johnson@email.com", "555-9876", property3));
        Tenant tenant4 = tenantRepository.save(new Tenant("Alice Williams", "alice@email.com", "555-1111", property4));
        Tenant tenant5 = tenantRepository.save(new Tenant("Charlie Brown", "charlie@email.com", "555-2222", property5));
        Tenant tenant6 = tenantRepository.save(new Tenant("Eva Davis", "eva@email.com", "555-3333", property1));
        Tenant tenant7 = tenantRepository.save(new Tenant("Frank Miller", "frank@email.com", "555-4444", property2));
        Tenant tenant8 = tenantRepository.save(new Tenant("Grace Taylor", "grace@email.com", "555-5555", property3));
        Tenant tenant9 = tenantRepository.save(new Tenant("Henry Wilson", "henry@email.com", "555-6666", property4));
        Tenant tenant10 = tenantRepository.save(new Tenant("Ivy Johnson", "ivy@email.com", "555-7777", property5));
        Tenant tenant11 = tenantRepository.save(new Tenant("Jack Robinson", "jack@email.com", "555-8888", property1));
        Tenant tenant12 = tenantRepository.save(new Tenant("Kelly White", "kelly@email.com", "555-9999", property2));
        Tenant tenant13 = tenantRepository.save(new Tenant("Leo Turner", "leo@email.com", "555-0000", property3));
        Tenant tenant14 = tenantRepository.save(new Tenant("Mia Clark", "mia@email.com", "555-1212", property4));
        Tenant tenant15 = tenantRepository.save(new Tenant("Nathan Davis", "nathan@email.com", "555-2323", property5));
        Tenant tenant16 = tenantRepository.save(new Tenant("Olivia Brown", "olivia@email.com", "555-3434", property1));
        Tenant tenant17 = tenantRepository.save(new Tenant("Peter Adams", "peter@email.com", "555-4545", property2));
        Tenant tenant18 = tenantRepository.save(new Tenant("Quinn Foster", "quinn@email.com", "555-5656", property3));
        Tenant tenant19 = tenantRepository.save(new Tenant("Rachel Hill", "rachel@email.com", "555-6767", property4));
        Tenant tenant20 = tenantRepository.save(new Tenant("Samuel Wright", "samuel@email.com", "555-7878", property5));

        User user1 = userRepository.save(new User("john.doe@email.com", "Password1", false, "Management", "555-1234", "123-45-6789"));
        User user2 = userRepository.save(new User("jane.smith@email.com", "SecurePass123", false, "Management", "555-5678", "987-65-4321"));
        User user3 = userRepository.save(new User("bob.johnson@email.com", "Pass123Word", true, "Office", "555-9876", "456-78-9012"));
    }
}
