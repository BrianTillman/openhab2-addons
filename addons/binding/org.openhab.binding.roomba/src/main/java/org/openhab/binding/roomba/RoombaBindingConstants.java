/**
 * Copyright (c) 2014-2016 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.roomba;

import org.eclipse.smarthome.core.thing.ThingTypeUID;

/**
 * The {@link RoombaBinding} class defines common constants, which are
 * used across the whole binding.
 *
 * @author Brian Tillman - Initial contribution
 */
public class RoombaBindingConstants {

    public static final String BINDING_ID = "roomba";

    // List of all Thing Type UIDs
    public final static ThingTypeUID ROOMBA_THING_TYPE = new ThingTypeUID(BINDING_ID, "roomba");

    // List of all Channel ids
    public final static String START = "start";
    public final static String PAUSE = "pause";
    public final static String DOCK = "dock";

}
