FROM jenkins/jenkins:lts-jdk11

USER root

COPY jenkins/plugins.txt /usr/share/jenkins/plugins.txt
COPY jenkins/jenkins-casc.yaml /usr/local/jenkins-casc.yaml
COPY jenkins/Hackathon.groovy /usr/local/Hackathon.groovy
COPY jenkins/airbnb.apk /opt/airbnb.apk

RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/plugins.txt

RUN apt-get update && apt-get install -y ca-certificates curl
RUN apt-get update && apt-get install -y maven
RUN apt-get update && apt-get install -y android-sdk

ARG NODE_VERSION=14.18.1
ARG NODE_PACKAGE=node-v$NODE_VERSION-linux-x64
ARG NODE_HOME=/opt/$NODE_PACKAGE
ENV NODE_PATH $NODE_HOME/lib/node_modules
ENV PATH $NODE_HOME/bin:$PATH
ENV ANDROID_HOME=/usr/lib/android-sdk
ENV PATH=$PATH:$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools

RUN curl https://nodejs.org/dist/v$NODE_VERSION/$NODE_PACKAGE.tar.gz | tar -xzC /opt/
RUN npm i android-platform-tools -g && adb devices
RUN npm i -g appium --unsafe-perm=true --allow-root

ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false -Djava.awt.headless=true -Dhudson.model.DirectoryBrowserSupport.CSP=""
ENV CASC_JENKINS_CONFIG /usr/local/jenkins-casc.yaml