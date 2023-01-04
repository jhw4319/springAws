#!/bin/bash

BUILD_JAR=$(ls /home/ec2-user/app/step2/build/libs/*.jar)
JAR_NAME=$(basename $BUILD_JAR)

echo "> build 파일명: $JAR_NAME"

echo "> build 파일 복사"

DEPLOY_PATH=/home/ec2-user/app/step2/

cp $BUILD_JAR $DEPLOY_PATH

echo "> 현재 실행중인 애플리케이션 pid 확인"

CURRENT_PID=$(pgrep -f $JAR_NAME)

echo "현재 구동 중인 애플리케이션 pid: $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
        echo ">현재 구동 중인 애플리케이션이 없으므로 종료하지 않습니다."
else
        echo"> kill -15 $CURRENT_PID"
        kill -15 $CURRENT_PID
        sleep 5
fi

echo "> DEPLOY_JAR 배포"

JAR_NAME_PATH=$(ls -tr $DEPLOY_PATH/*.jar | tail -n 1)

echo "> JAR Name: $JAR_NAME_PATH"

echo "> $JAR_NAME_PATH 에 실행권한 추가"

chmod +x $JAR_NAME_PATH

echo "> $JAR_NAME_PATH 실행"

nohup java -jar \
        -Dspring.config.location=classpath:/application.properties,classpath:/application-real.properties,/home/ec2-user/app/application-oauth.properties,/home/ec2-user/app/application-real-db.properties \
        -Dspring.profiles.active=real \
        $JAR_NAME_PATH > $DEPLOY_PATH/nohup.out 2>&1 &