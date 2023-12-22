package com.medhead.emergency.repository;

import com.medhead.emergency.entity.HospitalCsvHeader;
import com.medhead.emergency.entity.MedicalCenter;
import com.medhead.emergency.entity.Speciality;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
                    .setHeader(HospitalCsvHeader.class)
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

    /**
     * @inheritDoc
     */
    @Override
    public List<MedicalCenter> findAllMedicalCentersBySpeciality(Speciality speciality) {
        List<MedicalCenter> medicalCentersBySpeciality = findAllMedicalCenters().stream()
                .filter(medicalCenter -> medicalCenter.getSpecialities().contains(speciality))
                .toList();
        return medicalCentersBySpeciality;
    }

    private MedicalCenter transformCsvToMedicalCenter(CSVRecord record) {
        int id = Integer.parseInt(record.get(HospitalCsvHeader.OrganisationID));
        String name = record.get(HospitalCsvHeader.OrganisationName);
        String address1 = record.get(HospitalCsvHeader.Address1);
        String address2 = record.get(HospitalCsvHeader.Address2);
        String address3 = record.get(HospitalCsvHeader.Address3);
        String city = record.get(HospitalCsvHeader.City);
        String county = record.get(HospitalCsvHeader.County);
        String postcode = record.get(HospitalCsvHeader.Postcode);
        double latitude = isNotBlank(record.get(HospitalCsvHeader.Latitude)) ? Double.parseDouble(record.get(HospitalCsvHeader.Latitude)) : 0;
        double longitude = isNotBlank(record.get(HospitalCsvHeader.Latitude)) ? Double.parseDouble(record.get(HospitalCsvHeader.Longitude)) : 0;

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
        List<Speciality> specialities = new ArrayList<>();
        Speciality speciality1 = Speciality.randomSpeciality();
        Speciality speciality2 = Speciality.randomSpeciality();
        Speciality speciality3 = Speciality.randomSpeciality();
        specialities.add(speciality1);
        specialities.add(speciality2);
        specialities.add(speciality3);
        medicalCenter.setSpecialities(specialities);
        return medicalCenter;
    }
}
