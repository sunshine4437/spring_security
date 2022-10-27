package com.example.demo.dao;

import com.example.demo.dto.AuditDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditDAO {
    public int insertAudit(AuditDTO dto);

    public List<AuditDTO> getAuditAll();
}
