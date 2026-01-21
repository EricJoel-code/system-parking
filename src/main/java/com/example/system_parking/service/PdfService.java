package com.example.system_parking.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class PdfService {

    public byte[] generateVehicleReport(String vehicleId, String entryTime, String exitTime, double totalFee) throws DocumentException, IOException {
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        PdfWriter.getInstance(document, outputStream);
        document.open();

        document.add(new Paragraph("Informe de Estacionamiento"));
        document.add(new Paragraph("ID del Vehículo: " + vehicleId));
        document.add(new Paragraph("Hora de Entrada: " + entryTime));
        document.add(new Paragraph("Hora de Salida: " + exitTime));
        document.add(new Paragraph("Tarifa Total: $" + totalFee));

        document.close();

        return outputStream.toByteArray();
    }
}
