FROM java:8
ADD target/notice.jar notice.jar
EXPOSE 9000
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime  && echo 'Asia/Shanghai' >/etc/timezone
ENTRYPOINT ["java","-jar","notice.jar"]