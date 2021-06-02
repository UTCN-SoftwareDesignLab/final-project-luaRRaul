package com.stockhelt.backend.report;

import com.stockhelt.backend.user.UserRepository;
import com.stockhelt.backend.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.stockhelt.backend.report.ReportType.CSV;

@Service
@RequiredArgsConstructor
public class CSVReportService implements ReportService {
    private final UserRepository userRepository;

    @Override
    public ByteArrayOutputStream export() {
        List<User> database = userRepository.findAll();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append(HEADER);
        reportBuilder.append("\n");
        for (User u :database){
            reportBuilder.append(u.getUsername());
            reportBuilder.append(",");
            reportBuilder.append(u.getEmail());
            reportBuilder.append(",");
            reportBuilder.append(u.getClass().getSimpleName());
            reportBuilder.append("\n");
        }
        try {
            outputStream.write(reportBuilder.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputStream;
    }

    @Override
    public ReportType getType() {
        return CSV;
    }
}
