var currentTime = new Date().getTime();
/**
 *删除数组指定下标或指定对象
 */
Array.prototype.remove=function(obj){
    for(var i =0;i <this.length;i++){
        var temp = this[i];
        if(temp == obj){
            this.splice(i, 1);
        }
    }
}

/**
 * require 入口
 */
require.config({
	baseUrl:"",
//	urlArgs:"v=1.0",
	paths:{
		"jquery":"js/jquery203",
         "jqueryswipebox":"js/jquery.swipebox.min",
         "move":"js/move",
         "slider":"js/slider",
         "owl":"js/owl.carousel.min",
         "obj2json":"js/jquery.json.min",
         "qrcode":"js/jquery.qrcode.min",
         "alertify":"js/alertify.min",
         "scrollcore":"js/mobiscroll.core-2.5.2",
         "scrolldate":"js/mobiscroll.datetime-2.5.1",
         "angular" : "js/angular.min",//加载angular
         "angular-route" : "js/angular-route.min",//加载angular路由
         "angular-sanitize":"js/angular-sanitize.min",
        "angular-touch":"js/angular-touch.min",
        "commonService":"service/commonService.js?stmp="+currentTime,
        "userAddressService":"service/userAddressService.js?stmp="+currentTime,
        "userService":"service/userService.js?stmp="+currentTime,
        //加载程序
        "app":"app.js?stmp="+currentTime,
        "ngInfiniteScroll":"js/ng-infinite-scroll.min"

	},
	shim:{
		'angular': {
           exports : 'angular' ,
            init : function () {
                var _module = angular.module;
                angular.module = function () {
                    var newModule = _module.apply( angular , arguments );
                    if ( arguments.length >= 2 ) {
                        newModule.config( [
                            '$controllerProvider' ,
                            '$compileProvider' ,
                            '$filterProvider' ,
                            '$provide' ,
                            function ( $controllerProvider , $compileProvider , $filterProvider , $provide ) {
                                newModule.controller = function () {
                                    $controllerProvider.register.apply( this , arguments );
                                    return this;
                                };
                                newModule.directive = function () {
                                    $compileProvider.directive.apply( this , arguments );
                                    return this;
                                };
                                newModule.filter = function () {
                                    $filterProvider.register.apply( this , arguments );
                                    return this;
                                };
                                newModule.factory = function () {
                                    $provide.factory.apply( this , arguments );
                                    return this;
                                };
                                newModule.service = function () {
                                    $provide.service.apply( this , arguments );
                                    return this;
                                };
                                newModule.provider = function () {
                                    $provide.provider.apply( this , arguments );
                                    return this;
                                };
                                newModule.value = function () {
                                    $provide.value.apply( this , arguments );
                                    return this;
                                };
                                newModule.constant = function () {
                                    $provide.constant.apply( this , arguments );
                                    return this;
                                };
                                newModule.decorator = function () {
                                    $provide.decorator.apply( this , arguments );
                                    return this;
                                };
                            }
                        ] );
                    }
                    return newModule;
                };
            }
        } ,
		'angular-route':{
			deps: ["angular"],
			exports : 'angular-route'
		},
		'angular-sanitize':{
			deps: ["angular"],
			exports : 'angular-sanitize'
		},
        'angular-touch':{
            deps: ["angular"],
            exports : 'angular-touch'
        },
        'ngInfiniteScroll':{
            deps: ["jquery","angular"],
            exports : 'ngInfiniteScroll'
        },
        'jqueryswipebox':{
            deps: ["jquery"],
            exports : 'jqueryswipebox'
        },
		'validation':{
          deps: ["jquery"],
          exports: 'validation'
        },
        'obj2json':{
            deps: ["jquery"],
            exports: 'obj2json'
        },
        'scrolldate':{
          deps: ["jquery","scrollcore"],
          exports: 'scrolldate'
        }
	},
    map : {
        '*' : {
            'css' : 'js/css'
        }
    }
});


require(['jquery','angular','angular-route','app',
    'commonService','ngInfiniteScroll',
    'owl','jqueryswipebox','angular-sanitize','angular-touch'],function($,angular){
	$(function(){
        owlInit(jQuery,window,document);
        $("#status").fadeOut();
        $("#preloader").delay(350).fadeOut("slow");
		angular.bootstrap(document,["trainApp"]);

	});
});