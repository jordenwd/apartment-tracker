package org.jwd.apartmenttracker.repository;

import org.jwd.apartmenttracker.entities.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
}
