/**
 * Copyright (c) 2010-2017 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.roomba.discovery;

import static org.openhab.binding.roomba.RoombaBindingConstants.ROOMBA_THING_TYPE;

import java.util.Collections;

import org.eclipse.smarthome.config.discovery.AbstractDiscoveryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link roombaDiscoveryParticipant} is responsible for processing the
 * results of searched Roomba devices
 *
 * @author Brian Tillman - Initial contribution
 */
public class RoombaDiscoveryService extends AbstractDiscoveryService {
    private static final int DISCOVER_TIMEOUT_SECONDS = 30;
    private RoombaDiscoveryWorker discWorker = new RoombaDiscoveryWorker();
    private Logger logger = LoggerFactory.getLogger(RoombaDiscoveryService.class);

    public RoombaDiscoveryService() {
        super(Collections.singleton(ROOMBA_THING_TYPE), DISCOVER_TIMEOUT_SECONDS, false);
    }

    @Override
    protected void startScan() {
        discWorker.startDiscovery();
    }

    @Override
    protected synchronized void stopScan() {

        super.stopScan();
    }

    @Override
    protected void startBackgroundDiscovery() {

    }

    @Override
    protected void stopBackgroundDiscovery() {

    }
}
