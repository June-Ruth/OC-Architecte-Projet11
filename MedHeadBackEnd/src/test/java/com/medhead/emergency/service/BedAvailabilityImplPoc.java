package com.medhead.emergency.service;

/**
 * This implementation is dedicated for POC usage only.
 * The production implementation should call directly the API for bed availability.
 */
public class BedAvailabilityImplPoc implements BedAvailability {

    public BedAvailabilityImplPoc() {}

    /**
     * @inheritDoc
     */
    @Override
    public boolean isBedAvailable(int hospitalId) {
        return true;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean registerOneBedReservation(int hospitalId) {
        return true;
    }
}
