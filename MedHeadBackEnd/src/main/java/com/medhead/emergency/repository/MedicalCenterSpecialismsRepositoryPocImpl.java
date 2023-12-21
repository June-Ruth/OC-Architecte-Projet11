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

import static org.apache.logging.log4j.util.Strings.isNotBlank;

/**
 * This implementation is dedicated for POC usage only.
 * The production implementation should call directly the API for medical center specialisms.
 */
public class MedicalCenterSpecialismsRepositoryPocImpl implements MedicalCenterSpecialismsRepository {


    private final String hospitalFileUrl = "src/main/resources/hospital.csv";

    private final Reader reader = new FileReader(hospitalFileUrl, StandardCharsets.ISO_8859_1);

    public MedicalCenterSpecialismsRepositoryPocImpl() throws IOException {
    }

    /**
     * @inheritDoc
     */
    @Override
    public MedicalCenter findMedicalCenterById(int organisationId) {
        List<MedicalCenter> medicalCenters = findAllMedicalCenters();
        MedicalCenter medicalCenter = medicalCenters.stream()
                .filter((medicalCenter1 -> organisationId == medicalCenter1.getOrganisationId()))
                .findAny()
                .orElse(null);
        return medicalCenter;
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
                MedicalCenter medicalCenter = transformCsvToMedicalCenter(record);
                medicalCenters.add(medicalCenter);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return medicalCenters;
    }

    private MedicalCenter transformCsvToMedicalCenter(CSVRecord record) {
        int id = Integer.parseInt(record.get(Headers.OrganisationID));
        String name = record.get(Headers.OrganisationName);
        String address1 = record.get(Headers.Address1);
        String address2 = record.get(Headers.Address2);
        String address3 = record.get(Headers.Address3);
        String city = record.get(Headers.City);
        String county = record.get(Headers.County);
        String postcode = record.get(Headers.Postcode);
        double latitude = isNotBlank(record.get(Headers.Latitude)) ? Double.parseDouble(record.get(Headers.Latitude)) : 0;
        double longitude = isNotBlank(record.get(Headers.Latitude)) ? Double.parseDouble(record.get(Headers.Longitude)) : 0;

        MedicalCenter medicalCenter = new MedicalCenter();
        medicalCenter.setOrganisationId(id);
        medicalCenter.setOrganisationName(name);
        medicalCenter.setAddress1(address1);
        medicalCenter.setAddress2(address2);
        medicalCenter.setAddress3(address3);
        medicalCenter.setCity(city);
        medicalCenter.setCounty(county);
        medicalCenter.setPostcode(postcode);
        medicalCenter.setLatitude(latitude);
        medicalCenter.setLongitude(longitude);
        return medicalCenter;
    }

    private enum Headers {
        OrganisationID,
        OrganisationCode,
        OrganisationType,
        SubType,
        Sector,
        OrganisationStatus,
        IsPimsManaged,
        OrganisationName,
        Address1,
        Address2,
        Address3,
        City,
        County,
        Postcode,
        Latitude,
        Longitude,
        ParentODSCode,
        ParentName,
        Phone,
        Email,
        Website,
        Fax
    }
}
