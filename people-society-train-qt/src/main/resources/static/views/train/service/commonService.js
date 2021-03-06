//禁止选择文字
document.onselectstart =function(){
    return false;
}

/**
 * 是否特殊机型 需要使用h5的 摄像头人脸识别
 * @returns {boolean}
 */
function isSepcielPhone(phoneType){
    var OsObject=navigator.userAgent.toLocaleLowerCase();
    if(OsObject.indexOf("huawei")>=0 &&(
        OsObject.indexOf('huaweilio-an00')>=0||OsObject.indexOf('huaweirne-al00')>=0
        )||phoneType=='1'
    ){
        return true;
    }
    return false;
}

/**
 * 是否支持h5相机
 **/
function isH5Camera(){
    var OsObject=navigator.userAgent.toLocaleLowerCase();
    var os1 = getOs();
    //pc操作系统
    if(os1=='pc'){
        return true;
    }
    if(os1=='ios' || os1=='android')return false;

    //手机的uc浏览器
    if( OsObject.indexOf("ucbrowser")>=0){
        return true;
    }

    //chrom浏览器
    if( OsObject.indexOf("chrome")>=0){
        return true;
    }
    return false;
}
/**
 * 将图片进行保存
 * @param ele 文件dom
 * @param photoId 图片id
 * @param isOra 是否原样输出
 * @param maxSize 压缩后的图片大小
 * @param isRotat  安卓系统中是否旋转
 */
function uploadImageFile(ele,photoId,isOra,maxSize,isRotat){
    var file = ele.files[0];
    if(!/image\/\w+/.test(file.type)){
        alert("请确保文件为图像类型");
        return false;
    }
    var reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = function(e){

        var loadBase64Result = this.result;
        var lenthKb = loadBase64Result.length/1024;
        var bili = maxSize/lenthKb;
        if(!isOra){
            /*if(isRotat){
                var img = new Image();
                img.src = loadBase64Result;
                img.onload = function(){
                    compress_withrotate(img,file.type,bili,function(imgBase64Str){
                        saveImage(imgBase64Str,photoId);
                    });
                    img = null;
                };

            }else{*/
                app_compress(lenthKb,file,function(imgBase64Str){
                    saveImage(imgBase64Str,photoId);
                },maxSize,isRotat);
            // }

        }else{
            saveImage(loadBase64Result,photoId);
        }
    }
}

/*保存图片*/
function saveImage(imageBaseStr,photoId){
    $("#"+photoId).val(imageBaseStr.substring("data:image/jpeg;base64,".length));
    $("#"+photoId).change();
    $("#"+photoId+"Show").attr('src',imageBaseStr);
    $("#"+photoId+"Tip").html("");
}

/*图片压缩*/
function app_compress(lenthKb,file, callback,maxSize,isRotat){
    var bili = 1;
    var maxImgSize = 800;
    if(maxSize && maxSize>0){
        maxImgSize = maxSize;
    }
    if(lenthKb>maxImgSize){
        bili = maxImgSize/lenthKb;
    }
    var reader = new FileReader();

    reader.onload = function (e) {

        var image = $('<img/>');
        image.on('load', function () {
            var imageWidth = Math.round(bili * this.width );
            var imageHeight = Math.round(bili * this.height );
            var offsetX = 0,offsetY = 0;
            var canvas = document.createElement('canvas');

            canvas.width = imageWidth;
            canvas.height = imageHeight;

            var context = canvas.getContext('2d');
            context.imageSmoothingEnabled =true;
            var os = getOs();
            //如果是安卓进行旋转
            if(os=='android'&& isRotat){
                context.translate(imageWidth/2, imageHeight/2);
                context.rotate(-90 * Math.PI / 180);//旋转47度
                context.translate(-imageWidth/2, -imageHeight/2);
                context.clearRect(0, 0, imageHeight, imageWidth);
                context.drawImage(this, (imageWidth-imageHeight)/2, (imageHeight-imageWidth)/2, imageHeight,imageWidth );

			}else if(os=='ios') {
                context.clearRect(0, 0, imageWidth, imageHeight);
                // context.imageSmoothingEnabled =true;
                context.drawImage(this, offsetX, offsetY, imageWidth, imageHeight);
			}else{
                context.clearRect(0, 0, imageWidth, imageHeight);
                // context.imageSmoothingEnabled =true;
                context.drawImage(this, offsetX, offsetY, imageWidth, imageHeight);
			}

            var data = canvas.toDataURL('image/jpeg');
            callback(data);
        });

        image.attr('src', e.target.result);
    };

    reader.readAsDataURL(file);
}

