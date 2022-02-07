/**
 *  首页
 */
define(['app','commonService'], function(app){
	return app.controller('faXianDetailCtrl',
        ['$scope','$rootScope','$http','$timeout','appConfig','commonService','$routeParams',
	function ($scope,$rootScope,$http,$timeout,appConfig,commonService,$routeParams) {
        $scope.currentNav = "faxian";
        $scope.articleId = $routeParams.id;

        $scope.article = {};

        //查询详情信息
		$scope.selectVoById = function(){
            var url = "/article/selectVoById.jsn";
            var param = "id="+$scope.articleId;
            $rootScope.request(url,param,function(data){
                if(data.success){
                    $scope.article = data.data;
                    if($scope.article != null){
                        if($scope.article.mainPhoto == null){
                            $scope.article.mainPhoto = 'images/user-img.jpg';
                        }else{
                            var mainPhoto = $scope.article.mainPhoto;
                            if(mainPhoto.indexOf('http')!=0){
                                mainPhoto = appConfig.baseImgUrl + mainPhoto;
                                $scope.article.mainPhoto = mainPhoto;
                            }
                        }
                        var clickNum = $scope.article.clickNum;
                        if(clickNum == null || clickNum == ''){
                            clickNum = 1;
                        }else{
                            clickNum = parseInt(clickNum);
                            clickNum++;
                        }
                        $scope.article.clickNum = clickNum;
                        $scope.updateClickNum(data.data.id,clickNum);
                    }
                }else{
                    $rootScope.errHttpAction(data);
                }
            })
        }

        //修改点击量
        $scope.updateClickNum = function(id,clickNum){
		    var url = "/article/editClickNumById.jsn";
		    var param = "id="+id+"&clickNum="+clickNum;
		    $rootScope.request(url,param,function(data){
		        if(!data.success){
                    $rootScope.errHttpAction(data);
                }
            })
        }

        //查询详情信息
        $scope.selectVoById();

	}]);
});