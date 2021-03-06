package com.wxxr.nirvana.ui.render;

import java.io.IOException;

import com.wxxr.nirvana.IUIRenderContext;
import com.wxxr.nirvana.exception.NirvanaException;
import com.wxxr.nirvana.util.JspUtil;
import com.wxxr.nirvana.workbench.IDispatchUI;
import com.wxxr.nirvana.workbench.impl.UIComponent;

public class ViewRenderProvider extends UIComponentRender {

	@Override
	protected void doRenderComponent(UIComponent component,
			IUIRenderContext context) throws NirvanaException {
		IDispatchUI disppach = (IDispatchUI) component;
		String uri = disppach.getURI();
		try {
			context.getRequestContext().dispatch(
					JspUtil.getRealPath(component.getContributorId(),
							component.getContributorVersion(), uri));
		} catch (IOException e) {
			throw new NirvanaException(e);
		}
	}

	public String processComponent() {
		return "view";
	}

}
