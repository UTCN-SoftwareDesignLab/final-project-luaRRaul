package com.stockhelt.backend.report;

import java.io.ByteArrayOutputStream;

public interface ReportService {
    String HEADER = "Username,Email,Role";
    ByteArrayOutputStream export();

    ReportType getType();
}
