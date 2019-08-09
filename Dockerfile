FROM artifactory.cobalt.com/java8

# AppDynamics Application Configuration
ENV APPDYNAMICS_CONTROLER "cdk-test"
ENV APPDYNAMICS_AGENT_APPLICATION_NAME "pss_esign"
ENV APPDYNAMICS_AGENT_TIER_NAME "dc.cloud.svc.seed"
#AppDynamics Installation start
ENV APPDYNAMICS_ARTIFACTORY "http://artifactory.cdk.com/artifactory/binary-local/AppDynamics/install/docker"
ENV APPDYNAMICS_INSTALL "cdk-appd-docker-appagent-install_v5.sh"
USER root
# the installation shell script will by default check and if prod-* it will automatically set the controller to cdk-prod else will to cdk-test
RUN wget -O ./$APPDYNAMICS_INSTALL $APPDYNAMICS_ARTIFACTORY/$APPDYNAMICS_INSTALL \
    && bash ./$APPDYNAMICS_INSTALL appagent  \
    && rm ./$APPDYNAMICS_INSTALL
USER default
# AppDynamics Installation end
COPY target/*.jar /app.jar
EXPOSE 8080
LABEL vendor="Digital Contracting"
LABEL SERVICE_TAGS=api-gateway-external,v1
CMD java -Xmx2G -Duser.timezone=UTC -Djava.security.egd=file:/dev/./urandom -jar /app.jar
