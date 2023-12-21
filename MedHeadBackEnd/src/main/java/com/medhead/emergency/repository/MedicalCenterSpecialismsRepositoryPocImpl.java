package com.medhead.emergency.repository;

import com.medhead.emergency.entity.MedicalCenter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * This implementation is dedicated for POC usage only.
 * The production implementation should call directly the API for medical center specialisms.
 */
public class MedicalCenterSpecialismsRepositoryPocImpl implements MedicalCenterSpecialismsRepository {


    private final String hospitalFileUrl = "src/main/resources/hospital.csv";

    private final Reader reader = new FileReader(hospitalFileUrl, StandardCharsets.ISO_8859_1);

    private enum Headers {
        OrganisationID, OrganisationName, Address1, Address2, Address3, City, County, Postcode, Latitude, Longitude
    }


    public MedicalCenterSpecialismsRepositoryPocImpl() throws IOException {
    }

    /**
     * @inheritDoc
     */
    @Override
    public MedicalCenter findMedicalCenterById(int organisationId) {
        //TODO
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<MedicalCenter> findAllMedicalCenters() {
        List<MedicalCenter> medicalCenters = new ArrayList<>();
        try (reader) {
            CSVFormat csvFormat = CSVFormat.EXCEL.builder()
                    .setHeader(Headers.class)
                    .setSkipHeaderRecord(true)
                    .setDelimiter('¬')
                    .build();

            Iterable<CSVRecord> records = csvFormat.parse(reader);

            for (CSVRecord record : records) {
                int id = Integer.parseInt(record.get(Headers.OrganisationID));

                MedicalCenter medicalCenter = new MedicalCenter();
                medicalCenter.setOrganisationId(id);
                medicalCenters.add(medicalCenter);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return medicalCenters;
    }
}
