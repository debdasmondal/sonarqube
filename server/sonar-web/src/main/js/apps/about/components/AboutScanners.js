/*
 * SonarQube
 * Copyright (C) 2009-2016 SonarSource SA
 * mailto:contact AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
import React from 'react';
import ReadMore from './ReadMore';
import { translate } from '../../../helpers/l10n';

const scanners = [
  {
    key: 'sonarqube',
    link: 'http://redirect.sonarsource.com/doc/install-configure-scanner.html'
  }, {
    key: 'msbuild',
    link: 'http://redirect.sonarsource.com/doc/install-configure-scanner-msbuild.html'
  }, {
    key: 'maven',
    link: 'http://redirect.sonarsource.com/doc/install-configure-scanner-maven.html'
  }, {
    key: 'gradle',
    link: 'http://redirect.sonarsource.com/doc/gradle.html'
  }, {
    key: 'jenkins',
    link: 'http://redirect.sonarsource.com/plugins/jenkins.html'
  }, {
    key: 'ant',
    link: 'http://redirect.sonarsource.com/doc/install-configure-scanner-ant.html'
  }
];

export default class AboutScanners extends React.Component {
  render () {
    return (
        <div className="boxed-group">
          <h2>Start analyzing your projects with a SonarQube Scanner</h2>
          <div className="boxed-group-inner">
            <div className="about-page-analyzers">
              {scanners.map(scanner => (
                  <div key={scanner.key} className="about-page-analyzer-box">
                    <div className="big-spacer-bottom">
                      <img src={`${window.baseUrl}/images/scanner-logos/${scanner.key}.svg`} height={80}
                           alt={translate('about_page.scanners', scanner.key)}/>
                    </div>
                    <p className="about-page-text">{translate('about_page.scanners.sonarqube.text')}</p>
                    <ReadMore link={scanner.link}/>
                  </div>
              ))}
            </div>
          </div>
        </div>
    );
  }
}
