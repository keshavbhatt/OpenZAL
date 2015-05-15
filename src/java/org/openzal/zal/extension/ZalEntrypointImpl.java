/*
 * ZAL - The abstraction layer for Zimbra.
 * Copyright (C) 2014 ZeXtras S.r.l.
 *
 * This file is part of ZAL.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation, version 2 of
 * the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with ZAL. If not, see <http://www.gnu.org/licenses/>.
 */

package org.openzal.zal.extension;

import org.openzal.zal.ZalBuildInfo;
import org.openzal.zal.ZalVersion;
import org.openzal.zal.lib.ZimbraVersion;
import org.openzal.zal.log.ZimbraLog;
import org.openzal.zal.tools.JarUtils;

import java.io.File;
import java.io.IOException;

public class ZalEntrypointImpl implements ZalEntrypoint
{
  private String           mDirectoryName;
  private File             mDirectory;
  private ExtensionManager mExtensionManager;
  private boolean          mExtensionPathExists;
  private ZalEntrypoint    mZalEntryPoint;
  private File             mCustomExtensionDirectory;

  public ZalEntrypointImpl()
  {
    mExtensionManager = null;
    mDirectoryName = JarUtils.getCurrentJar().getParentFile().getName();
    mDirectory = JarUtils.getCurrentJar().getParentFile();
    mExtensionPathExists = false;
    mZalEntryPoint = null;
    mCustomExtensionDirectory = null;
  }

  private ExtensionManager getExtensionManager()
  {
    try
    {
      if (mExtensionManager == null)
      {
        mExtensionManager = (ExtensionManager) this.getClass().getClassLoader().loadClass(
          "org.openzal.zal.extension.ExtensionManagerImpl"
        ).newInstance();
      }

      return mExtensionManager;
    }
    catch (Exception e)
    {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void provideCustomClassLoader(ClassLoader classLoader)
  {
    getExtensionManager().setCustomClassLoader(classLoader);
  }

  @Override
  public void provideCustomExtensionController(ZalExtensionController zalExtensionController)
  {
    getExtensionManager().setCustomZalExtensionController(zalExtensionController);
  }

  @Override
  public void provideCustomExtensionDirectory(File extensionDirectory)
  {
    mDirectory = extensionDirectory;
    getExtensionManager().setCustomExtensionDirectory(extensionDirectory);
  }

  @Override
  public String getName()
  {
    return "Zimbra Abstraction Layer for: " + mDirectoryName;
  }

  @Override
  public void init()
  {
    ZimbraLog.mailbox.info(
      "Starting ZAL version " +
        ZalVersion.current +
        " commit " +
        ZalBuildInfo.COMMIT
    );
    if (!ZimbraVersion.current.equals(ZalVersion.target))
    {
      throw new RuntimeException("Zimbra version mismatch - ZAL built for Zimbra: " + ZalVersion.target.toString());
    }

    File extensionPathFile = new File(mDirectory, "extension-path");
    mExtensionPathExists = extensionPathFile.exists();

    try
    {
      if (mExtensionPathExists)
      {
        ZimbraLog.mailbox.info("File "+extensionPathFile.getAbsolutePath()+" present, using tiny boot");

        if( mCustomExtensionDirectory != null )
        {
          mZalEntryPoint = new TinyBoot(extensionPathFile).createZalEntryPoint(mCustomExtensionDirectory, new Controller());
        }
        else
        {
          mZalEntryPoint = new TinyBoot(extensionPathFile).createZalEntryPoint(new Controller());
        }

        mZalEntryPoint.init();
      }
      else
      {
        ZimbraLog.mailbox.info("File "+extensionPathFile.getAbsolutePath()+" not present, using standard boot");
        getExtensionManager().loadExtension();
      }
    }
    catch (Exception e)
    {
      throw new RuntimeException("Unable to load extension", e);
    }
  }

  class Controller implements ZalExtensionController
  {
    private void checkState()
    {
      if( mZalEntryPoint == null )
      {
        throw new RuntimeException();
      }
    }

    @Override
    public void shutdown()
    {
      checkState();
      destroy();
    }

    @Override
    public void reboot()
    {
      checkState();
      destroy();
      init();
      postInit();
    }

    @Override
    public void reload(File extensionDirectory)
    {
      checkState();
      destroy();
      mCustomExtensionDirectory = extensionDirectory;
      init();
      postInit();
    }
  }

  @Override
  public void postInit()
  {
    if( mZalEntryPoint != null )
    {
      mZalEntryPoint.postInit();
    }
    else
    {
      if( mExtensionManager != null )
      {
        mExtensionManager.startExtension();
      }
    }
  }

  @Override
  public void destroy()
  {
    if( mZalEntryPoint != null )
    {
      mZalEntryPoint.destroy();
    }
    else
    {
      if( mExtensionManager != null )
      {
        mExtensionManager.shutdownExtension();
      }
    }

    mZalEntryPoint = null;
  }
}
