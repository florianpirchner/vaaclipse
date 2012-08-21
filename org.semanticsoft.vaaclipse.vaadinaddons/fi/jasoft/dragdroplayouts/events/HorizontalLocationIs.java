/*
 * Copyright 2011 John Ahlroos
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fi.jasoft.dragdroplayouts.events;

import com.vaadin.event.dd.acceptcriteria.TargetDetailIs;
import com.vaadin.terminal.gwt.client.ui.dd.HorizontalDropLocation;

/**
 * A client side criterion for determining the horizontal location
 */
@SuppressWarnings("serial")
public final class HorizontalLocationIs extends TargetDetailIs {
	
	public static final String HORIZONTAL_LOCATION_ATTRIBUTE = "hdetail";
	
	/**
	 * Was the drop made to the left of the centerline of the component
	 */
    public static final HorizontalLocationIs LEFT = new HorizontalLocationIs(
            HorizontalDropLocation.LEFT);
    
    /**
     * Was the drop made in the middle of the component
     */
    public static final HorizontalLocationIs CENTER = new HorizontalLocationIs(
            HorizontalDropLocation.CENTER);
    
    /**
     * Was the drop made to the right of the centerline of the component
     */
    public static final HorizontalLocationIs RIGHT = new HorizontalLocationIs(
            HorizontalDropLocation.RIGHT);

    /**
     * A target detail for the horizontal location of a drop
     * 
     * @param location
     * 		The location of the drop
     */
    private HorizontalLocationIs(HorizontalDropLocation location) {
        super(HORIZONTAL_LOCATION_ATTRIBUTE, location.name());
    }
}
