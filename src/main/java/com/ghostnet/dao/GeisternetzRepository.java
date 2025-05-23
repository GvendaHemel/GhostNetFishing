package com.ghostnet.dao;

import com.ghostnet.model.Geisternetz;
import com.ghostnet.model.NetzStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeisternetzRepository extends JpaRepository<Geisternetz, Long> {

    // Alle Netze mit bestimmtem Status (z. B. BERGUNG_BEVORSTEHEND)
    List<Geisternetz> findByStatus(NetzStatus status);

    // Netze ohne bergende Person (für Zuweisung)
    List<Geisternetz> findByBergendePersonIsNull();
}
