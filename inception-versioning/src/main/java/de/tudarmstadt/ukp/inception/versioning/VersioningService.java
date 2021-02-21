/*
 * Licensed to the Technische Universität Darmstadt under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The Technische Universität Darmstadt
 * licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.tudarmstadt.ukp.inception.versioning;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

import org.eclipse.jgit.api.errors.GitAPIException;

import de.tudarmstadt.ukp.clarin.webanno.model.Project;

public interface VersioningService
{
    /**
     * Saves the project configuration and annotations of all users for the given project.
     * 
     * @param aProject
     *            The project to snapshot
     */
    void snapshotCompleteProject(Project aProject);

    void labelCurrentVersion(Project aProject, String aLabel);

    File getRepoDir(Project aProject);

    void initializeRepo(Project aProject) throws GitAPIException;

    boolean repoExists(Project aProject);

    Optional<String> getRemote(Project aProject) throws IOException, GitAPIException;

    void setRemote(Project aProject, String aValue)
        throws IOException, GitAPIException, URISyntaxException;
}
