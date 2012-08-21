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
package fi.jasoft.dragdroplayouts.drophandlers;

import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.AbsoluteLayout.ComponentPosition;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;

import fi.jasoft.dragdroplayouts.DDAbsoluteLayout;
import fi.jasoft.dragdroplayouts.DDAbsoluteLayout.AbsoluteLayoutTargetDetails;
import fi.jasoft.dragdroplayouts.events.LayoutBoundTransferable;

/**
 * A default drop handler for absolute layouts
 * 
 */
@SuppressWarnings("serial")
public class DefaultAbsoluteLayoutDropHandler extends
        AbstractDefaultLayoutDropHandler {

    /**
     * Called when a component changed location within the layout
     * 
     * @param event
     *            The drag and drop event
     */
    @Override
    protected void handleComponentReordering(DragAndDropEvent event) {
        AbsoluteLayoutTargetDetails details = (AbsoluteLayoutTargetDetails) event
                .getTargetDetails();
        DDAbsoluteLayout layout = (DDAbsoluteLayout) details.getTarget();
        LayoutBoundTransferable transferable = (LayoutBoundTransferable) event
                .getTransferable();
        Component component = transferable.getComponent();

        // Get top-left pixel position
        int leftPixelPosition = details.getRelativeLeft();
        int topPixelPosition = details.getRelativeTop();

        ComponentPosition position = layout.getPosition(component);

        position.setLeft((float) leftPixelPosition, Sizeable.UNITS_PIXELS);
        position.setTop((float) topPixelPosition, Sizeable.UNITS_PIXELS);
    }

    /**
     * Handle a drop from another layout
     * 
     * @param event
     *            The drag and drop event
     */
    @Override
    protected void handleDropFromLayout(DragAndDropEvent event) {
        AbsoluteLayoutTargetDetails details = (AbsoluteLayoutTargetDetails) event
                .getTargetDetails();
        LayoutBoundTransferable transferable = (LayoutBoundTransferable) event
                .getTransferable();
        Component component = transferable.getComponent();
        Component source = event.getTransferable().getSourceComponent();
        DDAbsoluteLayout layout = (DDAbsoluteLayout) details.getTarget();
        int leftPixelPosition = details.getRelativeLeft();
        int topPixelPosition = details.getRelativeTop();

        // Check that we are not dragging an outer layout into an
        // inner
        // layout
        Component parent = source.getParent();
        while (parent != null) {
            parent = parent.getParent();
        }

        // remove component from source
        if (source instanceof ComponentContainer) {
            ComponentContainer sourceLayout = (ComponentContainer) source;
            sourceLayout.removeComponent(component);
        }

        // Add component to absolute layout
        layout.addComponent(component, "left:" + leftPixelPosition + "px;top:"
                + topPixelPosition + "px");
    }
}
