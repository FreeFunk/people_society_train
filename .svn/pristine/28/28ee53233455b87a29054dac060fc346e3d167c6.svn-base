/**
 *  首页
 */
define(['app','commonService','ngInfiniteScroll'], function(app){
	return app.controller('orderPayCtrl',
        ['$scope','$rootScope','$http','$timeout','appConfig','commonService','$routeParams','$routeParams',
	function ($scope,$rootScope,$http,$timeout,appConfig,commonService,$routeParams) {
	    $scope.currentNav = "orderPay";
        $scope.pageInfoObj = {};

        var stuCourseId = $routeParams.stuCourseId;
        //初始化数据方法
        $scope.loadCourse = function(){
            var url = "/stuCourse/loadCourse.jsn";
            var param ='stuCourseId=' + stuCourseId;
            $rootScope.request(url,param,function(data){
                if(data.success){
                    var courseImage = appConfig.baseImgUrl+data.data.courseImage;
                    $scope.stuCourse = data.data;
                    $scope.stuCourse.courseImage = courseImage;
                }else{
                    $rootScope.errHttpAction(data);
                }
            })
        }
        $scope.loadCourse();

        //提交订单
        $scope.submitOrder = function(){
            var url = "/stuCourse/submitBillForWxPay.jsn";
            var param ='stuCourseId=' + stuCourseId;
            $rootScope.request(url,param,function(data){
                if(data.success){
                    $scope.prepayId = data.data;
                    $("#prepayIdHidden").val($scope.prepayId);
                    //提交到支付中心
                    $('#prepay_submit_form').submit();
                }else{
                    $rootScope.errHttpAction(data);
                }
            })
        }
        //返回上一页
        $scope.backPage = function(){
            $rootScope.backPage();
        }

	}]);
});