function compress_withrotate(img, fileType,bili,callBack) {
    var canvas = document.createElement("canvas");
    var rotateshow;
    EXIF.getData(img, function(){
        EXIF.getAllTags(img);
        Orientation = EXIF.getTag(img,'Orientation');
        switch (Orientation){
            case 6:
                rotateImg(img,'left',canvas,fileType);
                break;
            case 8:
                rotateImg(img,'right',canvas,fileType);
                break;
            case 3:
                rotateImg(img,'right',canvas,fileType);
                rotateImg(img,'right',canvas,fileType);
                break;
            default:
                break;
        }
        rotateshow = canvas.toDataURL(fileType,bili);
        callBack(rotateshow);
    });
    function rotateImg(img, direction,canvas,fileType) {
        var min_step = 0;
        var max_step = 3;
        //var img = document.getElementById(pid);

        if (img == null)return;
        //img的高度和宽度不能在img元素隐藏后获取，否则会出错
        var height = img.height;
        var width = img.width;

        //var step = img.getAttribute('step');
        var step = 2;
        if (step == null) {
            step = min_step;
        }
        if (direction == 'right') {
            step++;
            //旋转到原位置，即超过最大值
            step > max_step && (step = min_step);
        } else {
            step--;
            step < min_step && (step = max_step);
        }
        var degree = step * 90 * Math.PI / 180;
        var ctx = canvas.getContext('2d');
        switch (step) {
            case 0:
                canvas.width = width;
                canvas.height = height;
                ctx.drawImage(img, 0, 0);
                break;
            case 1:
                canvas.width = height;
                canvas.height = width;
                ctx.rotate(degree);
                ctx.drawImage(img, 0, -height);
                break;
            case 2:
                canvas.width = width;
                canvas.height = height;
                ctx.rotate(degree);
                ctx.drawImage(img, -width, -height);
                break;
            case 3:
                canvas.width = height;
                canvas.height = width;
                ctx.rotate(degree);
                ctx.drawImage(img, -width, 0);
                break;
        }


    }
}



