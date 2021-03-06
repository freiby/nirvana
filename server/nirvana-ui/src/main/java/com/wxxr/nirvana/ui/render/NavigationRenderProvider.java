package com.wxxr.nirvana.ui.render;

import java.io.IOException;
import java.io.PrintWriter;

import com.wxxr.nirvana.IUIRenderContext;
import com.wxxr.nirvana.context.IRequestContext;
import com.wxxr.nirvana.exception.NirvanaException;
import com.wxxr.nirvana.jsp.taglib.UITag;
import com.wxxr.nirvana.ui.PageNavigationContext.PageNavigation;
import com.wxxr.nirvana.workbench.IRenderContext;
import com.wxxr.nirvana.workbench.IWorkbenchPage;
import com.wxxr.nirvana.workbench.impl.UIComponent;

public class NavigationRenderProvider extends CommonRenderProvider {

	private UIComponent component;
	public void doRender(UIComponent component, IUIRenderContext context)
			throws NirvanaException {
		if (!context.getParameterMap().containsKey(UITag.RENDER)) {
			PageNavigation nav = (PageNavigation) component;
			try {
				PrintWriter out = context.getRequestContext().getWriter();
				IWorkbenchPage[] pages = nav.getAllPages();
				out.println("<ol>");
				for (IWorkbenchPage page : pages) {
					out.println("<li>" + page.getName() + "</li>");
				}
				out.println("</ol>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			String uri = (String) context.getParameterMap().get(UITag.RENDER);
			try {
				context.getRequestContext().dispatch(uri);
			} catch (IOException e) {
				throw new NirvanaException(e);
			}
		}
		this.component = component; 
	}

	public String processComponent() {
		return "navigation";
	}
}
