/*
 * @(#)AbstractWorkbenchPage.java	 2007-9-28
 *
 * Copyright 2004-2007 WXXR Network Technology Co. Ltd. 
 * All rights reserved.
 * 
 * WXXR PROPRIETARY/CONFIDENTIAL.
 */
package com.wxxr.nirvana.workbench.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wxxr.nirvana.platform.IConfigurationElement;
import com.wxxr.nirvana.workbench.IRender;
import com.wxxr.nirvana.workbench.IView;
import com.wxxr.nirvana.workbench.IViewManager;
import com.wxxr.nirvana.workbench.IWorkbenchPage;
import com.wxxr.nirvana.workbench.IWorkbenchPageManager;

/**
 * @author fudapeng
 *
 */
public class WorkbenchPage extends UIComponent implements IWorkbenchPage {

	protected static final Log log = LogFactory.getLog(WorkbenchPage.class);

	protected IWorkbenchPageManager manager;
	protected String defaultRegionId;
	protected Map<String, List<String>> viewsOfRegion = new HashMap<String, List<String>>();

	public static final String VIEW_ELEMENT = "view";

	private String[] viewids;

	private ViewRef[] viewRefs;

	private IViewManager viewManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wxxr.web.platform.interfaces.WorkbenchPage#getWorkbenchWindow()
	 */
	public IWorkbenchPageManager getWorkbenchPageManager() {
		return manager;
	}

	public void init(IWorkbenchPageManager manager,
			IConfigurationElement config, IRender render) {
		super.init(config, render);
		initViews(config);
	}

	protected void initViews(IConfigurationElement config) {
		IConfigurationElement[] viewConfigs = config.getChildren(VIEW_ELEMENT);
		if (viewConfigs == null)
			return;
		viewids = new String[viewConfigs.length];
		viewRefs = new ViewRef[viewConfigs.length];
		for (int j = 0; j < viewConfigs.length; j++) {

			IConfigurationElement viewConfig = viewConfigs[j];
			String viewid = viewConfig.getAttribute(UIComponent.ATT_REF);
			viewids[j] = viewid;
			ViewRef v = new ViewRef(viewid, viewConfig);
			viewRefs[j] = v;
		}
	}

	public boolean hasView(String vid) {
		if (viewids != null) {
			for (String id : viewids) {
				if (id.equals(vid)) {
					return true;
				}
			}
		}
		return false;
	}

	public IView getViewsById(String id) {
		if (hasView(id)) {
			return viewManager.find(id);
		}
		return null;
	}

	public String[] getAllViewIds() {
		return viewids;
	}

	public void destroy() {

	}

	public class ViewRef {

		private String id;
		private IConfigurationElement elem;

		public ViewRef(String id, IConfigurationElement elem) {
			super();
			this.id = id;
			this.elem = elem;
		}

		public String get(String attr) {
			if (elem != null) {
				return elem.getAttribute(attr);
			}
			return null;
		}

		public String getId() {
			return id;
		}

	}

	public ViewRef[] getAllViewRefs() {
		return this.viewRefs;
	}

	public ViewRef getViewRefById(String id) {
		if (viewRefs != null && StringUtils.isNotBlank(id)) {
			for (ViewRef item : viewRefs) {
				if (item.getId().equals(id)) {
					return item;
				}
			}
		}
		return null;
	}

}
