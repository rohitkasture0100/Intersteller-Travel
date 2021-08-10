package za.co.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.assignment.model.Planets;

public interface PlanetRepository extends JpaRepository<Planets, Long> {
}
