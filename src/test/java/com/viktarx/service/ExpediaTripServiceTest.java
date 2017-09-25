package com.viktarx.service;

import com.viktarx.agent.TripOption;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.Assert.assertTrue;

/**
 * Tests for the ExpediaTripService class.
 */
public class ExpediaTripServiceTest {
    @Test
    public void urlParameters() throws Exception {
    }

    @Test
    public void parsedTripOptionsFromWebPageContent() throws Exception {
    }

    @Test
    public void serviceUrl() throws Exception {
    }

    @Test
    public void tripOptions() throws Exception {
        TripService service = new ExpediaTripService();
        Set<TripOption> tripOptions = service.tripOptions("San Francisco, CA", "New York, NY", LocalDate.now().plusDays(5), LocalDate.now().plusDays(7));
        assertTrue(tripOptions != null && !tripOptions.isEmpty());
    }

}