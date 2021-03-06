package com.wxxr.nirvana.platform;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import java.net.URL;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.wxxr.nirvana.exception.NirvanaException;
import com.wxxr.nirvana.theme.ITheme;
import com.wxxr.nirvana.workbench.IDispatchUI;
import com.wxxr.nirvana.workbench.IProduct;
import com.wxxr.nirvana.workbench.IWebResource;
import com.wxxr.nirvana.workbench.IWorkbench;
import com.wxxr.nirvana.workbench.IWorkbenchPage;
import com.wxxr.nirvana.workbench.impl.WorkbenchBoostrapBefore;
import com.wxxr.nirvana.workbench.impl.WorkbenchFactory;

public class WorkbenchTest {

	@Before
	public void setUp() throws Exception {
		System.getProperties().setProperty("data.dir", "target/partest");
		String parfile = "test.par";
		URL url = PlatformTest.class.getResource(parfile);

		PlatformLocator.getPlatform().start();

		PlatformLocator.getPlatform().deployPlugin(url);
		String parstylefile = "testStyle.par";
		url = PlatformTest.class.getResource(parstylefile);
		PlatformLocator.getPlatform().deployPlugin(url);
		Thread.currentThread().sleep(5000L);
	}

	// @Test
	public void dumyTest() {

	}

	@Test
	public void testWorkbenchCreate() throws NirvanaException {
		WorkbenchBoostrapBefore boostrap = new WorkbenchBoostrapBefore();
		boostrap.start(new HashMap<String, Object>()); 
		IProduct p = boostrap.getServiceManager().getProductManager().getProductById(
				"com.wxxr.nirvana.test.nirvana");
		assertNotNull(p);
		String themeid = p.getTheme();
		assertEquals("com.wxxr.nirvana.style.nirvanaStyle_theme", themeid);
		ITheme theme = boostrap.getServiceManager().getThemeManager().getTheme(themeid);
		assertNotNull(theme);
		String themeuri = ((IDispatchUI) theme.getDesktop()).getURI();
		assertEquals("desktopuri", themeuri);
		// String pageuri = ((IDispatchUI)theme.getPageLayout()).getURI();
		// assertEquals("pagelayouturi", pageuri);

		String defaultPage = p.getDefaultPage();
		assertEquals("com.wxxr.nirvana.test.niravanaPage", defaultPage);
		assertEquals(2, p.getAllPages().length);

		IWorkbench workbench = WorkbenchFactory.createWorkbench();
		IWorkbenchPage page = workbench.getWorkbenchPageManager()
				.getWorkbenchPage(defaultPage);
		assertNotNull(page);
		String[] views = page.getAllViewIds();
		assertEquals(3, views.length);

//		views = workbench.getWorkbenchPageManager().getViewIds();
//		assertEquals(3, views.length);

//		IView view = workbench.getViewManager().find(
//				"com.wxxr.nirvana.test.chart2");
//		assertNotNull(view);
//		ResourceRef[] vrr = view.getResourcesRef();
//		assertEquals(1, vrr.length);

		List<IWebResource> webrs = boostrap.getServiceManager().getWebResourceManager()
				.getResources();
		assertEquals(3, webrs.size());

		for (IWebResource wr : webrs) {
			assertEquals("js", wr.getType());
		}
	}

}
