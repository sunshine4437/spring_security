package com.example.demo.service;


import com.example.demo.dto.AuditDTO;

import java.util.List;

public interface AuditService {
    public int insertAudit(AuditDTO auditDTO);

    public List<AuditDTO> getAuditAll(AuditDTO auditDTO);
}
