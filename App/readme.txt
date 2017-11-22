npm install -g create-react-native-app
npm install -g exp
sudo npm install -g react-native-git-upgrade
create-react-native-app tmpl
npm start

npm run eject



set http_proxy=http://localhost:56534


npm config list
npm config set proxy http://username:password@server:port
npm config set https-proxy http://username:pawword@server:port
npm config set registry https://registry.npm.taobao.org
$cat /Users/great/.npmrc
registry=http://registry.npm.taobao.org/
npm config delete proxy
npm config delete https-proxy
vi /etc/profile
	export ANDROID_HOME=/Users/great/Library/Android/sdk



sudo chmod 777 /usr/local/lib/node_modules
npm i -g react-native-cli
react-native init tmpl
react-native run-ios
react-native run-android
react-native start

调试：
npm install -g react-devtools
react-devtools

更新：
react-native-git-upgrade
react-native upgrade
