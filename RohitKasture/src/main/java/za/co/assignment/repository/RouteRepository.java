package za.co.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.assignment.model.Routes;

public interface RouteRepository extends JpaRepository<Routes, Long> {
}
