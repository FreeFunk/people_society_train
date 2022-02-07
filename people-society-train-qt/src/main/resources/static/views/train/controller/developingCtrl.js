/**
 *  首页
 */
define(['app','commonService'], function(app){
	return app.controller('developingCtrl',
        ['$scope','$rootScope','$http','$timeout','appConfig','commonService','$routeParams',
	function ($scope,$rootScope,$http,$timeout,appConfig,commonService,$routeParams) {
        $scope.currentNav = "faxian";



	}]);
});