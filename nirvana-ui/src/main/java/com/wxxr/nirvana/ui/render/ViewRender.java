package com.wxxr.nirvana.ui.render;

import java.io.IOException;

import com.wxxr.nirvana.IRenderContext;
import com.wxxr.nirvana.exception.NirvanaException;
import com.wxxr.nirvana.ui.UIComponentRender;
import com.wxxr.nirvana.workbench.IDispatchUI;
import com.wxxr.nirvana.workbench.IView;
import com.wxxr.nirvana.workbench.impl.UIComponent;

public class ViewRender extends UIComponentRender {

	public boolean accept(UIComponent component) {
		return component instanceof IView;
	}

	@Override
	protected void doRenderComponent(UIComponent component,
			IRenderContext context) throws NirvanaException {
		IDispatchUI disppach = (IDispatchUI)component;
		String uri = disppach.getURI();
		try {
			context.getRequestContext().dispatch(uri);
		} catch (IOException e) {
			throw new NirvanaException(e);
		}
	}

}
