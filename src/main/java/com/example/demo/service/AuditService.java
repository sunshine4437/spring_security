package com.example.demo.service;


import com.example.demo.dto.Audit;

import java.util.List;

public interface AuditService {
    public int insertAudit(Audit auditDTO);

    public List<Audit> selectAuditAll(Audit auditDTO);
}
