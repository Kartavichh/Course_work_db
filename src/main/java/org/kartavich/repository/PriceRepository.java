package org.kartavich.repository;

import org.kartavich.domain.IphonesEntities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository @Transactional
public interface PriceRepository extends JpaRepository<IphonesEntities, Integer> {
}
