package com.example.adf_project_2;

import com.example.adf_project_2.entities.Tenant;
import com.example.adf_project_2.repositories.ITenantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {
    private ITenantRepository tenantRepository;

    public DataLoader(ITenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Tenant john = tenantRepository.save(new Tenant("John","john@gmail.com", "0123456789"));
        Tenant mary = tenantRepository.save(new Tenant("Mary","mary@gmail.com", "1234567890"));
        Tenant nina = tenantRepository.save(new Tenant("Nina","nina@gmail.com", "2345678901"));
        Tenant max = tenantRepository.save(new Tenant("Max","max@gmail.com", "3456789012"));
        Tenant tom = tenantRepository.save(new Tenant("Tom","tom@gmail.com", "4567890123"));
    }
}
