FROM tomcat:latest

ADD ./var/lib/jenkins/jobs/package/builds/53/archive/webapp/target/*.war /usr/local/tomcat/webapps/

EXPOSE 8080

CMD ["catalina.sh", "run"]