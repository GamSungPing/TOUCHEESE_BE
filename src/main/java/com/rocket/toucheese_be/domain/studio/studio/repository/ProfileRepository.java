package com.rocket.toucheese_be.domain.studio.studio.repository;

import com.rocket.toucheese_be.domain.studio.studio.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
}

