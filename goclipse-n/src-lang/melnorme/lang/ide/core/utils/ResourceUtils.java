/*******************************************************************************
 * Copyright (c) 2014, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Bruno Medeiros - initial API and implementation
 *******************************************************************************/
package melnorme.lang.ide.core.utils;

import static melnorme.utilbox.core.Assert.AssertNamespace.assertFail;

import java.io.InputStream;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

public class ResourceUtils {

	public static void writeToFile(IFile file, InputStream is) throws CoreException {
		if(file.exists()) {
			file.setContents(is, false, false, null);
		} else {
			file.create(is, false, null);
		}
	}

	public static void createFolder(IFolder folder, boolean force, boolean local, IProgressMonitor monitor) 
			throws CoreException {
		if (folder.exists()) {
			return;
		}
		
		IContainer parent = folder.getParent();
		if (parent instanceof IFolder) {
			createFolder((IFolder) parent, force, local, monitor);
		}
		folder.create(force, local, monitor);
	}
	
	public static String printDelta(IResourceDelta delta) {
		StringBuilder sb = new StringBuilder();
		doPrintDelta(delta, "  ", sb);
		return sb.toString();
	}
	
	protected static void doPrintDelta(IResourceDelta delta, String indent, StringBuilder sb) {
		sb.append(indent);
		sb.append(delta);
		
		sb.append(" " + deltaKindToString(delta) + "\n");
		for (IResourceDelta childDelta : delta.getAffectedChildren()) {
			doPrintDelta(childDelta, indent + "  ", sb);
		}
	}
	
	protected static String deltaKindToString(IResourceDelta delta) {
		switch (delta.getKind()) {
		case IResourceDelta.ADDED: return "+";
		case IResourceDelta.REMOVED: return "-";
		case IResourceDelta.CHANGED: return "*";
		case IResourceDelta.ADDED_PHANTOM: return "%+%";
		case IResourceDelta.REMOVED_PHANTOM: return "%-%";
		default:
			throw assertFail();
		}
	}
	
}