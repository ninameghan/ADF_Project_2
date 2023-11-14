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
    private ITenantRepository tenantRepository;
    private IUserRepository userRepository;
    private IPropertyRepository propertyRepository;

    public DataLoader(ITenantRepository tenantRepository, IUserRepository userRepository) {
        this.tenantRepository = tenantRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Property property1 = propertyRepository.save(new Property("123 Main St", "EIR123", 5, 1500.0));
        Property property2 = propertyRepository.save(new Property("456 Oak St", "EIR456", 8, 2000.0));
        Property property3 = propertyRepository.save(new Property("789 Pine St", "EIR789", 10, 2500.0));

        Tenant tenant1 = tenantRepository.save(new Tenant("John Doe", "john.doe@email.com", "555-1234", property1));
        Tenant tenant2 = tenantRepository.save(new Tenant("Jane Smith", "jane.smith@email.com", "555-5678", property2));
        Tenant tenant3 = tenantRepository.save(new Tenant("Bob Johnson", "bob.johnson@email.com", "555-9876", property3));

        User user1 = userRepository.save(new User("john.doe@email.com", "Password1", false, "Management", "555-1234", "123-45-6789"));
        User user2 = userRepository.save(new User("jane.smith@email.com", "SecurePass123", false, "Management", "555-5678", "987-65-4321"));
        User user3 = userRepository.save(new User("bob.johnson@email.com", "Pass123Word", true, "Office", "555-9876", "456-78-9012"));
    }
}
