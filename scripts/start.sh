#!/usr/bin/env bash

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh

BUILD_JAR=$(ls /home/ec2-user/app/step3/build/libs/*.jar)
JAR_NAME=$(basename $BUILD_JAR)

echo "> build 파일명: $JAR_NAME"

echo "> build 파일 복사"

DEPLOY_PATH=/home/ec2-user/app/step3/

cp $BUILD_JAR $DEPLOY_PATH

echo "> DEPLOY_JAR 배포"

JAR_NAME_PATH=$(ls -tr $DEPLOY_PATH/*.jar | tail -n 1)

echo "> JAR Name: $JAR_NAME_PATH"

echo "> $JAR_NAME 에 실행권한 추가"

chmod +x $JAR_NAME_PATH

echo "> $JAR_NAME 실행"

IDLE_PROFILE=$(find_idle_profile)

echo "> $JAR_NAME 를 profile=$IDLE_PROFILE 로 실행합니다."

nohup java -jar \
        -Dspring.config.location=classpath:/application.properties,classpath:/application-$IDLE_PROFILE.properties,/home/ec2-user/app/application-oauth.properties,/home/ec2-user/app/application-real-db.properties \
        -Dspring.profiles.active=$IDLE_PROFILE \
        $JAR_NAME_PATH > $DEPLOY_PATH/nohup.out 2>&1 &