package org.activiti.designer.kickstart.eclipse.common;

import java.net.URL;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class KickstartPlugin extends AbstractUIPlugin {

  public static final String PLUGIN_ID = "org.activiti.designer.kickstart.eclipse"; //$NON-NLS-1$

  private static KickstartPlugin _plugin;

  // The image cache object used in the plugin
  private static ImageCache imageCache;

  /**
   * Creates the Plugin and caches its default instance.
   */
  public KickstartPlugin() {
    _plugin = this;
  }

  // ============ overwritten methods of AbstractUIPlugin ====================

  /**
   * This method is called upon plug-in activation.
   * 
   * @param context
   *          the context
   * 
   * @throws Exception
   *           the exception
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);

    // Initialize the image cache
    imageCache = new ImageCache();
  }

  @Override
  public void stop(BundleContext context) throws Exception {
    // Allow the image cache to destroy itself so image references are cleaned
    // up
    imageCache.dispose();
  }

  // ======================== static access methods ==========================

  /**
   * Gets the default-instance of this plugin. Actually the default-instance
   * should always be the only instance -> Singleton.
   * 
   * @return the default
   */
  public static KickstartPlugin getDefault() {
    return _plugin;
  }

  // =========================== public helper methods ======================

  /**
   * Returns the current Workspace.
   * 
   * @return The current Workspace.
   */
  public static IWorkspace getWorkspace() {
    return ResourcesPlugin.getWorkspace();
  }

  /**
   * Returns the URL, which points to where this Plugin is installed.
   * 
   * @return The URL, which points to where this Plugin is installed.
   */
  public static URL getInstallURL() {
    return getDefault().getBundle().getEntry("/");
  }

  /**
   * Returns the Plugin-ID.
   * 
   * @return The Plugin-ID.
   */
  public static String getID() {
    return getDefault().getBundle().getSymbolicName();
  }

  /**
   * Returns the currently active WorkbenchPage.
   * 
   * @return The currently active WorkbenchPage.
   */
  public static IWorkbenchPage getActivePage() {
    IWorkbenchWindow workbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
    if (workbenchWindow != null)
      return workbenchWindow.getActivePage();
    return null;
  }

  /**
   * Returns the currently active Shell.
   * 
   * @return The currently active Shell.
   */
  public static Shell getShell() {
    return getDefault().getWorkbench().getActiveWorkbenchWindow().getShell();
  }

  /**
   * Returns an image descriptor for the image file at the given plug-in
   * relative path
   * 
   * @param path
   *          the path
   * @return the image descriptor
   */
  public static ImageDescriptor getImageDescriptor(String path) {
    return imageDescriptorFromPlugin(PLUGIN_ID, path);
  }

  /**
   * Gets an image from this plugin and serves it from the {@link ImageCache}.
   * 
   * @param pluginImage
   *          the PluginImage to get the image for
   * 
   * @return an Image if the image was found, null otherwise
   */
  public static Image getImage(PluginImage pluginImage) {
    return imageCache.getImage(pluginImage);
  }

  /**
   * Gets an image from this plugin and serves it from the {@link ImageCache}.
   * 
   * @param imageDescriptor
   *          the ImageDescriptor to get the image for
   * 
   * @return an Image if the image was found, null otherwise
   */
  public static Image getImage(ImageDescriptor imageDescriptor) {
    return ImageCache.getImage(imageDescriptor);
  }

  /**
   * Gets an image descriptor for an image from this plugin.
   * 
   * @param pluginImage
   *          the PluginImage to get the image descriptor for
   * 
   * @return an ImageDescriptor if the image was found, null otherwise
   */
  public static ImageDescriptor getImageDescriptor(PluginImage pluginImage) {
    return getImageDescriptor(pluginImage.getImagePath());
  }

}
