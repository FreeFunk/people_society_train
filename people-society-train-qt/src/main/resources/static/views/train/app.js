define(['angular','alertify'],function(angular,alertify){
    var app = angular.module('trainApp', ['ngRoute','infinite-scroll','ngSanitize','ngTouch']);
    app.constant('appConfig',{
        httpHeader:{headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}},
        isApp:false,
        productName:'视频教育',
        baseUrl:'',
        baseImgUrl:'http://studyimg.heb12328.com:60001',
        /*  baseImgUrl:'http://learnimg.qhdrc.com:8787',*/
        wxCommonUrl: 'http://wx.qhd12328.com/wxplant',
        // wxCommonUrl: 'http://localhost:8177',
        //练习题是否显示解析
        showPractiseFenxi:'1'
    });

    /********** 路由  begin *************/
    app.config(['$routeProvider',function($routeProvider){
        $routeProvider
            .when('/sdudy/:stuCourseId',{
                templateUrl:'view/study.html?stmp='+currentTime,
                controller:'studyCtrl',
                resolve: {
                    load: loadDeps([
                        'controller/studyCtrl.js?stmp='+currentTime
                    ])
                }
            })
            .when('/sdudyweb/:stuCourseId',{
                templateUrl:'view/study_web.html?stmp='+currentTime,
                controller:'studyCtrl',
                resolve: {
                    load: loadDeps([
                        'controller/studyCtrl.js?stmp='+currentTime
                    ])
                }
            })
            .when('/login',{
                templateUrl:'view/login.html?stmp='+currentTime,
                controller:'loginCtrl',
                resolve: {
                    load: loadDeps([
                        'controller/loginCtrl.js?stmp='+currentTime
                    ])
                }
            })
            .when('/main',{
                templateUrl:'view/main.html?stmp='+currentTime,
                controller:'mainCtrl',
                resolve: {
                    load: loadDeps([
                        'js/slider.js?stmp='+currentTime,
                        'controller/mainCtrl.js?stmp='+currentTime
                    ])
                }
            })
            .when('/fenlei',{
                templateUrl:'view/fenlei.html?stmp='+currentTime,
                controller:'fenLeiCtrl',
                resolve: {
                    load: loadDeps([
                        'controller/fenLeiCtrl.js?stmp='+currentTime
                    ])
                }
            })
            .when('/search/:courseName?/:majorId?/:courseClsId?',{
                templateUrl:'view/search.html?stmp='+currentTime,
                controller:'searchCtrl',
                resolve: {
                    load: loadDeps([
                        'controller/searchCtrl.js?stmp='+currentTime
                    ])
                }
            })
            .when('/studentPro/:stuAndCourseId',{
                templateUrl:'view/studentPro.html?stmp='+currentTime,
                controller:'studentProCtrl',
                resolve: {
                    load: loadDeps([
                        'controller/studentProCtrl.js?stmp='+currentTime
                    ])
                }
            })
            .when('/courDetail/:courseId',{
                templateUrl:'view/courDetail.html?stmp='+currentTime,
                controller:'courDetailCtrl',
                resolve: {
                    load: loadDeps([
                        'controller/courDetailCtrl.js?stmp='+currentTime
                    ])
                }
            })
            .when('/userInfo',{
                templateUrl:'view/userInfo.html?stmp='+currentTime,
                controller:'userInfoCtrl',
                resolve: {
                    load: loadDeps([
                        'controller/userInfoCtrl.js?stmp='+currentTime
                    ])
                }
            })
            .when('/courseList',{
                templateUrl:'view/courseList.html?stmp='+currentTime,
                controller:'courseListCtrl',
                resolve: {
                    load: loadDeps([
                        'controller/courseListCtrl.js?stmp='+currentTime
                    ])
                }
            })
            .when('/realName',{
                templateUrl:'view/realName.html?stmp='+currentTime,
                controller:'realNameCtrl',
                resolve: {
                    load: loadDeps([
                        'controller/realNameCtrl.js?stmp='+currentTime
                    ])
                }
            })
            .when('/realNameWeb',{
                templateUrl:'view/realName_web.html?stmp='+currentTime,
                controller:'realNameCtrl',
                resolve: {
                    load: loadDeps([
                        'controller/realNameCtrl.js?stmp='+currentTime
                    ])
                }
            })
            .when('/developing',{
                templateUrl:'view/developing.html?stmp='+currentTime,
                controller:'developingCtrl',
                resolve: {
                    load: loadDeps([
                        'controller/developingCtrl.js?stmp='+currentTime
                    ])
                }
            })
            .when('/faxianDetail/:id',{
                templateUrl:'view/faxianDetail.html?stmp='+currentTime,
                controller:'faXianDetailCtrl',
                resolve: {
                    load: loadDeps([
                        'controller/faXianDetailCtrl.js?stmp='+currentTime
                    ])
                }
            })
            .when('/faxian',{
                templateUrl:'view/faxian.html?stmp='+currentTime,
                controller:'faXianCtrl',
                resolve: {
                    load: loadDeps([
                        'controller/faXianCtrl.js?stmp='+currentTime
                    ])
                }
            })
            .when('/order',{
                templateUrl:'view/order.html?stmp='+currentTime,
                controller:'orderCtrl',
                resolve: {
                    load: loadDeps([
                        'controller/orderCtrl.js?stmp='+currentTime
                    ])
                }
            })
            .when('/editUser',{
                templateUrl:'view/editUser.html?stmp='+currentTime,
                controller:'editUserCtrl',
                resolve: {
                    load: loadDeps([
                        'controller/editUserCtrl.js?stmp='+currentTime
                    ])
                }
            })
            .when('/editPwd',{
                templateUrl:'view/editPwd.html?stmp='+currentTime,
                controller:'editPwdCtrl',
                resolve: {
                    load: loadDeps([
                        'controller/editPwdCtrl.js?stmp='+currentTime
                    ])
                }
            })
            .when('/examList',{
                templateUrl:'view/examList.html?stmp='+currentTime,
                controller:'examListCtrl',
                resolve: {
                    load: loadDeps([
                        'controller/examListCtrl.js?stmp='+currentTime
                    ])
                }
            })
            .when('/examDesc/:courseId',{
                templateUrl:'view/examDesc.html?stmp='+currentTime,
                controller:'examDescCtrl',
                resolve: {
                    load: loadDeps([
                        'controller/examDescCtrl.js?stmp='+currentTime
                    ])
                }
            })
            .when('/exam/:paperId',{
                templateUrl:'view/exam.html?stmp='+currentTime,
                controller:'examCtrl',
                resolve: {
                    load: loadDeps([
                        'controller/examCtrl.js?stmp='+currentTime
                    ])
                }
            })
            .when('/examResult/:paperId',{
                templateUrl:'view/examResult.html?stmp='+currentTime,
                controller:'examResultCtrl',
                resolve: {
                    load: loadDeps([
                        'controller/examResultCtrl.js?stmp='+currentTime
                    ])
                }
            })
            .when('/examDetail/:paperId/:getScore/:rightQuesCount',{
                templateUrl:'view/examDetail.html?stmp='+currentTime,
                controller:'examDetailCtrl',
                resolve: {
                    load: loadDeps([
                        'controller/examDetailCtrl.js?stmp='+currentTime
                    ])
                }
            })
            .when('/examRecList/:courseId',{
                templateUrl:'view/examRecList.html?stmp='+currentTime,
                controller:'examRecListCtrl',
                resolve: {
                    load: loadDeps([
                        'controller/examRecListCtrl.js?stmp='+currentTime
                    ])
                }
            })
            .when('/certRecList',{
                templateUrl:'view/certRecList.html?stmp='+currentTime,
                controller:'certRecListCtrl',
                resolve: {
                    load: loadDeps([
                        'controller/certRecListCtrl.js?stmp='+currentTime
                    ])
                }
            })
            .when('/certInfo/:id',{
                templateUrl:'view/certInfo.html?stmp='+currentTime,
                controller:'certInfoCtrl',
                resolve: {
                    load: loadDeps([
                        'controller/certInfoCtrl.js?stmp='+currentTime
                    ])
                }
            })
            /*确认购买页面*/
            .when('/orderPay/:stuCourseId',{
                templateUrl:'view/orderPay.html?stmp='+currentTime,
                controller:'orderPayCtrl',
                resolve: {
                    load: loadDeps([
                        'controller/orderPayCtrl.js?stmp='+currentTime
                    ])
                }
            })
             .when('/register',{
                 templateUrl:'view/register.html?stmp='+currentTime,
                 controller:'registerCtrl',
                 resolve: {
                     load: loadDeps([
                         'controller/registerCtrl.js?stmp='+currentTime
                     ])
                 }
             })
             .when('/registerWeb',{
                 templateUrl:'view/register_web.html?stmp='+currentTime,
                 controller:'registerCtrl',
                 resolve: {
                     load: loadDeps([
                         'controller/registerCtrl.js?stmp='+currentTime
                     ])
                 }
             })
            .when('/loginWeb',{
                templateUrl:'view/login_web.html?stmp='+currentTime,
                controller:'loginCtrl',
                resolve: {
                    load: loadDeps([
                        'controller/loginCtrl.js?stmp='+currentTime
                    ])
                }
            })
            /*.when('/shopfenlei/:compId',{
                templateUrl:'view/shopfenlei.html',
                controller:'shopfenleiCtrl',
                resolve: {
                    load: loadDeps([
                        'controller/shopfenleiCtrl',
                        'css!../m/assets/css/child.css',
                        'css!../m/assets/css/m_category.css'
                    ])
                }
            })*/
            .otherwise({redirectTo:'/courseList'});

        /**
         * 加载依赖的辅助函数
         * @param deps
         * @returns {*[]}
         */
        function loadDeps(deps) {
            return [
                '$q', function ($q) {
                    var def = $q.defer();
                    require(deps, function () {
                        def.resolve();
                    });
                    return def.promise;
                }
            ];
        }
    }]).run(['$rootScope',function ($rootScope) {

    }]);
    /********** 路由  end *************/

    /********** bodyController  start *************/
    app.controller('bodyCtrl', ['$scope','$rootScope','$http','$timeout','$location','$route','$routeParams','appConfig','commonService','$interval','$timeout',
        function ($scope,$rootScope,$http,$timeout,$location,$route,$routeParams,appConfig,commonService,$interval,$timeout) {
            $rootScope.config = appConfig
            //全局location
            $rootScope.location = $location;
            $rootScope.route = $route;
            $rootScope.commonService = commonService;

            /**
             * 将秒数转换成 分:秒这样的格式
             */
            $rootScope.changeSecondToStr = function(sec){
                if(sec!=null){
                    var minuteNum =0;
                    var secondNum = 0;
                    minuteNum = parseInt(sec/60);
                    secondNum = parseInt(sec -  minuteNum*60);
                    var timeStr = "";
                    timeStr += ( minuteNum+":");
                    if(secondNum>=10){
                        timeStr += secondNum;
                    }else{
                        timeStr += '0'+secondNum;
                    }

                    return timeStr;
                }
            }

            $rootScope.changePage = function (path) {
                $rootScope.location.path(path);
            }
            //底部菜单切换
            $rootScope.changeMenuBar = function(pagePath){
                $rootScope.changePage("/"+pagePath);
            }
            //上一页
            $rootScope.backPage = function(){
                window.history.go(-1);
            }

            alertify.set({
                labels : {
                    ok     : "确定",
                    cancel : "取消"
                },
                delay : 1500,
                buttonReverse : false,
                buttonFocus   : "ok"
            });


            /**
             * 公共请求中加了学校id
             * @param url
             * @param param
             * @param callBack
             * @param errCallBack
             */
            $rootScope.request = function(url,param,callBack,errCallBack){
                var newUrl = appConfig.baseUrl + url;
                //TODO: 本项目中独有的学校id
                /*if(newUrl.indexOf("?")>=0){
                    newUrl = newUrl + "&sch=" + window.globalSchId
                }else{
                    newUrl = newUrl + "?sch=" + window.globalSchId
                }*/
                appConfig.httpHeader.headers['sys-sch']=window.globalSchId;
                if(appConfig.isApp){
                    $rootScope.requestJsonp(newUrl,callBack,param);
                }else{
                    $http.post(newUrl,param,appConfig.httpHeader).success(function(data){
                        if(data.success){
                            if(callBack){
                                callBack(data);
                            }
                        }else{
                            $rootScope.errHttpAction(data,errCallBack);
                        }
                    }).error(function(data, status, headers, config) {
                        if(errCallBack){
                            errCallBack(data);
                        }
                    });
                }
            }

            //TODO:本系统中特有代码加载全局的学校信息
            $rootScope.globalSchObj = {};
            $scope.initSchool = function(){
                var url = "/school/getSchInfo.jsn";
                $rootScope.request(url,'',function(data){
                    var temSchool = data.data;
                    if(temSchool.schoolAppLoginImg==null || temSchool.schoolAppLoginImg==''){
                        temSchool.schoolAppLoginImg = 'images/logon.png';
                    }else{
                        temSchool.schoolAppLoginImg = appConfig.baseImgUrl+temSchool.schoolAppLoginImg
                    }
                    $rootScope.globalSchObj = temSchool;
                    window.globalSchId = temSchool.id;
                    $('title').html($rootScope.globalSchObj.schoolName);
                });
            }
            $scope.initSchool();

            /**
             * 请求jsonP
             * @param url
             * @param callBack
             * @param param
             */
            $rootScope.calbackParam = "?callback=";
            $rootScope.jsonCount = 0;
            $rootScope.requestJsonp = function(url,callBack,param){
                var callBackStr = 'angular_callbacks_mi' +  $rootScope.jsonCount;
                var urlNew = appConfig.baseUrl + url + $rootScope.calbackParam + callBackStr;
                $rootScope.jsonCount++;
                window[callBackStr] = function(data){
                    callBack(data);
                    delete window[callBackStr];
                }
                if(param && param!=''){
                    urlNew += '&'+ param;
                }
                try{

                    $http.jsonp(urlNew).success(callBack);
                }catch(e){
                    $rootScope.alertError('网络错误!');
                }
            }
            /* $rootScope.requestWithoutJsonp = function(url,param,callBack){
             var str = "http://192.168.1.102:8080"
             $http.post(str+url,param,appConfig.httpHeader).success(callBack);
             }*/


            /**
             * 错误处理
             */
            $rootScope.errHttpAction = function(responseJson,callBack){
                if(responseJson.errType=="not_login"){
                    if(isWx()){
                        //微信自动登录
                        var contextPath = getContextRealPath();
                        var loginUrl =  appConfig.wxCommonUrl + '/wx/login.jsn?d=' + contextPath + '/wxLogin.jsn';
                        var url = window.location.href;
                        //将url中的参数转译下
                        url = url.replace('#','**_**');
                        loginUrl = loginUrl + '?dwr=' + url;
                        window.location.href=loginUrl;
                    }else{
                        //跳转到登录页面
                        if(getOs()=='pc') {
                            $rootScope.changePage("/loginWeb");
                        }else {
                            $rootScope.changePage("/login");
                        }
                    }

                }else if(responseJson.errType=="notBind"){
                    //跳转到登录页面
                    if(getOs()=='pc') {
                        $rootScope.changePage("/loginWeb");
                    }else {
                        $rootScope.changePage("/login");
                    }
                }else if(responseJson.errType=="notRealName"){
                    if(getOs()=='pc'){
                        $rootScope.changePage("/realNameWeb");
                    }else{
                        $rootScope.changePage("/realName");
                    }
                }else{
                    if(callBack){callBack(responseJson);return}

                    if(responseJson.errMsg){
                        $rootScope.alertError(responseJson.errMsg);
                    }else if(responseJson.fieldError){
                        for(p in responseJson.fieldError){
                            $rootScope.alertError(responseJson.fieldError[p]);
                        }
                    }else{
                        $rootScope.alertError('请求失败');
                    }
                }
            }

            //成功
            $rootScope.alertSuccess = function(msg){
                $rootScope.closeWait();
                alertify.success(msg);
            }
            //错误
            $rootScope.alertError = function(msg){
                $rootScope.closeWait();
                alertify.error(msg);
            }
            //确定
            $rootScope.alertConfirm = function(msg,callBack){
                $rootScope.closeWait();
                alertify.confirm(msg,function(btn){
                    if(callBack){
                        $scope.$apply(callBack(btn));
                    }
                });
            }
            $rootScope.alert = function(msg,callBack){
                $rootScope.closeWait();
                alertify.alert(msg,function(btn){
                    if(callBack){
                        $scope.$apply(callBack(btn));
                    }
                });
            }

            $rootScope.alertPrompt = function(msg,callBack){
                $rootScope.closeWait();
                alertify.prompt(msg,function(btn,value){
                    if(callBack){
                        $scope.$apply(callBack(btn,value));
                    }
                });
            }



            $rootScope.showWait = function(){
                $("#containerLoading").show();
            }

            $rootScope.closeWait = function(){
                $("#containerLoading").hide();
            }



            $rootScope.schoolId = commonService.getUrlParam('sch');
            //TODO: 刷新学校logo和名字和网页标题
            $rootScope.freshSchool =  function(){
                //刷新school信息放到 root域中
            }

        }]);
    /********** bodyContrl  end *************/

    /********* 页面底部导航切换  begin *********/
    app.directive('bottomNav',[function(){
        var template = "<footer class='aui-footer aui-footer-fixed'>";
        template += "<a href='javascript:void(0);' ng-click='changeMenu({menu:\"main\"})' class='aui-tabBar-item {{currentNav==\"main\"?\"aui-tabBar-item-active\":\"\"}}'>";
        template += "<span class='aui-tabBar-item-icon'>";
        template += "<i class='iconfont icon-shouye {{currentNav==\"main\"?\"font18\":\"\"}}'></i>";
        template += "</span>";
        template += "<span class='aui-tabBar-item-text'>首页</span>";
        template += "</a>";
        template += "<a href='javascript:void(0);' ng-click='changeMenu({menu:\"fenlei\"})' class='aui-tabBar-item {{currentNav==\"fenlei\"?\"aui-tabBar-item-active\":\"\"}}'>";
        template += "<span class='aui-tabBar-item-icon {{currentNav==\"fenlei\"?\"font18\":\"\"}}'>";
        template += "<i class='iconfont icon-2'></i>";
        template += "</span>";
        template += "<span class='aui-tabBar-item-text'>分类</span>";
        template += "</a>";
        template += "<a href='javascript:void(0);' ng-click='changeMenu({menu:\"faxian\"})' class='aui-tabBar-item {{currentNav==\"faxian\"?\"aui-tabBar-item-active\":\"\"}}'>";
        template += "<span class='aui-tabBar-item-icon font18'>";
        template += "<i class='iconfont icon-faxian {{currentNav==\"faxian\"?\"font18\":\"\"}}'></i>";
        template += "</span>";
        template += "<span class='aui-tabBar-item-text'>发现</span>";
        template += "</a>";
        template += "<a href='javascript:void(0);' ng-click='changeMenu({menu:\"courseList\"})' class='aui-tabBar-item {{currentNav==\"courseList\"?\"aui-tabBar-item-active\":\"\"}}'>";
        template += "<span class='aui-tabBar-item-icon font18'>";
        template += "<i class='iconfont icon-xuexi {{currentNav==\"courseList\"?\"font18\":\"\"}}'></i>";
        template += "</span>";
        template += "<span class='aui-tabBar-item-text'>学习</span>";
        template += "</a>";
        template += "<a href='javascript:void(0);' ng-click='changeMenu({menu:\"userInfo\"})' class='aui-tabBar-item {{currentNav==\"userInfo\"?\"aui-tabBar-item-active\":\"\"}}'>";
        template += "<span class='aui-tabBar-item-icon font18'>";
        template += "<i class='iconfont icon-zhanghao00 {{currentNav==\"userInfo\"?\"font18\":\"\"}}'></i>";
        template += "</span>";
        template += "<span class='aui-tabBar-item-text'>账号</span>";
        template += "</a>";
        template += "</footer>";
        return {
            restrict:'E',
            scope:{
                currentNav:'=',
                changeMenu:'&'
            },
            template:template
        };
    }])
    /********* 页面底部导航切换  start *********/

    /********** phonecodebutten  start *************/
    app.directive('phonecodebutten',[function(){
        return {
            restrict:'E',
            template:'<button  ng-click="clickPhoneCode()" ng-bind="phoneCode.text" type="button" class="l-btn-phonecode-btn mt10" style="width:40%;"></button>',
            replace:true,
            scope:{
                phonenum:'=',
                codetype:'='
            },
            controller:['$scope','$rootScope','$timeout',function($scope,$rootScope,$timeout){
                $scope.phoneCode = {
                    text:'获取验证码',
                    count:0
                }
                //点击
                $scope.clickPhoneCode = function(){
                    if(($scope.codetype=='reg'||$scope.codetype=='foget') && ($scope.phonenum==null || $scope.phonenum.length!=11)){
                        $rootScope.alertError('请填写手机号!');
                        return;
                    }
                    if($scope.phoneCode.count != 0){
                        return;
                    }
                    var url = '/yw/phonemsg/sendMsgCode';
                    var param = '';
                    if($scope.codetype=='reg'){
                        url = '/yw/phonemsg/sendRegistCode';
                        param = 'phoneNum=' + $scope.phonenum;
                    }else if($scope.codetype=='foget'){
                        url = '/yw/phonemsg/sendFindPwdCode';
                        param = 'phoneNum=' + $scope.phonenum;
                    }else if($scope.codetype=='updatepwd'){
                        url = '/yw/phonemsg/sendUpdatePwdCode';
                    }else if($scope.codetype=='updatepaypwd'){
                        url = '/yw/phonemsg/sendUpdatePayPwdCode';
                    }else if($scope.codetype=='upmobilorgcode'){
                        url = '/yw/phonemsg/sendBindOraPhoneCode';
                    }else if($scope.codetype=='upmobilnewcode'){
                        url = '/yw/phonemsg/sendBindNewPhoneCode';
                        param = 'phoneNum=' + $scope.phonenum;
                    }else{
                        $rootScope.alertError('非法的手机码类型!');
                        return;
                    }
                    $rootScope.request(url,param,function(data){
                        if(data.success){
                            $scope.phoneCode.count = 180;
                            $scope.countNum();
                        }else{
                            $rootScope.errHttpAction(data);
                            //$rootScope.alertError(data.errMsg);
                        }
                    })
                }

                $scope.countNum = function(){
                    if($scope.phoneCode.count==1){
                        $scope.phoneCode.text = '获取验证码';
                        $scope.phoneCode.count--;
                        return;
                    }else{
                        $timeout($scope.countNum,1000);
                        $scope.phoneCode.text = "(" + $scope.phoneCode.count + "秒)获取";
                    }
                    $scope.phoneCode.count--;
                }

            }]
        };
    }]);
    /********** phonecodebutten  end *************/
    return app;
});