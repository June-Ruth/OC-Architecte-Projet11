package com.medhead.emergency.service;

/**
 * This implementation is dedicated for POC usage only.
 * The production implementation should call directly the API for bed availability.
 */
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
    public boolean registerOneBedReservation(int organisationId) {
        return true;
    }
}
