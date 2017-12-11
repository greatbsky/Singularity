import { DeviceEventEmitter } from 'react-native';

import Notify from './native/modules/Notify';
import RNBridge from './native/modules/RNBridge';
import ImagePicker from './native/modules/ImagePicker';

export default class {

    //处理需要全局初始化的逻辑
    static init(target) {
        RNBridge.init(); //初始化java端的RNBridge
    }

    //开启通知
    static startNotify(target) {
        DeviceEventEmitter.addListener('Notify', (params) => {
            console.log(params);
            target.setState({
                    newMsg: {
                        title: params.title,
                        content: params.content,
                        createTime: params.createTime
                    }
                });
        });
        Notify.start();
        // let callbk = (title, content, createTime) => {
        //     target.setState({
        //         newMsg: {
        //             title,
        //             content,
        //             createTime
        //         }
        //     });
        //     Notify.recall(callbk);
        //     console.log('target.state.newMsg:');
        //     console.log(target.state.newMsg);
        // };
        // Notify.start(callbk);

        // console.log(1111111);
        // var f = async function() {
        //     console.log(await Notify.startSync());
        //         console.log(3333333);
        // };
        // f();
        // console.log(22222222);
    }

    static imagePicker() {
        ImagePicker.pickImage().then((p) => {
            console.log(p);
        });
    }
}

/**
 * 删除字符串两边的空白
 */
String.prototype.trim = function() {
	return this.replace(/^\s*|\s*$/g, "");
};
/**
 * 字符串格式化
 * var template1="我是{0}，今年{1}了";
 * var template2="我是{name}，今年{age}了";
 * var result1=template1.format("loogn",22);
 * var result2=template2.format({name:"loogn",age:22});
 * 两个结果都是"我是loogn，今年22了"
 *
 */
String.prototype.format = function(args) {
    var result = this;
    if (arguments.length > 0) {
        if (arguments.length == 1 && typeof (args) == "object") {
            for (var key in args) {
                if(args[key]!=undefined){
                    var reg = new RegExp("({" + key + "})", "g");
                    result = result.replace(reg, args[key]);
                }
            }
        }
        else {
            for (var i = 0; i < arguments.length; i++) {
                if (arguments[i] != undefined) {
                    var reg = new RegExp("({[" + i + "]})", "g");
                    result = result.replace(reg, arguments[i]);
                }
            }
        }
    }
    return result;
}

/**
 * 判断字符串是否以指定字符开头
 * return true or false
 */
String.prototype.startWith = function(str) {
	var reg = new RegExp("^" + str);
	return reg.test(this);
}

/**
 * 判断字符串是否以指定字符结尾
 * return true or false
 */
String.prototype.endWith = function(str) {
	var reg = new RegExp(str + "$");
	return reg.test(this);
}

//对Date的扩展，将 Date 转化为指定格式的String
//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符
//年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
//(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
//(new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
Date.prototype.format = function(fmt) {
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()	// 毫秒
	};
	if (/(y+)/.test(fmt)) {
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	}
	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(fmt)) {
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
		}
	}
	return fmt;
}

/**
 * 将数值四舍五入(保留2位小数)后格式化成金额形式
 *
 * @param num 数值(Number或者String)
 * @return 金额格式的字符串,如'1,234,567.45'
 * @type String
 */
function formatCurrency(num) {
    num = num.toString().replace(/\$|\,/g,'');
    if(num == 0){
    	return 0;
    }
    if(isNaN(num)){
		num = "0";
    }
    sign = (num == (num = Math.abs(num)));
    num = Math.floor(num*100+0.50000000001);
    cents = num%100;
    num = Math.floor(num/100).toString();
    if(cents<10){
    	cents = "0" + cents;
    }
    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++){
    	num = num.substring(0,num.length-(4*i+3))+','+num.substring(num.length-(4*i+3));
    }
    return (((sign)?'':'-') + num + '.' + cents);
}
