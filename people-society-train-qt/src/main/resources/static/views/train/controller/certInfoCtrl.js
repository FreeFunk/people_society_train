/**
 *  首页
 */
define(['app','commonService'], function(app){
	return app.controller('certInfoCtrl',
        ['$scope','$rootScope','$http','$timeout','appConfig','commonService','$routeParams',
	function ($scope,$rootScope,$http,$timeout,appConfig,commonService,$routeParams) {
        $scope.certId = $routeParams.id;
        $scope.certInfo = {};

        $scope.currentNav = "";

        //查询证书信息
        $scope.selectCertInfo = function(){
            var url= "/stu/selectStuCertInfo.jsn";
            var param = "stuCertId="+$scope.certId;
            $rootScope.request(url,param,function(data){
                if(data.success){
                    $scope.certInfo = data.data;
                    var certTime = commonService.dateFormat(data.data.certificateTime,"yyyy年MM月dd日");
                    $scope.certInfo.certificateTime = certTime;
                }else{
                    $rootScope.errHttpAction(data);
                }
            })
        }

        //查询证书信息
        $scope.selectCertInfo();

	}]);
});