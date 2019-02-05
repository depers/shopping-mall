echo "------------------进入git项目目录--------------------"
cd /usr/local/git-repository/shopping-mall

echo "------------------git切换分支到"$1"------------------"
git checkout $1

echo "------------------git fetch-------------------------"
git fetch

echo "------------------git pull-------------------------"
git pull

echo "------------------编译并跳过单元测试,切换到线上配置-----------------"
mvn clean package -Dmaven.test.skip=true -Pprod

echo "------------------删除旧的ROOT.war------------------"
rm /usr/local/web/$2/webapps/ROOT.war

echo "------------------拷贝编译出来的war包到tomcat下------------------"
cp /usr/local/git-repository/shopping-mall/target/fmall.war /usr/local/web/$2/ROOT.war

echo "------------------删除tomcat下旧的ROOT文件夹------------------"
rm -rf /usr/local/web/$2/ROOT

echo "------------------关闭tomcat------------------"
/usr/local/web/$2/bin/shutdown.sh

echo "------------------睡眠十秒------------------"
for i in {1..10}
do
    echo $i"s"
    sleep 1s
done

echo "------------------启动tomcat------------------"
/usr/local/web/$2/bin/start.sh