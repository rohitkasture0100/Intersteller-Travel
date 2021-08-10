package za.co.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.assignment.model.Traffic;

public interface TrafficRepository extends JpaRepository<Traffic, Long> {
}
