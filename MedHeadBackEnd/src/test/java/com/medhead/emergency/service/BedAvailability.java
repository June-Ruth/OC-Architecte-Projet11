package com.medhead.emergency.service;

public interface BedAvailability {

    /**
     * Check if a bed is available for a given hospital.
     * @param organisationId, id of the given hospital
     * @return true if a bed is available
     */
    boolean isBedAvailable(int organisationId);

    /**
     * Register one bed in a given hospital.
     * @param organisationId, id of the given hospital
     * @return true if the reservation succeed
     */
    boolean registerOneBedReservation(int organisationId);
}
