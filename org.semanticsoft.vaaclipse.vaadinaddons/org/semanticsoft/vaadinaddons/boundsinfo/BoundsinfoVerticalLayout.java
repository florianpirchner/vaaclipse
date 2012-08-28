/*******************************************************************************
 * Copyright (c) 2012 Rushan R. Gilmullin and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Rushan R. Gilmullin - initial API and implementation
 *******************************************************************************/

package org.semanticsoft.vaadinaddons.boundsinfo;

import java.util.Map;

import org.semanticsoft.commons.geom.Bounds;
import org.semanticsoft.vaadinaddons.boundsinfo.client.ui.BoundsParser;

import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;
import com.vaadin.ui.VerticalLayout;

/**
 * Server side component for the VBoundsinfoVerticalLayout widget.
 */
@com.vaadin.ui.ClientWidget(org.semanticsoft.vaadinaddons.boundsinfo.client.ui.VBoundsinfoVerticalLayout.class)
public class BoundsinfoVerticalLayout extends VerticalLayout
{

	private String message = "Click here.";
	private int clicks = 0;
	
	private Integer absoluteLeft;
	private Integer absoluteTop;
	private Integer offsetWidth;
	private Integer offsetHeight;

	@Override
	public void paintContent(PaintTarget target) throws PaintException
	{
		super.paintContent(target);

		// Paint any component specific content by setting attributes
		// These attributes can be read in updateFromUIDL in the widget.
		target.addAttribute("clicks", clicks);
		target.addAttribute("message", message);

		// We could also set variables in which values can be returned
		// but declaring variables here is not required
	}

	/**
	 * Receive and handle events and other variable changes from the client.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void changeVariables(Object source, Map<String, Object> variables)
	{
		super.changeVariables(source, variables);
		
		if (variables.containsKey("bounds"))
		{
			System.out.println("update bounds of BoundsInfoVerticalLayout");
			String boundsStr = (String) variables.get("bounds");
			if (boundsStr != null)
			{
				int[] bounds = BoundsParser.fromString(boundsStr);
				absoluteLeft = bounds[0];
				absoluteTop = bounds[1];
				offsetWidth = bounds[2];
				offsetHeight = bounds[3];
			}
			else
				System.err.println("Error update bounds of BoundsInfoVerticalLayout");
		}
		
		if (variables.containsKey("absolute_top"))
		{
			absoluteTop = (Integer) variables.get("absolute_top");	
		}
		
		if (variables.containsKey("offset_width"))
		{
			offsetWidth = (Integer) variables.get("offset_width");	
		}
		
		if (variables.containsKey("offset_height"))
		{
			offsetHeight = (Integer) variables.get("offset_height");	
		}
		
//		System.out.println("absoluteLeft: " + absoluteLeft);
//		System.out.println("absoluteTop: " + absoluteTop);
//		System.out.println("offsetWidth: " + offsetWidth);
//		System.out.println("offsetHeight: " + offsetHeight);
		
	}
	
	public boolean hasBoundsInfo()
	{
		return this.absoluteTop != null;
	}
	
	public Integer getAbsoluteLeft()
	{
		return absoluteLeft;
	}
	
	public Integer getAbsoluteTop()
	{
		return absoluteTop;
	}
	
	public Integer getOffsetWidth()
	{
		return offsetWidth;
	}
	
	public Integer getOffsetHeight()
	{
		return offsetHeight;
	}
	
	public Bounds getBounds()
	{
		if (hasBoundsInfo())
			return new Bounds(absoluteLeft, absoluteTop, offsetWidth, offsetHeight);
		else
			return null;
	}
}