package org.jwd.apartmenttracker.repository;

import org.jwd.apartmenttracker.entities.Floorplan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FloorplanRepository extends JpaRepository<Floorplan, Long> {
}
