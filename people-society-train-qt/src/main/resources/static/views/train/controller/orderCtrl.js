/**
 *  首页
 */
define(['app','commonService'], function(app){
	return app.controller('orderCtrl',
        ['$scope','$rootScope','$http','$timeout','appConfig','commonService','$routeParams',
	function ($scope,$rootScope,$http,$timeout,appConfig,commonService,$routeParams) {
        $scope.payState = "unPaid";

        //支付tab切换
        $scope.payStateTab = function(payState){
            $scope.payState = payState;
        }

	}]);
});