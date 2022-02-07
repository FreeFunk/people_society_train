/**
 *  首页
 */
define(['app','commonService','ngInfiniteScroll'], function(app){
	return app.controller('certRecListCtrl',
        ['$scope','$rootScope','$http','$timeout','appConfig','commonService','$routeParams',
	function ($scope,$rootScope,$http,$timeout,appConfig,commonService,$routeParams) {
        $scope.pageInfoObj = {};

        $scope.currentNav = "";

        //查询证书列表
        $scope.searchStuCertListPage = function(){
            var url = "/stu/selectStuCertListPage.jsn";
            commonService.generatePageObj($scope.pageInfoObj,url,5);
            $scope.nextCertPage();
        }

        //下一页
        $scope.nextCertPage = function(){
            var promis = commonService.getNextPageObjectByCondition($scope.pageInfoObj);
            if(promis){
                promis.then(function(list){
                    //设置
                    for(i=0;i<list.length;i++){
                        var cert = list[i];
                        var certImage = cert.certificateImageUrl;
                        if(!certImage || certImage==''){
                            cert.certificateImageUrl = "images/news.jpg";
                        }else{
                            if(cert.certificateImageUrl.indexOf("http")!=0){
                                cert.certificateImageUrl = appConfig.baseImgUrl + cert.certificateImageUrl ;
                            }
                        }
                    }
                });
            }
        }

        //去到证书信息页面
        $scope.gotoCertInfoPage = function(id){
            $rootScope.changePage("/certInfo/"+id);
        }

        //查询证书列表
        $scope.searchStuCertListPage();

	}]);
});