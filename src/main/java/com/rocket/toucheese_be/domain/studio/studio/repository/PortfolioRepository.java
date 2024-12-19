package com.rocket.toucheese_be.domain.studio.studio.repository;

import com.rocket.toucheese_be.domain.studio.studio.entity.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
}