define(['app','obj2json'],function(app){
	app.factory('commonService',['$q','$location','$http','$rootScope','appConfig',function($q , $location , $http , $rootScope,appConfig){
		var service = {};

        service.generatePageObj = function(obj,url,pageCount){
            obj.url = url;
            obj.param = '';
            obj.currentPage = 0;
            obj.totalPage = -1;
            obj.total = 0;
            obj.pageCount = pageCount;
            obj.list = [];
            obj.isWorking = false;
        }

        service.getPageObjectByCondition = function(pageObj){
            if((pageObj.currentPage>pageObj.totalPage&&pageObj.totalPage>=0) || pageObj.isWorking){
                return;
            }
            pageObj.isWorking = true;
            var def = $q.defer();

            var start = (pageObj.currentPage-1)*pageObj.pageCount;
            var limit = pageObj.pageCount;
            var param = '';
            if(pageObj.param==''){
                param = 'start='+start+'&limit='+limit;
            }else{
                param = pageObj.param + '&start='+start+'&limit='+limit;
            }
            $rootScope.request(pageObj.url,param,function(data){
                pageObj.total = data.totalCount;
                pageObj.totalPage = data.totalPage;
                if(pageObj.currentPage==1){
                    pageObj.list = data.list;
                }else{
                    pageObj.list = pageObj.list.concat(data.list);
                }
                def.resolve(data.list);
                pageObj.isWorking=false;
            });
            return def.promise;
        }

        /**
         * 下一页
         * @param pageObj
         */
        service.getNextPageObjectByCondition = function(pageObj){
            if(isNaN(pageObj.currentPage)){return;}
            if((pageObj.currentPage>=pageObj.totalPage&&pageObj.totalPage>=0) || pageObj.isWorking){
                return;
            }else{
                pageObj.currentPage++;
                return service.getPageObjectByCondition(pageObj);
            }

        }

		/********************
		 * 向localStorage中设置值
		 * @param key 键值
		 * @param value 值，任何可转化成json的
		 */
		service.setValueToLocalStorage = function (key, value){
			var valueStr = $.toJSON({'value':value});
			window.localStorage.removeItem(key);
			window.localStorage.setItem(key, valueStr);
		}


		/*************
		 * 从localStorage读取值
		 * @param key
		 */
		service.getValueFromLocalStorage = function(key) {
			var valueStr = window.localStorage.getItem(key);
			if (valueStr) {
				return $.parseJSON(valueStr).value;
			}
			return valueStr;
		}


		service.toFix2 = function(num){
			var temp = parseFloat(parseInt(num*100)/100.0);
			return temp;
		}

		service.trim = function(str){
			if(!str)return '';
			return str.replace(/(^\s+)|(\s+$)/g,"");
		}


		service.scrollTop = function(){
			var targetOffset = $("body").height();
			$('html,body').animate({
				scrollTop: 0
			},700);
		}

		service.scrollBottom = function(){

			var targetOffset = $("body").height();
			$('html,body').animate({
				scrollTop: targetOffset-50
			},700);
		}

		service.getBillStateStr = function(bill){
			var billState = bill.billState;
			var sendState = bill.sendState;
			if(billState=='shopcar')return '购物车未下单';
			if(billState=='created')return '待付款';
			if(billState=='finished')return '完结';
			if(billState=='payed'){
				if(sendState=='waitsend')return '待发货';
				if(sendState=='sended')return '待收货';
				if(sendState=='geted')return '已签收';
			}
			return '订单状态';
		}

        //获得url参数中的学校id 设置成全局变量
        service.getUrlParam = function(paramKey){
            var reg = new RegExp("(^|&)"+ paramKey +"=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r!=null)
                return decodeURI(r[2]);
            return null;
        }

		/**
		 * 调用支付接口
		 * @param prepayId
         */
		service.payByWxWeb = function(prePayId){
			$("#wxPayBillForm").find("input[name='prePayId']").val(prePayId);
			$("#wxPayBillForm").submit();
		}

        /**
         * 校验手机号
         * @param phoneNum
         */
        service.checkPhoneNum = function(phoneNum){
            var length = phoneNum.length;
            var mobile = /^[1][0-9]{10}$/;
            return (length == 11 && mobile.test(phoneNum));
        }

        /**
         * 身份证城市代码列表
         */
        service.cityCode = { // 城市代码列表
            11: "北京", 12: "天津", 13: "河北", 14: "山西", 15: "内蒙古", 21: "辽宁", 22: "吉林",
            23: "黑龙江 ", 31: "上海", 32: "江苏", 33: "浙江", 34: "安徽", 35: "福建", 36: "江西",
            37: "山东", 41: "河南", 42: "湖北 ", 43: "湖南", 44: "广东", 45: "广西", 46: "海南",
            50: "重庆", 51: "四川", 52: "贵州", 53: "云南", 54: "西藏 ", 61: "陕西", 62: "甘肃",
            63: "青海", 64: "宁夏", 65: "新疆", 71: "台湾", 81: "香港", 82: "澳门", 91: "国外 "
        };

        /**
         *  校验身份证号
         * @param idCardNum
         */
        service.checkIdCardNum = function(idCardNum){
            var reg = /(^\d{15}$)|(^\d{17}(\d|X)$)/;
            if(!reg.test(idCardNum)){
                return false;
            }
            //校验身份证号长度
            var len = idCardNum.length;
            if(len != 15 && len != 18){
                return false;
            }
            if(len == 15){
                idCardNum = service.idCardFifteenToEighteen(idCardNum);
            }
            //校验省份
            var province = idCardNum.substring(0,2);
            if(service.cityCode[province] == undefined){
                return false;
            }
            //校验生日
            var re_eighteen = /^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/;
            var arr_data = idCardNum.match(re_eighteen); // 正则取号码内所含出年月日数据
            var year = arr_data[2];
            var month = arr_data[3];
            var day = arr_data[4];
            var birthday = new Date(year + '/' + month + '/' + day);
            var flag = service.checkBirthday(year, month, day, birthday);
            if(!flag){
                return false;
            }
            //校验位的检测
            var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
            var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
            var cardTemp = 0;
            for (var i = 0; i < 17; i++) {
                cardTemp += idCardNum.substr(i, 1) * arrInt[i];
            }
            var valNum = arrCh[cardTemp % 11];
            if (valNum != idCardNum.substr(17, 1)) {
                return false;
            }
            return true;
        }

        /**
         * 校验生日
         * @param birthday
         */
        service.checkBirthday = function(year,month,day,birthday){
            var now = new Date();
            var now_year = now.getFullYear();
            if(birthday.getFullYear() == year && (birthday.getMonth()+1) == month && birthday.getDate() == day){
                var old = now_year - year;
                if(old > 3 && old < 150){
                    return true;
                }
                return false;
            }
            return false;
        }


        /**
         *  15位身份证号转18位身份证号
         * @param idCardNum
         */
        service.idCardFifteenToEighteen = function(idCardNum){
            if (idCardNum.length == '15') {
                var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
                var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
                var cardTemp = 0, i;
                idCardNum = idCardNum.substr(0, 6) + '19' + idCardNum.substr(6, idCardNum.length - 6);
                for (i = 0; i < 17; i++) {
                    cardTemp += idCardNum.substr(i, 1) * arrInt[i];
                }
                idCardNum += arrCh[cardTemp % 11];
                return idCardNum;
            }
            return idCardNum;
        }

        /**
         * 日期格式化
         * @param dateString
         */
        service.convertDateFromString = function(dateString) {
            if (dateString) {
                var arr1 = dateString.split(" ");
                var sdate = arr1[0].split('-');
                var date = new Date(sdate[0], sdate[1]-1, sdate[2]);
                return date;
            }
        }

        /**
         * 日期格式化
         */
        service.dateFormat = function(dateStr,fmt) { //author: meizz
            var date = service.convertDateFromString(dateStr);
            if (date!=null && date!=''&& typeof(date) != undefined){
                var o = {
                    "M+": date.getMonth() + 1,                 //月份
                    "d+": date.getDate(),                    //日
                    "h+": date.getHours(),                   //小时
                    "m+": date.getMinutes(),                 //分
                    "s+": date.getSeconds(),                 //秒
                    "q+": Math.floor((date.getMonth() + 3) / 3), //季度
                    "S": date.getMilliseconds()             //毫秒
                };
                if (/(y+)/.test(fmt))
                    fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
                for (var k in o)
                    if (new RegExp("(" + k + ")").test(fmt))
                        fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
                return fmt;
            }
            return "";
        }

		return service;
	}]);
});