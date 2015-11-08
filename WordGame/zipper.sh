#!/bin/bash

DIR=WordGame

if [ -e $DIR/build/libs ]
then
    rm -rf $DIR/build/libs/
fi
mkdir -p $DIR/build/libs

if [ -e $DIR/conf/ ]
then
    rm -rf $DIR/conf/
fi

mkdir -p $DIR/conf/

if [ -e $DIR/images/ ]
then
    rm -rf $DIR/images/
fi

mkdir -p $DIR/images/

if [ -e $DIR/lib/ ]
then
    rm -rf $DIR/lib/
fi

mkdir -p $DIR/lib/

if [ -e $DIR/javaRun.sh ]
then
    rm -rf $DIR/javaRun.sh
fi


cp javaRun.sh $DIR/javaRun.sh
cp -r build/libs/* $DIR/build/libs/
cp -r images/ $DIR/
cp -r lib/ $DIR/
cp -r conf/ $DIR/

VER=$(cat VERSION)

if [ -e $DIR-$VER.zip ]
then
    rm $DIR-$VER.zip
fi

zip -r $DIR-$VER.zip $DIR/

ssh -i ~/.ssh/deploy_rsa -o StrictHostKeyChecking=no viking@127.0.0.1 "if [ ! -e /var/www/develosapiens.net/downloads/app/$DIR/ ]; then mkdir /var/www/develosapiens.net/downloads/app/$DIR/;fi"
scp -i ~/.ssh/deploy_rsa -o StrictHostKeyChecking=no $DIR-$VER.zip viking@127.0.0.1:/var/www/develosapiens.net/downloads/app/$DIR/
ssh -i ~/.ssh/deploy_rsa -o StrictHostKeyChecking=no viking@127.0.0.1 "sudo chmod g+w /var/www/develosapiens.net/downloads/app/$DIR/$DIR-$VER.zip"
ssh -i ~/.ssh/deploy_rsa -o StrictHostKeyChecking=no viking@127.0.0.1 "sudo chown viking:www-data /var/www/develosapiens.net/downloads/app/$DIR/$DIR-$VER.zip"


ssh -i ~/.ssh/deploy_rsa -o StrictHostKeyChecking=no viking@127.0.0.1 "cat /var/www/develosapiens.net/downloads/app/$DIR/$DIR.html | grep -v \"</html>\|</body>\|$DIR-$VER.zip\" > /var/www/develosapiens.net/downloads/app/$DIR/tmp ;\
echo \"<a href=\\\"$DIR-$VER.zip\\\">$DIR-$VER</a><br />\" >> /var/www/develosapiens.net/downloads/app/$DIR/tmp; echo \"</body>\" >> /var/www/develosapiens.net/downloads/app/$DIR/tmp ;\
echo \"</html>\" >> /var/www/develosapiens.net/downloads/app/$DIR/tmp; mv /var/www/develosapiens.net/downloads/app/$DIR/tmp /var/www/develosapiens.net/downloads/app/$DIR/$DIR.html; sudo chown viking:www-data /var/www/develosapiens.net/downloads/app/$DIR/$DIR.html"

