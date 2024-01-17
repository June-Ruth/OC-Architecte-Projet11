package com.medhead.emergency.service;

public interface BedAvailabilityService {

    /**
     * Check if a bed is available for a given hospital.
     * @param organisationId, id of the given hospital
     * @return true if a bed is available
     */
    boolean isBedAvailable(int organisationId);

    /**
     * Register one bed in a given hospital.
     * @param organisationId, id of the given hospital
     * @return number of the reservation if succeed
     */
    int registerOneBedReservation(int organisationId);
}
