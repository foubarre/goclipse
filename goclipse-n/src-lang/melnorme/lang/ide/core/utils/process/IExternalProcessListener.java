/*******************************************************************************
 * Copyright (c) 2014, 2014 Bruno Medeiros and other Contributors.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Bruno Medeiros - initial API and implementation
 *******************************************************************************/
package melnorme.lang.ide.core.utils.process;

import java.io.IOException;

import org.eclipse.core.resources.IProject;

import melnorme.utilbox.concurrency.ExternalProcessOutputHelper;



public interface IExternalProcessListener {
	
	void handleProcessStarted(ProcessBuilder pb, IProject project, ExternalProcessOutputHelper processHelper);
	
	void handleProcessStartFailure(ProcessBuilder pb, IProject project, IOException e);
	
}