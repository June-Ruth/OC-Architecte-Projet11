package com.medhead.emergency.service;

import org.springframework.stereotype.Service;

/**
 * This implementation is dedicated for POC usage only.
 * The production implementation should call directly the API for bed availability.
 */
@Service
public class BedAvailabilityServicePocImpl implements BedAvailabilityService {

    public BedAvailabilityServicePocImpl() {}

    /**
     * @inheritDoc
     */
    @Override
    public boolean isBedAvailable(int organisationId) {
        return true;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int registerOneBedReservation(int organisationId) {
        return 27;
    }
}
