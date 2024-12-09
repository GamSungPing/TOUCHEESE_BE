package com.rocket.toucheese_be.domain.member.repository;

import com.rocket.toucheese_be.domain.member.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    Optional<Device> findByMemberId(Long memberId);
}

