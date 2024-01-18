package com.medhead.emergency.datasource;

import com.medhead.emergency.configuration.EnvironmentValues;
import com.medhead.emergency.entity.GeographicCoordinates;
import com.medhead.emergency.entity.HospitalCsvHeader;
import com.medhead.emergency.entity.MedicalCenter;
import com.medhead.emergency.entity.Speciality;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.apache.logging.log4j.util.Strings.isNotBlank;

/**
 * For POC usage only
 */
public enum MedicalCenterDataBaseManager {

    INSTANCE;

    private final MedicalCenterDataBase MEDICAL_CENTER_DB;

    //@Value("${data.csv.file}")
    //private String hospitalFileUrl;

    MedicalCenterDataBaseManager() {
        List<MedicalCenter> medicalCenters = new ArrayList<>();
        Reader reader;
        try {
            String hospitalFileUrl = EnvironmentValues.getHospitalFileUrl();
            reader = new FileReader(hospitalFileUrl, StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try(reader) {
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

        MEDICAL_CENTER_DB = new MedicalCenterDataBase(medicalCenters);
    }

    public MedicalCenterDataBase getMEDICAL_CENTER_DB() {
        return MEDICAL_CENTER_DB;
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
        medicalCenter.setGeographicCoordinates(new GeographicCoordinates(latitude, longitude));
        List<Speciality> specialities = new ArrayList<>();
        Speciality speciality1 = Speciality.ALLERGY;
        Speciality speciality2 = Speciality.randomSpeciality();
        Speciality speciality3 = Speciality.randomSpeciality();
        specialities.add(speciality1);
        specialities.add(speciality2);
        specialities.add(speciality3);
        medicalCenter.setSpecialities(specialities);
        return medicalCenter;
    }

}
